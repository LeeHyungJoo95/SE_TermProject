package view;


import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.event.DocumentEvent.EventType;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class EditableTableCell extends TableCell<HashMap, String> {
      
	
	  private TextField textField;

	  public TextField getTextField()
	  {
		  return textField;
	  }
	  
	  public EditableTableCell()
	  {
		  textField = new TextField();
		  
		  textField.focusedProperty().addListener(new ChangeListener<Boolean>(){
          @Override
          public void changed(ObservableValue<? extends Boolean> arg0, 
              Boolean arg1, Boolean arg2) {
                  if (!arg2) {
                      commitEdit(textField.getText());
                     }
          }    
		  });
		  
		  
		  
	  }
	  
	  
	  @Override
		protected void updateItem(final String item, final boolean empty) {

			super.updateItem(item, empty);
          
          //수정
          if (empty) {
              setText(null);
              setGraphic(null);
          } else {
          	
              if (isEditing()) {
                  if (textField != null) {
                      textField.setText(getString());
                  }

                  setText(null);
                  setGraphic(textField);
              } else {
                  setText(getString());
                  setGraphic(null);
              }
          } 
          
          this.getStyleClass().remove("vacant");
          try{
        	  if(item.equals("\0"))
        	  {
        		  this.getStyleClass().add("vacant");
        	  }
          }
          catch(NullPointerException SIBAL)
          {
        	  //I don't know why it happened;
          }
        }
	  
	  
	  
		
      
      
      
      @Override
      public void startEdit() {
          if (!isEmpty()) {
              super.startEdit();

              createTextField();
              setText(null);
              setGraphic(textField);
              textField.selectAll();
          }
      }

      @Override
      public void cancelEdit() {
    	  super.cancelEdit();
          setText(textField.getText());
          setGraphic(null);
      }


      
      private void createTextField() {

          textField.setText(getString());
          textField.setMinWidth(this.getWidth() - this.getGraphicTextGap()* 2);
          
		  
      }

      private String getString() {
          return (getItem() == null) ? "" : getItem().toString();
      }

      
	}
	
	