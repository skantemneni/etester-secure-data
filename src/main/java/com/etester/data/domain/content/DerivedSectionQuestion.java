package com.etester.data.domain.content;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="derived_section_question")
public class DerivedSectionQuestion {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_derived_section_question")
	private Long idDerivedSectionQuestion;
	
	@Column(name = "id_section")
	private Long idSection;
	
	@Column(name = "id_question")
	private Long idQuestion;
	
    @Column(name = "question_order")
    private Integer questionOrder;

}
