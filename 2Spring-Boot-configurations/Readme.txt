This Project Consists of Following Topics -

1. Actuator
Dependency -
        <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
			<version>3.4.2</version>
		</dependency>
-It Automatically exposes endpoints to monitor and manage your application
-With Actuator you can easily get Dev-Ops functionality out-of-the-box
-Below 2 Lines in resources>application.properties is opening the actuator endpoints -
    management.endpoints.web.exposure.include=health,info
    management.info.env.enabled=true
-After this try to access -
    http://localhost:8080/actuator/health
    or
    http://localhost:8080/actuator/info
-If you want to make all the actuator points enabled then use -
    management.endpoints.web.exposure.include=*
    management.info.env.enabled=true
-You can also ssecure the endpoints to all by using the below lines -
    management.endpoints.web.exposure.include=*
    management.info.env.enabled=true
    management.endpoints.web.exposure.exclude=health, info
-By this all the endpoints are open except health,info.

2. Spring Boot Actuator Security
Dependency -
        <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
			<version>3.4.2</version>
		</dependency>
-If no username and password is set in the application.properties then default user is - "user" and password will be the password which you will see in the console when you run the application. Something like this - "f2ce8f32-7936-49c0-af3e-d523d1012c07"
-I have set the following username and password in the application.properties -
        spring.security.user.name=honey
        spring.security.user.password=honey123
-Pass the credentials to access any links like -
        http://localhost:8080/actuator/info

3. To make app CLI-App -
-Step1 - Set the Environment Variable in system variable as-
    Variable Name   = JAVA_HOME
    Variable Value  = C:\Program Files\Java\jdk-21

    And in Path the Below entry should be present -->
    Path --->  %JAVA_HOME%\bin
-Step2 - In cmd , go to the path where your project folder is present. My folder was present at - Note - In the below folder mvnw.cmd file is present as provided by start.spring.io when downloading the demo project from start.spring.io website
        cd C:\Users\ACNusrHoneyP\Desktop\springForGithub\2spring-boot-configurations-crud\2Spring-Boot-configurations
-Step3 - Type the below command in cmd at the above folder location to package it -
        mvnw package
-Step4 - You will get the Build Success output in cmd. And the build will be saved in the "target" folder with the name of the project - "configurations-0.0.1-SNAPSHOT.jar"
-Step5 - Now rn this jar in new cmd using -
        java -jar configurations-0.0.1-SNAPSHOT.jar
-so now the whole application is in one package, you can move this on any system and run the jar from cmd.
-Step6 - use CTRL+C to stop the application in cmd.

4. Injecting Custom Application Properties
-It just means you do not need to hard code evey variable inside the code. Declare the variable in some properties file like - application.properties and call them from there inside the codes.
-Declare the below variables in the application.properties
    #Injecting Custom Application Properties
    teacher.name=Huang Sun
    teacher.qualification=Senior Software Developers [Spring Boot]
    teacher.location=China
-And in configurationscontroller call them using
    @Value("${teacher.name}")
-Try to access the below link -
    http://localhost:8080/configurations/teacher
    Output in postman -
    Huang Sun : Senior Software Developers [Spring Boot] : China

5. Configuring Spring Boot
[A] Logging
-Although spring is smart enough to capture all the required properties from its default dependencies still we can do custom modification/configuration of certain properties that we want to be custom specific inside the application.properties file. Eg - Server port, context path, actuator, security, etc.
-Lets Configure for Logger - [web already has logging properties. You can any one - default[i.e. web - logger already present in starter-web dependency] or log4j2. I am using spring-boot-starter-web]
-Give the logging file path and logging file name in application.properties
    logging.file.path=C:/Users/ACNusrHoneyP/Desktop/logs
    logging.file.name=C:/Users/ACNusrHoneyP/Desktop/logs/configurations.log
-you will see that the configurations.log file is automatically created
-Now let make the endpoint also for logger.
-Code done in the controller with the method name -
    @GetMapping("/logs")
        public String log(){
        ....
        }
[B] Port
-In application.properties, you can change the port to -
#HTTP Server port
#server.port=7070
[C] Timeout - HTTP session will expire after 15 minutes of inactivity.
#Making Session Timeout
#HTTP session will expire after 15 minutes of inactivity.
#server.servlet.session.timeout=15m

6. Now Lets create the Students - from the application only
-Create : [Not in DB, Create in the Application only]
    A] Create a POJO class "Student", also called as entity class - with no arg. constuctor, arg. constuctor, getter, setter and to string
    B] Create the below method - This method itself create the new students when the getmapping /students is called and return those students.
    @GetMapping("/students")
        public List<Student> getStudents(){
        ...
        }
    C] Hit - http://localhost:8080/configurations/students

7. PATH VARIABLES
-If we want to retrieve only the single student information then it will be retrieved by its studentId. This “studentId” is known as a “path variable”.
Q. The previous method is wrong, because whenever we hit  @GetMapping("/students") - everytime it will create the students.
   This is wrong. Student should be created once only.
Ans - Yes, So here comes - "@PostConstruct"
-So what will happen -
    i] create all the students in another method named "loadData()" [@PostConstruct on this method will make this method run only once when app is loaded]
    ii] And when @GetMapping("/getAllStudents") is called, all the students will be returned, thats it [and not created]. Because created by above method
-Now Path Variable -->
    @GetMapping("/students/{studentId}")
        public Student getStudent(@PathVariable int studentId) {
        ...
        }
-But when you pass 3 in the URL for getting the student with id or index = 3 ==> Array Index Out Of Bound Exception
-HERE COMES EXCEPTION HANDLING

8. EXCEPTION HANDLING
-created package "exception"
-created class -
    a] StudentErrorResponse==> will be used to set the values to be send in the response to the client --> status, message, timestamp
    b] StudentNotFoundException==> Our custom exception class.
    c] StudentConfigExceptionHandler==>
        -Whenever the exception is occured, it will set the responses and we will set the status and message in this.
        -@ControllerAdvice is used here -
                @ControllerAdvice in Spring Boot is a specialized component used to handle cross-cutting concerns for your controllers — like global exception handling, data binding, and model formatting. It allows you to write logic that applies to multiple controllers from a single place, instead of repeating the same code in each controller.
                Think of it like a “filter” for controllers, but way more powerful. It’s also a perfect example of AOP (Aspect-Oriented Programming)
        -ResponseEntity will be set here using @ExceptionHandler
    show the image in pics folder.

9. OK, Cool but what if user call for any random id like --"@" or "$" or "!" then?
Like - http://localhost:8080/configurations/getStudents/@
-Here Comes GLOBAL EXCEPTION HANDLING - just one more method in class "StudentConfigExceptionHandler"


10. CRON
-The term cron has its roots in Greek, where cron means time. Something that will execute automatically at some time.
-It is made to clear the log at a specific time. [LogCleanerCron].
-The above class only works if @EnableScheduling Annotation is present on the main app.
*/30 * * * * *
|    | | | | |
|    | | | | +---- Day of the Week (0 - 7) (Sunday=0 or 7)
|    | | | +------ Month (1 - 12)
|    | | +-------- Day of the Month (1 - 31)
|    | +---------- Hour (0 - 23)
|    +------------ Minute (0 - 59)
+----------------- Second (0 - 59). Here */30 means every 30 seconds.






