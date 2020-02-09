const uniqid = require('uniqid')

class Car {
    constructor(owner) {
        this.owner = owner //Owner UserID
        this.carID = uniqid()
        this.carMake = ""
        this.carModel = ""
        this.carTransmission = ""
        this.carSeats = -1
        this.pricePerDay = -1
        this.pricePerHour = -1
    }
}


module.exports = Car