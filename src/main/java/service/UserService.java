package service;

import java.sql.SQLException;

import dao.UserDAO;
import dto.UserDTO;

public class UserService{
	
    private UserDAO userDAO = new UserDAO();
	private String loginId;

    public boolean Id_Check(Boolean id_check) throws ClassNotFoundException, SQLException {
    	UserDTO userDTO = userDAO.selectByLoginId(loginId);
        return userDTO != null ;
    }
}