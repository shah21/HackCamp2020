const db = require('../db/init')

var fetchCarsList = function(callback) {
    db.findDocument({},{ projection: { _id: 0} },'cars',function(docs){
        callback(docs)
    });
}


/*  Export Function  */
const listCars = function(req,res,next) {

    fetchCarsList((data) => {
        res.status(200).json(data)
    })

}

module.exports = listCars