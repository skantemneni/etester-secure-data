package com.etester.data.entity;

import java.util.Date;

import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@Table(name = "TEST")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@JsonPropertyOrder()
public class TestEntity {

	public static Long RFPROVIDER = new Long (0);
	
    private Long idTest;
	private Long idProvider;
	private Long idOrganization;
	private Long idChannel;
	private String name;
	private String description;
	private String text;
	private String addlInfo;
    private String testLevel;
	private Integer timed;
	private Integer reportBySubject;
	private Integer timeToAnswer;
    private Integer published;
    private Integer accessLevel;
    private String testType;
    private Integer isPractice;
    private Integer autoGrade;
    private Integer autoPublishResults;
    private Integer isFree;
	private Date dateFreeStart;
	private Date dateFreeEnd;
	private Date subscriptionDateFreeEnd;
    private String freeMessage;
    private String freeMessageMore;
	private Integer questionCount;
    private Float pointCount;
	private String examtrack;
	private Integer combineSections;
	private String organizationName;
	private String providerName;
	private String channelName;
	private String examtrackDescription;

//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@JoinColumn(name = "id_testsegment")
//	private List<Testsegment> testsegments;
}
