# spring-grabbag [![Build Status](https://travis-ci.org/LanyonM/spring-grabbag.png?branch=master)](https://travis-ci.org/LanyonM/spring-grabbag)
This project is meant to be a fun sampling of what you can do with Spring 3.2.  I have been a fan of Spring's Java Config since it was pre-release, and therefore this project attempts to do everything without xml config.

# Running
This project uses maven, and therefore you should not need to have anything aside from Java 6 and Maven 3 installed.

    mvn package tomcat7:run

# Testing
Because this project uses maven, tests can be run quite simply:

    mvn test

If you find yourself needing to exclude tests, you can simply add ``-Dmaven.test.skip=true``.

# ORM
This project currently uses straight JDBC and MyBatis for different repository classes.

# TODO

* i18n messages w/locale detection
* example datetime command object conversion
* Add view-layer framework
* MyBatis transaction management
* Expose MyBatis Cache Stats via JMX
* Add sample JNDI configuration
* Quartz Scheduling
* Spring Security
* Spring Integration with Mongo

