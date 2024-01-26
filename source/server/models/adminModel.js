const mongoose = require('mongoose');
const schema = mongoose.Schema;
const bcrypt = require('bcryptjs')

const userSchema = new schema({
    name: {
        reuired: true,
        type: String
    },
    email: {
        requiered: true,
        type: String
    },
    password: {
        required: true,
        type: String
    }
},
);
userSchema.pre('save', async function (next) {

    if (!this.isModified('password')) {
        return next();
    }
    try {
        const salt = await bcrypt.genSalt(10);
        this.password = await bcrypt.hash(this.password, salt);
        next();
    } catch (error) {
        return next(error);
    }
})



module.exports = mongoose.model('contactAdmin', userSchema);