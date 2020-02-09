const fs   = require('fs');
const jwt = require('jsonwebtoken')

var signOptions = {
    issuer:  'The Car Rental Company',
    subject:  'auth@manushamil.com',
    audience:  'manushamil.com',
    expiresIn:  "12h",
    algorithm:  "RS256"
};

var verifyOptions = {
    issuer:  'The Car Rental Company',
    subject:  'auth@manushamil.com',
    audience:  'manushamil.com',
    expiresIn:  "12h",
    algorithm:  "RS256"
};


// PRIVATE and PUBLIC key
var privateKEY  = fs.readFileSync('./private.key', 'utf8');
var publicKEY  = fs.readFileSync('./public.key', 'utf8');


const generateToken = function(payload) {
    var token = jwt.sign(payload, privateKEY, signOptions);

    return token;

}

var verifyToken = function(token) {
    var legit = jwt.verify(token, publicKEY, verifyOptions);

    return legit;
}

var getUser = function(token,callback) {
    var obj = jwt.verify(token, publicKEY, verifyOptions, function(err,decoded) {
        if (err)
            callback(undefined)
            
        callback(decoded.userName)
    })

}

module.exports = {
    generateToken : generateToken,
    verifyToken : verifyToken,
    getUser: getUser
};