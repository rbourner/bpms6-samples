This project demonstrates how to setup and use a custom UserGroupCallback to be used by Human Tasks.

The steps to set it up are the following: 
org.kie.server.bypass.auth.user = true
org.jbpm.ht.callback = custom
org.jbpm.ht.custom.callback = com.redhat.bpm.CustomUserGroupCallback
org.jbpm.ht.userinfo = custom 
org.jbpm.ht.custom.userinfo=com.redhat.bpm.CustomUserInfo
