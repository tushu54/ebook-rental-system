const jwt = require('jsonwebtoken');
const ADMIN_SECRET_KEY=process.env.ADMIN_SECRET_KEY;


const adminauth = async (req, res, next) => {

    try {

         let token = req.headers.authorization
         console.log(token)
         if(token) {
            
            token = token.split(' ')[1];
            let user= jwt.verify(token, ADMIN_SECRET_KEY);
            req.userId= user.id;
            next();
            
         }
         else{

           return res.status(401).json({message:'Unauthrized Admin'});
         }



    }catch(err) {
        console.log(err)

        res.status(401).json({message:'Unauthrized Admin'});
    }
  
}

module.exports = adminauth;