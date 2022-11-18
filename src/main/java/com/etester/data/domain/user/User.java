package com.etester.data.domain.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.etester.data.domain.admin.Authority;
import com.etester.data.domain.content.ChannelSubscription;
import com.etester.data.domain.content.core.Channel;
import com.etester.data.domain.profile.Userprofile;
import com.etester.data.domain.test.instance.Usertest;

import lombok.Data;

@Data
// @Entity
// @Table(name="user")
public class User /* extends org.springframework.security.core.userdetails.User */ {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;
    @Column(name = "username", length = 50, nullable = false)
    private String username;
    @Column(name = "password", length = 200, nullable = false)
    private String password;
	@Column(name = "enabled")
    private boolean enabled;
    @Column(name = "email_address", length = 50, nullable = false)
	private String emailAddress;
    @Column(name = "first_name", length = 45, nullable = false)
    private String firstName;
    @Column(name = "last_name", length = 45, nullable = false)
    private String lastName;
    @Column(name = "middle_name", length = 45, nullable = false)
    private String middleName;

    private String fullName;
    
    @Transient private List<Authority> authorities;

    @Transient private List<String> permissions;

    @Transient private List<Usertest> tests;
	
	@Transient private List<Userprofile> profiles;
	
	// private webuser (containing extended user attributes - used in programmatically loading users)
	@Transient private Webuser webuser;
	// private channelSubscriptions list of Subscription channels and durations - used in programmatically loading users
	@Transient private List<ChannelSubscription> channelSubscriptions;
	// private studentOrganizations corresponding to the organizations the student is associated with - used in programmatically loading users
	@Transient private List<Long> idOrganizationsList;

	// organizationName corresponding to id_organization
	private String organizationName;
    
	private Long idOrganization;
	
	@Transient private List<Channel> channels;

	@Transient private List<Channel> subscriptions;

	public User() {
		this("username", "password", new ArrayList<Authority>());
    }

	public User(String username, String password, List<Authority> authorities) {
		this.username = username;
		this.password = password;
		this.authorities = authorities;
    }

	public User(String username, String password) {
		this (username, password, new ArrayList());
	}

    public User(Long idUser, String username, String password, String firstName, String lastName, String middleName, List<Authority> authorities) {
    	this(username, password, authorities);
        this.idUser = idUser;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
    }


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", username=" + username
				+ ", password=" + password + ", enabled=" + enabled
				+ ", emailAddress=" + emailAddress + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", middleName=" + middleName
				+ ", fullName=" + fullName + ", authorities=" + authorities
				+ ", permissions=" + permissions + ", tests=" + tests
				+ ", profiles=" + profiles + ", webuser=" + webuser
				+ ", channelSubscriptions=" + channelSubscriptions
				+ ", idOrganizationsList=" + idOrganizationsList
				+ ", organizationName=" + organizationName
				+ ", idOrganization=" + idOrganization + ", channels="
				+ channels + ", subscriptions=" + subscriptions + "]";
	}


}
