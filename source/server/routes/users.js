var express = require('express');
var router = express.Router();
const { register, login, refreshAccessToken, forgotPassword, checkExistingUser, getCurrent} = require('../controllers/userController');
const { verifyAccessToken } = require('../middlewares/verifyToken');

/* GET users listing. */
router.get('/checkExistingUser', checkExistingUser);
router.post('/register', register);
router.post('/login', login);
router.get('/current', verifyAccessToken, getCurrent);
router.post('/refreshtoken', refreshAccessToken);
router.post('/forgotpassword', forgotPassword);

module.exports = router;
