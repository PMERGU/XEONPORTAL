# PojectMobile

This application uses a spring-boot backend and an angularjs front end.
The stack allows for bi-directional binding between the backend to front end and enables browser-sync based development to improve development time.

## What we gonna use
* [Spring Boot](http://projects.spring.io/spring-boot/)
* [Node.js](https://nodejs.org/)
* [Bower](http://bower.io/)
* [Gulp](http://gulpjs.com/)
* [BrowserSync](http://www.browsersync.io/)
* [Karma](http://karma-runner.github.io/)
* [Jasmine](http://jasmine.github.io/2.0/introduction.html)
* [Protractor](https://angular.github.io/protractor/)

Before you can build this project, you must install and configure the following dependencies on your machine:

## Setup of all the components
1. Install [Java](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) JDK 1.8 (latest version) - run javac from terminal, if not found search online

2. Install [Maven](https://maven.apache.org/download.cgi) (add to env property) - run mvn from terminal, if not found search online

3. Setup project in IntelliJ. Read : 
    * [Building an Application with Spring Boot](https://spring.io/guides/gs/spring-boot/)
    * [Creating Spring Boot Projects](https://www.jetbrains.com/idea/help/creating-spring-boot-projects.html)
    * [Run/Debug Configuration: Spring Boot](https://www.jetbrains.com/idea/help/run-debug-configuration-spring-boot.html)
    
4. [Node.js](https://nodejs.org/): We use Node to run a development web server and build the project. Depending on your system, you can install Node either from source or as a pre-packaged bundle.

5. After installing Node, you should be able to run the following command to install development tools (like [Bower](http://bower.io/) and [BrowserSync](http://www.browsersync.io/)). You will only need to run this command when dependencies change in package.json.

        npm install

    We use [Gulp](http://gulpjs.com/) as our build system. Install the Gulp command-line tool globally with:

        npm install -g gulp

    Run the following commands in two separate terminals to create a blissful development experience where your browser
    auto-refreshes when files change on your hard drive.

        mvn
        gulp

    Bower is used to manage CSS and JavaScript dependencies used in this application. You can upgrade dependencies by
    specifying a newer version in `bower.json`. You can also run `bower update` and `bower install` to manage dependencies.
    Add the `-h` flag on any command to see how you can use it. For example, `bower update -h`.

# Running with the correct profile
This application support multiple profiles (needs fixes due to os depedencies and sap libs) in a 3 dimensional state. ie [dev,uat,prod] x [win,mac,linux]

    mvn spring-boot:run #works currently
    mvn spring-boot:run -Drun.profiles=dev.win #need to fix this
    
The application will load the correct native SAP (dll, so, jnilib) and environment variables needed to run against specific profile.

# Testing services
We use postman for all service call testing. import this link [xeon collection](https://www.getpostman.com/collections/3cc698791fe3c38f33f4)

# Building for production

    In future
        
# Learning

### Yeoman
* Short [video](http://www.youtube.com/watch?v=zBt2g9ekiug&feature=c4-overview&list=UUfetJpmQH2XpFj8uFgWsezw)

### Grunt
* Short [video](http://www.youtube.com/watch?v=q3Sqljpr-Vc)
* Long [video](http://www.youtube.com/watch?v=bntNYzCrzvE)

### Bower
* Short [video](http://www.youtube.com/watch?v=HHNf512zM-M)
* Long [video](http://www.youtube.com/watch?v=o9Xo_WFAyqg)

### Angular
Start at lession 1....for [every video you will ever need](http://egghead.io/lessons)

### Angular and yeoman combined
* [Yeoman, Angular combined](http://www.youtube.com/watch?v=V_x14_62m3Q)
