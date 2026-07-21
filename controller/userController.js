const {User,Rental, Ebook, Payment}= require('../model/models');
const bcrypt= require('bcrypt');
const jwt= require('jsonwebtoken')

const SECRET_KEY=process.env.SECRET_KEY;

const singup= async(req,res) => {

    const {  email, password, name } = req.body;

    try {
        const exitingUser= await User.findOne({ email: email });
        if(exitingUser){
          return res.status(200).json({ message:"User already exists"});
        }
        const hasPassword =await bcrypt.hash(password, 10);

        const result =await User.create({ 
            email: email,
             password:hasPassword,
            name:name });

            const token= jwt.sign({email:result.email, id:result._id},SECRET_KEY)
            res.status(200).json({user:result,token:token});
    }catch(e){
        console.log(e);
        res.status(200).json({message:'Something went wrong'})
    }

}

const singin= async (req,res) => {

    const { email, password } = req.body;
try{
    const exitingUser = await User.findOne({email:email});
    if(!exitingUser){
        return res.status(200).json({ message:"User not found"});
    }
    const matchtPassword = await bcrypt.compare(password,exitingUser.password);

    if(!matchtPassword){
        return res.status(200).json({message:'invalid Credentials'});
    }
    if(exitingUser.is_blocked){
        return res.status(200).json({message:'you are blocked'});
    }

    const token = jwt.sign({email: exitingUser.email,id : exitingUser._id},SECRET_KEY);

    res.status(200).json({user:exitingUser,token:token});
}catch(e){
        console.log(e);
        res.status(200).json({message:'Something went wrong'})
    }
}

const profile= async(req,res) => {

    const userId= req.userId;
    console.log(userId)
    const user =await User.findById(userId)
    const rentals=await Rental.find({user_id:user.id})

    
    const rental1= await Promise.all(rentals.map(async (rental)=>{
        
        const ebook=await Ebook.findById(rental.ebook_id)
        const payment=await Payment.findById(rental.payment_id)
        const expiry_date=rental.expiry_date
        const rental_date=rental.rental_date
       return{ 
        ebook,
        payment,
        expiry_date,
        rental_date
       }
    }))
    var data={email:user.email,
        name:user.name,
        rental: rental1
    }
    res.status(200).json(data)


}





module.exports={singin,singup,profile}