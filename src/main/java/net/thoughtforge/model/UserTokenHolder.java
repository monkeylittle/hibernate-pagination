package net.thoughtforge.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class UserTokenHolder extends PersistentEntity {

	private static final long serialVersionUID = 2445167691757491332L;

	private Set<UserToken> userTokens;
	
	public void addUserToken(UserToken userToken) {
        if (userTokens == null) {
            userTokens = new HashSet<UserToken>();
        }
        
        if (!userTokens.contains(userToken)) {
            userTokens.add(userToken);
        }
    }

    public void removeUserToken(UserToken userToken) {
        if (userTokens != null) {
        	userTokens.remove(userToken);
        }
    }
    
    public Set<UserToken> getUserTokens() {
        if (userTokens == null) {
        	userTokens = new HashSet<UserToken>();
        }
        
        return Collections.unmodifiableSet(userTokens);
    }
}
