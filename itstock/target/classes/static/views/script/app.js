let METHOD = { POST : "POST", GET: "GET", PUT: "PUT", DELETE : "DELETE"};
let app = angular.module("stockApp", [ "ngRoute", "ngMaterial", "ngMessages", "md.data.table" ]);
app.controller('mainController', function($scope, $mdSidenav, $location){
	$scope.toggleLeft = buildToggler('left');

    function buildToggler(componentId) {
      return function() {
        $mdSidenav(componentId).toggle();
      };
    }
    
    $scope.route = function(path){
    	$location.path(path);
    }
    
});
app.config(function($routeProvider) {
	$routeProvider.when("/", {
		templateUrl : "../views/asset.html",
		controller : "assetController"
	}).when("/user", {
		templateUrl : "users.html"
	}).when("/asset", {
		templateUrl : "../views/asset.html",
		controller : "assetController"
	}).when("/type", {
		templateUrl : "../views/type.html",
		controller : "typeController"
	}).when("/field", {
		templateUrl : "../views/field.html",
		controller : "fieldController"
	});
});
//app.service('util', function(){
//	this.padLeft = function(num, rep, len){
//		if(rep === undefined){
//			rep = '0';
//		}
//		
//		if(len === undefined){
//			len = 2;
//		}
//		num = num + '';
//		
//		var repCount = len - num.length;
//		var repValue = '';
//		for(var i = 0; i < repCount; i++){
//			repValue += rep;
//		}
//		return repValue + num;
//	}
//});
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

	this.saveField = function(field, callback){
		request("http://localhost:8080/fields", METHOD.POST, null, field, function(response){
			callback(response);
		});
	};
	
	this.getFields = function(callback){
		request("http://localhost:8080/fields", METHOD.GET, null, null, function(response){
			callback(response);
		});
	};
	
	this.getAllAssetType = function(onlylabel, callback){
		request("http://localhost:8080/assettype", METHOD.GET, {"onlylabel":onlylabel}, null, function(response){
			callback(response);
		})
	};
	
	this.getAssetType = function(assetTypeKey, callback){
		request(`http://localhost:8080/assettype/${assetTypeKey}`, METHOD.GET, null, null, function(response){
			callback(response);
		});
	};
	
//	this.saveAsset = function(asset, callback){
//		request("/assets", METHOD.POST, null, asset, function(response){
//			callback(response);
//		});
//	};
//	
//	this.getAssets = function(callback){
//		request("/assets", METHOD.GET, null, null, function(response){
//			callback(response);
//		});
//	};
//	
//	this.getAssetsByType = function(name, callback){
//		request(`/assets/${name}`, METHOD.GET, null, null, function(response){
//			callback(response);
//		});
//	};
//	
//	this.getAssetsForChild = function(callback){
//		request("/assets", METHOD.GET, {parent:false}, null, function(response){
//			callback(response);
//		});
//	}
//	
//	this.deleteAsset = function(name, callback){
//		request(`/assets/${name}`, METHOD.DELETE, null, null, function(response){
//			callback(response);
//		});
//	};
//	
//	this.getAssetTypeCount = function(type, callback){
//		request(`/assets/${type}/count`, METHOD.GET, null, null, function(response){
//			callback(response);
//		});
//	};
//	
//	this.saveAssetType = function(type, callback){
//		request("/assettype", METHOD.POST, null, type, function(response){
//			callback(response);
//		});
//	};
//	
//	this.getAllAssetType = function(callback){
//		request("/assettype", METHOD.GET, null, null, function(response){
//			callback(response);
//		});
//	};
	
});