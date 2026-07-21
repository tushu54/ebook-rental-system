const express = require('express');
const rentalRouter= express.Router();
const { createRental,getAllRentals,getRentalById } = require('../controller/rentalController');
const { model } = require('mongoose');
const auth = require('../middlewares/auth');


rentalRouter.get('/getrentals',getAllRentals);
rentalRouter.get('/getrentalbyid/:id',getRentalById);
rentalRouter.post('/',auth,createRental);

module.exports=rentalRouter;