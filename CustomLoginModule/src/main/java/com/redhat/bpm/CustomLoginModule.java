package com.redhat.bpm;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.Iterator;

import org.jboss.security.auth.spi.UsernamePasswordLoginModule;

import javax.security.auth.login.LoginException;

import org.jboss.security.SimpleGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Custom login module for JBoss EAP demonstrating how to use it for Business Central authentication and authorization
 */
public class CustomLoginModule extends UsernamePasswordLoginModule {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomLoginModule.class);
	
	public static final String ROLES_GROUP_NAME = "Roles";
	public static final String GROUP_NAME1 = "admin";
	public static final String GROUP_NAME2 = "analyst";
	public static final String GROUP_NAME3 = "kie-server";
	public static final String GROUP_NAME4 = "kiemgmt";
	public static final String GROUP_NAME5 = "rest-all";
		
	public static final String USER_NAME = "redhat";
	public static final String USER_PWD = "redhatPwd";
	
	
	/** Create the set of roles the user belongs to 
	  @return Group[] containing the sets of roles 
	 */
	protected Group[] getRoleSets() throws LoginException
	{
	    ArrayList<Group> groups = new ArrayList<Group>();

		SimpleGroup rolesGroup = new SimpleGroup(ROLES_GROUP_NAME);
	    groups.add(rolesGroup);
	    try {
			rolesGroup.addMember(createIdentity(GROUP_NAME1));
		} catch (Exception exc) {
			logger.error("$$ Caution: cannot add '"+GROUP_NAME1+"' to group '"+rolesGroup.getName()+"'");
		}
	    try {
			rolesGroup.addMember(createIdentity(GROUP_NAME2));
		} catch (Exception exc) {
			logger.error("$$ Caution: cannot add '"+GROUP_NAME2+"' to group '"+rolesGroup.getName()+"'");
		}
	    try {
			rolesGroup.addMember(createIdentity(GROUP_NAME3));
		} catch (Exception exc) {
			logger.error("$$ Caution: cannot add '"+GROUP_NAME3+"' to group '"+rolesGroup.getName()+"'");
		}
	    try {
			rolesGroup.addMember(createIdentity(GROUP_NAME4));
		} catch (Exception exc) {
			logger.error("$$ Caution: cannot add '"+GROUP_NAME3+"' to group '"+rolesGroup.getName()+"'");
		}
	    try {
			rolesGroup.addMember(createIdentity(GROUP_NAME5));
		} catch (Exception exc) {
			logger.error("$$ Caution: cannot add '"+GROUP_NAME3+"' to group '"+rolesGroup.getName()+"'");
		}
	    
	    
	    
	    Group group1 = new SimpleGroup(GROUP_NAME1);
	    groups.add(group1);
	    Group group2 = new SimpleGroup(GROUP_NAME2);
	    groups.add(group2);
	    Group group3 = new SimpleGroup(GROUP_NAME3);
	    groups.add(group3);
	    Group group4 = new SimpleGroup(GROUP_NAME4);
	    groups.add(group4);
	    Group group5 = new SimpleGroup(GROUP_NAME5);
	    groups.add(group5);
	    
	    try {
	    	group1.addMember(createIdentity(USER_NAME));
		} catch (Exception e) {
			logger.error("$$ Caution: cannot add user '"+USER_NAME+"' to group '"+group1.getName()+"'");
		}
	    
	    try {
	    	group2.addMember(createIdentity(USER_NAME));
		} catch (Exception e) {
			logger.error("$$ Caution: cannot add user '"+USER_NAME+"' to group '"+group2.getName()+"'");
		}
	    
	    try {
	    	group3.addMember(createIdentity(USER_NAME));
		} catch (Exception e) {
			logger.error("$$ Caution: cannot add user '"+USER_NAME+"' to group '"+group3.getName()+"'");
		}
	    
	    try {
	    	group4.addMember(createIdentity(USER_NAME));
		} catch (Exception e) {
			logger.error("$$ Caution: cannot add user '"+USER_NAME+"' to group '"+group4.getName()+"'");
		}
	    
	    try {
	    	group5.addMember(createIdentity(USER_NAME));
		} catch (Exception e) {
			logger.error("$$ Caution: cannot add user '"+USER_NAME+"' to group '"+group5.getName()+"'");
		}
	    
	    Group[] roleSets = new Group[groups.size()];
	    groups.toArray(roleSets);
	    
	    // Only for logging
	    if (logger.isInfoEnabled()) {
		    Iterator<Group> iter = groups.iterator();
		    while (iter.hasNext()) {
		    	Group g = iter.next();
		    	logger.info("$$ Role: "+g.toString());
		    }
	    }
	    
	    return roleSets;
	}
	
	/**
	 * Get the password
	 */
	protected String getUsersPassword()
	{
		String username = getUsername();
		logger.info("$$ Retrieving pwd for user '"+username+"'");
		
		if (USER_NAME.equals(username))
			return USER_PWD;
		else {
			logger.warn("User not defined!");
			return "";
		}
	}
}
