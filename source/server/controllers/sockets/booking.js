const driverModel = require("../../models/driverModel");
const userModel = require("../../models/userModel");
const TripBookingRecordModel = require("../../models/TripBookingRecordModel");

module.exports = function (io) {
  io.of("/booking").on("connection", (socket) => {

    // tìm tài xế
    socket.on("find-a-driver", async (data) => {
      try {
        const {
          phoneNumber,
          driverSocketIds,
          pickupAddress,
          destinationAddress,
          distanceInKilometers,
          durationInMinutes,
          minutesToDriverArrival,
          kilometersToDriverArrival,
          paymentMethod,
          noteForDriver,
          cost,
        } = data;

        let userInfo = await userModel.findOne({ phoneNumber });

        if (!userInfo) {
          console.log("User not found");
          return;
        }

        let socketIds = driverSocketIds.map(function (element) {
          return element.socketId;
        });

        let tripBookingRecord = new TripBookingRecordModel({
          pickupAddress: JSON.parse(pickupAddress),
          destinationAddress: JSON.parse(destinationAddress),
          distanceInKilometers,
          durationInMinutes,
          minutesToDriverArrival,
          kilometersToDriverArrival,
          paymentMethod,
          noteForDriver,
          socketIdDriversReceived: socketIds,
          cost,
          userId: userInfo._id,
          status: "PENDING",
        });

        await tripBookingRecord.save()

        console.log(
          "Trip booking record created: " + tripBookingRecord
        );

        socketIds.map((e) => {
          io.of("/booking")
            .to(e)
            .emit("send-requesting-a-ride-to-drivers", {
              id: tripBookingRecord._id,
              pickupAddress,
              destinationAddress,
              distanceInKilometers,
              durationInMinutes,
              minutesToDriverArrival,
              kilometersToDriverArrival,
              paymentMethod,
              noteForDriver,
              cost,
              userInfo: {
                userName: userInfo.userName,
                phoneNumber: userInfo.phoneNumber,
              },
            });
        });
      } catch (err) {
        console.error("Error saving socket ID:", err);
      }
    });

    socket.on("driver-accept-request", async (data) => {
      try {
        const { id } = data;

        let driverAccepted = await driverModel.findOne({ socketId: socket.id });

        let tripBookingRecord = await TripBookingRecordModel.findOne({
          _id: id,
        });

        if (!tripBookingRecord || tripBookingRecord.status !== "PENDING") {
          socket.emit("notify-accept-request", {
            id: tripBookingRecord._id,
            success: false,
          });
          console.log("Trip booking record not found: " + id);
          return;
        }

        tripBookingRecord = await TripBookingRecordModel.findOneAndUpdate(
          { _id: id },
          {
            status: "ARRIVED_AT_PICKUP",
            driverId: driverAccepted._id,
          },
          { new: true }
        );

        let userRequested = await userModel.findOne({
          _id: tripBookingRecord.userId,
        });

        console.log(
          "Trip booking record updated ARRIVED_AT_PICKUP: " +
            tripBookingRecord._id
        );

        let driverAcceptResponse = {
          driverInfo: {
            driverName: driverAccepted.driverName,
            phoneNumber: driverAccepted.phoneNumber,
            driverAvatar: driverAccepted.driverAvatar,
            licensePlate: driverAccepted.licensePlate,
            vehicleBrand: driverAccepted.vehicleBrand,
            travelMode: driverAccepted.travelMode,
            currentLatitude: driverAccepted.currentLatitude,
            currentLongitude: driverAccepted.currentLongitude,
            totalRating: driverAccepted.totalRating,
          },
          pickupAddress: tripBookingRecord.pickupAddress.address,
          pickupLatitude: tripBookingRecord.pickupAddress.latitude,
          pickupLongitude: tripBookingRecord.pickupAddress.longitude,
          destinationAddress: tripBookingRecord.destinationAddress.address,
          destinationLatitude: tripBookingRecord.destinationAddress.latitude,
          destinationLongitude: tripBookingRecord.destinationAddress.longitude,
          minutesToDriverArrival: tripBookingRecord.minutesToDriverArrival,
        };

        console.log(driverAcceptResponse);

        io.of("/booking")
          .to(userRequested.socketId)
          .emit("notify-accept-request", driverAcceptResponse);

        socket.emit("notify-accept-request", {
          id: tripBookingRecord._id,
          success: true,
        });

        // notify to drivers
        tripBookingRecord.socketIdDriversReceived.map((e) => {
          if (e !== socket.id) {
            io.of("/booking").to(e).emit("notify-delete-trip-record", {
              id: tripBookingRecord._id,
            });
          }
        });
      } catch (err) {
        console.error("Error saving socket ID:", err);
      }
    });

    socket.on("update-driver-location", async (data) => {
      try {
        const { currentLatitude, currentLongitude } = data;
        await driverModel.findOneAndUpdate(
          { socketId: socket.id },
          {
            currentLatitude,
            currentLongitude,
          }
        );

        console.log("Driver updated location: " + socket.id);
      } catch (err) {
        console.error("Error saving socket ID:", err);
      }
    });

    socket.on("driver-connect-socket", async (data) => {
      try {
        const { phoneNumber, currentLatitude, currentLongitude } = data;
        await driverModel.findOneAndUpdate(
          { phoneNumber },
          {
            socketId: socket.id,
            currentLatitude,
            currentLongitude,
            activeStatus: true,
          },
          { new: true }
        );

        console.log("Driver connected socket: " + socket.id);
      } catch (err) {
        console.error("Error saving socket ID:", err);
      }
    });

    socket.on("user-connect-socket", async (data) => {
      try {
        const { phoneNumber } = data;
        let user = await userModel.findOneAndUpdate(
          { phoneNumber },
          {
            socketId: socket.id,
          },
          { new: true }
        );

        if (!user) {
          console.log("User not found: " + phoneNumber);
          return;
        }

        console.log("User connected socket: " + socket.id);
      } catch (err) {
        console.error("Error saving socket ID:", err);
      }
    });

    socket.on("disconnect", async (data) => {
      try {
        let user = await userModel.findOne({ socketId: socket.id });
        if (user) {
          // delete trip booking records if exist
          let deletedTrip = await TripBookingRecordModel.findOneAndDelete({
            userId: user._id,
          });
          console.log("Trip booking records deleted: " + deletedTrip._id);

          // notify to drivers
          deletedTrip.socketIdDriversReceived.map((e) => {
            io.of("/booking").to(e).emit("notify-delete-trip-record", {
              id: deletedTrip._id,
            });
          });
        }

        await userModel.findOneAndUpdate(
          { socketId: socket.id },
          {
            socketId: null,
          }
        );
        await driverModel.findOneAndUpdate(
          { socketId: socket.id },
          {
            socketId: null,
            activeStatus: false,
          }
        );

        console.log("Client disconnected: " + socket.id);
      } catch (err) {
        console.error("Error saving socket ID:", err);
      }
    });
  });
};
