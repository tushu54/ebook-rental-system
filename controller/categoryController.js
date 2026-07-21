const { Category } = require('../model/models');

const getCategory= async  (req, res) =>{

    try {
        
        const category = await Category.find()
        res.status(200).json(category);

    } catch (error) {
        console.log(error);
        res.status(500).json({message:'Something went wrong'});
    }
}

module.exports={getCategory}