cordova.define("com.miguelalonso.phonegap.phoneinfoplugin", 
		
		function(require, exports, module){
			var exec = require('cordova/exec');
			var phoneInfo = require('./PhoneInfo');
			
			var PhoneInfoPlugin = function(){
				
			};
			
			/**
			 * returns PhoneInfo con el numero, imei y imsi
			 */
			PhoneInfoPlugin.prototype.obtenerInfo = function(success, fail){
				exec(function(jsonJava){
					var resultado = new PhoneInfo();
					resultado.imei = jsonJava.imei;
					resultado.numero = jsonJava.numero;
					resultado.imsi = jsonJava.imsi;
					success(resultado);
				}, fail, 'PhoneInfoPlugin', 'OBTENER_INFO_ACCION', []);
			}
			
			
			/*  Con este codigo queda más limpio y es suficiente, y aque lo anterior es explicativo
			 * 	pero en este caso es redundante y pondriamos este
				PhoneInfoPlugin.prototype.obtenerInfo = function(success, fail){
					exec(sucess, fail, 'PhoneInfoPlugin', []);
				}
			 */
			
			module.exports = new PhoneInfoPlugin();
		}
);