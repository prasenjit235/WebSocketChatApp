define(['jquery','underscore','backbone','handlebar','chatBoxManager','jqueryUI','scripts/common/WebsocketClient','scripts/common/CommonModel'],function($,_,Backbone,Handlebars,chatBoxManager,jqueryUI,connector,commonModel){
	var counter = 0;
	var idList = new Array();
	var chatBox={};
    var broadcastMessageCallback = function(to, msg) {
    	
        /*for(var i = 0; i < idList.length; i ++) {
        	chatBoxManager.addBox(idList[i]);
            $("#" + idList[i]).chatbox("option", "boxManager").addMsg(from, msg);
        }*/
    	var friend=[];
    	friend.push(to);
    	var connectorID=commonModel.get('connectorId');
    	var userName=commonModel.get('userName');
    	var friendToFriendReq={
				requestId:'FriendToFriend',
				connectorId:connectorID,
				userId:userName,
				friendList:friend,
				msgToFriend:msg
		}
    	connector.sendMsg(friendToFriendReq);
    	$("#" +to).chatbox("option", "boxManager").addMsg(userName, msg);
    }
    
	var chatroomView=Backbone.View.extend({
		initialize:function(){
			connector.registerListener('NotifyLoggedInUsers',this);
			connector.registerListener('FriendToFriendRes',this);
		},
		render:function(){
		var chatView=this;
		require(['text!../static/content/chatroom/chatroom.html'],function(chatroomTemplate){
			var compiledTemplate=Handlebars.compile(chatroomTemplate);
			var loginResponseObj=window.Map['loginResponse'];
			console.log("loginResponseObj::"+loginResponseObj);
			var html=compiledTemplate(loginResponseObj);
			chatView.$el.html(html);
			chatBoxManager.init({messageSent : broadcastMessageCallback});
			chatView.delegateEvents({"click a[data-friend-id]":"createChatBox"});
		});
		
		},
		createChatBox:function(event){
			var element;
			counter++;
			var id="chat_div"+counter;
			if (event) {
				element=event.srcElement;
				var friendId=$(element).attr('data-friend-id');
				console.log("friendId::"+friendId);
				id=friendId;
			}
			idList.push(id);
			var isChatExists=chatBox[id];
			var userName=commonModel.get('userName');
			if (userName!==id && !isChatExists) {
				chatBoxManager.addBox(id,{dest:"dest" + counter, // not used in demo
					title:"box" + counter,
					first_name:id,
					last_name:""
						//you can add your own options too
				});
				chatBox[id]=true;
			}
			event.preventDefault();
		},
		handleMsg:function(event){
			var chatView=this;
			console.log('@@@handleMsg###'+event);
			if (event.responseId==='FriendToFriendRes') {
				handleFriendToFriendRes(event);
			}else if (event.responseId==='NotifyLoggedInUsers') {
				handleNotifyLoggedInUsers(event,chatView);
			}
		}
	});
	
	function handleNotifyLoggedInUsers(resObj,chatView){
		chatView.undelegateEvents();
		require(['text!../static/content/chatroom/friendlist.html'],function(friendlistTemplate){
			var compiledTemplate=Handlebars.compile(friendlistTemplate);
			var html=compiledTemplate(resObj);
			chatView.$el.html(html);
			chatView.delegateEvents({"click a[data-friend-id]":"createChatBox"});
		});
	}
	
	function handleFriendToFriendRes(resObj){
		var isChatExists=chatBox[resObj.friendId];
		if (!isChatExists) {
			var id =resObj.friendId;
			chatBoxManager.addBox(id,{dest:"dest" , // not used in demo
				title:"box" ,
				first_name:id,
				last_name:""
					//you can add your own options too
			});
			chatBox[id]=true;
		}
		$("#" +resObj.friendId).chatbox("option", "boxManager").addMsg(resObj.friendId, resObj.msgFromFriend);
	}
	
	return chatroomView;
})