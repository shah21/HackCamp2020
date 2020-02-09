const db = require('../db/init')
const bcrypt = require('bcrypt')
const tokens = require('../token/token')

const register = function(req,res,next) {
    const response = new Response()

    var body = req.body;

    var email = body.email
    var username = body.username
    var password = body.password


    bcrypt.hash(password, 10, function(err, hash) {

        var newuser = {
            email: email,
            username: username,
            password: hash
        }

        db.insertDocument(newuser,'users',function(doc) {
            if (doc > 0) {
                var token = tokens.generateToken({ userName : newuser.username })

                response.setResponse(200,{'result' : token})

                response.sendResponse(res)
            } else {
                response.setResponse(409,{'result' : 'Could not register user.'})

                response.sendResponse(res)
            }
        })
    });
}

class Response {
    constructor(){
        this.status = null
        this.response = {}
        this.responseSent = false

        this.response['data'] =  []
    }

    setResponse(status,response) {
        this.status = status
        this.response = response
    }

    sendResponse(res) {
        if (this.responseSent == false) {
            this.responseSent = true
            res.status(this.status).json(this.response)
        } else {
            console.log('Blocked from sending response twice!')
        }
    }


}

module.exports = register