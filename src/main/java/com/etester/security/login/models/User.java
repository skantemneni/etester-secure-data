package com.etester.security.login.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user")
	private Long idUser;

	@NotBlank
	@Size(max = 50)
	private String username;

	@NotBlank
	@Size(max = 45)
	@Column(name = "first_name")
	private String firstName;

	@NotBlank
	@Size(max = 45)
	@Column(name = "last_name")
	private String lastName;

	@Size(max = 45)
	@Column(name = "middle_name")
	private String middleName;

	@NotBlank
	@Size(max = 50)
	@Email
	@Column(name = "email_address")
	private String emailAddress;

	@NotBlank
	@Size(max = 200)
	private String password;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "authorities", joinColumns = @JoinColumn(name = "username"), inverseJoinColumns = @JoinColumn(name = "authority"))
	private Set<Role> roles = new HashSet<>();

	public User() {
	}

	public User(String username, String firstName, String lastName, String middleName, String emailAddress,
			String password) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.emailAddress = emailAddress;
		this.password = password;
	}

	/*
	 * public Long getIdUser() { return idUser; }
	 * 
	 * public void setIdUser(Long idUser) { this.idUser = idUser; }
	 * 
	 * public String getUsername() { return username; }
	 * 
	 * public void setUsername(String username) { this.username = username; }
	 * 
	 * public String getEmailAddress() { return emailAddress; }
	 * 
	 * public void setEmailAddress(String emailAddress) { this.emailAddress =
	 * emailAddress; }
	 * 
	 * public String getPassword() { return password; }
	 * 
	 * public void setPassword(String password) { this.password = password; }
	 * 
	 * public Set<Role> getRoles() { return roles; }
	 * 
	 * public void setRoles(Set<Role> roles) { this.roles = roles; }
	 */
}
