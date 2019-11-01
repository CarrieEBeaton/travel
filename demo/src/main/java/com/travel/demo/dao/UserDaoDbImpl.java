package com.travel.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.travel.demo.dto.Country;
import com.travel.demo.dto.User;

@Repository
public class UserDaoDbImpl implements UserDao {

	private static final String SQL_INSERT_USER = "insert into usertbl " + "(first_name, last_name, email, country_id) "
			+ "values (?, ?, ?, ?)";
	private static final String SQL_DELETE_USER = "delete from usertbl where user_id = ?";
	private static final String SQL_SELECT_USER = "select * from usertbl where user_id = ?";
	private static final String SQL_UPDATE_USER = "update usertbl set "
			+ "first_name = ?, last_name = ?, email = ?, country_id = ? " + "where user_id = ?";
	private static final String SQL_SELECT_ALL_USERS = "select * from usertbl";
	private static final String SQL_SELECT_USERS_BY_LAST_NAME = "select * from usertbl where last_name = ?";

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<User> getAllUsers() {
		List<User> userList = jdbcTemplate.query(SQL_SELECT_ALL_USERS, new UserMapper());
		return userList;

	}

	@Override
	public User getUserById(Long userId) {
		try {
			return jdbcTemplate.queryForObject(SQL_SELECT_USER, new UserMapper(), userId);
		} catch (EmptyResultDataAccessException ex) {
			// there were no results for the given user id - we just
			// want to return null in this case
			return null;
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public User addUser(User user) {

		if (user.getCountry() == null) {
			jdbcTemplate.update(SQL_INSERT_USER, user.getFirstName(), user.getLastName(), user.getEmail(), null);
		} else {
			jdbcTemplate.update(SQL_INSERT_USER, user.getFirstName(), user.getLastName(), user.getEmail(),
					user.getCountry().getCountryId());
		}

		int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

		user.setUserId(new Long(newId));
		return user;
	}

	@Override
	public void deleteUser(Long userId) {
		jdbcTemplate.update(SQL_DELETE_USER, userId);

	}

	@Override
	public User updateUser(User user) {

		int userId;
		if (user.getCountry() == null) {
			userId = jdbcTemplate.update(SQL_UPDATE_USER, user.getFirstName(), user.getLastName(), user.getEmail(), null,  user.getUserId());
		} else {
			userId = jdbcTemplate.update(SQL_UPDATE_USER, user.getFirstName(), user.getLastName(), user.getEmail(),
					user.getCountry().getCountryId(),  user.getUserId());
		}

		User updatedUser = getUserById(new Long(userId));
		return updatedUser;
	}

	private static final class UserMapper implements RowMapper<User> {

		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setUserId(rs.getLong("user_id"));
			user.setFirstName(rs.getString("first_name"));
			user.setLastName(rs.getString("last_name"));
			user.setEmail(rs.getString("email"));
//        	user.setCountry(rs.getLong("country_id"));
			return user;
		}
	}

}
