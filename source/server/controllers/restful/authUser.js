const {
  generateAccessToken,
  generateRefreshToken,
} = require("../../middlewares/jwt");
const jwt = require("jsonwebtoken");

const userModel = require("../../models/userModel");
const asyncHandler = require("express-async-handler");

const checkExistingUser = asyncHandler(async (req, res) => {
  try {
    const { phoneNumber } = req.query;
    const check = await userModel.findOne({ phoneNumber });
    return res.status(200).json({
      data: check ? true : false,
    });
  } catch (error) {
    return res.status(400).send("error.");
  }
});

const register = asyncHandler(async (req, res) => {
  const { password, phoneNumber, userName, email } = req.body;
  if (!userName || !password || !phoneNumber || !email) {
    res.status(400);
    throw new Error("Missing input.");
  }
  const user = await userModel.findOne({ phoneNumber });
  if (user) {
    res.status(400);
    throw new Error("User has existed!");
  } else {
    const newUser = await userModel.create(req.body);
    if (newUser) {
      return res.status(200).send("Registered successfully.");
    } else {
      res.status(400);
      throw new Error("Registered failed.");
    }
  }
});

// refresh token => cap moi access token
//access token => xac thuc va phan quyen nguoi dung
const login = asyncHandler(async (req, res) => {
  const { phoneNumber, password } = req.body;
  if (!phoneNumber || !password) {
    res.status(400);
    throw new Error("Missing input!");
  }

  const response = await userModel.findOne({ phoneNumber });

  if (response && (await response.isCorrectPassword(password))) {
    // tách pw và role ra khỏi response
    // const { password, role, ...user } = response.toObject();
    // tạo access token
    const accessToken = generateAccessToken(response._id, role);
    // tạo refresh token
    const refreshToken = generateRefreshToken(response._id);
    //Lưu refresh token vào database
    const userData = await userModel
      .findByIdAndUpdate(response._id, { refreshToken }, { new: true })
      .select("-password -role");
    // Lưu refresh token vào cookie
    res.cookie("refreshToken", refreshToken, {
      httpOnly: true,
      maxAge: 7 * 24 * 60 * 60 * 1000,
    });
    return res.status(200).json({
      accessToken,
      refreshToken,
      userData,
    });
  } else {
    res.status(400);
    throw new Error("Invalid credenttials!");
  }
});

const refreshAccessToken = asyncHandler(async (req, res) => {
  const cookie = req.cookies;
  if (!cookie && !cookie.refreshToken)
    throw new Error("No refresh token in cookies");
  const rs = await jwt.verify(cookie.refreshToken, process.env.JWT_SECRET);

  const response = await userModel.findOne({
    _id: rs._id,
    refreshToken: cookie.refreshToken,
  });
  if (response) {
    return res.status(200).json({
      newAccessToken: generateAccessToken(response._id, response.role),
    });
  } else {
    res.status(400).send("error.");
  }
});

module.exports = {
  checkExistingUser,
  register,
  login,
  refreshAccessToken,
};
