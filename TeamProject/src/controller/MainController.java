package controller;


import java.io.File;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import com.sun.javafx.collections.MappingChange.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.image.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import model.SMergeModel;
import view.*;


public class MainController implements Initializable {

	
	private SMergeModel model = new SMergeModel();
	
	@FXML
	private TableView textArea;
	@FXML
	private TableColumn<Map, String> textArea_L, textArea_R;
	
	@FXML
	private ImageView btnEdit_L, btnEdit_R;
	
	@FXML
	private ImageView btnOpen_L, btnOpen_R;
	
	@FXML
	public void edit_L()
	{
		textArea_L.setEditable(!textArea_L.isEditable());
		
	}
	
	@FXML
	public void edit_R()
	{
		textArea_R.setEditable(!textArea_R.isEditable());
	
	}
	@FXML
	public void open_L(){
	
		File file;
		FileChooser fc = new FileChooser();
		try{
			file = fc.showOpenDialog(null);
			textArea_L.setText(file.getAbsolutePath());
			model.setleftFile(file);
			model.setleftPath(file.getAbsolutePath());
			model.leftLoad();
		}
		catch(NullPointerException e){
			//do nothing
		}
		
	}

	@FXML
	public void open_R(){
	
		File file;
		FileChooser fc = new FileChooser();
		try{
			file = fc.showOpenDialog(null);
			textArea_R.setText(file.getAbsolutePath());
			model.setrightFile(file);
			model.setrightPath(file.getAbsolutePath());
			model.rightLoad();
		}
		catch(NullPointerException e){
			//do nothing
		}
		
	}
	
	@FXML
	public void save_L()
	{
		
	}
	
	@FXML
	public void save_R()
	{
	}
	@FXML
	public void save_All()
	{
		//not yet;;;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		}
	/*
	
	private ObservableList<Map> generateDataInMap(String[] left, String[] right, int[] difference){
		int max = difference.length;
		ObservableList<Map> allData = FXCollections.observableArrayList();
		
		for(int i = 0; i < max ; i++)
		{
				
		
		}
	}*/
	
}
