const tokens = require('./../token/token')

function authenticate(req,res,next) {

    var token = req.headers.token

    try {
        if (tokens.verifyToken(token)) {
            next()
        }
    } catch (error) {
        res.status(403).json('Could verify token!')
    }

}

module.exports = authenticate