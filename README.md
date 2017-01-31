# ePayment


## About:
Project for Electronic Payment Systems course at Faculty of Technical Sciences, University of Novi Sad, 2016/2017


## Team:
Radomir Marinković, Dorian Čizmar, Dražen Đanić, Branislav Čogić


## Stack:
Spring Boot with Java 1.8 + AngularJS 1.5


## How To Develop:

### 1) install node modules
path: /travelsave
```
npm install
```

### 2) install bower components
path: /travelsafe/src/main/webapp
```
bower install
```

### 3) start developing


## How To Deploy:

### 1) & 2) from 'How To Develop' section

### 3) run gulp task that creates webapp (/dist) folder
path: /travelsafe
```
gulp build
```

### 4)
delete 'webapp' from travelsafe/src/main

rename 'dist' from travelsafe/src/main to 'webapp'

### 5) deploy as war or jar

#### As war
1. do 3 steps marked in travelsafe/pom.xml
2. path: travelsafe/
   ```
   mvn package
   ```
3. generated war is in travelsafe/target

#### As jar
1. path: travelsafe/
   ```
   mvn clean install
   ```
2. generated jar is in travelsafe/target



