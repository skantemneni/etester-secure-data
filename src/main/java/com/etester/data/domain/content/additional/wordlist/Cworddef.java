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
@Table(name="cworddef")
public class Cworddef {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cworddef")
	private Long idCworddef;
	
	@NotNull
    @Column(name = "id_cword")
	private Long idCword;
	
    @Size(min=0, max=5)
	@Column(name = "pos")
	private String pos;

    @Size(min=0, max=1000)
	@Column(name = "definition")
	private String definition;

    @Size(min=0, max=400)
	@Column(name = "synonym")
	private String synonym;

    @Size(min=0, max=200)
	@Column(name = "antonym")
	private String antonym;

    @Size(min=0, max=200)
	@Column(name = "thesaurus")
	private String thesaurus;

	@Column(name = "sampletext")
	private String sampletext;

}
