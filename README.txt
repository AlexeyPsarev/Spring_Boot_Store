# Building and deploying the application

1. To build the application change your current directory to the store subdirectory and type:  

mvn package

2. To launch the application type:

java  -Dport=<port> -Dprofile=<profile> -jar target\store.war

If you don't specify a port and an active profile then the application will be run with default settings (port=8000 and profile=main).  

3. Open a browser and browse to http://localhost:<port> .
