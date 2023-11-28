const express = require("express");
const router = express.Router();

const authDriverController = require("../controllers/authDriver.js");

router.get("/check-existing-user", authDriverController.checkExistingDriver);
router.post("/login", authDriverController.login);
router.post("/register", authDriverController.register);

module.exports = router;