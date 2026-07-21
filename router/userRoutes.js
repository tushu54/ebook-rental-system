const express = require('express');
const userRouter= express.Router();
const { singin,singup,profile } = require('../controller/userController');
const auth = require('../middlewares/auth');

userRouter.post('/singup',singup);
userRouter.post('/singin',singin);
userRouter.get('/profile',auth,profile);
module.exports=userRouter;