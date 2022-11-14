package com.etester.data.domain.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class JdbcTestsectionInstructionsDao extends NamedParameterJdbcDaoSupport implements TestsectionInstructionsDao {

	@Override
	public Integer updateTestsectionInstructions(
			TestsectionInstructions testsectionInstructions) {
		// update test metadata
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(testsectionInstructions);
        getNamedParameterJdbcTemplate().update(upsertTestsectionInstructionsSQL, parameterSource);
        return 0;
	}

	@Override
	public TestsectionInstructions getTestsectionInstructions(String instructionsName) {
        BeanPropertyRowMapper<TestsectionInstructions> testsectionInstructionsRowMapper = BeanPropertyRowMapper.newInstance(TestsectionInstructions.class);
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("instructionsName", instructionsName);
		// queryForObject throws an exception when the Level is missing.  this should be ignored/swallowed
        TestsectionInstructions testsectionInstructions = null;
        try {
        	testsectionInstructions = getNamedParameterJdbcTemplate().queryForObject(getTestsectionInstructionsSQL, args, testsectionInstructionsRowMapper);
        } catch (IncorrectResultSizeDataAccessException e) {}
        return testsectionInstructions;
	}

}
