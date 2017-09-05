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
import javafx.scene.input.MouseButton;
import javafx.util.Callback;
import model.Document;

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
		MenuItem itemDir = new MenuItem("�½��ļ���");
		MenuItem itemFile = new MenuItem("�½��ļ�");
		tableViewMenu.getItems().clear();
		tableViewMenu.getItems().add(itemFile);
		tableViewMenu.getItems().add(itemDir);
	}
	private void setTableView() {
		ObservableList<Document> documents = FXCollections.observableArrayList();
		documents.add(new Document("bin",1,0,"admin","2017-9-4","2017-9-4",1));
		tableView.setItems(documents);
		tableView.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> {
					
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
				MenuItem openItem = new MenuItem("��");
				openItem.setOnAction(e->{
					System.out.println("���ļ�");
				});
				MenuItem deleteItem = new MenuItem("ɾ��");
				MenuItem renameItem = new MenuItem("������");
				MenuItem setmodeItem = new MenuItem("��������");
				ContextMenu menu = new ContextMenu(openItem,deleteItem,renameItem,setmodeItem);
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
}
