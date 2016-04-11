package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.soundconnect.Beans.User;

public class UserDaoImpl implements UserDao{

	final String getUser = "SELECT * FROM users WHERE id=";
	
	@Autowired
	JdbcTemplate jdbcTemplate; 
	
	@Override
	public User getUserById(long id) throws SQLException {
		jdbcTemplate.query(getUser, new long[]{id}, new UserMapper());
		resultSet.
	}

	@Override
	public void deleteUserById(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createUser(User user) {
		// TODO Auto-generated method stub
		
	}

	 class UserMapper implements RowMapper<User>{

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User(rs.getLong("id"));
			user.setConference(rs.getString("conference"));
			
			return null;
		}
		
	}
}
