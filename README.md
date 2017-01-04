# ePayment

About:
Project for Electronic Payment Systems course at Faculty of Technical Sciences, University of Novi Sad, 2016/2017

Team:
Radomir Marinkovic, Dorian Cizmar, Drazen Flight, Branislav Cogic(850e)

Stack:
Spring + AngularJS 1.5


How To Develop:

1) install node modules
path: /travelsave
npm install

2) install bower components
path: /travelsafe/src/main/webapp
bower install

3) start developing


How To Deploy:

1) & 2)

3) gulp task that creates webapp for production
path: /travelsafe
gulp build

4) //TODO - make task that does this
delete 'webapp' from travelsafe/src/main
rename 'dist' from travelsafe/src/main to 'webapp'

5) deploy as war or jar

-To make war
1. do 3 steps marked in travelsafe/pom.xml
2. path: travelsafe/
   mvn package
3. generated war is in travelsafe/target

-To make jar
1. path: travelsafe/
   mvn clean install
2. generated jar is in travelsafe/target



