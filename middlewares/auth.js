const jwt = require('jsonwebtoken');
const SECRET_KEY=process.env.SECRET_KEY;


const auth = async (req, res, next) => {

    try {

         let token = req.headers.authorization
         console.log(token)
         if(token) {

            token = token.split(' ')[1];
            let user= jwt.verify(token, SECRET_KEY);
            req.userId= user.id;
            next();
            
         }
         else{

           return res.status(401).json({message:'Unauthrized User'});
         }



    }catch(err) {
        console.log(err)

        res.status(401).json({message:'Unauthrized User'});
    }
  
}

module.exports = auth;