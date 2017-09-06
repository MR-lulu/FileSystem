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
 * @date 2017��9��6��
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
		dialog.setTitle("ע���û�");
		dialog.setHeaderText("");
		dialog.setContentText("ѡ����Ҫע�����û�");
	}
	public Optional<String> showAndWait(){
		return dialog.showAndWait();
	}
}
