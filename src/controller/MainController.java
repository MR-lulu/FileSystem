package controller;

import java.util.Optional;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseButton;
import javafx.util.Callback;
import model.Document;
import util.SimpleSuccessAlert;

/**
 * ClassName: MainController 
 * @Description: TODO
 * @author Mr_blue
 * @date 2017��9��5��
 */
public class MainController {
	@FXML private TableView<Document> tableView;
	@FXML private TableColumn<Document, String> nameColumn;
	@FXML private TableColumn<Document, String> modifyTimeColumn;
	@FXML private TableColumn<Document, String> typeColumn;
	@FXML private TableColumn<Document, Integer> sizeColumn;
	private ObservableList<Document> documents = FXCollections.observableArrayList();
	
	@FXML private Label ownerLabel;
	@FXML private Label createTimeLabel;
	@FXML private Label positionLabel;
	@FXML private Label modelLabel;

	@FXML private Button cdReturnButton;
	@FXML private MenuBar menuBar;
	@FXML private ContextMenu tableViewMenu;

	@FXML private void initialize() {
		setMenu();
		setTableView();
		setTableRow();
		setTableColumn();
	}

	private void setMenu(){
		MenuItem itemDir = new MenuItem("�½��ļ���");
		MenuItem itemFile = new MenuItem("�½��ļ�");
		MenuItem userItem,aboutItem;
		
		Menu fileMenu = menuBar.getMenus().get(0);
		Menu toolMenu = menuBar.getMenus().get(1);
		Menu helpMenu = menuBar.getMenus().get(2);
		
		tableViewMenu.getItems().clear();
		tableViewMenu.getItems().addAll(itemFile,itemDir);
		
		toolMenu.setText("Tool");
		userItem = toolMenu.getItems().get(0); 
		userItem.setText("�û�����");
		userItem.setOnAction(e->{
			//�û��������������
		});
		
		fileMenu.getItems().clear();
		fileMenu.getItems().addAll(itemFile,itemDir);
		itemDir.setOnAction(e->{
			
		});
		itemFile.setOnAction(e->{
			
		});
		
		aboutItem = helpMenu.getItems().get(0); 
		aboutItem.setText("����");
		aboutItem.setOnAction(e->{
			//���ڵ�����
		});	
	}
	private void setTableView() {
		documents.add(new Document("home",0,0,"pcy","2017-9-4","2017-9-4",1));
		documents.add(new Document("data.txt",1,0,"admin","2017-9-4","2017-9-4",2));
		documents.add(new Document("bin",0,0,"admin","2017-9-4","2017-9-4",1));
		tableView.setItems(documents);
		tableView.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> {
					showFileDetails(newValue);
				});
	}
	private void setTableColumn() {
		nameColumn.setCellValueFactory(value->value.getValue().nameProperty());
		modifyTimeColumn.setCellValueFactory(value->value.getValue().modifyTimeProperty());
		typeColumn.setCellValueFactory(value->new SimpleObjectProperty<>(value.getValue().getTypeString()));
		sizeColumn.setCellValueFactory(value->value.getValue().sizeProperty().asObject());
	}
	private void setTableRow() {
		tableView.setRowFactory(new Callback<TableView<Document>, TableRow<Document>>() {
			@Override
			public TableRow<Document> call(TableView<Document> param) {
				// TODO Auto-generated method stub
				TableRow<Document> row = new TableRow<Document>();
				row.setOnMouseClicked(e->{
					if (e.getButton() == MouseButton.PRIMARY &&
							!row.isEmpty()) {
						row.setContextMenu(getContextMenu());
					}
				});
				return row;
			}
		});
	}
	
	private void showFileDetails(Document document){
		if (document!=null) {
			ownerLabel.setText(document.getOwner());
			createTimeLabel.setText(document.getCteateTime());
			positionLabel.setText("");
			modelLabel.setText("");
		} else {
			ownerLabel.setText("");
			createTimeLabel.setText("");
			positionLabel.setText("");
			modelLabel.setText("");
		}
	}
	private ContextMenu getContextMenu(){
		MenuItem openItem = new MenuItem("��");
		MenuItem deleteItem = new MenuItem("ɾ��");
		MenuItem shareItem = new MenuItem("����");
		MenuItem renameItem = new MenuItem("������");
		MenuItem setmodeItem = new MenuItem("��������");
		
		String name = tableView.getSelectionModel().selectedItemProperty().getValue().getName();
		renameItem.setOnAction(e->{
			TextInputDialog dialog = new TextInputDialog(name);
			dialog.setTitle("������");
			dialog.setHeaderText("");
			dialog.setContentText("������������:");
			
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()) {
				tableView.getSelectionModel().selectedItemProperty().getValue().setName(result.get());
			}
		});
		openItem.setOnAction(e->{
			int type = tableView.getSelectionModel().selectedItemProperty().getValue().getType();
			//������ļ�
			if (type == 1) {
				Dialog<String> dialog = new Dialog<>();
				dialog.setTitle(name);
				dialog.setHeaderText("");
				
				ButtonType buttonType = new ButtonType("����", ButtonData.OK_DONE);
				dialog.getDialogPane().getButtonTypes().addAll(buttonType, ButtonType.CANCEL);
				
				TextArea textArea = new TextArea();
				dialog.getDialogPane().setContent(textArea);
				
				dialog.showAndWait();
			} else {
				//������ļ���
			}
		});
		deleteItem.setOnAction(e->{
			SimpleSuccessAlert alert = new SimpleSuccessAlert("ɾ��","","�Ƿ�Ҫɾ�����ļ�(��)");
			Optional result = alert.showAndWait();
			if (result.isPresent()) {
				int i = tableView.getSelectionModel().getSelectedIndex();
				documents.remove(i);
			}
		});
		shareItem.setOnAction(e->{
			SimpleSuccessAlert alert = new SimpleSuccessAlert("����","","�Ƿ�Ҫ������ļ�");
			Optional result = alert.showAndWait();
			if (result.isPresent()) {
				
			}
		});
		setmodeItem.setOnAction(e->{
			
		});
		
		ContextMenu menu = new ContextMenu(openItem,deleteItem,renameItem,setmodeItem,shareItem);
		return menu;
	}
}
