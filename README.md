# wifi-info-client

##Build
mvn clean && install
mvn package

##Run
export JAVA_OPTS=-Xmx1024m -XX:MaxPermSize=128M
cd Wifi-Info-Client-App
mvn spring-boot:run
