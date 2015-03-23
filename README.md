# spring-grabbag [![Build Status](https://travis-ci.org/lanyonm/spring-grabbag.png?branch=master)](https://travis-ci.org/lanyonm/spring-grabbag) [![Coverage Status](https://coveralls.io/repos/lanyonm/spring-grabbag/badge.png)](https://coveralls.io/r/lanyonm/spring-grabbag)
This project is meant to be a fun sampling of what you can do with Spring 3.2.  I have been a fan of Spring's Java Config since it was pre-release, and therefore this project attempts to do everything without xml config.

# Running
This project uses maven, and therefore you should not need to have anything aside from Java 6 and Maven 3 installed.

    mvn package tomcat7:run

# Testing
Because this project uses maven, tests can be run quite simply:

    mvn test

If you find yourself needing to exclude tests, you can simply add ``-Dmaven.test.skip=true``.

# ORM
This project currently uses straight JDBC and [MyBatis](http://mybatis.github.com/spring/) for different repository classes.

# Testing
Travis-ci is configured for this project and is currently configured to build against OpenJDK6.

# [The Site](http://lanyonm.github.com/spring-grabbag/)
Maven will generate a site with general information, javadoc, code coverage, update reports and more. See [Github Maven Plugins](https://github.com/github/maven-plugins) for more info about how to configure a maven published gh-pages site.

# TODO

* example datetime command object conversion
* MyBatis transaction management
* Expose MyBatis Cache Stats via JMX
* Add sample JNDI configuration
* Quartz Scheduling
* Spring Security
* Spring Integration with Mongo

