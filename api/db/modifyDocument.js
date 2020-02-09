
const MongoClient = require('mongodb').MongoClient;
const assert = require('assert');


const modify = function(filter,data,collection_name,callback) {
    const url = 'mongodb://localhost:27017';

    const dbName = 'rental';

    const client = new MongoClient(url);

    client.connect(function(err) {
        assert.equal(null, err);
    
        const db = client.db(dbName);
    
        const collection = db.collection(collection_name)

        collection.findOneAndUpdate(filter,data, { returnOriginal: false },function(err,res) {
            assert.equal(null, err);

            callback(res);
        })
    });


}

module.exports = modify