cordova.define("com.plugin.deviceimei", function(require, exports, module) { 

var argscheck = require('cordova/argscheck'),
    channel = require('cordova/channel'),
    utils = require('cordova/utils'),
    exec = require('cordova/exec'),
    cordova = require('cordova');
    
    
    channel.createSticky('onCordovaInfoReady');
    
// accesso al canale nel caso di onCordovainfoReady

	channel.waitForInitialization('onCordovaInfoReady');

	/**
    * rappresentazione del dispositivo e costruttore.
    * @constructor
    */
    
function DeviceImei() {
    
    this.imei= null;
    
    
    channel.onCordovaReady.subscribe(function() {
        me.getInfo(function(info) {
           
	    me.imei = info.imei;
	    
            channel.onCordovaInfoReady.fire();
        },function(e) {
            me.available = false;
            utils.alert("[ERROR] Error initializing Cordova: " + e);
        });
    });
}

/**
 * accesso alle informazioni
 */
Device.prototype.getInfo = function(successCallback, errorCallback) {
    argscheck.checkArgs('fF', 'DeviceImei.getInfo', arguments);
    exec(successCallback, errorCallback, "DeviceImei", "getDeviceImei", []);
};

module.exports = new DeviceImei();

});
    
    
