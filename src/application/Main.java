package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.ViewHelp;

/**
 * 
 * ClassName: Main 
 * @Description: 程序入口
 * @author Mr_blue
 * @date 2017年6月27日
 */
public class Main extends Application{
	private Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage; //程序主窗口
		initRootlayout();
		primaryStage.setHeight(500);   //设置窗口高度
		primaryStage.setWidth(800);		//设置窗口宽度
		primaryStage.setTitle("模拟文件系统");
		primaryStage.show();   //显示窗口
	}
	
	//初始化加载页面
	private void initRootlayout(){
		Parent root = null;
		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(ViewHelp.class.getResource(ViewHelp.mainView));
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}

