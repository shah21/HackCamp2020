const db = require('../db/init')
const tokens = require('../token/token')

const bcrypt = require('bcrypt')


function login(req,res,next) {

    const response = new Response()

    var params = req.query;

    var username = params.username
    var password = params.password


    db.findDocument({username : username},{ _id: 0},'users',function(docs){


        if (docs.length <= 0) {
            response.setResponse(404,{'result' : 'No user found!'})
            response.sendResponse(res)

        }


        var password_received = docs[0].password

        bcrypt.compare(password,password_received, function(err, is_ok) {
            if (is_ok) {
                var token = tokens.generateToken({ userName : docs[0].username })

                response.setResponse(200,{'result' : token})
    
                response.sendResponse(res)
            } else {
                response.setResponse(403,{'result' : 'password wrong!!'})
                response.sendResponse(res)
            }
        })

    })
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

module.exports = login