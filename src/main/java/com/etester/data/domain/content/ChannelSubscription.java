package com.etester.data.domain.content;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name="channel_subscriptions")
public class ChannelSubscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_channel_subscription")
    private Long idChannelSubscription;

    @NotNull
    @Column(name = "id_channel")
	private Long idChannel;

	@NotNull
    @Column(name = "id_student")
	private Long idStudent;

	@NotNull
    @Column(name = "start_date")
	private Date startDate;

	@NotNull
    @Column(name = "end_date")
	private Date endDate;

    public ChannelSubscription() {
    }

    public ChannelSubscription(Long idChannel, Long idStudent, Date startDate, Date endDate) {
        this.idChannel = idChannel;
        this.idStudent = idStudent;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
