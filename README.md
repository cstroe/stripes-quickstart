# A Stripes Quickstart

This project is meant to be a starting point for a Java Web App.  Feel free to adapt it to your needs.

## What is it?

A simple note management application, with the following features:
* Create Notes with title, created date, and note body
* Organize Notes in named Notebooks

## Usage

To run the web application using an in memory database in Jetty:

    mvn -Djetty.http.port=8080 jetty:run

You should then be able to access it at http://localhost:8080

NOTE: The `jetty:run` command will fail with `Address already in use` if you already have an application running on port 8080.

# Technical Features

## View Layer
* MVC using the [Stripes Framework](http://www.stripesframework.org/)
* Twitter's [Bootstrap](http://getbootstrap.com/) for frontend styling

## Testing
* BDD style tests using [Scalatest](http://www.scalatest.org)
* [Selenium](http://docs.seleniumhq.org/) with [PhantomJS](http://phantomjs.org/) for full stack testing.
* Use [DBUnit](http://dbunit.sourceforge.net/) to reset database to known state when running Selenium tests

## Persistence Layer
* [HyperSQL](http://hsqldb.org/) for the database
* [Hibernate ORM](http://hibernate.org/orm/) for the object-relational mapper (with a [DAO design](https://developer.jboss.org/wiki/GenericDataAccessObjects))
* [HikariCP](https://brettwooldridge.github.io/HikariCP/) for connection pooling

# References

## Design articles
* [Clean and usable URLs in Stripes](https://110j.wordpress.com/2010/05/07/clean-and-usable-urls-in-stripes/)
* [Stop Unit Testing Database Code](http://blog.jooq.org/2014/06/26/stop-unit-testing-database-code/)

## Scalatest
* [Scalatest with Arquillian](https://github.com/tsabirgaliev/arquillian-scalatest-bootstrap)
* [Detailed description of Scalatest Runner](http://www.scalatest.org/user_guide/using_the_runner)

## Maven Resource Filtering
* [Maven resource filtering](http://stackoverflow.com/a/3697482/1591777)
* [Dependency resolution in Maven resource filtering](https://issues.apache.org/jira/browse/MRESOURCES-31)
* [Work around for Maven resource filtering bug](http://stackoverflow.com/a/2247645/1591777)
* [maven-resources-plugin](http://maven.apache.org/plugins/maven-resources-plugin/source-repository.html) uses [maven-filtering](https://maven.apache.org/shared/maven-filtering/source-repository.html) and by default uses the `DefaultMavenFileFilter` part of of the [Maven Shared](https://maven.apache.org/shared/) project
* project.dependencies is scoped only in the execution scope of plugins, not available for substitution in the POM, see [issue](http://stackoverflow.com/a/2247645/1591777) and [clarification](https://issues.apache.org/jira/browse/MRESOURCES-118?focusedCommentId=14446377&page=com.atlassian.jira.plugin.system.issuetabpanels:comment-tabpanel#comment-14446377) (read carefully)
