package com.etester.data.domain.content;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name="printsectionsettings")
public class Printsectionsettings {

	@Id
    @Column(name = "id_section")
    private Long idSection;
	
	@NotNull
	@Column(name = "settings")
	private String settings;

	@Column(name = "date_saved")
	private Date dateSaved;

}
