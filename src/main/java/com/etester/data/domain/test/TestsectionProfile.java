package com.etester.data.domain.test;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name="anal_testsection_rollup")
public class TestsectionProfile {

	@Id
	@NotNull
    @Column(name = "id_testsection")
	private Long idTestsection;

    @Column(name = "id_testsegment")
	private Long idTestsegment;

    @Column(name = "id_test")
	private Long idTest;

	@Column(name = "correct_count_average")
    private Integer correctCountAverage;
	
	@Column(name = "correct_points_average")
    private Float correctPointsAverage;
	
	@Column(name = "percentage_average")
    private Float percentageAverage;
	
	@Column(name = "sd1")
    private Float sd1;
	
    @Column(name = "percentile_distribution")
	private String percentileDistribution;

}
