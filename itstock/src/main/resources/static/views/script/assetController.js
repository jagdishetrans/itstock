app.controller('assetController', function($scope, http, util) {

	$scope.type = {};
	$scope.loading = false;
	$scope.parentAsset = { "tag" : "Select" };

	
	
	$scope.include = 'VIEW';
	
	getAllAssetType();

	function getAllAssetType() {
		http.getAllAssetType(function(response) {
			$scope.assetTypeList = response.data;
		});
	}

	$scope.getAssets = function() {
		if ($scope.assets === undefined || $scope.assets.length === 0) {
			http.getAssetsForChild(function(response) {
				$scope.assets = response.data;
			});
		}
	};

	$scope.edit = function(index){
		$scope.include = 'FORM';
		http.getAssetsByType($scope.assets[index].type ,function(response){
			console.log(response);
			$scope.assets = response.data;
		});
	}
	
	$scope.add = function(index){
		$scope.include = 'FORM';
	}
	
	$scope.save = function() {
		$scope.loading = true;
		console.log($scope.type.data);
		let saveObject = {};
		saveObject["type"] = $scope.type.data.type;
		saveObject["category"] = $scope.type.data.category;
		saveObject["tag"] = $scope.type.data.tag;
		saveObject["fields"] = [];
		saveObject["child"] = [];
		saveObject["parent"] = null;
		
		for ( let index in $scope.type.data.fields) {
			let object = $scope.type.data.fields[index];
			let fieldObject = {};
			fieldObject["name"] = object.name;
			fieldObject["value"] = object.value;
			fieldObject["isMandatory"] = object.isMandatory;
			saveObject.fields.push(fieldObject);
		}

		if($scope.childAssetSelectShow){
			for(let index in $scope.assets){
				let asset = $scope.assets[index];
				if(asset.checked){
					saveObject.child.push(asset.id);
				}
			}
		}
		
		if($scope.parentAssetSelectShow){
			if($scope.parentAsset.tag !== "Select"){
				saveObject["parent"] = $scope.parentAsset.id;
			}
		}
		
		console.log(saveObject);
		http.saveAsset(saveObject, function(response) {
			console.log(response);
			if (response.state === 201) {
				for ( let index in $scope.type.data.fields) {
					let object = $scope.type.data.fields[index].value = null;
				}
			}
			$scope.loading = false;
		});
	}

	$scope.assetTypeData = [];

	$scope.selectedAssetType = function(index){
		$scope.include = 'VIEW';
		var assetType = $scope.assetTypeList[index];
		http.getAssetsByType(assetType.type ,function(response){
			console.log(response);
			$scope.assets = response.data;
		});
	};
	
//	$scope.selectedAssetType = function(index) {
//		var assetType = $scope.assetTypeList[index];
//		$scope.type.data = assetType;
//		
//		http.getAssetTypeCount(assetType.type, function(response){
//			generateId(response.data);
//		});
//	};

	$scope.selectParentAsset = function(index){
		if(index == -1){
			$scope.parentAsset = { "tag" : "Select" };
		}else{
			$scope.parentAsset = $scope.assets[index];
		}
	};
	
	$scope.showAssetDetails = function(index){
		if(index == -1){
			$scope.showAsset = $scope.parentAsset;
		}else{
			$scope.showAsset = $scope.assets[index];
		}
		$("#assetModal").modal('show');
	};
	
	$scope.removeChildAssetSelection = function(index){
		$scope.assets[index].checked = false;
	}
	
	function generateId(max) {
		max = util.padLeft(max+1, '0', 4);
		$scope.type.data.tag = `eTrans/IT/${$scope.type.data.type}/${max}`;
	}

	
});