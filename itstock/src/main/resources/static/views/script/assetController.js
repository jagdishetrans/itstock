app.controller('assetController', function($scope, http){
	
	$scope.assetTypeList = [
		"RAM",
		"MONITOR",
		"HARD DISK",
		"PROCESSOR",
		"KEYBOARD",
		"Field Test"
	];
	
	$scope.type = {};
	$scope.dummy = [];
	
	$scope.save = function(){
		console.log($scope.type.data);
		let saveObject = {};
		saveObject["type"] = $scope.type.data.type;
		saveObject["category"] = $scope.type.data.category;
		saveObject["fields"] = [];
		
		for(let index in $scope.type.data.fields){
			let object = $scope.type.data.fields[index];
			let fieldObject = {};
			fieldObject["name"] = object.name;
			fieldObject["value"] = object.value;
			saveObject.fields.push(fieldObject);
		}
		
		console.log(saveObject);
	}
	
	$scope.assetTypeData = [
		{"type":"RAM","category":"0","fields":[{"name":"SL No","type":{"name":"Numeric"},"isMandatory":true,},{"name":"Purchase Date","type":{"name":"DateTime","type":"date"},"isMandatory":true,},{"name":"Model","type":{"name":"Text"},"isMandatory":true,},{"name":"PR No","type":{"name":"Text"},},{"name":"Users","type":{"name":"Dropdown","type":"C","value":["Sachin","Sourav","Rahul","Dhoni","Kohli","Sehwag"]},"isMandatory":true,}]},
		{"type":"Field Test","category":"0","fields":[{"name":"Dropdown field","type":{"name":"Dropdown","type":"C","value":["DD1","DD2","DD3","DD4"]},"isMandatory":true,"$$hashKey":"object:51"},{"name":"Checklist field","type":{"name":"Checklist","type":"C","value":["CC1","CC2","CC3"]},"isMandatory":true,"$$hashKey":"object:58"},{"name":"Option field","type":{"name":"Option","type":"C","value":["OP1","OP2","OP3","OP4"]},"isMandatory":true,"$$hashKey":"object:66"},{"name":"Text field","type":{"name":"Text"},"isMandatory":true,"$$hashKey":"object:69"},{"name":"Numeric field","type":{"name":"Numeric"},"isMandatory":true,"$$hashKey":"object:72"},{"name":"Datetime field","type":{"name":"DateTime","type":"date"},"isMandatory":true,"$$hashKey":"object:75"}]}
	];
	
	$scope.selectedAssetType = function(index){
		var assetType = $scope.assetTypeList[index];
		for(let i = 0; i < $scope.assetTypeData.length; i++){
			if(assetType === $scope.assetTypeData[i].type){
				$scope.type.data = $scope.assetTypeData[i];
				return;
			}
		}
		$scope.type.data = {};
	};
	
});