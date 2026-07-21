const {User,Rental, Ebook, Payment,Admin}= require('../model/models');
const bcrypt= require('bcrypt');
const jwt= require('jsonwebtoken')
const ADMIN_SECRET_KEY=process.env.ADMIN_SECRET_KEY;

const singin= async (req,res) => {

    const { email, password } = req.body;
try{
    const exitingUser = await Admin.findOne({email:email});
    if(!exitingUser){
        return res.status(404).json({ message:"Amin not found"});
    }
    const matchtPassword = await bcrypt.compare(password,exitingUser.password);

    if(!matchtPassword){
        return res.status(401).json({message:'invalid Credentials'});
    }

    const token = jwt.sign({email: exitingUser.email,id : exitingUser._id},ADMIN_SECRET_KEY);

    res.status(200).json({user:exitingUser,token:token});
}catch(e){
        console.log(e);
        res.status(500).json({message:'Something went wrong'})
    }
}


const singup= async(req,res) => {

    const {  email, password, name } = req.body;

    try {
        const exitingUser= await Admin.findOne({ email: email });
        if(exitingUser){
          return res.status(401).json({ message:"User already exists"});
        }
        const hasPassword =await bcrypt.hash(password, 10);

        const result =await Admin.create({ 
            email: email,
             password:hasPassword,
            name:name });

            const token= jwt.sign({email:result.email, id:result._id},ADMIN_SECRET_KEY)
            res.status(200).json({user:result,token:token});
    }catch(e){
        console.log(e);
        res.status(200).json({message:'Something went wrong'})
    }

}


const getAllUser = async (req, res) => {
    try {
         const randomEbook = await User.find()
        if (!randomEbook) {
            return res.status(401).json({ message: 'No User found' });
        }
         
        res.status(200).json(randomEbook);
    } catch (error) {
        res.status(500).json({ message: 'Error fetching User', error: error.message });
    }
};

const DeleteUser = async (req, res) => {
    try {
        const id =req.query.user
        const randomEbook = await User.findByIdAndDelete(id)
        if (!randomEbook) {
            return res.status(401).json({ message: 'No User found' });
        }
        res.status(200).json(randomEbook);
    } catch (error) {
        console.log(error.message)
        res.status(500).json({ message: 'Error fetching User', error: error.message });
    }
};


const getAllRental = async (req, res) => {
    try {
        const rentals = await Rental.find()
        if (!rentals) {
            return res.status(404).json({ message: 'Rental not found' });
        }

        const rental1= await Promise.all(rentals.map(async (rental)=>{
        
            const ebook=await Ebook.findById(rental.ebook_id)
            const payment=await Payment.findById(rental.payment_id)
            const user= await User.findById(rental.user_id)
            const expiry_date=rental.expiry_date
            const rental_date=rental.rental_date
           return{ 
            ebook,
            payment,
            user,
            expiry_date,
            rental_date
           }
        }))
        res.status(200).json(rental1);
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
};


module.exports={singin,singup,getAllUser,DeleteUser,getAllRental}