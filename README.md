# Novamedia Fundraising API (OSGi)
This is the Novamedia Fundraising API that must be deployed on JBoss Fuse 6.1.

# Building
The following two project must first be built:

* webservice-api
* nfa-api

# Environments

<table>
 <tr><th>Environment</th><th>JBoss Fuse</th><th>Database</th></tr>
 <tr><td>KETAL</td><td>gnlasdatnfsp110.nl.novamedia.com</td><td>gnlasdatnfsp118.nl.novamedia.com</td></tr>
 <tr><td>KETAM</td><td>gnlasdatnfsp114.nl.novamedia.com</td><td>gnlasdatppas197.nl.novamedia.com</td></tr>
 <tr><td>PRODUCTION</td><td>gnlnorprnfsp137.nl.novamedia.com</td><td>gnlnorprnfsp141.nl.novamedia.com</td></tr>
</table>

# Maven configuration
Put the following in settings.xml:


    <?xml version="1.0" encoding="utf-8"?>
    <settings xmlns="http://maven.apache.org/SETTINGS/1.0.0" 
              xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
              xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
        <mirrors>
            <mirror>
                <id>nexus</id>
                <mirrorOf>*</mirrorOf>
                <url>http://buildblox.nl.novamedia.com:8081/nexus/content/repositories/public/</url>
            </mirror>
        </mirrors>
        <servers>
            <server>
                <id>svn01.nl.novamedia.com</id>
                <username>your MSO username here</username>
                <password>your password here</password>
            </server>
            <server>
                <id>novamedia</id>
                <username>your MSO username here</username>
                <password>your password here</password>
            </server>
            <server>
                <id>novamedia-snapshots</id>
                <username>your MSO username here</username>
                <password>your password here</password>
            </server>
        </servers>
    </settings>


# IntelliJ Maven configuration:
IntelliJ -> Maven -> Runner -> VMOptions:

```
-XX:MaxPermSize=256m -Djava.awt.headless=true -Dfile.encoding=UTF-8
```
