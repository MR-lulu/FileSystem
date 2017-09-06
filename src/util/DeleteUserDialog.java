package util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javafx.scene.control.ChoiceDialog;
import model.User;

/**
 * ClassName: DeleteUserDialog 
 * @Description: TODO
 * @author Mr_blue
 * @date 2017年9月6日
 */
public class DeleteUserDialog {
	private ChoiceDialog<String> dialog; 
	public DeleteUserDialog(){
		List<String> choices = new ArrayList<>();
		UserManage userManage = UserManage.getInstance();
		
		for (User user : userManage.getUsers()) {
			choices.add(user.getUsername());
			System.out.println(user.getUsername());
		}
	
		ChoiceDialog<String> dialog = new ChoiceDialog<>("", choices);
		dialog.setTitle("注销用户");
		dialog.setHeaderText("");
		dialog.setContentText("选择需要注销的用户");
	}
	public Optional<String> showAndWait(){
		return dialog.showAndWait();
	}
}
