const express = require('express')
const app = express()
const morgan = require('morgan')
const bodyParser = require('body-parser')

app.use(morgan('dev'))

app.use(bodyParser.urlencoded({ extended: false })) 

app.use(bodyParser.json())


app.use(express.static('www'))

app.use(require('./routes/initRouter'))


app.listen(8080, () => {
    console.log('Running on port 8080')
})


