package com.etester.data.domain.test;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name="testsection_instructions")
public class TestsectionInstructions {

	@Id
	@Column(name = "instructions_name")
    @Size(min=1, max=100)
	private String instructionsName;

    @Size(min=0, max=200)
	private String description;

	@Column(name = "text")
	private String text;

	@Column(name = "addl_info")
	private String addlInfo;


	public TestsectionInstructions() {
	}

}
