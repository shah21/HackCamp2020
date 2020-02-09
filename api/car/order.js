const db = require('../db/init')
const Car = require('./car')
const token = require('../token/token')

var findCar = function(carID,callback) {
    db.findDocument({carID: carID},{ projection: { _id: 0} },'cars',function(docs){
        callback(docs)
    });
}


var orderCar = function(req,res,next) {
    var body = req.body
    var carID = body.carID

    var myToken = req.headers.token

    token.getUser(myToken,function(username) {

        //Find if car with CarID exits in DB
        findCar(carID,function(docs) {
            console.log(docs)

            
        })
    })

}

module.exports = orderCar