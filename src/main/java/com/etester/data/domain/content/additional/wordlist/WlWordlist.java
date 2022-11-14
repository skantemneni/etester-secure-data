package com.etester.data.domain.content.additional.wordlist;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.etester.data.domain.content.core.Question;
import com.etester.data.domain.content.core.Section;

import lombok.Data;

@Data
@Entity
@Table(name="wl_wordlist")
public class WlWordlist {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_wordlist")
	private Long idWordlist;
	
	@NotNull
    @Column(name = "id_skill")
	private Long idSkill;

	@NotNull
    @Column(name = "id_provider")
	private Long idProvider;

	@NotNull
    @Size(min=1, max=100)
	private String name;
    
    @Size(min=0, max=200)
	private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name= "id_wordlist")
    private List<WlWord> words;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name= "id_wordlist")
    private List<WlPassage> passages;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name= "id_section")
    private List<Question> questions;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name= "id_section")
    private Section parentSection;

}
