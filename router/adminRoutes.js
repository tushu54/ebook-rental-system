const express = require('express');
const adminRouter= express.Router();
const adminauth=require('../middlewares/adminAuth');
const { singin, singup,getAllUser, DeleteUser, getAllRental } = require('../controller/adminController');
const { getAllBooks, deleteBooks, editBooks, createEbook } = require('../controller/ebookController');
const { verifyPayment } = require('../controller/paymentController');

adminRouter.post('/singin',singin);
adminRouter.post('/singup',singup);
adminRouter.get('/allbooks',adminauth,getAllBooks);
adminRouter.get('/allusers',adminauth,getAllUser);
adminRouter.delete('/user',adminauth,DeleteUser)
adminRouter.delete('/books',adminauth,deleteBooks)
adminRouter.get('/rentals',adminauth,getAllRental)
adminRouter.get('/payment/verify',adminauth,verifyPayment)
adminRouter.post('/book/edit',adminauth,editBooks)
adminRouter.post('/book',adminauth,createEbook)

module.exports=adminRouter;