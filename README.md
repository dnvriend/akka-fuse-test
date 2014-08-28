# Akka Fuse Test
This project is a small test how to get Scala 2.11.2, Akka 2.3.5, Akka Persistence, Akka Persistence JDBC on 
Postgresql and Akka Cluster to work on JBoss Fuse 6.1.0. JBoss Fuse is an Integration Platform, but I am also using
it as an Enterprise Application Platform. 

A lot of the libraries we need to get cloud computing to work are not OSGi ready. The way to get it to work is to
tell the bnd plugin to package the jars into the OSGi bundle, and let the bundle's own classloader handle the loading
of classes and resources, thus not having the 'class not found' and 'resource not found' exceptions. 
 
#Note:
You have to configure Akka to not exit the JVM on a 'fatal' error (not all errors are fatal in by book) by setting 
the following key in application.conf:

    akka.jvm-exit-on-fatal-error = off
    
If you don't, the whole fuse container can go up in smoke without a good reason in the logs.     

# Install

    install -s mvn:com.github.dnvriend/akka-fuse-test/1.0.0
    
# Uninstall

    uninstall <bundle-id>
    
    