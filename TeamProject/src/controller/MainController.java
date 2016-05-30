package controller;


import java.io.File;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.swing.event.DocumentEvent.EventType;

import com.sun.javafx.collections.MappingChange.Map;
import com.sun.javafx.scene.control.behavior.TableCellBehavior;
import com.sun.javafx.scene.control.skin.TableCellSkin;
import com.sun.javafx.scene.control.skin.TableCellSkinBase;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.util.Callback;
import model.SMergeModel;
import view.*;


public class MainController implements Initializable {

	
	
	
	private SMergeModel model = new SMergeModel();
	
	
	@FXML
	private TableView tableArea;
	
	
	
	@FXML
	private TableColumn<HashMap, String> tableArea_L, tableArea_R;
	
	
	@FXML
	private ImageView btnEdit_L, btnEdit_R;
	
	@FXML
	private ImageView btnSaveEdit_L, btnSaveEdit_R;
	
	@FXML
	private ImageView btnOpen_L, btnOpen_R;
	
	@FXML
	public void diff()
	{
	}
	
	@FXML

	public void edit_L()
	{		
		tableArea_L.setEditable(!tableArea_L.isEditable());
		btnEdit_L.setVisible(!btnEdit_L.isVisible());
		btnSaveEdit_L.setVisible(!btnSaveEdit_L.isVisible());
		tableArea.requestFocus();
	}
	
	@FXML
	public void edit_R()
	{
		tableArea_R.setEditable(!tableArea_R.isEditable());
		btnEdit_R.setVisible(!btnEdit_R.isVisible());
		btnSaveEdit_R.setVisible(!btnSaveEdit_R.isVisible());
		tableArea.requestFocus();
	}
	
	
	@FXML
	public void open_L(){
	
		File file;
		FileChooser fc = new FileChooser();
		try{
			file = fc.showOpenDialog(null);
			tableArea_L.setText(file.getAbsolutePath());
			model.setleftFile(file);
			model.setleftPath(file.getAbsolutePath());
			model.leftLoad();
			
			if(model.getrightFile() != null)
			{
				setTable();
			}
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
			tableArea_R.setText(file.getAbsolutePath());
			model.setrightFile(file);
			model.setrightPath(file.getAbsolutePath());
			model.rightLoad();
			
			if(model.getleftFile() != null)
				setTable();
		}
		catch(NullPointerException e){
			//do nothing
		}
		
	}
	
	public void setTable()
	{
		
		ObservableList<HashMap> allData = FXCollections.observableArrayList();
	
		int leftsize = model.getleftTxt().size();
		int rightsize = model.getrightTxt().size();
		int maxsize = (leftsize > rightsize) ? leftsize : rightsize;
	
		
		for(int i = 0; i < maxsize ; i++)
		{
			HashMap<String, String> dataRow = new HashMap<>();
			if(i < leftsize)
				dataRow.put("left", model.getleftTxt().get(i));
			if(i< rightsize)
				dataRow.put("right", model.getrightTxt().get(i));
			allData.add(dataRow);
		}
		tableArea.setItems(allData);
		
		
	}
	
	
	@FXML
	public void set_L(TableColumn.CellEditEvent<HashMap, String> t) {
		  ((HashMap)t.getTableView().getItems().get(
                  t.getTablePosition().getRow())).put("left", t.getNewValue());
		  
			model.getleftTxt().set(t.getTablePosition().getRow(), t.getNewValue());
		
	}
	
	@FXML
	public void set_R(TableColumn.CellEditEvent<HashMap, String> t) {
		  ((HashMap)t.getTableView().getItems().get(
                  t.getTablePosition().getRow())).put("right", t.getNewValue());
		  
		  
		  model.getrightTxt().set(t.getTablePosition().getRow(), t.getNewValue());
		
	}

	
	@FXML
	public void save_L()
	{
		//diff
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
		
		
		tableArea_L.setCellFactory(new Callback<TableColumn<HashMap, String>, TableCell<HashMap, String>>() {      
				@Override
				public TableCell<HashMap, String> call(TableColumn<HashMap, String> param)
				{
					TableCells tablecell = new TableCells();
					// TODO Auto-generated method stub
					tablecell.getTextField().setOnKeyPressed(new EventHandler<KeyEvent>(){
						@Override
						public void handle(KeyEvent event) {
							// TODO Auto-generated method stub
							if((event.getCode() == KeyCode.BACK_SPACE && tablecell.getTextField().getText().equals("")) )
							{
								tablecell.commitEdit(tablecell.getTextField().getText());
								model.getleftTxt().remove(tablecell.getTableRow().getIndex());
								setTable();
							}
							else if(event.getCode() == KeyCode.ENTER) {
								tablecell.commitEdit(tablecell.getTextField().getText());
								model.getleftTxt().add(tablecell.getTableRow().getIndex()+1, "");
								setTable();
				                }
				             
							else if (event.getCode() == KeyCode.ESCAPE) {
								tablecell.commitEdit(tablecell.getTextField().getText());
					        }
							
						}});
					
					return tablecell;	
				}
				});
		
		tableArea_R.setCellFactory(new Callback<TableColumn<HashMap, String>, TableCell<HashMap, String>>() {      
			@Override
			public TableCell<HashMap, String> call(TableColumn<HashMap, String> param)
			{
				TableCells tablecell = new TableCells();
				// TODO Auto-generated method stub
				tablecell.getTextField().setOnKeyPressed(new EventHandler<KeyEvent>(){
					@Override
					public void handle(KeyEvent event) {
						// TODO Auto-generated method stub
						if(event.getCode() == KeyCode.BACK_SPACE && tablecell.getTextField().getText().equals(""))
						{
							tablecell.commitEdit(tablecell.getTextField().getText());
							model.getrightTxt().remove(tablecell.getTableRow().getIndex());
							setTable();
						}
					}});
				
				return tablecell;	
			}
			});
		
			
		tableArea_L.setCellValueFactory(new MapValueFactory("left"));
		tableArea_R.setCellValueFactory(new MapValueFactory("right"));
		}
	

	
	
	
}


