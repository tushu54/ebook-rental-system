const express = require('express');
const { getCategory } = require('../controller/categoryController');
const categoryRoutes= express.Router();

categoryRoutes.get('/categorys',getCategory)


module.exports=categoryRoutes;