# MoviesWebChat
Spring based Web site with CrudRepository support and Spring Security , running on Tomcat 8 with Maven build with a WebSockets based chat room

This is a demonstration project on Spring development. It involves:
MySQL as a database with Spring CrudRepository support 
Eclipse Mars with Maven plugin as an IDE
Tomcat 8 as a container

moviesDBChat.sql was used to generate the MySql database used in this project.

This project can run on Tomcat 8 from Eclipse or on Tomcat 8 standalone from a .war file. It requires Java 8 Runtime Environment.

This is the 5th version of this project. More stuff is coming soon with a version that implements a chat room.

For the basic JDBC version of this project see the repository:

https://github.com/dubersfeld/MoviesWebJDBC

For the JPQL version of this project see the repository:

https://github.com/dubersfeld/MoviesWebJPQL

For the Criteria version of this project see the repository:

https://github.com/dubersfeld/MoviesWebCriteria

For the CrudRepository version of this project see the repository:

https://github.com/dubersfeld/MoviesWebCrud

For the Spring Security version of this project see the repository:

https://github.com/dubersfeld/MoviesWebSecurity

For a full tutorial about this project please visit my personal site:

http://www.dominique-ubersfeld.com/JAVADEV/SpringDevelopment.html

As a reference book I mainly used Java for Web Applications by Nicholas S. Williams

Note: this project was run on my home computer. To run it on your system you have to edit some files to customize them to your actual file system. They are:

source/production/resources/install.properties: photoTempDir=/home/dominique/Pictures/tmp/

source/production/resources/log4j2.xml: &lt;RollingFile name="WroxFileAppender" fileName="/home/dominique/logs/application.log"
                                     filePattern="/home/dominique/logs/application-%d{MM-dd-yyyy}-%i.log"&gt;

where the folder /home/dominique should be replaced by a folder that matches your own file system.

Note on authorities:

I have predefined 5 authorities: VIEW, CREATE, UPDATE, DELETE, CHAT

The source file moviesDBSecu.sql declares 5 users with different authorities and passwords listed below:

Username        Password        Authorities

Carol           s1a2t3o4r       VIEW, CHAT

Albert          a5r6e7p8o       VIEW, CHAT

Werner          t4e3n2e1t       VIEW, CHAT

Alice           o8p7e6r5a       VIEW, CREATE, UPDATE, CHAT

Richard         r1o2t3a4s       VIEW, CREATE, UPDATE, DELETE, CHAT

Moreover any new user can register and be granted the only VIEW and CHAT authorities. All menus are customized to display only the requests that are allowed to the actual user.

Note that a given user is only allowed a single connection to a given chatroom, i.e. he cannot connect at the same time from two different IPs such as a desktop and a smartphone.


Dominique Ubersfeld 
