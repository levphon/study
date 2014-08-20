package spring.jdbc.domain.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import spring.jdbc.domain.Dept;

public class DeptMapper implements RowMapper<Dept> {

	@Override
	public Dept mapRow(ResultSet rs, int rowNum) throws SQLException {
		Dept dept = new Dept();
		dept.setDeptno(rs.getInt("deptno"));
		dept.setDname(rs.getString("dname"));
		dept.setLoc(rs.getString("loc"));
		return dept;
	}

}
