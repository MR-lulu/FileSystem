package util;

import java.util.Optional;

import javafx.geometry.Insets;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

/**
 * ClassName: ModelDialog 
 * @Description: TODO
 * @author Mr_blue
 * @date 2017��9��6��
 */
public class ModelDialog {
	private Dialog<Integer> dialog;
	private int index;
	public ModelDialog(int model){
		dialog = new Dialog<Integer>();
		dialog.setTitle("����");
		dialog.setHeaderText("ѡ������");
		
		ButtonType loginButtonType = new ButtonType("ȷ��", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
		
		final ToggleGroup group = new ToggleGroup();
		RadioButton rb1 = new RadioButton("ֻ��");
		rb1.setToggleGroup(group);

		RadioButton rb2 = new RadioButton("��д");
		rb2.setToggleGroup(group);
		
		RadioButton rb3 = new RadioButton("ֻ��ִ��");
		rb3.setToggleGroup(group);
		
		if (model == 1) {
			rb1.setSelected(true);
		} else if (model == 2) {
			rb2.setSelected(true);
		} else {
			rb3.setSelected(true);
		}
		
		group.selectedToggleProperty().addListener((ov,old,newl)->{
			if (group.getSelectedToggle()==rb1) {
				index = 1;
			} else if (group.getSelectedToggle()==rb2) {
				index = 2;
			} else if (group.getSelectedToggle()==rb3) {
				index = 3;
			}
		});
		
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));
		
		grid.add(rb1, 0, 0);
		grid.add(rb2, 0, 1);
		grid.add(rb3, 0, 2);
		
		dialog.getDialogPane().setContent(grid);
		dialog.setResultConverter(dialogButton -> {
		    if (dialogButton == loginButtonType) {
		        return new Integer(index);
		    }
		    return null;
		});
	}
	public Optional<Integer> showAndWait() {
		return dialog.showAndWait();
	}
}
