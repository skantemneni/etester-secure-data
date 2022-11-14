package com.etester.data.domain.content.additional.wordlist;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name="cwordusage")
public class Cwordusage {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cwordusage")
	private Long idCwordusage;
	
	@NotNull
    @Column(name = "id_cword")
	private Long idCword;
	
	@Column(name = "text")
	private String text;

    @Size(min=0, max=200)
	@Column(name = "source")
	private String source;

}
