const { Ebook, Category } = require('../model/models');
const maxEbooks = 15;



const getRandomEbook = async (req, res) => {
    try {
        const count = await Ebook.countDocuments();
        const limit = Math.min(count, maxEbooks);
        if (limit === 0) {
            return res.status(200).json({ message: 'No ebooks found' });
        }
        const randomIndex = Math.floor(Math.random() * limit);
        const randomEbook = await Ebook.find()
        if (!randomEbook) {
            return res.status(200).json({ message: 'No ebooks found' });
        }
         
        res.status(200).json(randomEbook);
    } catch (error) {
        res.status(500).json({ message: 'Error fetching random ebook', error: error.message });
    }
};

const getRandomEbookByCategory = async (req, res) => {
    const { categoryId } = req.params;
    try {
        const count = await Ebook.find({ category_id:categoryId });
        if (!count) {
            return res.status(404).json({ message: 'No ebooks found in this category' });
        }
        res.status(200).json(count);
    } catch (error) {
        res.status(500).json({ message: 'Error fetching random ebook', error: error.message });
    }
};


const getEbookById = async (req, res) => {
    const { id } = req.params;

    try {
        const ebook = await Ebook.findById(id).populate('categoryId');

        if (!ebook) {
            return res.status(404).json({ message: 'Ebook not found' });
        }

        res.status(200).json(ebook);
    } catch (error) {
        res.status(500).json({ message: 'Error fetching ebook', error: error.message });
    }
};


/*const createEbook = async (req, res) => {
    console.log(req.body);
    const { title, description, pdf_url, price, author, category_id } = req.body;
    try {
        const newEbook = new Ebook({ title, description,image_url,language,date_of_publication, pdf_url, price, author, category_id });
        await newEbook.save();
        res.status(200).json(newEbook);
    } catch (error) {
        res.status(500).json({ error: error.message });
    }
};*/

const createEbook = async (req, res) => {
    console.log(req.body);

    const { 
        title, 
        description, 
        pdf_url, 
        price, 
        author, 
        category_id,
        image_url,
        language,
        date_of_publication
    } = req.body;

    try {
        const newEbook = new Ebook({
            title,
            author,
            category_id,
            description,
            language,
            date_of_publication,
            price,
            image_url,
            pdf_url
        });

        await newEbook.save();

        res.status(201).json(newEbook);

    } catch (error) {
        console.log(error);
        res.status(500).json({ error: error.message });
    }
};

const getAllBooks = async (req, res) => {
    try {
         const randomEbook = await Ebook.find()
        if (!randomEbook) {
            return res.status(401).json({ message: 'No ebooks found' });
        }   
         
        res.status(200).json(randomEbook);
    } catch (error) {
        res.status(500).json({ message: 'Error fetching random ebook', error: error.message });
    }
};


const deleteBooks = async (req, res) => {
    try {
        const id =req.query.book
        const randomEbook = await Ebook.findByIdAndDelete(id)
        if (!randomEbook) {
            return res.status(401).json({ message: 'No Book found' });
        }
        res.status(200).json(randomEbook);
    } catch (error) {
        console.log(error.message)
        res.status(500).json({ message: 'Error fetching Book', error: error.message });
    }
};


const editBooks = async (req, res) => {
    try {
        const { title, description, pdf_url, price, author, category_id } = req.body;
        const update={
            title: title, description:description, pdf_url:pdf_url,price: price, author:author, category_id:category_id 
        }
        const id =req.query.book
        const randomEbook = await Ebook.findByIdAndUpdate(id,update,{new:true})
        if (!randomEbook) {
            return res.status(401).json({ message: 'No Book found' });
        }
        res.status(200).json(randomEbook);
    } catch (error) {
        console.log(error.message)
        res.status(500).json({ message: 'Error fetching Book', error: error.message });
    }
};




module.exports = { getEbookById, getRandomEbook, getRandomEbookByCategory, createEbook ,getAllBooks,deleteBooks,editBooks}