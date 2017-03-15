This project demonstrates how to setup and use a custom UserGroupCallback to be used by Human Tasks.

Environments variables need to be set:
- org.kie.server.bypass.auth.user : Allows to bypass the authenticated user for task-related operations (values=true or false)
- org.jbpm.ht.callback : Specifies the implementation of user group callback to be used:
     mvel:   Default; mostly used for testing.
     ldap:   LDAP; requires additional configuration in the jbpm.usergroup.callback.properties file.
     db:     Database; requires additional configuration in the jbpm.usergroup.callback.properties file.
     jaas:   JAAS; delegates to the container to fetch information about user data.
     props:  A simple property file; requires additional file that will keep all information (users and groups).
     custom: A custom implementation; you must specify the fully qualified name of the class in the org.jbpm.ht.custom.callback. In our sample, value is com.redhat.bpm.CustomUserInf  
- org.jbpm.ht.custom.callback : A custom implementation of the UserGroupCallback interface in case the org.jbpm.ht.callback property is set to custom.  
- org.jbpm.ht.custom.userinfo : A custom implementation of the UserInfo interface in case the org.jbpm.ht.userinfo property is set to custom.  
- org.jbpm.ht.userinfo : Specifies what implementation of the UserInfo interface to use for user or group information providers.
     ldap:   LDAP; needs to be configured in the file specified in jbpm-user.info.properties.
     db:     Database; needs to be configured in the file specified in jbpm-user.info.properties.
     props:  A simple property file; set the property jbpm.user.info.properties to specify the path to the file.
     custom: A custom implementation; you must specify the fully qualified name of the class in the org.jbpm.ht.custom.userinfo property.
- org.jbpm.ht.user.separator : An alternative separator when loading actors and groups for user tasks from a String.

The steps are the following : 
1- Have Java JDK 8 installed
2- Have JBoss EAP 7.0.3 installed
3- Set the environment variables described above to be picked up at server start
4- Modify the BPM applications jboss-web.xml files to change to security domain to be used for authentication. This needs to be done for Business Central, for Decision Server, and eventually for Business Activity Monitoring.
6- Compile the code using the *mvn clean package* or *mvn clean install* command
7- The JAR created by the Maven command needs to be deployed in JBoss EAP.  There are 2 options here:
     - create a JBoss EAP module common to all applications deployed onto the JBoss EAP server.
     - copy this JAR file inside each WAR: for Business Central, the JAR file needs to be copied in the *$EAP_HOME/standalone/deployments/business-central.war/WEB-INF/lib* folder.
       Repeat the operation for kie-server and dashbuilder.
8- Start JBoss EAP : *$EAP_HOME/bin/standalone.sh*
9- Create a BPM project and deploy it onto your local Maven
10- Create a container for this KJar on KieServer
11- Start a new process instance. You should see the logs coming from your custom UserGroupCallback.
