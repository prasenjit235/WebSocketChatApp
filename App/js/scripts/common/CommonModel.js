define(['jquery','underscore','backbone','handlebar'],function($,_,Backbone,Handlebars){
	var appModel=Backbone.Model.extend({
	    initialize: function() {
	        this.set("connectorId", null);
	    }
	});
	
	var connectorModel=new appModel();
	var set=function(attr,value){
		connectorModel.set(attr,value);
	}

	var get=function(attr){
		return connectorModel.get(attr);
	}
	
	return {
		set:set,
		get:get
	};
})