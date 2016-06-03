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
	@FXML
	private TableView tableArea;
	
	@FXML
	private TableColumn tableArea_Index;
	
	@FXML
	private Menu menuMerge;
	
	@FXML
	private TableColumn<HashMap, String> tableArea_L, tableArea_R;
	
	@FXML
	private TextField textField;
	
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
		model.copyToLeft(textField.getText());
		textField.setText("");
		tableArea.requestFocus();
		setTable();
	}
	
	@FXML
	public void mergeToRight()
	{
		model.copyToRight(textField.getText());	
		textField.setText("");
		tableArea.requestFocus();
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
	
		File file;
		FileChooser fc = new FileChooser();
		try{
			file = fc.showOpenDialog(null);
			tableArea_L.setText(file.getAbsolutePath());
			model.setleftFile(file);
			model.setleftPath(file.getAbsolutePath());
			model.leftLoad();

			if(model.getrightPath() != null)
			{
				initialize(null, null);
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
			model.setrightFile(file);
			model.setrightPath(file.getAbsolutePath());
			model.rightLoad();
			tableArea_R.setText(file.getAbsolutePath());
			if(model.getleftPath() != null)
			{
				initialize(null, null);
				setTable();
			}
		}
		catch(NullPointerException e){
			//do nothing
		}
		
	}
	
	public void setTable()
	{
		/*
		if(model.getleftTxt().get(model.getleftTxt().size()-2).equals("\0") && model.getrightTxt().get(model.getrightTxt().size()-2).equals("\0"))
		{
			model.getleftTxt().remove(model.getleftTxt().size()-2);
			model.getrightTxt().remove(model.getrightTxt().size()-2);

		}
		*/
	
		ObservableList<HashMap> allData = FXCollections.observableArrayList();
	
		int leftsize = model.getleftTxt().size();
		int rightsize = model.getrightTxt().size();

		System.out.println(leftsize);
		System.out.println(rightsize);
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
	
	@FXML
	public void compare()
	{
		if(model.getleftFile().exists() && model.getrightFile().exists())
		{
			menuMerge.setDisable(false);
			btnMergeto_L.setDisable(false);
			btnMergeto_R.setDisable(false);
			textField.setDisable(false);
			

						
			tableArea_L.setCellFactory(column ->{return new EditableTableCell(){
		
				@Override
		        protected void updateItem(String item, boolean empty) {
					
		            getStyleClass().setAll();
		            try{
		            	if(model.getleftTxt().get(this.getTableRow().getIndex()).equals(model.getrightTxt().get(this.getTableRow().getIndex())))
		            	{
		            		getStyleClass().add("same");
		            	}
		            	else// 
		            	{
		            		if(!model.getleftTxt().get(this.getTableRow().getIndex()).equals("\0"))
		            			getStyleClass().add("different");
		            	}
		            }
		            catch(IndexOutOfBoundsException e)
		            {
		            	//Certainly! It doesn't care, anymore.
		            }
		            
		            
		            super.updateItem(item, empty);
		            this.getTextField().setOnKeyPressed(new EditHandler(this));
					  
		            
		        }
			};});
			
		

			tableArea_R.setCellFactory(column ->{return new EditableTableCell(){
				
				@Override
		        protected void updateItem(String item, boolean empty) {
					
		            
		            getStyleClass().setAll();
		            try{
		            	if(model.getleftTxt().get(this.getTableRow().getIndex()).equals(model.getrightTxt().get(this.getTableRow().getIndex())))
		            	{
		            		getStyleClass().add("same");
		            	}
		            	else//
		            	{
		            		if(!model.getrightTxt().get(this.getTableRow().getIndex()).equals("\0"))
		            			getStyleClass().add("different");
		            	}
		            }
		            /*try{
		            	if(model.gettxtBoolean().get(this.getTableRow().getIndex()))
		            	{
		            		getStyleClass().add("same");
		            	}
		            	else if(model.gettxtBoolean().get(this.getTableRow().getIndex()) == false)
		            	{
		            		if(!model.getrightTxt().get(this.getTableRow().getIndex()).equals("\0"))
		            			getStyleClass().add("different");
		            	}
		            }*/
		            catch(IndexOutOfBoundsException e)
		            {
		            	//Certainly! It doesn't care, anymore.
		            }
		            
		            
		            super.updateItem(item, empty);
		            this.getTextField().setOnKeyPressed(new EditHandler(this));
					   
		        }
			};});
		
			model.lcsDiff();
			setTable();
		}

	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		textField.setDisable(true);
		btnMergeto_L.setDisable(true);
		btnMergeto_R.setDisable(true);
		menuMerge.setDisable(true);
		tableArea_L.setCellFactory(new callback());				
		tableArea_R.setCellFactory(new callback());
		
		
		
		tableArea_Index.setCellValueFactory(new MapValueFactory("index"));
		tableArea_L.setCellValueFactory(new MapValueFactory("left"));
		tableArea_R.setCellValueFactory(new MapValueFactory("right"));
		
		}
	
	
	private class callback implements Callback<TableColumn<HashMap, String>, TableCell<HashMap, String>>
	{

		@Override
		public TableCell<HashMap, String> call(TableColumn<HashMap, String> param) {
			
			// TODO Auto-generated method stub
			EditableTableCell tablecell = new EditableTableCell();
			tablecell.getTextField().setOnKeyPressed(new EditHandler(tablecell));
			
			
			return tablecell;
		}
		
	}
		
	private class EditHandler implements EventHandler<KeyEvent>
	{
		EditableTableCell tablecell;
		
		EditHandler(EditableTableCell tablecell)
		{
			this.tablecell = tablecell;
		}
		
			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
					if(event.getCode() == KeyCode.ENTER) {
						try{
						if(tablecell.getTableColumn().equals(tableArea_L)){//왼쪽 테이블
							System.out.println(tablecell.getTextField().getCaretPosition());
							if(tablecell.getTextField().getCaretPosition() == 0)//커서가 맨앞일 때
							{
								model.getleftTxt().add(tablecell.getTableRow().getIndex(), "\n");
								model.getrightTxt().add(model.getrightTxt().size()-1, "\0");
							
							}
							else//커서가 맨앞이 아닐 때.
							{	
								String str = tablecell.getTextField().getText();
								String front = str.substring(0, tablecell.getTextField().getCaretPosition())+"\n";
								String back = str.substring(tablecell.getTextField().getCaretPosition(), tablecell.getTextField().getText().length());
								
								model.getleftTxt().set(tablecell.getTableRow().getIndex(), front);
								model.getleftTxt().add(tablecell.getTableRow().getIndex()+1, back);
								model.getrightTxt().add(model.getrightTxt().size()-1, "\0");
							
							}
						}
						else{//오른쪽 테이블
							if(tablecell.getTextField().getCaretPosition() == 0)//커서가 맨 앞일 떄.
							{
								model.getrightTxt().add(tablecell.getTableRow().getIndex(), "\n");
								model.getleftTxt().add(model.getleftTxt().size()-1, "\0");
							
							}
							else//커서가 맨앞이 아닐 때
							{
								String str = tablecell.getTextField().getText();
								String front = str.substring(0, tablecell.getTextField().getCaretPosition())+"\n";
								String back = str.substring(tablecell.getTextField().getCaretPosition(), tablecell.getTextField().getText().length());
								
								model.getrightTxt().set(tablecell.getTableRow().getIndex(), front);
								model.getrightTxt().add(tablecell.getTableRow().getIndex()+1, back);
								model.getleftTxt().add(model.getleftTxt().size()-1, "\0");
							}	
						}
						}
						catch(IndexOutOfBoundsException Idontknow)
						{
							
						}
					}
					else if (event.getCode() == KeyCode.ESCAPE) {
						if(tablecell.getTableColumn().equals(tableArea_L)){//왼쪽
							model.getleftTxt().set(tablecell.getTableRow().getIndex(), tablecell.getTextField().getText());
						}
						else{//오른쪽
							model.getrightTxt().set(tablecell.getTableRow().getIndex(), tablecell.getTextField().getText());
			            }
						
			        }
					else if(event.getCode() == KeyCode.BACK_SPACE && tablecell.getTextField().getText().equals(""))
					{

						if(tablecell.getTableColumn().equals(tableArea_L)){
							model.getleftTxt().add(model.getleftTxt().size()-1, "\0");
							model.getleftTxt().remove(tablecell.getTableRow().getIndex());
						}
						else{
							model.getrightTxt().add(model.getrightTxt().size()-1, "\0");
							model.getrightTxt().remove(tablecell.getTableRow().getIndex());
						}
					}
					else if(event.getCode() == KeyCode.DELETE && tablecell.getTextField().getCaretPosition() == tablecell.getTextField().getText().length())
					{
						if(tablecell.getTableColumn().equals(tableArea_L)){
							model.getleftTxt().add(model.getleftTxt().size()-1, "\0");
							model.getleftTxt().set(tablecell.getTableRow().getIndex(), tablecell.getTextField().getText()+model.getleftTxt().get(tablecell.getTableRow().getIndex()+1));
							//맨뒤에 테스트해봐야함.
							model.getleftTxt().remove(tablecell.getTableRow().getIndex()+1);
						}
						else{
							model.getrightTxt().add(model.getrightTxt().size()-1, "\0");
							model.getrightTxt().set(tablecell.getTableRow().getIndex(), tablecell.getTextField().getText()+model.getrightTxt().get(tablecell.getTableRow().getIndex()+1));
							
							model.getrightTxt().remove(tablecell.getTableRow().getIndex());
						}
					}
					setTable();

			}
	
	}
}


