var express = require('express');
var router = express.Router();

const { forgotPassword, getCurrent} = require('../controllers/userController');
const { verifyAccessToken } = require('../middlewares/verifyToken');

router.get('/current', verifyAccessToken, getCurrent);
router.post('/change-password', forgotPassword);

module.exports = router;
