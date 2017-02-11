# ePayment
ePayment is a repository of web application *Travelsafe* which represents one implementation of project task for *Electronic Payment Systems* course at Faculty of Techical Sciences, Univeristy of Novi Sad, 2016/2017. 
*Travelsafe* enables its users to buy travel insurances and pay them using [PayPal](https://www.paypal.com/). 

## Content
* [Stack](#stack)
* [How To Develop](#how-to-develop)
* [How To Deploy](#how-to-deploy)
* [Team](#team)
* [License](#license)


## Stack
### Backend
* [MySql Database](https://www.mysql.com/)
* [Spring Boot](https://projects.spring.io/spring-boot/)
* [JBoss Drools](https://www.drools.org/)

### Frontend
* [HTML and CSS](https://www.w3.org/standards/webdesign/htmlcss)
* [AngularJS 1.5](https://angularjs.org/)



## How To Develop
To start developing, you need to have installed [npm](https://www.npmjs.com/) and [bower](https://bower.io/). 
### 1) Install node modules
Navigate to path */travelsafe* and run next command:
```
npm install
```

### 2) Install bower components
Navigate to path */travelsafe/src/main/webapp* and run next command:
```
bower install
```

### 3) Set your database and email params
-Navigate to path */travelsafe/src/main/resources/application.properties and set your DB and email params
-Run MySQL
-Create 'travelsafe' schema

### 4) Start developing



## How To Deploy
Before you deploy, you have to do steps described in section [How To Develop](#how-to-develop). After that do next steps:

### 1) Run gulp task that creates 'webapp' folder for production (this will delete current webapp folder)
Navigate to path */travelsafe* and run next command:
```
gulp prod
```

### 2) Deploy as .war or .jar
#### As .war
1. In */travelsafe/pom.xml*
    STEP 01: choose 'war' (place in code marked as STEP 01)
    STEP 02: include dependency (place in code marked s STEP 02)
2. Navigate to path */travelsafe* and run next command:
```
mvn package
```
-> Generated .war file is in */travelsafe/target*

#### As .jar
1. In */travelsafe/pom.xml*
    STEP 01: choose 'jar' (place in code marked as STEP01
    STEP 02: include dependency (place in code marked as STEP 02)
2. Navigate to path */travelsafe* and run next command:
```
   mvn clean install
```
-> Generated .jar file is in */travelsafe/target*

## Team
* [Radomir Marinković](https://github.com/alohaaloha)
* [Dorian Čizmar](https://github.com/dorianciz)
* [Dražen Đanić](https://github.com/DrazenRocket)
* [Branislav Čogić](https://github.com/banecogic)


## License
MIT
