const mongoose = require('mongoose')

const UserSchema= mongoose.Schema({
    name:{type:String,required:true},
    email:{type:String,required:true},
    password:{type:String,required:true},
    is_blocked:{type:Boolean,default:false}

});

const AdminSchema= mongoose.Schema({
    name:{type:String,required:true},
    email:{type:String,required:true},
    password:{type:String,required:true}

});

const CategorieSchema= mongoose.Schema({
    name:{type:String,required:true},
    image_url:{type:String,required:true}

});

const EbookSchema= mongoose.Schema({
    title:{type:String,required:true},
    author:{type:String,required:true},
    category_id:{
        type:mongoose.Schema.Types.ObjectId,
        ref:"Category",
        required:true
    },
    description:{type:String,required:true},
    language:{type:String,required:true},
    date_of_publication:{type:String,required:true},
    price:{type:String,required:true},
    image_url:{type:String,required:true},
    pdf_url:{type:String,required:true}
});

const RentalSchema= mongoose.Schema({
    user_id:{
        type:mongoose.Schema.Types.ObjectId,
        ref:"User",
        required:true
    },
    ebook_id:{
        type:mongoose.Schema.Types.ObjectId,
        ref:"Ebook",
        required:true
    },
    payment_id:{
        type:mongoose.Schema.Types.ObjectId,
        ref:"Payment",
        required:true
    },
    rental_date:{type:String,required:true},
    expiry_date:{type:String,required:true}
    

});

const PaymentSchema= mongoose.Schema({  
    transaction_id:{type:String, required:true},
    amount:{type:String,required:true},
    payment_date:{type:String,required:true},
    verifed:{type:Boolean,default:false,required:false}
});

const User =mongoose.model('User',UserSchema);
const Admin =mongoose.model('Admin',AdminSchema);
const Category = mongoose.model('Category', CategorieSchema);
const Ebook = mongoose.model('Ebook', EbookSchema);
const Rental = mongoose.model('Rental', RentalSchema);
const Payment = mongoose.model('Payment', PaymentSchema);
module.exports = {
    User,
    Admin,
    Category,
    Ebook,
    Rental,
    Payment
};