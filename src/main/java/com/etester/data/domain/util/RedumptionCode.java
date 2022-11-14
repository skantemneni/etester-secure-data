package com.etester.data.domain.util;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name="redumption_code")
public class RedumptionCode {

	@Id
    @Column(name = "redumption_code")
    private String redumptionCode;
	
	@NotNull
    @Column(name = "total_uses")
	private Integer totalUses;

	@NotNull
    @Column(name = "current_uses")
	private Integer currentUses;

    @Column(name = "id_channel")
	private Long idChannel;

	@Column(name = "test_restricted")
	private Integer testRestricted;

	@Column(name = "retail_price")
    private Integer retailPrice;
	    
	@Column(name = "sale_price")
    private Integer salePrice;
	    
	@Column(name = "purchaser")
	private String purchaser;

	@Column(name = "redeemed")
	private Integer redeemed;

	@Column(name = "start_date")
	private Date startDate;
	
	@Column(name = "expiration_date")
	private Date expirationDate;
	
	@Transient
	private List<Long> testRestrictions;
}
