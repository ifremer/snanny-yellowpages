#!/bin/bash
clear
echo "---------Starting Script---------"
echo
java -DconfigFile=config.properties -cp EmsoToSensorNannyDraw-1.0-SNAPSHOT-jar-with-dependencies.jar $1
echo
echo "---------Finished Script---------"