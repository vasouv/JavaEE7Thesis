#Web Tutoring Site JavaEE 7#

This is my JavaEE 7 thesis project. It resembles a web tutoring site like Udemy or Udacity.

A user can create an account, buy courses and watch videos for each of the course's lectures. Administrators can view all registered users in the DB, create new users and delete existing ones, along with creating new courses and adding lecture material.

The project obviously doesn't meet any quality standards and isn't up to par with proper applications (for example, no SQL injection is prevented).

##Technologies Used##
- **Java 8** - Download JDK 1.8.0_u25 from [here](http://www.oracle.com/technetwork/java/javase/downloads/index-jsp-138363.html).
- **JavaDB 10.10.2.0** - Comes pre-installed with the JDK.
- **JavaEE 7** - Comes pre-installed with Glassfish.
- **Glassfish 4.1 Full** - See How to set up the project
- **Netbeans 8.0.2** - Download EE version from [here](https://netbeans.org/downloads/)

##How to set up the project##
1. Default installation for all mentioned software.
2. Download and unzip my pre-configured Glassfish version and place it in C:
3. Download and unzip my database in C:\Users\user\netbeans-derby
4. Download a copy of this project and unzip it in NetbeansProjects.
5. Make sure that Netbeans has properly registered Glassfish and JavaDB in the Services window.
6. Start Glassfish. If JavaDB asks for username and password, it's 'thesis' and '1234' respectively.
7. Build the project so it downloads all Maven dependencies.
8. Deploy and have fun!

##Problems##
So far, I haven't noticed any problems with my mentioned set up. I have successfully deployed the project on another computer and it works. If you run into any issues, tough luck, I don't know how to fix it. If you know how to fix it though, please see Contributing.

##Contributing##
Any suggestions regarding the code or my methodology, are more than welcome. Also, if there are better options on how to distribute and test my project on other computers, I'd be glad to hear them.

##License##
This is my own work. If I use someone else's code as-is either for temporary or permanent use, I will explicitly say so. Likewise if you use my code, you are required to mention me as the source.

Glassfish license can be found [here](https://glassfish.java.net/license.html). I have not modified the server's source code in any way. My pre-configured version simply has a JDBC realm and connection pools, I'm distributing my version simply for ease of use.
