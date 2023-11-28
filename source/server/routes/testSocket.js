var express = require("express");
var router = express.Router();

const { test } = require("../controllers/testSocket");

router.put("/", test);

module.exports = router;
