package com.redhat.bpm;

import org.kie.internal.task.api.UserInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

import org.kie.api.task.model.Group;
import org.kie.api.task.model.OrganizationalEntity;



/**
 * Custom User Info for Human Tasks
 */
public class CustomUserInfo implements UserInfo { 
	
	private static final Logger logger = LoggerFactory.getLogger(CustomUserInfo.class);

	public String getDisplayName(OrganizationalEntity entity) {
		logger.info("$$ CustomUserInfo.getDisplayName is being called");
		return "name";
	}

	public Iterator<OrganizationalEntity> getMembersForGroup(Group group) {
		logger.info("$$ CustomUserInfo.getMembersForGroup is being called");
		return null;
	}

	public boolean hasEmail(Group group) {
		logger.info("$$ CustomUserInfo.hasEmail is being called");
		return false;
	}

	public String getEmailForEntity(OrganizationalEntity entity) {
		logger.info("$$ CustomUserInfo.getEmailForEntity is being called");
		return "email";
	}

	public String getLanguageForEntity(OrganizationalEntity entity) {
		logger.info("$$ CustomUserInfo.getLanguageForEntity is being called");
		return "language";
	}
}
