This project demonstrates how to setup and use a custom login module.
It shows how the integration with external systems for authentication and authorization can be performed.

The steps are the following : 
1- Have Java JDK 8 installed
2- Have JBoss EAP 7.0.3 installed
3- Have JBoss BPM deployed
4- Modify the JBoss EAP configuration file to create a new security domain to replace the one used by default for the BPM applications
If using JBoss EAP in standalone mode with default profile, the file to modify is *$EAP_HOME/standalone/configuration/standalone.xml*.
There are 2 ways to perform this configuration:
   - By using JBoss EAP CLI (Command Line Interface) to do the configuration. This option is preferable as it can be automated if needed. 
     The following commands will create what is needed :
     	/subsystem=security/security-domain=bpm:add(cache-type=default)
     	/subsystem=security/security-domain=bpm/authentication=classic:add(login-modules=[{code=com.redhat.bpm.CustomLoginModule,flag=required}])
   - Manually by modifying the security sub-system, defined by *urn:jboss:domain:security*.
     Next to the default security domain called 'other', add a new one such as the following:
         *<security-domain name="bpm" cache-type="default">
             <authentication>
                  <login-module code="com.redhat.bpm.CustomLoginModule" flag="required"/>
             </authentication>
         </security-domain>*
     The name of the class and its package must match the one that is in the Maven project.
5- Modify the BPM applications jboss-web.xml files to change to security domain to be used for authentication. This needs to be done for Business Central, for Decision Server, and eventually for Business Activity Monitoring.
For Business Central, the impacted file is *$EAP_HOME/standalone/deployments/business-central.war/WEB-INF/web.xml*
The change consists on replacing the existing *<security-domain>other</security-domain>* with *<security-domain>bpm</security-domain>*
Note that the name of this security domain is not important, and it is possible to pick whatever you want, but it just needs to match the one defined in the previous step.
6- Compile the code using the *mvn clean package* or *mvn clean install* command
7- The JAR created by the Maven command needs to be deployed in JBoss EAP.  There are 2 options here:
     - create a JBoss EAP module common to all applications deployed onto the JBoss EAP server.
     - copy this JAR file inside each WAR: for Business Central, the JAR file needs to be copied in the *$EAP_HOME/standalone/deployments/business-central.war/WEB-INF/lib* folder.
       Repeat the operation for kie-server and dashbuilder.
8- Start JBoss EAP : *$EAP_HOME/bin/standalone.sh*
9- Login to Business Central : *http://localhost:8080/business-central* and enter the user and password set up in the custom code

Once this work, adapt the Java code of the custom login module to your need, eventually by integrating with external system.
Please note that for DB or LDAP authentication integration, there is no need to implement a custom login as JBoss EAP provides some out of the bos for this.
