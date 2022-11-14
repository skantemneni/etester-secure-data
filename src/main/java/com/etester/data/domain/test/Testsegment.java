package com.etester.data.domain.test;

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
@Table(name="testsegment")
public class Testsegment {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_testsegment")
    private Long idTestsegment;
	
	@NotNull
    @Column(name = "id_test")
	private Long idTest;

	@NotNull
    @Size(min=1, max=100)
	private String name;
    
    @Size(min=0, max=200)
	private String description;

	@Column(name = "text")
	private String text;

	@Column(name = "addl_info")
	private String addlInfo;

    @Column(name = "seq")
	private Integer seq;

	@Column(name = "time_to_answer")
	private Integer timeToAnswer;

	@Column(name = "published")
    private Integer published;

	@Column(name = "sectionwrapper")
    private Integer sectionwrapper;

	@Column(name = "question_count")
	private Integer questionCount;
	
	@Column(name = "point_count")
	private Float pointCount;
	
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name= "id_testsection")
    private List<Testsection> testsections;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name= "id_testsynopsislink")
    private List<Testsynopsislink> testsynopsislinks;

}
