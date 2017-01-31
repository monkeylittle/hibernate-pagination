package net.thoughtforge.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class User extends UserTokenHolder {

	private static final long serialVersionUID = -6761432798193877730L;

	private String firstName;

    private String lastName;

    private String password;

    private Set<UserGroup> userGroups;
    
    private String username;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void addUserGroup(UserGroup userGroup) {
        if (userGroups == null) {
            userGroups = new HashSet<UserGroup>();
        }
        
        if (!userGroups.contains(userGroup)) {
            userGroups.add(userGroup);
        }
    }

    public void removeUserGroup(UserGroup userGroup) {
        if (userGroups != null) {
        	userGroups.remove(userGroup);
        }
    }
    
    public Set<UserGroup> getUserGroups() {
        if (userGroups == null) {
        	userGroups = new HashSet<UserGroup>();
        }
        
        return Collections.unmodifiableSet(userGroups);
    }
    
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
