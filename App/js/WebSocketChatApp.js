requirejs.config({
	
	paths:{
		jquery: 'lib/jquery/jquery-1.7.1',
		jqueryUI:'lib/jquery/jquery-ui-1.8.23.custom.min',
		underscore:'lib/underscore/underscore-min',
		backbone:'lib/backbone/backbone-min',
		handlebar:'lib/handlebar/Handlebars',
		text:'lib/text/text',
		chatWidget:'lib/plugins/jqueryChatBox/jquery.ui.chatbox',
		chatBoxManager:'lib/plugins/jqueryChatBox/ChatBoxManager'
	}	
});

require(['scripts/BootStrap'],function(BootStrap){
	//alert('App loaded');
	console.log('BootStrap loaded!!');
	BootStrap.initialize();
})