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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.util.Callback;
import model.SMergeModel;
import view.*;


public class MainController implements Initializable {

	private SMergeModel model = new SMergeModel();
	int i = 0;
	@FXML
	private TableView tableArea;
	
	@FXML
	private TableColumn tableArea_Index;
	
	@FXML
	private Menu menuMerge;
	
	@FXML
	private TableColumn<HashMap, String> tableArea_L, tableArea_R;
	
	
	@FXML
	private ImageView btnMergeto_L, btnMergeto_R;
	
	@FXML
	private ImageView btnEdit_L, btnEdit_R;
	
	@FXML
	private ImageView btnSaveEdit_L, btnSaveEdit_R;
	
	@FXML
	private ImageView btnOpen_L, btnOpen_R;
	
	@FXML
	private ImageView btnCompare;
	
	
	@FXML
	public void mergeToLeft()
	{	
		model.copyToLeft();
		setTable();
	}
	
	@FXML
	public void mergeToRight()
	{
		model.copyToRight();
		setTable();
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
	/*
		File file;
		FileChooser fc = new FileChooser();
		try{
			file = fc.showOpenDialog(null);
			tableArea_L.setText(file.getAbsolutePath());
			model.setleftFile(file);
			model.setleftPath(file.getAbsolutePath());
			model.leftLoad();

			isFileOpened_L = true;
			if(model.getrightFile() != null)
			{
				setTable();
			}
		}
		catch(NullPointerException e){
			//do nothing
		}
		*/
		ArrayList<String> left = new ArrayList<String>();
		left.add("a");
		left.add("b");
		left.add("\0");
		left.add("c");
		left.add("d");
		left.add("\n");
		left.add("e");
		left.add("\n");
		left.add("\0");
		left.add("\0");
		left.add("\n");
		
		ArrayList<String> right = new ArrayList<String>();
		right.add("a");
		right.add("c");
		right.add("\n");
		right.add("b");
		right.add("d");
		right.add("\n");
		right.add("f");
		right.add("e");
		right.add("e");
		right.add("\0");
		right.add("\n");

		model.setleftTxt(left);
		model.setrightTxt(right);
		setTable();
	}

	@FXML
	public void open_R(){

		System.out.println(model.getleftTxt());
		System.out.println(model.getrightTxt());
	/*
		File file;
		FileChooser fc = new FileChooser();
		try{
			file = fc.showOpenDialog(null);
			model.setrightFile(file);
			model.setrightPath(file.getAbsolutePath());
			model.rightLoad();
			isFileOpened_R = true;
			tableArea_R.setText(file.getAbsolutePath());
			if(model.getleftFile() != null)
			{
				setTable();
			}
		}
		catch(NullPointerException e){
			//do nothing
		}
		*/
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
			dataRow.put("index", String.valueOf(i));
			dataRow.put("left", model.getleftTxt().get(i));
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
		  	model.getleftTxt().set(t.getTablePosition().getRow(), t.getNewValue());
		
	}

	
	@FXML
	public void save_L()
	{/*
		tableArea_L.setCellFactory(new Callback<TableColumn<HashMap, String>, TableCell<HashMap, String>>() {      
			@Override
			public TableCell<HashMap, String> call(TableColumn<HashMap, String> param)
			{
				// TODO Auto-generated method stub
				EditableTableCell tablecell = new EditableTableCell(){
					@Override
					 protected void updateItem(String item, boolean empty) {
						
			            super.updateItem(item, empty);
			            
			            if(model.gettxtBoolean().get(this.getTableRow().getIndex()))
			            {
			            	setStyle("-fx-background-color: rgba(255, 248, 33, .4);");
			            }
			            else if(model.gettxtBoolean().get(this.getTableRow().getIndex()) == false)
			            {
		                    setStyle(" -fx-background-color: rgba(255, 159, 160, .4);");

			            	System.out.println(getTableRow().getIndex());
			            	System.out.println(model.gettxtBoolean().get(this.getTableRow().getIndex()));
			         
			            }
			            
			            this.getTextField().setOnKeyPressed(new EditHandler(this));
			            
			        }
					
				};
				
				return tablecell;	
			}
			});*/
		
		
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

	@FXML
	public void compare()
	{
		model.lcsDiff();
		
		if(model.getleftFile().exists() && model.getrightFile().exists())
		{
			menuMerge.setDisable(false);
			btnMergeto_L.setDisable(true);
			btnMergeto_R.setDisable(true);
		}
		
		tableArea_L.setCellFactory(column ->{return new EditableTableCell(){
			
			@Override
	        protected void updateItem(String item, boolean empty) {
				
	            super.updateItem(item, empty);
	            
	            getStyleClass().setAll();
	            if(model.gettxtBoolean().get(this.getTableRow().getIndex()))
	            {
	            	getStyleClass().add("same");
	            }
	            else if(model.gettxtBoolean().get(this.getTableRow().getIndex()) == false)
	            {
	            	getStyleClass().add("different");
	            }
	            
	            this.getTextField().setOnKeyPressed(new EditHandler(this));
	            
	        }
			
			
			
		};});
		
			tableArea_R.setCellFactory(column ->{return new EditableTableCell(){
			
			@Override
	        protected void updateItem(String item, boolean empty) {
				
	            super.updateItem(item, empty);
	            
	            getStyleClass().setAll();
	            if(model.gettxtBoolean().get(this.getTableRow().getIndex()))
	            {
	            	getStyleClass().add("same");
	            }
	            else if(model.gettxtBoolean().get(this.getTableRow().getIndex()) == false)
	            {
	            	getStyleClass().add("different");
	            }
	            
	            this.getTextField().setOnKeyPressed(new EditHandler(this));
	            
	        }
			
			
			
		};});
		
		setTable();

	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		
		model.setleftFile(new File("/Users/LeeHyungJoo/Downloads"));//삭제요망
		model.setrightFile(new File("/Users/LeeHyungJoo/Downloads"));//삭제요망
		
		
		tableArea_L.setCellFactory(new Callback<TableColumn<HashMap, String>, TableCell<HashMap, String>>() {      
				@Override
				public TableCell<HashMap, String> call(TableColumn<HashMap, String> param)
				{
					// TODO Auto-generated method stub
					EditableTableCell tablecell = new EditableTableCell();
					tablecell.getTextField().setOnKeyPressed(new EditHandler(tablecell));
					
					
					return tablecell;	
				}
				});
		
		
		tableArea_R.setCellFactory(new Callback<TableColumn<HashMap, String>, TableCell<HashMap, String>>() {      
			@Override
			public TableCell<HashMap, String> call(TableColumn<HashMap, String> param)
			{				// TODO Auto-generated method stub

				EditableTableCell tablecell = new EditableTableCell();
				tablecell.getTextField().setOnKeyPressed(new EditHandler(tablecell));
				
				return tablecell;	
			}
			});
		
		tableArea_Index.setCellValueFactory(new MapValueFactory("index"));
		tableArea_L.setCellValueFactory(new MapValueFactory("left"));
		tableArea_R.setCellValueFactory(new MapValueFactory("right"));
		
		}
	

	
	
	class EditHandler implements EventHandler<KeyEvent>
	{
		EditableTableCell tablecell;
		
		EditHandler(EditableTableCell tablecell)
		{
			this.tablecell = tablecell;
		}
		
			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				try{

					if(event.getCode() == KeyCode.ENTER) {
						
						if(tablecell.getTableColumn().equals(tableArea_L)){
							model.getleftTxt().set(tablecell.getTableRow().getIndex(), tablecell.getTextField().getText());
							model.getleftTxt().add(tablecell.getTableRow().getIndex()+1, "\n");
							model.getrightTxt().add("\0");
						}
						else{
							model.getrightTxt().set(tablecell.getTableRow().getIndex(), tablecell.getTextField().getText());
							model.getrightTxt().add(tablecell.getTableRow().getIndex()+1, "\n");
							model.getleftTxt().add("\0");
						
			                }
						}
		             
					else if (event.getCode() == KeyCode.ESCAPE) {
						if(tablecell.getTableColumn().equals(tableArea_L)){
							model.getleftTxt().set(tablecell.getTableRow().getIndex(), tablecell.getTextField().getText());
						}
						else{
							model.getrightTxt().set(tablecell.getTableRow().getIndex(), tablecell.getTextField().getText());
			            }
						
			        }
					
					else if(event.getCode() == KeyCode.BACK_SPACE && tablecell.getTextField().getText().equals(""))
					{

						if(tablecell.getTableColumn().equals(tableArea_L)){
							model.getleftTxt().add("\0");
							model.getleftTxt().remove(tablecell.getTableRow().getIndex());
						}
						else{
							model.getrightTxt().add("\0");
							model.getrightTxt().remove(tablecell.getTableRow().getIndex());
						}
					}
					setTable();
				}
				catch(Exception S)
				{
					System.out.println(S);
				}
			}
	
	}
}


