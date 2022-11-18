package com.etester.data.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.etester.data.domain.test.JdbcDaoStaticHelper;
import com.etester.data.domain.user.User;
import com.etester.security.login.models.Role;

@Repository
public class JdbcUserDetailsRepository extends NamedParameterJdbcDaoSupport implements UserDetailsRepository {

	PasswordEncoder passwordEncoder;

	public JdbcUserDetailsRepository(DataSource dataSource, PasswordEncoder passwordEncoder) {
		super();
		setDataSource(dataSource);
//		this.dataSource = dataSource;
		this.passwordEncoder = passwordEncoder;
	}
	
	
	@Override
	public Optional<User> findUserDetailsByUsername(String username) {
		User userDetails = JdbcDaoStaticHelper.findUserByUsername(getNamedParameterJdbcTemplate(), username, true);
		return Optional.ofNullable(userDetails);
		
//		
//		String sqlUser = findByUsernameForLoginSQL;
//        BeanPropertyRowMapper<User> userRowMapper = BeanPropertyRowMapper.newInstance(User.class);
//        Map<String, Object> args = new HashMap<String, Object>();
//        args.put("username", username);
//		// queryForObject throws an exception when the Level is missing.  this should be ignored/swallowed
//        User userDetails = null;
//        try {
//        	userDetails = getNamedParameterJdbcTemplate().queryForObject(sqlUser, args, userRowMapper);
//        } catch (IncorrectResultSizeDataAccessException e) {}
//        if (userDetails != null) {
//    		String sqlRoles = findRolesByUsernameForLoginSQL;
//            BeanPropertyRowMapper<Role> roleRowMapper = BeanPropertyRowMapper.newInstance(Role.class);
//    		// queryForObject throws an exception when the Level is missing.  this should be ignored/swallowed
//            List<Role> rolesList = getNamedParameterJdbcTemplate().query(sqlRoles, args, roleRowMapper);
////            if (rolesList != null && rolesList.size() > 0) {
////            	userDetails.setRoles(Set.copyOf(rolesList));
////            }
//        }
//        return Optional.ofNullable(userDetails);
	}

	@Override
	public Boolean existsByUsername(String username) {
		String sql = existsByUsernameSQL;
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("username", username);
		int existsCount = getNamedParameterJdbcTemplate().queryForObject(sql, args, Integer.class);
		if (existsCount > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Boolean existsByEmailAddress(String emailAddress) {
		String sql = existsByEmailAddressSQL;
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("emailAddress", emailAddress);
		int existsCount = getNamedParameterJdbcTemplate().queryForObject(sql, args, Integer.class);
		if (existsCount > 0) {
			return true;
		} else {
			return false;
		}
	}


//	@Override
//	public User findByUsername(String username) {
//		User u = JdbcDaoStaticHelper.findUserByUsername(getNamedParameterJdbcTemplate(), username);
//		return u;
//	}




}
