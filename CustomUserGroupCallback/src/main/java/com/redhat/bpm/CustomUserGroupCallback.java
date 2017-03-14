package com.redhat.bpm;

import org.kie.api.task.UserGroupCallback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.ArrayList;


/**
 * Custom User Group Callback for Human Tasks
 */
public class CustomUserGroupCallback implements UserGroupCallback {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomUserGroupCallback.class);
	
    
    public static final String ROLES_GROUP_NAME = "Roles";
    public static final String GROUP_NAME1 = "admin";
    public static final String GROUP_NAME2 = "analyst";
    
    public static final String USER_NAME = "redhat";
    public static final String USER_PWD = "redhatPwd";


	/**
     * Resolves existence of user id.
     * @param userId    the user id assigned to the task
     * @return true if userId exists, false otherwise.
     */
    public boolean existsUser(String userId) {
        logger.info("$$ Does user '"+userId+"' exist?");
        return USER_NAME.equals(userId);
    }

    /**
     * Resolves existence of group id.
     * @param groupId the group id assigned to the task
     * @return true if groupId exists, false otherwise.
     */
    public boolean existsGroup(String groupId) {
        logger.info("$$ Does group '"+groupId+"' exist?");
    	return (GROUP_NAME1.equals(groupId) || GROUP_NAME2.equals(groupId));
    }

    /**
     * Returns list of group ids for specified user id.
     * @param userId the user id assigned to the task
     * @param groupIds list of group ids assigned to the task
     * @param allExistingGroupIds list of all currently known group ids
     * @return List of group ids.
     */
    public List<String> getGroupsForUser(String userId, List<String> groupIds, List<String> allExistingGroupIds) {
    	logger.info("$$ Getting groups for user '"+userId+"', groupIds="+groupIds.toString()+", and allExistingGroupIds="+allExistingGroupIds.toString());
    	List<String> res = new ArrayList<String>();

        if (USER_NAME.equals(userId)) {
            res.add(GROUP_NAME1);
            res.add(GROUP_NAME2);
        }

        logger.info("$$ Returning groups "+res.toString()+" for user '"+userId+"'");
        return res;
    }
}
