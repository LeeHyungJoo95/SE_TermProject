package view;


import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.event.DocumentEvent.EventType;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class TableCells extends TableCell<HashMap, String> {
      
	
	  private static final String CSS_ORIGINAL = "cell-renderer-original";
	  private static final String CSS_CHANGED = "cell-renderer-changed";
	  private static final String CSS_ERROR = "cell-renderer-error";
	  private static final String CSS_ERROR_AND_CHANGED = "cell-renderer-error-and-changed";
 
	  private TextField textField;

	  public TextField getTextField()
	  {
		  return textField;
	  }
	  
	  public TableCells()
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
         
			//색 입히기
          getStyleClass().removeAll(CSS_ORIGINAL, CSS_CHANGED, CSS_ERROR, CSS_ERROR_AND_CHANGED);
          updateStyles(item);
         
          
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
      }
	  
		

	  private void updateStyles(String item) {
          if (item == null) {
              return;
          }
          if (item.startsWith("R") || item.startsWith("j")) {
              getStyleClass().add(CSS_CHANGED);
          }
          else if (item.startsWith("e")) {
              getStyleClass().add(CSS_ERROR);
          }
          else if (item.startsWith("m")) {
              getStyleClass().add(CSS_ERROR_AND_CHANGED);
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
	
	