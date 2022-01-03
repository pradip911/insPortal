package com.myjavablog.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RoleRepoJDBCTemplateImpl implements RoleRepoJDBCTemplate{

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Override
	public String roleName(String user_id) {
		String sql = "SELECT r.ROLE FROM USER_ROLE ur JOIN ROLE r ON ur.ROLE_ID=r.ROLE_ID WHERE ur.USER_ID = ?";

        return jdbcTemplate.queryForObject(
                sql, new Object[]{user_id}, String.class);
	}

}
