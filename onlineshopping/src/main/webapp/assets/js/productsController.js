var app = angular.module('ShoppingApp', []);

app.controller('ProductController', function($http) {
	
	var me = this;
		
	me.mvProducts = [];
	me.mpProducts = [];
	
	
	me.fetchProducts = function() {
		
		var jsonUrl1 = window.contextRoot + '/json/data/mv/products';
		var jsonUrl2 = window.contextRoot + '/json/data/mp/products';
		
		$http.get(jsonUrl1)
			.then(function(response) {
				me.mvProducts = response.data;
		});
			
			
		$http.get(jsonUrl2)
		.then(function(response) {
			me.mpProducts = response.data;
		});
				
	}
	
});