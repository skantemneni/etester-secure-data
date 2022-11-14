package com.etester.data.domain.util.email;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Table(name="send_email_data")
public class SendEmailData {
    
	@Column(name = "username", length = 50)
    private String username;

    @Column(name = "email")
	private String email;
    
    @Column(name = "phone")
	private String phone;
    
    @NotNull
    @Column(name = "problem_type_code")
	private Integer problemTypeCode;
    
    @Column(name = "problem_type_description")
	private String problemTypeDescription;
    
	@Column(name = "subject")
	private String subject;
	
	@Column(name = "message")
	private String message;

	@Column(name = "date_sent")
	private Date dateSent;

}
