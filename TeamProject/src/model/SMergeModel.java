package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class SMergeModel {
	
	
	private String leftPath;			
	private String rightPath;			
	private ArrayList<String> leftTxt;		
	private ArrayList<String> rightTxt;		
	private ArrayList<Boolean> txtBoolean;
	private ArrayList<String> diffLeftTxt;
	private ArrayList<String> diffRightTxt;
	private File leftFile;				
	private File rightFile;		
	
	
	
	
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
	}
	
	public void leftSave(){
		try{
			FileWriter l_writer = new FileWriter(leftFile);
		                                                   
			for(int i=0;i<leftTxt.size();i++){
				if(leftTxt.get(i)!="\0")
					l_writer.write(leftTxt.get(i)+"\r\n"); 
			}
			l_writer.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		
		}catch(IOException e){
			e.printStackTrace();
		}		
	}
	
	public void rightSave(){
		try{
			FileWriter r_writer = new FileWriter(rightFile);
		                                                  
			for(int i=0;i<rightTxt.size();i++){
				if(rightTxt.get(i)!="\0")
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
				leftTxt.add(leftScanner.nextLine());
			}
			leftScanner.close();
		 }
		catch (Exception e) {
			System.exit(1);
		}
		if(rightPath!=null){
			
			deleteGap();
			fitLength();
		}
		
	}
	
	public void rightLoad(){	
		rightTxt = new ArrayList<String>();
		try {
	    	Scanner rightScanner = new Scanner(rightFile);
	        while(rightScanner.hasNext()){
	        	rightTxt.add(rightScanner.nextLine());
	        }
	        rightScanner.close();
	    }
	    catch (Exception e) {
	    	System.exit(1); 
	    }
		if(leftPath!=null){
			deleteGap();
			fitLength();
		}
	}	
	
	private boolean isParsable(String s){
	    boolean parsable = true;
	    try{
	        Integer.parseInt(s);
	    }catch(NumberFormatException e){
	        parsable = false;
	    }
	    return parsable;
	}
	

	
	public boolean copyToLeft(String s){
		
		int input;
		if(s.equals("\0")){
			leftTxt = new ArrayList<String>(rightTxt);
			
			for( int i = 0; i < leftTxt.size(); i++){
		         if(leftTxt.get(i).equals("\0")){
		            leftTxt.remove(i);
		         }
		      }
		      
		      for( int i = 0; i < rightTxt.size(); i++){
		         if(rightTxt.get(i).equals("\0")){
		            rightTxt.remove(i);
		         }
		      }
			
		      fitLength();
		      return true;
		}
		
		if(!isParsable(s)){
			return false;
		}
		
		input = Integer.parseInt(s);
		
		if(input < 0 || input > txtBoolean.size() - 1){
			return false;
		}
		
		
		if(txtBoolean.get(input)){
			leftTxt.set(input, rightTxt.get(input));
			return true;
		}
		
		else{
			input--;
			while(!txtBoolean.get(input)){
				input--;
			}
			input++;
			while(!txtBoolean.get(input)){
				leftTxt.set(input, rightTxt.get(input));
				input++;
			}
			
			
		}
		
	      fitLength();
	      return true;
		
		
	}
	

	public boolean copyToRight(String s){

		int input;
		
		
		if(s.equals("\0")){
			rightTxt = new ArrayList<String>(leftTxt);
			for( int i = 0; i < leftTxt.size(); i++){
		         if(leftTxt.get(i).equals("\0")){
		            leftTxt.remove(i);
		         }
		      }
		      
		      for( int i = 0; i < rightTxt.size(); i++){
		         if(rightTxt.get(i).equals("\0")){
		            rightTxt.remove(i);
		         }
		      }

		      fitLength();
		      return true;
		}
		
		if(!isParsable(s)){
			return false;
		}
		
		input = Integer.parseInt(s);
		
		if(input < 0 || input > txtBoolean.size() - 1){
			return false;
		}
		
		if(txtBoolean.get(input)){
			rightTxt.set(input, leftTxt.get(input));
			return true;
		}
		
		else{
			input--;
			while(!txtBoolean.get(input)){
				input--;
			}
			input++;
			while(!txtBoolean.get(input)){
				rightTxt.set(input, leftTxt.get(input));
				input++;
			}
		}
		
		fitLength();
	      
	      return true;
	}
	
	
	public void lcsDiff(){
		
		leftTxt.add("\r\n");
		rightTxt.add("\r\n");
		
		txtBoolean = new ArrayList<Boolean>();
		
		deleteGap();
		
		
		int sizeofleftList = leftTxt.size();
		int sizeofrightList = rightTxt.size();
		int[][] lcsArray = new int[sizeofleftList+1][sizeofrightList+1];
		int[][] lcsDirection = new int[sizeofleftList+1][sizeofrightList+1]; // 1�씠硫� ��媛�, 2硫� �쇊履�, 3�씠硫� �쐞 , 4 �뼇履� �뿉�꽌 �씫�뼱�삩�떎
		
		for(int i = 1 ; i < sizeofleftList+1; i++){
			for(int j = 1 ; j < sizeofrightList+1; j++){
				if (leftTxt.get(i-1).equals(rightTxt.get(j-1))){
					lcsArray[i][j] = lcsArray[i-1][j-1] + 1;
					lcsDirection[i][j] = 1;
				}
				else{
					if (lcsArray[i-1][j] > lcsArray[i][j-1]){
						lcsArray[i][j] = lcsArray[i-1][j];
						lcsDirection[i][j] = 3;
					}
					else if (lcsArray[i-1][j] < lcsArray[i][j-1]){
						lcsArray[i][j] = lcsArray[i][j-1];
						lcsDirection[i][j] = 2;
					}
					else {
						lcsArray[i][j] = lcsArray[i][j-1];
						lcsDirection[i][j] = 4;
					}
				}	
			}	
		}
			//// �씠 �븘�옒遺��꽣 backtracking
			int i = sizeofleftList;
			int j = sizeofrightList;
			int past_i; // �씠�쟾 �쐞移�
			int past_j; // �씠�쟾 �쐞移�
			ArrayList<String> c_element = new ArrayList<String>(); // 怨듯넻�썝�냼 ���옣 �닔�뿴
			
			while( lcsArray[i][j] != 0){
				if ( lcsDirection[i][j] == 1){
					past_i = i; past_j = j;
					i--; j--;
					if( lcsArray[i][j] < lcsArray[past_i][past_j])
						c_element.add(0, rightTxt.get(past_j-1));
				}
				else if ( lcsDirection[i][j] == 2){
					past_i = i; past_j = j;
					j--;
					if( lcsArray[i][j] < lcsArray[past_i][past_j])
						c_element.add(0, rightTxt.get(past_j-1));
				}
				else if ( lcsDirection[i][j] == 3){
					past_i = i; past_j = j;
					i--;
					if( lcsArray[i][j] < lcsArray[past_i][past_j])
						c_element.add(0, leftTxt.get(past_i-1));
				}
				else if ( lcsDirection[i][j] == 4){
					past_i = i; past_j = j;
					j--;
					if( lcsArray[i][j] < lcsArray[past_i][past_j])
						c_element.add(0, rightTxt.get(past_j-1));
				}
				else{
					break;
				}
			}
			
			
			int index = 0;
			for(int a = 0; a < c_element.size(); a++){
				while(true){
					if ( c_element.get(a).equals(leftTxt.get(index)) && c_element.get(a).equals(rightTxt.get(index))){
						txtBoolean.add(true);
						index = index + 1;
						break;
					}
					else if ( c_element.get(a).equals(leftTxt.get(index)) && !c_element.get(a).equals(rightTxt.get(index))){
						txtBoolean.add(false);
						leftTxt.add(index, "\0");
					}
					else if ( !c_element.get(a).equals(leftTxt.get(index)) && c_element.get(a).equals(rightTxt.get(index))){
						txtBoolean.add(false);
						rightTxt.add(index, "\0");
					}
					else{
						txtBoolean.add(false);
					}
					
					index = index + 1;
				}
			}

			leftTxt.remove("\r\n");
			rightTxt.remove("\r\n");
			txtBoolean.remove(txtBoolean.size()-1);
		}	

	
	
	private void fitLength()
	{
		int leftSize=leftTxt.size();
		int rightSize=rightTxt.size();
		
		if(leftSize<rightSize){
			for(int i=0;i<(rightSize-leftSize);i++){
				leftTxt.add(leftTxt.size(),"\0");
			}
		}
		else if(rightSize<leftSize){
			for(int i=0;i<(leftSize-rightSize);i++){
				rightTxt.add(rightTxt.size(),"\0");
			}
		}
	}
	
	private void deleteGap()
	{
		for(Iterator<String> itr = rightTxt.iterator() ; itr.hasNext() ; ){
			if(itr.next().equals("\0"))
				itr.remove();
		}
		for(Iterator<String> itr = leftTxt.iterator() ; itr.hasNext() ; ){
			if(itr.next().equals("\0"))
				itr.remove();
		}
	}
}