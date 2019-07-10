app.controller('fieldController', function($scope, http){
	$scope.fieldType = [{"value":"text", "name":"Text"}, {"value":"number", "name":"Number"}, {"value":"currency", "name":"Currency"}, 
		{"value":"date", "name":"Date"}, {"value":"select", "name":"Select"}, {"value":"switch", "name":"Switch"}];

	$scope.fieldSubType = {
			"text": [{"value": "single", "name": "Singleline"}, {"value": "multi", "name": "Multiline"}],
			"number": [{"value": "decimal", "name":"Decimal"}, {"value":"float", "name":"Float"}],
			"currency":[{"value":"inr","name":"INR"}, {"value":"usd","name":"USD"}], 
			"date": [{"value":"datetime","name":"DateTime"}, {"value":"date","name":"Date (Only)"}, {"value":"time","name":"Time (Only)"}], 
			"select": [{"value":"single","name":"Single Choice"},{"value":"multi","name":"Multi Select"}],
			"switch": null};
	
	
	//$scope.fields = [{"name":"Model No", "type":"text","subType":"single"},{"name":"Model No", "type":"text","subType":"single"},{"name":"Model No", "type":"text","subType":"single"},{"name":"Model No", "type":"text","subType":"single"},{"name":"Model No", "type":"text","subType":"single"}];
	$scope.fields = [];
	$scope.listOptions = [];
	$scope.readonly = false;
	$scope.removable = true;
	
	
	$scope.submit = function(){
		var saveObj = {};
		saveObj["name"] = $scope.field.name;
		saveObj["type"] = $scope.field.type;
		if($scope.field.type === 'number'){
			if($scope.field.decimalPlaces == null || $scope.field.decimalPlaces === 0){
				$scope.field.subType = "decimal";
			}else{
				$scope.field.subType = "float";
			}
		}
		if($scope.field.type !== 'switch'){
			saveObj["subType"] = $scope.field.subType;
		}
		if($scope.field.type === 'select'){
			saveObj["options"] = $scope.listOptions;
		}
		if($scope.field.subType === 'float'){
			saveObj["options"] = $scope.field.decimalPlaces;
		}
		
		console.log(saveObj);
		http.saveField(saveObj, function(response){
			console.log(response);
			alert('Done');
		});
	};
	
	$scope.view = function(){
		if($scope.fields.length === 0){
			getFields();
		}
	}
	
	function getFields(){
		http.getFields(function(response){
			console.log(response);
			$scope.fields = response.data;
		});
	};
});