require("dotenv").config();

const express = require('express')
const app = express()
const dotenv = require('dotenv');
const cors = require('cors');
const connectDB = require('./config/db');
const userRouter = require('./router/userRoutes');
const ebookRouter = require('./router/ebookRoutes');
const rentalRouter = require('./router/rentalRoutes');
const pyemntrouter = require('./router/paymentRouter');
const categoryRoutes = require('./router/categoryRoutes');
const adminRouter = require('./router/adminRoutes');

dotenv.config();
app.use(express.json());
app.use(cors());

connectDB();

app.use("/api/user",userRouter)
app.use("/api/ebook",ebookRouter)
app.use("/api/rental",rentalRouter);
app.use("/api/payment",pyemntrouter);
app.use("/api/category",categoryRoutes);


app.use("/api/admin",adminRouter);

app.get('/', (req, res)=>{

    res.status(200).send("Hello Wold");

})

const PORT = process.env.PORT || 5000;
app.listen(PORT, () => {
  console.log(`Server running on port ${PORT}`);
});