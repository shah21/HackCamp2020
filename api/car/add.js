const db = require('../db/init')
const Car = require('./car')
const token = require('../token/token')

var addCars = function(req,res,next) {
    var body = req.body

    var myCar = body

    var myToken = req.headers.token

    token.getUser(myToken,function(username) {
        if (username) {

            var car = new Car(username)

            car.carMake = myCar.carMake
            car.carModel = myCar.carModel
            car.carTransmission = myCar.carTransmission
            car.carSeats = myCar.carSeats
            car.pricePerDay = myCar.pricePerDay
            car.pricePerHour = myCar.pricePerHour
        
            db.insertDocument(car,'cars', function(doc) {
                res.status(200).json(car)
            })
        } else {
            res.status(409).json({})
        }
    })


}

module.exports = addCars