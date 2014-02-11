define(['jquery','underscore','backbone','handlebar','scripts/common/CommonModel'],function($,_,Backbone,Handlebars,commonModel){
	
	var loginView=Backbone.View.extend({
		initialize:function(){
			console.log("loginView::initialize");
			this.model=new loginModel();
			this.render();
		},
		render:function(){
		var loginview=this;	
		require(['text!../static/content/login/login.html'],function(loginTemplate){
			var compiledTemplate=Handlebars.compile(loginTemplate);
			var html=compiledTemplate({});
			loginview.$el.html(html);
			//loginview.delegateEvents({"click #login":"doLogin"});
		});
		},
		events:{
			"click #login":"doLogin"
		},
		doLogin:function(){
			
			var userName=$('#username').val();
			var pwd=$('#password').val();
			var connectorID=commonModel.get('connectorId');
			console.log('@@@connectorId:::'+connectorID);
			this.model.set({username:userName,password:pwd,connectorId:connectorID});
			this.model.save(this.model.toJSON(),{
				success:function(model,response){
					if (response.status===0) {
						commonModel.set('userName',userName);
						$(document).trigger("routeEvent",[response]);
					}else{
						$('#statusMsg').html('<p>Username or Password incorrect.</p>')
					}
					model.clear();
				},
				error:function(parama1){
					alert('Error');
				}
			});
			
		},
		close:function(){
			this.remove();
			this.unbind();
		}
	})
	var loginModel=Backbone.Model.extend({
		urlRoot: "rest/login",
		defaults:{
			"username":"SampleUser",
			"password":"password"
		}
	});
	return loginView;
})