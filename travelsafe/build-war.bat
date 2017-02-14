ECHO ...
ECHO "*****************************"
ECHO "installing node modules..."
ECHO "*****************************"
call npm install
ECHO "*****************************"
ECHO "done"
ECHO "*****************************"

cd src/main/webapp

ECHO "*****************************"
ECHO "installing bower components"
ECHO "*****************************"
call bower install
ECHO "*****************************"
ECHO "done"
ECHO "*****************************"

cd..
cd..
cd..

ECHO "*****************************"
ECHO "running 'build' task"
ECHO "*****************************"
call gulp prod
ECHO "*****************************"
ECHO "done"
ECHO "*****************************"

call mvn clean package

pause
