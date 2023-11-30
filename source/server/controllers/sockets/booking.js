const driverModel = require("../../models/driverModel");
const userModel = require("../../models/userModel");

module.exports = function (io) {
  io.of("/booking").on("connection", (socket) => {
    console.log("New client connected: " + socket.id);

    // tìm tài xế
    socket.on("findDriver", async (data) => {
      try {
        // console.log(data);
        const {
          fareAmount,
          idUser,
          drivers,
          startLatitude,
          startLongitude,
          endLatitude,
          endLongitude,
        } = data;
        //   console.log(listDriver);
        const listSocketId = await driverModel
          .find({ _id: drivers, activeStatus: true })
          .select("socketId -_id");
        listSocketId.map((e) => {
          // console.log(fareAmount, e.socketId);
          io.to(e.socketId).emit("receive-data", {
            idUser,
            fareAmount,
            startLatitude,
            startLongitude,
            endLatitude,
            endLongitude,
          });
        });
      } catch (err) {
        console.error("Error saving socket ID:", err);
      }
    });

    socket.on("delete-orders", async (data) => {
      try {
        // console.log(data);
        const {
          idUser,
          drivers,
        } = data;
        //   console.log(listDriver);
        const listSocketId = await driverModel
          .find({ _id: drivers, activeStatus: true })
          .select("socketId -_id");
        listSocketId.map((e) => {
          // console.log(fareAmount, e.socketId);
          io.to(e.socketId).emit("delete-data", {
            idUser,
          });
        });
      } catch (err) {
        console.error("Error saving socket ID:", err);
      }
    });

    socket.on("notification-order", async (data) => {
      try {
        // console.log(data);
        const {
          idUser
        } = data;
        //   console.log(listDriver);
        const user = await userModel
          .find({ _id: idUser })
          .select("socketId -_id");
        
          io.to(user.socketId).emit("notification-data", "The driver is coming to pick you up.");
      
      } catch (err) {
        console.error("Error saving socket ID:", err);
      }
    });
  });
};
