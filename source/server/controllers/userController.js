const userModel = require("../models/userModel");
const asyncHandler = require("express-async-handler");

const getCurrent = asyncHandler(async (req, res) => {
  const { _id } = req.user;
  const user = await userModel
    .findById(_id)
    .select("-refreshToken -password -role");
  return res.status(200).json({
    succes: user ? true : false,
    rs: user ? user : "User not found!",
  });
});

const forgotPassword = asyncHandler(async (req, res) => {
  const { phoneNumber, newPassword } = req.body;
  const user = await userModel.findOne({ phoneNumber });
  user.password = newPassword;
  const check = await user.save();
  return res.status(200).json({
    succes: check ? true : false,
    rs: check ? "Changed password successfully!" : "Password change failed",
  });
});

module.exports = {
  getCurrent,
  forgotPassword,
};
