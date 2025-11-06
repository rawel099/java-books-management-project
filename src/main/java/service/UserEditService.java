package service;

import java.sql.SQLException;

import dao.UserDAO;
import domain.User;
import dto.UserDTO;


public class UserEditService {
	
	public boolean userEditDo(User user) throws ClassNotFoundException, SQLException {
		UserDTO dto = new UserDTO(user.getLoginId(),false, user.getPassword(),user.getName(), user.getNew_list() , 0);
		UserDAO userDAO = new UserDAO();
		int result = userDAO.edit(dto);
		
		if(result == 1) {
			return true;
		}else {
			return false;
		}
	}

}
