const express = require('express');
const pyemntrouter= express.Router();
const {createPayment, getAllPayments, verifyPayment}= require('../controller/paymentController')


pyemntrouter.post('/', createPayment);
pyemntrouter.get('/getallpayments', getAllPayments);
pyemntrouter.put('/:id/verify', verifyPayment);


module.exports=pyemntrouter