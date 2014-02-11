define( ['jquery',  'underscore', 'backbone', 'handlebar',
		'scripts/login/LoginView','scripts/chatroom/ChatroomView','scripts/common/WebsocketClient'  ], function($, _,
		Backbone, Handlebars, LoginView,ChatroomView,connector) {

	/*
	 * alert('module loaded !!');
	 * require(['text!../static/content/login/login.html'],function(loginTemplate){
	 * var compiledTemplate=Handlebars.compile(loginTemplate); var
	 * html=compiledTemplate({}); $('#appContainer').html(html); });
	 */
	var views={};
	var AppRouter = Backbone.Router.extend( {
		routes : {

			'login' : 'login',
			'chatroom' : 'chatroom',

			'*actions' : 'defaultAction'
		}
	});

	var initialize = function() {
		var app_router = new AppRouter();
		
		app_router.on('route:login', function() {
			if (views['loginView']) {
				views['loginView'].close();
			}
			var loginView = new LoginView({el:'#appContainer'});
			views['loginView']=loginView;
			loginView.render();
		});

		app_router.on('route:chatroom', function() {
			var chatroomView = new ChatroomView({el:'#appContainer'});
			chatroomView.render();
		});
		app_router.on('route:defaultAction', function(actions) {

			console.log('No route:', actions);
		});
		
		$(document).on('routeEvent',function(event,responseObj){
			window.Map['loginResponse']=responseObj;
			app_router.navigate('chatroom',{trigger: true});
		})
		Backbone.history.start();
		connector.start('ws://10.8.160.246:8080/WebSocketChatApp/samplewebsocketchat');
	};
	
	window.Map={};
	
	return {

		initialize : initialize
	}
})