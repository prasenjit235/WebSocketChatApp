define(['scripts/common/CommonModel'],function(commonModel){
	var connectionObj=null;
	var connectorCache={};
	var listeners={};
	start=function(wsURI){
		if (connectionObj==null) {
			connectionObj=new WebSocket(wsURI);
		}
		connectionObj.onopen=function(event){handleOpen(event);};
		connectionObj.onclose=function(event){handleClose(event);};
		connectionObj.onmessage=function(event){handleMessage(event);};
		connectionObj.onerror=function(event){handleError(event);};
	}
	
	function handleOpen(event) {
		console.log('Connection Opened!!');
	}
	
	function handleClose(event) {
		console.log('Connection Closed!!');
		clearInterval(timerTask);
		connectionObj=null;
	}
	
	function handleMessage(event) {
		console.log('handleMessage::'+event.data);
		var msgObj=JSON.parse(event.data);
		if (msgObj.responseId==='ConnectInit') {
			handleConnectionInit(msgObj);
		}else if (msgObj.responseId==='HandShakeRes') {
			handleHandShake(msgObj);
		}else if (msgObj.responseId==='Pong') {
			handlePONG(msgObj);
		}else{
			dispatchToListener(msgObj);
		}
		
	}
	
	function handleConnectionInit(msgObj){
		connectorCache['connectorId']=msgObj.connectorId;
		commonModel.set('connectorId',msgObj.connectorId);
		var handShakeReq={
				requestId:'HandShakeReq',
				connectorId:connectorCache['connectorId'],
				userId:'',
				friendList:[]
		}
		connectionObj.send(JSON.stringify(handShakeReq));
	}
	
	var timerTask;
	
	function handleHandShake(msgObj){
		timerTask=setInterval(function() {
			sendPING();
		},60000);
	}
	
	function sendPING() {
		var connectorId=connectorCache['connectorId'];
		var pingReq={
				requestId:'Ping',
				connectorId:connectorCache['connectorId'],
				userId:'',
				friendList:[]
		}
		connectionObj.send(JSON.stringify(pingReq));
	}
	
	function handlePONG(msgObj){
		//console.log('Yet To Handle!!');
	}
	
	function dispatchToListener(msgObj) {
		if (listeners && listeners[msgObj.responseId]) {
			listeners[msgObj.responseId].handleMsg(msgObj);
		}
	}
	
	function handleError(event) {
		console.log('Connection Opened!!');
	}
	
	registerListener=function(serialID,handler){
		listeners[serialID]=handler;
	}
	
	sendMsg=function(reqObj){
		connectionObj.send(JSON.stringify(reqObj));
	}
	
	return {
		
		start:start,
		registerListener:registerListener,
		sendMsg:sendMsg
	}
})