let METHOD = { POST : "POST", GET: "GET", PUT: "PUT", DELETE : "DELETE"};
let app = angular.module("stockApp", [ "ngRoute" ]);
app.config(function($routeProvider) {
	$routeProvider.when("/", {
		templateUrl : "../views/dashboard.html"
	}).when("/user", {
		templateUrl : "../views/users.html"
	}).when("/asset", {
		templateUrl : "../views/asset.html",
		controller : "assetController"
	}).when("/type", {
		templateUrl : "../views/type.html",
		controller : "typeController"
	});
});
app.service('http', function($http) {
	// http call method
	function request(rUrl, methodname, parameters, data, callback) {
		$http({
			url : rUrl,
			method : methodname,
			params : parameters,
			data : data
		}).then(function successCallback(response) {
			callback(response);
		}, function errorCallback(response) {
			callback(response);
		});
	}

	this.saveAsset = function(asset, callback){
		request("/assets", METHOD.POST, null, asset, function(response){
			callback(response);
		});
	};
	
	this.getAssets = function(callback){
		request("/assets", METHOD.GET, null, null, function(response){
			callback(response);
		});
	};
	
	this.deleteAsset = function(name, callback){
		request(`/assets/${name}`, METHOD.DELETE, null, null, function(response){
			callback(response);
		});
	}
	
});