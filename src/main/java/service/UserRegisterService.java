package service;

import java.sql.SQLException;

import dao.UserDAO;
import domain.User;
import dto.UserDTO;

public class UserRegisterService {
	public boolean userEntryConfirm(User user) throws ClassNotFoundException, SQLException {
		
		//DBにユーザーが既に存在するかチェック
		UserDAO userDAO = new UserDAO();
		UserDTO userDTO = userDAO.selectByLoginId(user.getLoginId());
		
		//ユーザーが存在しない場合→登録内容確認画面に画面遷移させる
		if(userDTO == null) {
			return true;
		}else {
			//既に存在する場合
			return false;
		}
	}
	public boolean userEntryDo(User user) throws ClassNotFoundException, SQLException {
		UserDAO userDAO = new UserDAO();
		UserDTO dto = new UserDTO(user.getLoginId(),false, user.getPassword(),user.getName(), user.getNew_list(), 0);
		
		int result = userDAO.insert(dto);
		if(result == 1) {
			return true;
		}else {
			return false;
		}
	}
}