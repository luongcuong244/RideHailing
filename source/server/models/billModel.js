const mongoose = require("mongoose"); // Erase if already required

// Declare the Schema of the Mongo model
var userSchema = new mongoose.Schema(
  {
    travelMode: {
      type: String,
      enum: ["TAXI", "BIKE"],
      required: true,
    },
    startLatitude: {
      type: Number,
      required: true,
    },
    startLongitude: {
      type: Number,
      required: true,
    },
    endLatitude: {
      type: Number,
      required: true,
    },
    endLongitude: {
      type: Number,
      required: true,
    },
    distanceInKilometersth: {
      type: Number,
      required: true,
    },
    user: {
      type: mongoose.Types.ObjectId,
      ref: "User",
      required: true,
    },
    createByDriver: {
      type: mongoose.Types.ObjectId,
      ref: "Driver",
      required: true,
    },
    status: {
      type: String,
      required: true,
    },
  },
  {
    timestamps: true,
  }
);

//Export the model
module.exports = mongoose.model("User", userSchema);
