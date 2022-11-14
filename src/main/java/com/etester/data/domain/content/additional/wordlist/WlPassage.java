package com.etester.data.domain.content.additional.wordlist;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="wl_passage")
public class WlPassage {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_passage")
	private Long idPassage;
	
	@NotNull
    @Column(name = "id_wordlist")
	private Long idWordlist;
	
	@Column(name = "text")
	private String text;

	public WlPassage() {
	}

	/**
	 * @return the idPassage
	 */
	public Long getIdPassage() {
		return idPassage;
	}

	/**
	 * @param idPassage the idPassage to set
	 */
	public void setIdPassage(Long idPassage) {
		this.idPassage = idPassage;
	}

	/**
	 * @return the idWordlist
	 */
	public Long getIdWordlist() {
		return idWordlist;
	}

	/**
	 * @param idWordlist the idWordlist to set
	 */
	public void setIdWordlist(Long idWordlist) {
		this.idWordlist = idWordlist;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WlPassage [idPassage=" + idPassage + ", idWordlist="
				+ idWordlist + ", text=" + text + "]";
	}

}
