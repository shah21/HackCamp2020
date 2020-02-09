
const MongoClient = require('mongodb').MongoClient;
const assert = require('assert');


const find = function(query,options,collection_name,callback) {
    const url = 'mongodb://localhost:27017';

    const dbName = 'rental';

    const client = new MongoClient(url);

    client.connect(function(err) {
        assert.equal(null, err);
    
        const db = client.db(dbName);
    
        const collection = db.collection(collection_name)

        collection.find(query,options).toArray(function(err, docs) {
            assert.equal(err, null);
            callback(docs);
        })
    });


}

module.exports = find