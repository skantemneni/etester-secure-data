package com.etester.data.domain.profile;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name="profile")
public class Profile {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_profile")
    private Long idProfile;
	
	@NotNull
    @Column(name = "id_provider")
	private Long idProvider;

	// providerName corresponding to id_provider
	private String providerName;

	@NotNull
    @Column(name = "id_organization")
	private Long idOrganization;

	// organizationName corresponding to id_organization
	private String organizationName;

	@NotNull
    @Size(min=1, max=100)
	private String name;
    
    @Size(min=0, max=200)
	private String description;

	@Column(name = "access_level")
    private Integer accessLevel;

	@Column(name = "published")
    private Integer published;
	
	@NotNull
	@Column(name = "profile_type")
    private Integer profileType;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_profilesegment")
	private List<Profilesegment> profilesegments;

}
