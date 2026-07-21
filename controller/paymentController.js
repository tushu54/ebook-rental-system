const { Payment } = require('../model/models');

const createPayment = async (req, res) => {
    const { transaction_id, amount, payment_date } = req.body;
  
    try {
      const payment = new Payment({
        transaction_id,
        amount,
        payment_date,
      });
  
      const savedPayment = await payment.save();
      
      res.status(200).json(savedPayment);
    } catch (error) {
      res.status(200).json({ message: error.message });
    }
  };

  const verifyPayment = async (req, res) => {
    const  id  = req.query.id;
    try {
      const payment = await Payment.findById(id);
      if (!payment) {
        return res.status(200).json({ message: 'Payment not found' });
      }
      
      payment.verifed = true;
      await payment.save();
      res.status(200).json(payment);
    } catch (error) {
      res.status(200).json({ message: error.message });
    }
  };

  const getAllPayments = async (req, res) => {
    try {
      const payments = await Payment.find()
      res.status(200).json(payments);
    } catch (error) {
      res.status(200).json({ message: error.message });
    }
  };

  module.exports={
    getAllPayments,
    verifyPayment,
    createPayment
  }