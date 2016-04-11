package DAO;

import java.sql.SQLException;

import com.soundconnect.Beans.User;

public interface UserDao {
	
	User getUserById(long id) throws SQLException;
	
	void deleteUserById(long id);
	
	void createUser(User user);

}
