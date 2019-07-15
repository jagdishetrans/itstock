app.controller('typeController', function($scope,  http) {
	$scope.fieldType = [{"value":"text", "name":"Text"}, {"value":"number", "name":"Number"}, {"value":"currency", "name":"Currency"}, 
		{"value":"date", "name":"Date"}, {"value":"select", "name":"Select"}, {"value":"switch", "name":"Switch"}];
	
    $scope.fields = [{"type": "text", "field":[{"name":"Model No", "key":"23aba2ed-b219-4861-a52a-7bc75bc91b19", "type":"text"},
    	{"name":"Text Field 1", "key":"23aba2ed-b219-4861-a52a-7bc75bc91b18", "type":"text"},
    	{"name":"Text Field 2", "key":"23aba2ed-b219-4861-a52a-7bc75bc91b17", "type":"text"},
    	{"name":"Text Field 3", "key":"23aba2ed-b219-4861-a52a-7bc75bc91b16", "type":"text"}]},
    	{"type": "select", "field":[{"name":"Brands","key":"23aba2ed-b219-4861-a52a-7bc75bc91b09", "type":"select", "options":["Samsung","Nokia","Sony"]}, 
    		{"name":"Select Field 1","key":"23aba2ed-b219-4861-a52a-7bc75bc91b08", "type":"select", "options":["Option11","Option12","Options13"] },
    		{"name":"Select Field 2","key":"23aba2ed-b219-4861-a52a-7bc75bc91b07", "type":"select", "options":["Option21","Option22","Option23"]},
    		{"name":"Select Field 3","key":"23aba2ed-b219-4861-a52a-7bc75bc91b06", "type":"select", "options":["Option31","Option32","Option33"]}]}]
	    
	$scope.selectedFields = [];
    $scope.selection = true;
    
    $scope.selectField = function(field){
    	$scope.selectedFields.push(field);
    };
    
    $scope.removeSelectedField = function(field){
    	for(var x in $scope.selectedFields){
    		if(field.key === $scope.selectedFields[x].key){
    			$scope.selectedFields.splice(x, 1);
    			break;
    		}
    	}
    };
    
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
    	
    	$scope.selectedFieldItem = type;
    	console.log($scope.selectedFieldItem);
    };
    
});