const {  Rental,Ebook, User,Payment } = require('../model/models');

const getAllRentals = async (req, res) => {
    try {
        const rentals = await Rental.find()

        res.status(200).json(rentals);
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
};

const getRentalById = async (req, res) => {
    try {
        const rental = await Rental.findById(req.params.id)
        console.log(req.params.id)
        if (!rental) {
            return res.status(404).json({ message: 'Rental not found' });
        }

        final={user:await User.findById(rental.user_id),
            book:await Ebook.findById(rental.ebook_id),
            payment: await Payment.findById(rental.payment_id),
            rental_date:rental.rental_date,
            expiry_date:rental.expiry_date
        }

        res.status(200).json(final);
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
};

const createRental = async (req, res) => {
    const { ebook_id, rental_date, expiry_date, payment_id} = req.body;
const user_id=req.userId;
    try {
        const ebook = await Ebook.findById(ebook_id);
        if (!ebook) {
            return res.status(404).json({ message: 'eBook not found' });
        }

        const rental = new Rental({
            user_id,
            ebook_id,
            rental_date,
            expiry_date,
            payment_id,
        });
       

        const savedRental = await rental.save();
        res.status(200).json(savedRental);
    } catch (error) {
        res.status(200).json({ message: error.message });
    }
};

module.exports={getAllRentals,getRentalById,createRental}