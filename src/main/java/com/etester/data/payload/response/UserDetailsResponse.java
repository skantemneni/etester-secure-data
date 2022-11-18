package com.etester.data.payload.response;

import java.util.List;
import java.util.stream.Collectors;

import com.etester.data.domain.content.ChannelSubscription;
import com.etester.data.domain.content.core.Channel;
import com.etester.data.domain.profile.Userprofile;
import com.etester.data.domain.test.instance.Usertest;
import com.etester.data.domain.user.User;

import lombok.Data;

@Data
public class UserDetailsResponse {
	private Long idUser;
	private String username;
	private boolean enabled;
	private String firstName;
	private String lastName;
	private String middleName;
	private String emailAddress;
	private List<String> roles;
	private List<String> permissions;
	private List<Usertest> tests;
	private List<Userprofile> profiles;
	private List<ChannelSubscription> channelSubscriptions;
	private List<Long> idOrganizationsList;
	private String organizationName;
	private Long idOrganization;
	private List<Channel> channels;
	private List<Channel> subscriptions;

	public UserDetailsResponse(User user) {
		this.idUser = user.getIdUser();
		this.username = user.getUsername();
		this.enabled = user.isEnabled();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.middleName = user.getMiddleName();
		this.emailAddress = user.getEmailAddress();
		if (user.getAuthorities() != null) {
			List<String> roles = user.getAuthorities().stream().map(authority -> authority.getAuthority().toString())
					.collect(Collectors.toList());
			this.roles = roles;
		}
		this.permissions = user.getPermissions();
		this.tests = user.getTests();
		this.profiles = user.getProfiles();
		this.channelSubscriptions = user.getChannelSubscriptions();
		this.idOrganizationsList = user.getIdOrganizationsList();
		this.organizationName = user.getOrganizationName();
		this.idOrganization = user.getIdOrganization();
		this.channels = user.getChannels();
		this.subscriptions = user.getSubscriptions();
	}

}
