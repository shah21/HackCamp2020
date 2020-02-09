const app = require('express')
const router = app.Router()

const checkAuthenticity = require('../api/sec/authenticate')

const cars = require('../api/car/init')
const users = require('../api/user/init')

router.get('/api/car/list',cars.listCars)
router.post('/api/car/add', checkAuthenticity, cars.addCars)
router.post('/api/car/order', checkAuthenticity, cars.orderCar)


router.get('/api/user/login', users.login)

router.post('/api/user/register', users.register)


module.exports = router