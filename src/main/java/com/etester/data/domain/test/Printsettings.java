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
@Table(name="printsettings")
public class Printsettings {

	@Id
    @Column(name = "id_test")
    private Long idTest;
	
	@NotNull
	@Column(name = "settings")
	private String settings;

	@Column(name = "date_saved")
	private Date dateSaved;

}
