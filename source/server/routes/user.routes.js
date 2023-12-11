var express = require("express");
var router = express.Router();

const {
  forgotPassword,
  getCurrent,
  addAddress,
  deleteAddress,
  getAddresses,
  getBills,
} = require("../controllers/restful/user");
const { verifyAccessToken } = require("../middlewares/verifyToken");

router.get("/current", verifyAccessToken, getCurrent);
router.get("/get-addresses", verifyAccessToken, getAddresses);
router.post("/change-password", forgotPassword);
router.post("/add-address", verifyAccessToken, addAddress);
router.post("/delete-address/:did", verifyAccessToken, deleteAddress);
router.get("/get-bills", verifyAccessToken, getBills)

module.exports = router;
