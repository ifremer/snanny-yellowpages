# snanny-yellowpages

Extract from EMSO yellow pages database (http://www.esonetyellowpages.com/), the hardware and sensor description in sensorML v2.0

#Installation :
1- run in command line `mvn clean install` ( JAVA 8 required )

2- move jar EmsoToSensorNannyDraw-1.0-SNAPSHOT-jar-with-dependencies in parent directory

3- Configurate config.properties

Field  | Description
------------- | -------------
db_URL | URL to Emso Yellow Pages Mysql Database
db_User  | DataBase Administrator Username
db_Pass  | DataBase Administrator Password
modelsDestPath  | Destination Path for JSON Files to export from Emso Yellow pages DataBase
modelsSrcPath  | Source Path for JSON files to export to SensorML
sensorMLDestPath | Destination Path for SensorML exporting
typesJSDestPath | Destination Path for Esonet types in JavaScript format
imagesSrcPath  | Source Path for Images Aspired from Emsonet Yellow Pages website
imagesDestPath  | Destination Path for the renamed images 



4- run in command line `run ClassName` for Windows or ./run ClassName for Linux
    

ClassName  |Functionnality
------------- | -------------
MysqlToJSON  | Export Data from Esonet DB to JSON
JSONToSensorML  | Generate SensorML description from JSON Files previously exported with MysqlToJSON
JSTypeGeneration  | Generate JavaScript Files required by SensorNannyDraw to use Esonet Models
RenameImages  | Rename Images aspired from Esonet website to work with SensorNannyDraw


Note that one need to aspire sensor's or hardware's images separtelly and priori to run the command. It can be quickly done with wget:

wget -r --no-parent https://www.esonetyellowpages.com/img/sensors/
