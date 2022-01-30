var mongoose = require('mongoose');
var Schema = mongoose.Schema;

// Define collection and schema for Items
var Item = new Schema({ websiteUrl: { type: String }, password: { type: String } } , { collection: 'items' });

module.exports = mongoose.model('Item', Item);