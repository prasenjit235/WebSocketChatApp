Websocket Chatting App
-----------------------

This App is developed as part of 'Websocket with eclipse jetty' discussion covered in my <a href="http://prasenjitdas235.blogspot.in/">blog</a>. The main focus was to develop server side frame work which can support any client with websocket implementation. At present I have developed browser based client with HTML5 websocket which is still under development and very frajile but still can be used to get you started.

STEPS TO DEPLOY
----------------

1) Download eclipse jetty from <a href="http://www.eclipse.org/jetty/">here</a>. I am using version 8.1.12.v20130726.
2) Download following jars as well for logging. <a href="http://repo1.maven.org/maven2/org/slf4j/slf4j-log4j12/1.6.6/slf4j-log4j12-1.6.6.jar">slf4j-log4j12-1.6.6.jar</a> ,
<a href="http://repo1.maven.org/maven2/org/slf4j/slf4j-api/1.6.6/slf4j-api-1.6.6.jar">slf4j-api-1.6.6.jar</a> , <a href="http://repo1.maven.org/maven2/log4j/log4j/1.2.17/log4j-1.2.17.jar">log4j-1.2.17.jar</a> .
3) The app is a WebApp , so directory structure should be followed (war file can be creted out of it or below deplyoment structure can be followed.). Below can be used :

 Under webapps directory of ${jetty.home}:
 
  WebSocketChatApp ---|
                      |--App
                      |--WEB-INF--|
                                  |--classes
                                  |--lib
                                  |--applicationContext.xml
                                  |--web.xml
                                  
4) For logging create a ${jetty.home}/lib/logging/ directory and place the three JAR files in step 2. Next you can follow this <a href="http://www.eclipse.org/jetty/documentation/current/example-logging-log4j.html">tutorial</a>. 

5) Then start the server . Go to ${jetty.home} , use command : java -jar start.jar 

6) Launch the APP from browser. http://localhost:8080/WebSocketChatApp/index.html#login . 


  
  
  
                      
