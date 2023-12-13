const userModel = require("../../models/userModel");
const driverModel = require("../../models/driverModel");
const billModel = require("../../models/billModel");  
const asyncHandler = require("express-async-handler");

const getCurrent = asyncHandler(async (req, res) => {
  const { _id } = req.user;
  const user = await userModel
    .findById(_id)
    .select("-refreshToken -password -role");
  if (user) {
    return res.status(200).json({
      phoneNumber: user.phoneNumber,
      userName: user.userName,
      email: user.email,
    });
  } else {
    res.status(400);
    throw new Error("error");
  }
});

const forgotPassword = asyncHandler(async (req, res) => {
  const { phoneNumber, newPassword } = req.body;
  const user = await userModel.findOne({ phoneNumber });

  if (!user) {
    console.log("User not found.");
    res.status(400);
    throw new Error("error");
  }

  let result = await userModel.findByIdAndUpdate(user._id, {
    password: newPassword,
  });

  if (result) {
    return res.status(200).send("success.");
  } else {
    res.status(400);
    throw new Error("error");
  }
});

const addAddress = asyncHandler(async (req, res) => {
  const { _id } = req.user;
  const { addressType, shortName, fullName, longitude, latitude } = req.body;
  if (!addressType || !shortName || !fullName || !longitude || !latitude)
    throw new Error("Missing inputs.");
  const response = await userModel.findByIdAndUpdate(
    _id,
    {
      $push: { address: req.body },
    },
    { new: true }
  );

  if (!response) res.status(400).send("Something went wrong.");

  return res.status(200).send("success.");
});

const deleteAddress = asyncHandler(async (req, res) => {
  const { _id } = req.user;
  const { did } = req.params;
  const response = await userModel.findByIdAndUpdate(
    _id,
    {
      $pull: { address: { _id: did } },
    },
    { new: true }
  );
  return res.json({
    data: response ? true : false,
    // deleteBlog: response ? response : " Something went wrong. ",
  });
});

const getAddresses = asyncHandler(async (req, res) => {
  const { _id } = req.user;
  const response = await userModel.findByIdAndUpdate(_id);
  if (response) {
    console.log("Addresses: " + response.address);

    return res.status(200).json({
      addresses: response.address ? response.address : [],
    });
  } else {
    res.status(400);
    throw new Error("error");
  }
});

const getBills = asyncHandler(async (req, res) => {
  const { _id } = req.user;
  const bills = await billModel.find({ userId: _id });
  if (!bills) {
    res.status(400);
    throw new Error("error");
  }

  const billList = [];
  for (let i = 0; i < bills.length; i++) {
    const driver = await driverModel.findById(bills[i].driverId);
    if (!driver) {
      res.status(400);
      throw new Error("error");
    }
    billList.push({
      id: bills[i]._id,
      pickupAddress: bills[i].pickupAddress.address,
      pickupLatitude: bills[i].pickupAddress.latitude,
      pickupLongitude: bills[i].pickupAddress.longitude,
      destinationAddress: bills[i].destinationAddress.address,
      destinationLatitude: bills[i].destinationAddress.latitude,
      destinationLongitude: bills[i].destinationAddress.longitude,
      distanceInKilometers: bills[i].distanceInKilometers,
      durationInMinutes: bills[i].durationInMinutes,
      paymentMethod: bills[i].paymentMethod,
      noteForDriver: bills[i].noteForDriver,
      cost: bills[i].cost,
      travelMode: bills[i].travelMode,
      driverInfo: {
        id: driver._id,
        driverName: driver.driverName,
        phoneNumber: driver.phoneNumber,
        driverAvatar: driver.driverAvatar,
        licensePlate: driver.licensePlate,
        vehicleBrand: driver.vehicleBrand,
        travelMode: driver.travelMode,
        currentLatitude: driver.currentLatitude,
        currentLongitude: driver.currentLongitude,
        totalRating: driver.totalRating,
      },
      createdTime: bills[i].createdAt.getTime(),
    });
  }

  console.log("billList: " + billList);

  return res.status(200).json({
    bills: billList,
  });
});

module.exports = {
  getCurrent,
  forgotPassword,
  addAddress,
  deleteAddress,
  getAddresses,
  getBills,
};
