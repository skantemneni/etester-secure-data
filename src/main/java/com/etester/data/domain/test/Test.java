package com.etester.data.domain.test;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="test")
public class Test {

	public static Long RFPROVIDER = new Long (0);
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_test")
    private Long idTest;
	
	@NotNull
    @Column(name = "id_provider")
	private Long idProvider;

	@NotNull
    @Column(name = "id_organization")
	private Long idOrganization;

	@NotNull
    @Column(name = "id_channel")
	private Long idChannel;

	@NotNull
    @Size(min=1, max=100)
	private String name;
    
    @Size(min=0, max=200)
	private String description;

	@Column(name = "text")
	private String text;

	@Column(name = "addl_info")
	private String addlInfo;

	@Column(name = "test_level")
    private String testLevel;
    
	@Column(name = "timed")
	private Integer timed;

	@Column(name = "report_by_subject")
	private Integer reportBySubject;

	@Column(name = "time_to_answer")
	private Integer timeToAnswer;

	@Column(name = "published")
    private Integer published;
	
	@Column(name = "access_level")
    private Integer accessLevel;

	@NotNull
	@Column(name = "test_type")
    private String testType;
	
	@Column(name = "is_practice")
    private Integer isPractice;

	@Column(name = "auto_grade")
    private Integer autoGrade;

	@Column(name = "auto_publish_results")
    private Integer autoPublishResults;

	@Column(name = "is_free")
    private Integer isFree;

	private Date dateFreeStart;

	private Date dateFreeEnd;

	private Date subscriptionDateFreeEnd;

    private String freeMessage;

    private String freeMessageMore;

	@Column(name = "question_count")
	private Integer questionCount;
	
	@Column(name = "point_count")
    private Float pointCount;
	
	@Column(name = "examtrack")
	private String examtrack;
	
	@Column(name = "combine_sections")
	private Integer combineSections;
	
	// organizationName corresponding to id_organization
	private String organizationName;
    
	// providerName corresponding to id_provider
	private String providerName;

	// organizationName corresponding to id_organization
	private String channelName;
    
	// providerName corresponding to id_provider
	private String examtrackDescription;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_testsegment")
	private List<Testsegment> testsegments;

	// Variable used when this bean is used in the context of administering a test. This represents 
	// the status of the user instance of the test    
	private String testStatus;
	// Variable also used when this bean is used in the context of administering a test. This represents 
	// the current value of any saved response for the test instance    
	private String testResponse;

	// Variable used when this bean is used in the context of print functionality. This represents the 
	// print settings if any saved for the test    
	private String printsettings;

}
