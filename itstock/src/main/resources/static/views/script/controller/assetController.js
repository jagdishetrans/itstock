app.controller('assetController', function($scope, http) {

	$scope.assetTypeLabels = [{"key":"c72fda75-4004-4bea-a071-60b37e2467bb","name":"Monitor","category":0,"hasParent":true,"hasChild":false,"createdOn":"2019-07-12T23:14:45.964"},{"key":"ab57febb-513f-4db5-b1ae-4ca85ac6c9b6","name":"Desktop","category":0,"hasParent":false,"hasChild":true,"createdOn":"2019-07-12T23:18:37.138"}]
	
	$scope.assetTypeObject = {"c72fda75-4004-4bea-a071-60b37e2467bb":{"key":"c72fda75-4004-4bea-a071-60b37e2467bb","name":"Monitor","category":0,"fields":[{"key":"c3d30ab7-b9dd-43f2-bb67-6053d6ec0bef","name":"Model","type":"text","subType":"single","mandatory":false,"placeholder":"Enter model"},{"key":"1020f2b6-8eda-4e68-a632-10ac170de235","name":"Purchase Date","type":"date","subType":"date","mandatory":true,"placeholder":"Enter Purchase Date"},{"key":"914c0bd2-0b15-4ba4-b291-6f4f59d8e616","name":"Invoice No","type":"text","subType":"single","mandatory":true,"placeholder":"Enter invoice no"},{"key":"4da30fcc-da85-48dc-b607-9f9ed2829af9","name":"Dimension","type":"measurement","subType":"dimension","mandatory":true,"placeholder":"Enter dimension","options":"inch"},{"key":"7f2bbefe-5d41-4e16-9a9d-acf361b72d93","name":"Description","type":"text","subType":"multi","mandatory":false,"placeholder":"Enter description"}],"hasParent":true,"hasChild":false,"createdOn":"2019-07-12T23:14:45.964"},"ab57febb-513f-4db5-b1ae-4ca85ac6c9b6":{"key":"ab57febb-513f-4db5-b1ae-4ca85ac6c9b6","name":"Desktop","category":0,"fields":[{"key":"1020f2b6-8eda-4e68-a632-10ac170de235","name":"Purchase Date","type":"date","subType":"date","mandatory":true,"placeholder":"Enter Purchase Date"},{"key":"914c0bd2-0b15-4ba4-b291-6f4f59d8e616","name":"Invoice No","type":"text","subType":"single","mandatory":true,"placeholder":"Enter invoice no"},{"key":"7f2bbefe-5d41-4e16-9a9d-acf361b72d93","name":"Description","type":"text","subType":"multi","mandatory":false,"placeholder":"Enter description"},{"key":"b2555588-6557-4ea2-ba64-dc60a9f2fbcd","name":"Department","type":"select","subType":"single","defaultValue":2,"mandatory":true,"placeholder":"Select department","options":["RND","CSD","IT","HR"]},{"key":"239b1032-6163-4150-b9bb-08da00c412ab","name":"Location","type":"select","subType":"single","defaultValue":0,"mandatory":true,"placeholder":"Select location","options":["KOLKATA","JAMSHEDPUR","JAJPUR","WEST BOKARO"]}],"hasParent":false,"hasChild":true,"createdOn":"2019-07-12T23:18:37.138"}};
	
	http.getAllAssetType(true,function(response){
		console.log(response);
	});
	
	http.getAssetType("", function(response){
		console.log(response);
	});
	
	 $scope.showSelectedType =function(index, type){
	    	if ($scope.selectedIndex === null) {
	    	      $scope.selectedIndex = index;
		    }
		    else if ($scope.selectedIndex === index) {
		      $scope.selectedIndex = null;
		    }
		    else {
		      $scope.selectedIndex = index;
		    }
	    	
	    	$scope.selectedAssetType = $scope.assetTypeObject[type.key];
	    	console.log($scope.selectedAssetType);
	  };
	
});