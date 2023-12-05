const driverModel = require("../../models/driverModel");
const userModel = require("../../models/userModel");
const tripBookingRecordModel = require("../../models/TripBookingRecordModel");

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

        let tripBookingRecord = await tripBookingRecordModel.create({
          pickupAddress,
          destinationAddress,
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

        console.log("Trip booking record created: " + tripBookingRecord._id);

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

    socket.on("delete-orders", async (data) => {
      try {
        // console.log(data);
        const { idUser, drivers } = data;
        //   console.log(listDriver);
        const listSocketId = await driverModel
          .find({ _id: drivers, activeStatus: true })
          .select("socketId -_id");
        listSocketId.map((e) => {
          io.to(e.socketId).emit("delete-data", {
            idUser,
          });
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
        await userModel.findOneAndUpdate(
          { phoneNumber },
          {
            socketId: socket.id,
          },
          { new: true }
        );

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
          let a = await tripBookingRecordModel.deleteMany({ userId: user._id });
          console.log("Trip booking records deleted: " + a);
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
