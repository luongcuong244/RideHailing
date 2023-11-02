const express = require("express");
const router = express.Router();

const authController = require("../controllers/authController.js");

router.get("/check-existing-user", authController.checkExistingUser);
router.post("/login", authController.login);
router.post("/register", authController.register);
router.post("/refresh-token", authController.refreshAccessToken);

module.exports = router;