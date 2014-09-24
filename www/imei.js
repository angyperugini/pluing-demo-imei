cordova.define("com.equipnet.imei.imei", function(require, exports, module) { var cordova = require('cordova');

var imei = {
	getImeiNumber : function(successCallback, errorCallback){
	cordova.exec(successCallback, errorCallback,'ImeiPlugin', 'getImeiNumber', []);//ImeiPlugin ->stringa che identifica 											      //l'oggetto e il rispettivo metodo richiamato
} 											//[] e relativi eventuali parametri
};

module.exports = imei;

});
