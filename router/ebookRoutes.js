const express = require('express');
const ebookRouter= express.Router();
const {getEbookById,getRandomEbookByCategory,getRandomEbook}=require('../controller/ebookController')

ebookRouter.get('/getbooks',getRandomEbook);
ebookRouter.get('/getbooksbycategory/:categoryId',getRandomEbookByCategory);
ebookRouter.get('/getbooksbyid',getEbookById);

module.exports=ebookRouter;