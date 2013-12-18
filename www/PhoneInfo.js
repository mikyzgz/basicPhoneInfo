
cordova.define("com.miguelalonso.phonegap.PhoneInfo", function(require, exports, module){
		
	var PhoneInfo = function(numero, imei, imsi){
		this.numero = numero;
		this.imei = imei;
		this.imsi = imsi;
	}
	
	PhoneInfo.prototype.numero = null;
	PhoneInfo.prototype.imei = null;
	PhoneInfo.prototype.imsi = null;
	
	module.exports = PhoneInfo;
	
});