const mongoose = require("mongoose"); // Erase if already required

const AddressSchema = mongoose.Schema({
  address: String,
  latitude: Number,
  longitude: Number,
});

// Declare the Schema of the Mongo model
var TripBookingRecordModel = new mongoose.Schema(
  {
    pickupAddress: {
      address: String,
      latitude: Number,
      longitude: Number,
    },
    destinationAddress: {
      address: String,
      latitude: Number,
      longitude: Number,
    },
    distanceInKilometers: {
      type: Number,
      required: true,
    },
    durationInMinutes: {
      type: Number,
      required: true,
    },
    minutesToDriverArrival: {
      type: Number,
      required: true,
    },
    kilometersToDriverArrival: {
      type: Number,
      required: true,
    },
    paymentMethod: {
      type: String,
      required: true,
    },
    noteForDriver: String,
    cost: {
      type: Number,
      required: true,
    },
    userId: {
      type: mongoose.Schema.Types.ObjectId,
      ref: "UserModel",
      required: true,
    },
    socketIdDriversReceived: {
      type: [String],
      default: [],
      required: true,
    },
    driverId: {
      type: mongoose.Schema.Types.ObjectId,
      ref: "DriverModel",
      default: null,
    },
    status: {
      type: String,
      required: true,
      enum: ["PENDING", "ACCEPTED", "COMPLETED"],
    },
  },
  {
    timestamps: true,
  }
);

//Export the model
module.exports = mongoose.model(
  "TripBookingRecordModel",
  TripBookingRecordModel
);