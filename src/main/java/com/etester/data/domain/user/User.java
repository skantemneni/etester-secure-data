package com.etester.data.domain.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;

import com.etester.data.domain.admin.Authority;
import com.etester.data.domain.content.ChannelSubscription;
import com.etester.data.domain.content.core.Channel;
import com.etester.data.domain.profile.Userprofile;
import com.etester.data.domain.test.instance.Usertest;

@Entity
@Table(name="user")
public class User extends org.springframework.security.core.userdetails.User {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;
//    @Column(name = "username", length = 50, nullable = false)
//    private String username;
    @Column(name = "password", length = 200, nullable = false)
//    private String password;
//	@Column(name = "enabled")
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
    
//    @Transient private List<Authority> authorities;

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
		super("username", "password", new ArrayList());
    }

	public User(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
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

	/**
	 * @return the idUser
	 */
	public Long getIdUser() {
		return idUser;
	}

	/**
	 * @param idUser the idUser to set
	 */
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

//	/**
//	 * @return the username
//	 */
//	public String getUsername() {
//		return username;
//	}
//
//	/**
//	 * @param username the username to set
//	 */
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//    /**
//	 * @return the password
//	 */
//	public String getPassword() {
//		return password;
//	}
//
//	/**
//	 * @param password the password to set
//	 */
//	public void setPassword(String password) {
//		this.password = password;
//	}

	/**
	 * @return the enabled
	 */
	public boolean getEnabled() {
		return enabled;
	}

	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

//	/**
//	 * @return the authorities
//	 */
//	public List<Authority> getAuthorities() {
//		return authorities;
//	}
//
//	/**
//	 * @param authorities the authorities to set
//	 */
//	public void setAuthorities(List<Authority> authorities) {
//		this.authorities = authorities;
//	}

	/**
	 * @return the permissions
	 */
	public List<String> getPermissions() {
		return permissions;
	}

	/**
	 * @param permissions the permissions to set
	 */
	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}

	/**
	 * @return the tests
	 */
	public List<Usertest> getTests() {
		return tests;
	}

	/**
	 * @param tests the tests to set
	 */
	public void setTests(List<Usertest> tests) {
		this.tests = tests;
	}

	/**
	 * @return the profiles
	 */
	public List<Userprofile> getProfiles() {
		return profiles;
	}

	/**
	 * @param profiles the profiles to set
	 */
	public void setProfiles(List<Userprofile> profiles) {
		this.profiles = profiles;
	}

	/**
	 * @return the organizationName
	 */
	public String getOrganizationName() {
		return organizationName;
	}

	/**
	 * @param organizationName the organizationName to set
	 */
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	/**
	 * @return the idOrganization
	 */
	public Long getIdOrganization() {
		return idOrganization;
	}

	/**
	 * @param idOrganization the idOrganization to set
	 */
	public void setIdOrganization(Long idOrganization) {
		this.idOrganization = idOrganization;
	}

	/**
	 * @return the channels
	 */
	public List<Channel> getChannels() {
		return channels;
	}

	/**
	 * @param channels the channels to set
	 */
	public void setChannels(List<Channel> channels) {
		this.channels = channels;
	}

	/**
	 * @return the subscriptions
	 */
	public List<Channel> getSubscriptions() {
		return subscriptions;
	}

	/**
	 * @param subscriptions the subscriptions to set
	 */
	public void setSubscriptions(List<Channel> subscriptions) {
		this.subscriptions = subscriptions;
	}

	/**
	 * Return the Webuser associated with the user, if any.  Note that Webuser is a simple construct 
	 * that contains extended user attributes and is used primarily while programmatically loading users
	 * @return the webuser
	 */
	public Webuser getWebuser() {
		return webuser;
	}

	/**
	 * Assign a Webuser to the user.  Note that Webuser is a simple construct that contains extended user 
	 * attributes and is used primarily while programmatically loading users
	 * @param webuser the Webuser to set
	 */
	public void setWebuser(Webuser webuser) {
		this.webuser = webuser;
	}


	/**
	 * Return the ChannelSubscriptions associated with the user, if any.  Note that this attribute is only 
	 * filled and used during the user create/update/upload process programatically 
	 * @return the channelSubscriptions
	 */
	public List<ChannelSubscription> getChannelSubscriptions() {
		return channelSubscriptions;
	}

	/**
	 * Set the ChannelSubscriptions associated with the user, if any.  Note that this attribute is only 
	 * filled and used during the user create/update/upload process programatically 
	 * @param channelSubscriptions the channelSubscriptions to set
	 */
	public void setChannelSubscriptions(List<ChannelSubscription> channelSubscriptions) {
		this.channelSubscriptions = channelSubscriptions;
	}

	/**
	 * StudentOrganizations corresponding to the organizations the student is associated with - used in programmatically loading users
	 * @return the idOrganizationsList
	 */
	public List<Long> getIdOrganizationsList() {
		return idOrganizationsList;
	}

	/**
	 * StudentOrganizations corresponding to the organizations the student is associated with - used in programmatically loading users
	 * @param idOrganizationsList the idOrganizationsList to set
	 */
	public void setIdOrganizationsList(List<Long> idOrganizationsList) {
		this.idOrganizationsList = idOrganizationsList;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", username=" + getUsername()
				+ ", password=" + getPassword() + ", enabled=" + enabled
				+ ", emailAddress=" + emailAddress + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", middleName=" + middleName
				+ ", fullName=" + fullName + ", authorities=" + getAuthorities()
				+ ", permissions=" + permissions + ", tests=" + tests
				+ ", profiles=" + profiles + ", webuser=" + webuser
				+ ", channelSubscriptions=" + channelSubscriptions
				+ ", idOrganizationsList=" + idOrganizationsList
				+ ", organizationName=" + organizationName
				+ ", idOrganization=" + idOrganization + ", channels="
				+ channels + ", subscriptions=" + subscriptions + "]";
	}


}
