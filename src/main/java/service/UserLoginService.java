package service;

import java.sql.SQLException;

import dao.UserDAO;
import domain.User;
import dto.UserDTO;

public class UserLoginService{
	
	public User loginCheck(String loginId, String password) {
		
		UserDAO userDAO = new UserDAO();
		//Controllerから受け取ったパラメーターをDAOのメソッドへ渡す
		UserDTO userDTO = null;
		try {
			userDTO = userDAO.selectByLoginId(loginId);
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		//DAOでのselect成功時はUserDTOにログインしたユーザ情報が格納されている
		//ユーザーが入力した値とDBの値が等しいかチェック
		if(userDTO != null && userDTO.getPassword().equals(password)) {
			
			//等しい場合はDTOの情報をDomainに移行
			User user = new User(userDTO.getLoginId(),userDTO.getId_check(), userDTO.getPassword(),userDTO.getName(), userDTO.getNew_list(), userDTO.getRental_books());
			user.setId(userDTO.getId());
			return user;
		}
		return null;
	}
}