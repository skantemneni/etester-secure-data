package com.etester.data.domain.test;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name="serialized_test")
public class SerializedTest {

	@Id
    @Column(name = "id_test")
    private Long idTest;
	
	@NotNull
    @Column(name = "test_string_json")
	private String testStringJson;
    
	@Column(name = "date_saved")
	private Date dateSaved;

	// Variable used when this bean is used in the context of administering a test. This represents 
	// the status of the user instance of the test    
	private String testStatus;
	// Variable also used when this bean is used in the context of administering a test. This represents 
	// the current value of any saved response for the test instance    
	private String testResponse;
}
