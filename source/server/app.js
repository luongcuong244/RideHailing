var createError = require("http-errors");
var express = require("express");
var path = require("path");
var cookieParser = require("cookie-parser");
var logger = require("morgan");

const { errorsMiddleware } = require("./middlewares/errorsMiddleware");
const dbConnect = require("./config/database");

dbConnect();

var app = express();

// view engine setup
app.set("views", path.join(__dirname, "views"));
app.set("view engine", "jade");

app.use(logger("dev"));
app.use(express.json());
app.use(express.urlencoded({ extended: true }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, "public")));

const route = require("./routes/index");
route(app);

// catch 404 and forward to error handler
app.use(function (req, res, next) {
  next(createError(404));
});

// error handler
app.use(errorsMiddleware);

const PORT = process.env.PORT;
const http = require("http");
const server = http
  .createServer(app)
  .listen(PORT, () => console.log(`Server running on port ${PORT}`));

const socketIO = require("socket.io");
const driverModel = require("./models/driverModel");
const io = socketIO(server, {
  cors: {   
    origin: "*",
  },
});

io.on("connection", (socket) => {
  console.log("New client connected: " + socket.id);

  socket.emit("connected", socket.id);

  // Xử lý khi tài xế đăng nhập thành công
  // socket.on("driver-login", async (driverData) => {
  //   // Lưu socket ID vào schema của tài khoản tài xế
  //   try {
  //     const { idDriver, socketId } = driverData;
  //     await driverModel.findOneAndUpdate(
  //       { _id: idDriver },
  //       { socketId: socketId },
  //       { new: true }
  //     );
  //     // console.log(respone);
  //   } catch (err) {
  //     console.error("Error saving socket ID:", err);
  //   }
  // });

  socket.on("receive-application", async (data) => {
    try {
      const { idDriver, currentLatitude, currentLongitude, socketId } = data;
      await driverModel.findOneAndUpdate(
        { _id: idDriver },
        {
          socketId,
          currentLatitude,
          currentLongitude,
          activeStatus: true,
        },
        { new: true }
      );
      // console.log(respone);
    } catch (err) {
      console.error("Error saving socket ID:", err);
    }
  });


  socket.on("disconnected", async (data) => {
    try {
      const { idDriver } = data;
      await driverModel.findOneAndUpdate(
        { _id: idDriver },
        {
          socketId: null,
          currentLatitude: null,
          currentLongitude: null,
          activeStatus: false,
        },
        { new: true }
      );
      // console.log(respone);
    } catch (err) {
      console.error("Error saving socket ID:", err);
    }
  });

  socket.on("findDriver", async (data) => {
    try {
      // console.log(data);
      const {
        fareAmount,
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
});

module.exports = app;
