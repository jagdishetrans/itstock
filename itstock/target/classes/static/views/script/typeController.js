app.controller('typeController', function($scope, http) {

	/*$scope.assetList = [
		"RAM",
		"MONITOR",
		"HARD DISK",
		"PROCESSOR",
		"KEYBOARD"
	];*/
	
	$scope.fields = [];
	$scope.asset = {};
	$scope.field = {};
	$scope.ddType = {};
	
	$scope.loading = false;

	$scope.addCustomDDValues = function(){
		var value = $scope.ddType.customDDValue;
		if($scope.field.type.value === undefined){
			$scope.field.type.value = [];
		}
		if($scope.field.type.value.indexOf(value) === -1){
			$scope.field.type.value.push(value);			
		}
		$scope.ddType.customDDValue = "";
	};
	
	$scope.removeCustomDDValues = function(index){
		$scope.field.type.value.splice(index, 1);
	};
	
	$scope.addFields = function(){
		var field = $scope.field;
		$scope.fields.push(field);
		$scope.field = {};
	};
	
	getAllAssetType();
	
	function getAllAssetType(){
		http.getAllAssetType(function(response){
			$scope.assetList = response.data;
		});
	}
	
	
	$scope.save = function(){
		$scope.asset.fields = $scope.fields;
		$scope.loading = true;
		http.saveAssetType($scope.asset, function(response){
			console.log(response);
			if(response.status === 201){
				$scope.fields = [];
				$scope.asset = {};
				$scope.field = {};
				$scope.ddType = {};
				getAllAssetType();
			}
			$scope.loading = false;
		});
		console.log($scope.asset);
	};
});