# ePayment
ePayment is a repository of web application *Travelsafe* which represents one implementation of project task for *Electronic Payment Systems* course at Faculty of Techical Sciences, Univeristy of Novi Sad, 2016/2017. 
*Travelsafe* enables its users to buy travel insurances and pay them using [PayPal](https://www.paypal.com/). 

![img](http://i.imgur.com/rwv6zyv.jpg)

## Content
* [Stack](#stack)
* [How To Continue Developing application](#how-to-continue-developing-application)
* [How To Create a Deployable war File](#how-to-create-a-deployable-war-file)
* [Team](#team)
* [License](#license)



## Stack
#### Backend
* [MySql Database](https://www.mysql.com/)
* [Spring Boot](https://projects.spring.io/spring-boot/)
* [JBoss Drools](https://www.drools.org/)

#### Frontend
* [HTML and CSS](https://www.w3.org/standards/webdesign/htmlcss)
* [AngularJS 1.5](https://angularjs.org/)



## How To Continue Developing application
To start developing, you need to have installed [npm](https://www.npmjs.com/) and [bower](https://bower.io/).

#### 1) Install node modules
Navigate to path */travelsafe* and run:
```
npm install
```

#### 2) Install bower components
Navigate to path */travelsafe/src/main/webapp* and run:
```
bower install
```

#### 3) Set your database and email params
-Navigate to path */travelsafe/src/main/resources/application.properties* and set your database and email params
-Run MySql and create 'your_schema_name' schema

#### 4) Start developing
-Run TravelsaveApplication.java and app will be available at: https://localhost:8090/


## How To Create a Deployable war File

#### 1) Switch from Developer Mode to Production Mode
- In */travelsafe/pom.xml* include commented dependency 'spring-boot-starter-tomcat'

#### 2) Run script that creates .war file 
- In */travelsafe* double click build-war.bat file - That's it, war file will be in */travelsafe/target*.


     - Note 1
     
     'build-war.bat' does following:

            1.install node modules

            2.install bower conponents

            3.run gulp task that create new version of 'webapp' folder

            4.run mvn task for building war file (spring boot task)
          

## Team
* [Radomir Marinković](https://github.com/alohaaloha)
* [Dorian Čizmar](https://github.com/dorianciz)
* [Dražen Đanić](https://github.com/DrazenRocket)
* [Branislav Čogić](https://github.com/banecogic)


## License
MIT
