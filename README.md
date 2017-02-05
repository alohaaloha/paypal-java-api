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

### 3) Start developing


## How To Deploy
Before you deploy, you have to follow and do steps described in section [How To Develop](#how-to-develop).
### 1) Run gulp task that creates webapp (/dist) folder
Navigate to path */travelsafe* and run next command:
```
gulp build
```

### 2) Delete and rename appropriate folders
delete 'webapp' from travelsafe/src/main

rename 'dist' from travelsafe/src/main to 'webapp'

### 3) Deploy as .war or .jar
#### As .war
1. Do 3 steps marked in travelsafe/pom.xml
2. Navigate to path */travelsafe* and run next command:
```
mvn package
```
3. Generated .war file is in */travelsafe/target*

#### As .jar
1. Navigate to path */travelsafe* and run next command:
```
   mvn clean install
```
2. Generated .jar file is in */travelsafe/target*

## Team
* [Radomir Marinković](https://github.com/alohaaloha)
* [Dorian Čizmar](https://github.com/dorianciz)
* [Dražen Đanić](https://github.com/DrazenRocket)
* [Branislav Čogić](https://github.com/banecogic)


## License
MIT
