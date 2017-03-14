This project demonstrates how to setup and use a custom UserGroupCallback to be used by Human Tasks.

The steps to set it up are the following: 
org.kie.server.bypass.auth.user = true
org.jbpm.ht.callback = custom
org.jbpm.ht.custom.callback = com.redhat.bpm.CustomUserGroupCallback
org.jbpm.ht.userinfo = custom 
org.jbpm.ht.custom.userinfo=com.redhat.bpm.CustomUserInfo

---------------------------------------------------------------------------

org.kie.server.bypass.auth.user : Allows to bypass the authenticated user for task-related operations.

org.jbpm.ht.callback : Specifies the implementation of user group callback to be used:
     mvel:   Default; mostly used for testing.
     ldap:   LDAP; requires additional configuration in the jbpm.usergroup.callback.properties file.
     db:     Database; requires additional configuration in the jbpm.usergroup.callback.properties file.
     jaas:   JAAS; delegates to the container to fetch information about user data.
     props:  A simple property file; requires additional file that will keep all information (users and groups).
     custom: A custom implementation; you must specify the fully qualified name of the class in the org.jbpm.ht.custom.callback.
     
org.jbpm.ht.custom.callback : A custom implementation of the UserGroupCallback interface in case the org.jbpm.ht.callback property is set to custom.  

org.jbpm.ht.custom.userinfo : A custom implementation of the UserInfo interface in case the org.jbpm.ht.userinfo property is set to custom.  

org.jbpm.ht.userinfo : Specifies what implementation of the UserInfo interface to use for user or group information providers.
     ldap:   LDAP; needs to be configured in the file specified in jbpm-user.info.properties.
     db:     Database; needs to be configured in the file specified in jbpm-user.info.properties.
     props:  A simple property file; set the property jbpm.user.info.properties to specify the path to the file.
     custom: A custom implementation; you must specify the fully qualified name of the class in the org.jbpm.ht.custom.userinfo property. 
     
org.jbpm.ht.user.separator : An alternative separator when loading actors and groups for user tasks from a String.
