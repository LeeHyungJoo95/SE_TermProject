package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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
	}
	
	public void leftSave(ArrayList<String> leftTxt){
		try{
			FileWriter l_writer = new FileWriter(leftFile);
		                                                   
			for(int i=0;i<leftTxt.size();i++){
				if(i!=(leftTxt.size()-1))
					if(leftTxt.get(i)!="\0")
						l_writer.write(leftTxt.get(i).substring(0, leftTxt.get(i).length()-1)+"\r\n"); 
			}
			l_writer.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		
		}catch(IOException e){
			e.printStackTrace();
		}		
	}
	
	public void rightSave(ArrayList<String> rightTxt){
		try{
			FileWriter r_writer = new FileWriter(rightFile);
		                                                  
			for(int i=0;i<rightTxt.size();i++){
				if(i!=(rightTxt.size()-1))
					if(rightTxt.get(i)!="\0")
						r_writer.write(rightTxt.get(i).substring(0, rightTxt.get(i).length()-1)+"\r\n"); 
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
		//leftPath=leftFile.getAbsolutePath();
		try {
			Scanner leftScanner = new Scanner(leftFile);
			while(leftScanner.hasNext()){
				leftTxt.add(leftScanner.nextLine()+"\n");
			}
			leftTxt.add("\r\n");
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
		//rightPath=rightFile.getAbsolutePath();
		try {
	    	Scanner rightScanner = new Scanner(rightFile);
	        while(rightScanner.hasNext()){
	        	rightTxt.add(rightScanner.nextLine()+"\n");
	        }
	        rightTxt.add("\r\n");
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
	
	public void copyToLeft(String s){
		
		int input;
		input = Integer.parseInt(s);
		if(txtBoolean.get(input)){
			leftTxt.set(input, rightTxt.get(input));
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
	}
	

	public void copyToRight(String s){

		int input;
		input = Integer.parseInt(s);
		if(txtBoolean.get(input)){
			rightTxt.set(input, leftTxt.get(input));
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
	}
	
	
	public void lcsDiff(){
		txtBoolean = new ArrayList<Boolean>();
		
		deleteGap();
		
		
		int sizeofleftList = leftTxt.size();
		int sizeofrightList = rightTxt.size();
		int[][] lcsArray = new int[sizeofleftList+1][sizeofrightList+1];
		int[][] lcsDirection = new int[sizeofleftList+1][sizeofrightList+1]; // 1?��?��뵠筌롳옙 ?��?��?��?��?��?��, 2筌롳?�� ?��?��?��筌잞?��, 3?��?��뵠筌롳옙 ?��?���? , 4 ?��?��?��筌잞?�� ?��?��?��?��?��?�� ?��?���??��?��?��?��?��궔占?���?
		
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
			//// ?��?���? ?��?��?��?��?��?���겫?��?��?��?���? backtracking
			int i = sizeofleftList;
			int j = sizeofrightList;
			int past_i; // ?��?��뵠占?��?�� ?��?��맄燁?��?��
			int past_j; // ?��?��뵠占?��?�� ?��?��맄燁?��?��
			ArrayList<String> c_element = new ArrayList<String>(); // �⑤벏�꽰占?��?��?��?���? ?��?��?��?��?��?�� ?��?��?��?��?��??
			
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
		}	

	
	
	private void fitLength()
	{
		int leftSize=leftTxt.size();
		int rightSize=rightTxt.size();
		
		if(leftSize<rightSize){
			for(int i=0;i<(rightSize-leftSize);i++){
				leftTxt.add(leftTxt.size()-1,"\0");
			}
		}
		else if(rightSize<leftSize){
			for(int i=0;i<(leftSize-rightSize);i++){
				rightTxt.add(rightTxt.size()-1,"\0");
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