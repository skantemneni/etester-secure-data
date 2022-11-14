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
@Table(name="serialized_testinstance")
public class SerializedTestinstance {

	@Id
    @Column(name = "id_testinstance")
    private Long idTestinstance;
	
	@NotNull
    @Column(name = "testinstance_string_json")
	private String testinstanceStringJson;

	@Column(name = "date_saved")
	private Date dateSaved;

}
