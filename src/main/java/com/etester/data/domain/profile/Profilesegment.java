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
@Table(name="profilesegment")
public class Profilesegment {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_profilesegment")
    private Long idProfilesegment;
	
	@NotNull
    @Column(name = "id_profile")
	private Long idProfile;

	@NotNull
    @Size(min=1, max=100)
	private String name;
    
    @Size(min=0, max=200)
	private String description;

    @Column(name = "seq")
	private Integer seq;

	@Column(name = "profiletest_wrapper")
    private Integer profiletestWrapper;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name= "id_profiletest")
    private List<Profiletest> profiletests;

}
