var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');

const { errorsMiddleware } = require('./middlewares/errorsMiddleware');
const dbConnect = require('./config/database');

dbConnect()

var app = express();



// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'jade');

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: true }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

const route = require('./routes/index');
route(app);

// catch 404 and forward to error handler
app.use(function(req, res, next) {
  next(createError(404));
});

// error handler
app.use(errorsMiddleware);

const PORT = process.env.PORT;
const http = require("http");

const httpServer = http
  .createServer(app)

httpServer.listen(PORT, () => console.log(`Server running on port ${PORT}`));

const io = require("socket.io")(httpServer, {
  pingInterval: 5000,
  pingTimeout: 5000,
});

const startSocketIO = require("./socketio/socket.connect");
startSocketIO(io);

module.exports = app;
