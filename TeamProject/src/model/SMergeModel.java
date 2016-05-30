package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SMergeModel {
	String leftPath;			
	String rightPath;			
	ArrayList<String> leftTxt;		
	ArrayList<String> rightTxt;		
	ArrayList<Boolean> txtBoolean;
	ArrayList<String> diffLeftTxt;
	ArrayList<String> diffRightTxt;
	File leftFile;				
	File rightFile;				
	
	public String getleftPath(){return leftPath;}
	public String getrightPath(){return rightPath;}
	public ArrayList<String> getleftTxt(){return leftTxt;}
	public ArrayList<String> getrightTxt(){return rightTxt;}
	public ArrayList<String> getDiffLeftTxt(){return diffLeftTxt;}
	public ArrayList<String> getDiffRightTxt(){return diffRightTxt;}
	public ArrayList<Boolean> gettxtBoolean(){return txtBoolean;}
	public File getleftFile(){return leftFile;}
	public File getrightFile(){return rightFile;}
	
	
	public void setleftPath(String leftPath){this.leftPath=leftPath;}
	public void setrightPath(String rightPath){this.rightPath=rightPath;}
	public void setleftTxt(ArrayList<String> leftTxt){this.leftTxt=leftTxt;}
	public void setrightTxt(ArrayList<String> rightTxt){this.rightTxt=rightTxt;}
	public void setDiffLeftTxt(ArrayList<String> diffLeftTxt){this.diffLeftTxt=diffLeftTxt;}
	public void setDiffRightTxt(ArrayList<String> diffRightTxt){this.diffRightTxt=diffRightTxt;}
	public void settxtBoolean(ArrayList<Boolean> txtBoolean){this.txtBoolean=txtBoolean;}
	public void setleftFile(File leftFile){this.leftFile=leftFile;}
	public void setrightFile(File rihgtFile){this.rightFile=rihgtFile;}
	
	public SMergeModel(){
		leftPath = null;
		rightPath = null;
		leftTxt = new ArrayList<String>();
		rightTxt = new ArrayList<String>();
		txtBoolean=new ArrayList<Boolean>();
	}
	
	public void leftSave(ArrayList<String> leftTxt){
		this.leftTxt = leftTxt;
		
		try{
			FileWriter l_writer = new FileWriter(leftFile);
		                                                   
			for(int i=0;i<leftTxt.size();i++){
				l_writer.write(leftTxt.get(i)+"\r\n"); 
			}
			l_writer.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		
		}catch(IOException e){
			e.printStackTrace();
		}		
	}
	
	public void rightSave(ArrayList<String> rightTxt){
		this.rightTxt = rightTxt;
		
		try{
			FileWriter r_writer = new FileWriter(rightFile);
		                                                  
			for(int i=0;i<rightTxt.size();i++){
				r_writer.write(rightTxt.get(i)+"\r\n"); 
			}
			
			r_writer.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		
		}catch(IOException e){
			e.printStackTrace();	
		}
	}
	
	public void leftLoad(){	
		leftTxt = new ArrayList<String>();
		try {
	    	Scanner leftScanner = new Scanner(leftFile);
	        while(leftScanner.hasNext()){
	        	leftTxt.add(leftScanner.nextLine()+"\n");
	        }
	        leftScanner.close();
			
	    }
	    catch (Exception e) {
	    	System.exit(1);
	    }
	}
	
	public void rightLoad(){	
		rightTxt = new ArrayList<String>();
		try {
	    	Scanner rightScanner = new Scanner(rightFile);
	        while(rightScanner.hasNext()){
	        	rightTxt.add(rightScanner.nextLine()+"\n");
	        }
	        rightScanner.close();
	    }
	    catch (Exception e) {
	    	System.exit(1); 
	    }	
	}	
	
	public void copyToLeft(){
		for(int i = 0 ; i < txtBoolean.size() ; i++){
			if(!txtBoolean.get(i)){
				diffLeftTxt.set(i, diffRightTxt.get(i));
			}
		}
	}
	

	public void copyToRight(){
		for(int i = 0 ; i < txtBoolean.size() ; i++){
			if(!txtBoolean.get(i)){
				diffRightTxt.set(i, diffLeftTxt.get(i));
			}
		}
	}
}