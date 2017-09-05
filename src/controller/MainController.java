package controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseButton;
import javafx.util.Callback;
import model.Document;

/**
 * ClassName: MainController 
 * @Description: TODO
 * @author Mr_blue
 * @date 2017年9月5日
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
	@FXML private ContextMenu tableViewMenu;

	@FXML private void initialize() {
		setMenu();
		setTableView();
		setTableRow();
		setTableColumn();
	}

	private void setMenu(){
		MenuItem itemDir = new MenuItem("新建文件夹");
		MenuItem itemFile = new MenuItem("新建文件");
		tableViewMenu.getItems().clear();
		tableViewMenu.getItems().add(itemFile);
		tableViewMenu.getItems().add(itemDir);
		
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
				MenuItem openItem = new MenuItem("打开");
				MenuItem deleteItem = new MenuItem("删除");
				MenuItem shareItem = new MenuItem("共享");
				MenuItem renameItem = new MenuItem("重命名");
				MenuItem setmodeItem = new MenuItem("设置属性");
				
				renameItem.setOnAction(e->{
					String name = tableView.getSelectionModel().selectedItemProperty().getValue().getName();
					TextInputDialog dialog = new TextInputDialog(name);
					dialog.setTitle("重命名");
					dialog.setHeaderText("");
					dialog.setContentText("请输入新名字:");
					
					dialog.showAndWait();
				});
				
				ContextMenu menu = new ContextMenu(openItem,deleteItem,renameItem,setmodeItem,shareItem);
				row.setOnMouseClicked(e->{
					if (e.getButton() == MouseButton.PRIMARY &&
							!row.isEmpty()) {
						row.setContextMenu(menu);
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
}
