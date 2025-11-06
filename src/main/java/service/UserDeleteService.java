package service;

import java.sql.SQLException;

import dao.UserDAO;
import domain.User;
import dto.UserDTO;

public class UserDeleteService {
	
	public boolean userDeleteDo(User user) throws ClassNotFoundException, SQLException{
		
		// 削除したいユーザーのloginIdをDTOに格納
		UserDAO userDAO = new UserDAO();
		UserDTO dto = new UserDTO(user.getLoginId(), null, null, null, null, 0);
		int result = userDAO.delete(dto);
		
		if(result == 1) {
			return true;
		}else {
			return false;
		}
	}

}
