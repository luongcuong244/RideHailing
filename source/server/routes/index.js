const userRouter = require("./user.routes");
const authRouter = require("./auth.routes");

function route(app) {
  app.use("/user", userRouter);
  app.use("/auth", authRouter);
}

module.exports = route;
