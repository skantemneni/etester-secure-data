package com.etester.data.domain.content.core;

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
@Table(name="system")
public class Channel {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_system")
	private Long idSystem;
	
	@NotNull
    @Size(min=1, max=100)
	private String name;
    
    @Size(min=0, max=200)
	private String description;

	@Column(name = "text")
	private String text;

	@Column(name = "addl_info")
	private String addlInfo;

	@Column(name = "editable")
	private Integer editable;

	@Column(name = "published")
    private Integer published;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name= "id_system")
    private List<Level> levels;

}
