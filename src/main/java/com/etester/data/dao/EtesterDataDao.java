package com.etester.data.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.etester.data.entity.TestEntity;

@Repository
public class EtesterDataDao extends NamedParameterJdbcDaoSupport {

	private DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	public EtesterDataDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public TestEntity findTestById(Long idTest) {
		String findTestByIdSQL = "SELECT * FROM test WHERE id_test = :idTest";
        BeanPropertyRowMapper<TestEntity> testRowMapper = BeanPropertyRowMapper.newInstance(TestEntity.class);
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("idTest",  idTest);
		TestEntity test = null;
        try {
        	test = getNamedParameterJdbcTemplate().queryForObject(findTestByIdSQL, args, testRowMapper);
        	// massage the published and public attributes
        } catch (IncorrectResultSizeDataAccessException e) {}
        return test;
	}

	public List<TestEntity> findAllTests() {
		String findAllTestsSQL = "SELECT * FROM test ";
        BeanPropertyRowMapper<TestEntity> testRowMapper = BeanPropertyRowMapper.newInstance(TestEntity.class);
		Map<String, Object> args = new HashMap<String, Object>();
        List<TestEntity> tests = getNamedParameterJdbcTemplate().query(findAllTestsSQL, args, testRowMapper);
		return tests;
	}

}
