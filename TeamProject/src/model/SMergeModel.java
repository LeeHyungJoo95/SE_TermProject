package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;



public class SMergeModel {
	String leftPath;			//?���? ?���? ?��?��?�� Path�? ?���?	
	String rightPath;			//?��른쪽 ?���? ?��?��?�� Path�? ?���?
	ArrayList<String> leftTxt;		//?���? ?��면의 ?��?��?���? ?���?
	ArrayList<String> rightTxt;		//?��른쪽 ?��면의 ?��?��?���? ?��
	ArrayList<Boolean> leftBoolean;
	ArrayList<Boolean> rightBoolean;
	File leftFile;				//?��른쪽 ?��?�� 구조�?
	File rightFile;				//?���? ?��?�� 구조�?
	
	public String getleftPath(){return leftPath;}
	public String getrightPath(){return rightPath;}
	public ArrayList<String> getleftTxt(){return leftTxt;}
	public ArrayList<String> getrightTxt(){return rightTxt;}
	public ArrayList<Boolean> getleftBoolean(){return leftBoolean;}
	public ArrayList<Boolean> getrightBoolean(){return rightBoolean;} 
	public File getleftFile(){return leftFile;}
	public File getrightFile(){return rightFile;}
	
	public void setleftPath(String leftPath){this.leftPath=leftPath;}
	public void setrightPath(String rightPath){this.rightPath=rightPath;}
	public void setleftTxt(ArrayList<String> leftTxt){this.leftTxt=leftTxt;}
	public void setrightTxt(ArrayList<String> rightTxt){this.rightTxt=rightTxt;}
	public void setleftFile(File leftFile){this.leftFile=leftFile;}
	public void setrightFile(File rihgtFile){this.rightFile=rihgtFile;}
	
	public SMergeModel(){
		leftPath = null;
		rightPath = null;
		leftTxt = new ArrayList<String>();
		rightTxt = new ArrayList<String>();
		leftBoolean=new ArrayList<Boolean>();
		rightBoolean=new ArrayList<Boolean>();
	}
	
	public void leftSave(ArrayList<String> leftTxt){
		this.leftTxt = leftTxt;
		
		try{
			FileWriter l_writer = new FileWriter(leftFile);// ?��?�� 객체�? 먼겨?��?��? ?��?���? ?��?�� ?��?���? ?��겨야?��?
		                                                   // ?��?���? 바�?? 경우 ?��?�� ?��?���? ?��?��?��?�� ?��?��?
			for(int i=0;i<leftTxt.size();i++){
				l_writer.write(leftTxt.get(i)+"\r\n"); // ?��?��?�� 기반 메모?��?��?��?�� 개항?�� ?��?��, 개행문자�?  /r/n�? ?��?�� ?��?��?��?��?�� 개행문자�? 메모?��?�� ?��?��?�� ( ?��?�� 로드 ?��?�� 개행문자 코드�? 바꾸�? ?��?��)
			}
			l_writer.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		
		}catch(IOException e){
			e.printStackTrace();
		}
		/*
		 * leftFile?��?���? leftTxt�? ?���? ?��주세?��(�??��)
		 * fileWriter ?��?��(?���? ?��?��?��?�� ?���??? ?��?��)
		 */
		
	}
	
	public void rightSave(ArrayList<String> rightTxt){
		this.rightTxt = rightTxt;
		
		try{
			FileWriter r_writer = new FileWriter(rightFile);// ?��?�� 객체�? 먼겨?��?��? ?��?���? ?��?�� ?��?���? ?��겨야?��?
		                                                   // ?��?���? 바�?? 경우 ?��?�� ?��?���? ?��?��?��?�� ?��?��?
			for(int i=0;i<rightTxt.size();i++){
				r_writer.write(rightTxt.get(i)+"\r\n"); // ?��?��?�� 기반 메모?��?��?��?�� 개항?�� ?��?��, 개행문자�?  /r/n�? ?��?�� ?��?��?��?��?�� 개행문자�? 메모?��?�� ?��?��?�� ( ?��?�� 로드 ?��?�� 개행문자 코드�? 바꾸�? ?��?��)
			}
			
			r_writer.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		
		}catch(IOException e){
			e.printStackTrace();	
		}
		/*
		 * rightFile?��?���? rightTxt�? ?���? ?��주세?�� (�??��)
		 * fileWriter ?��?��(?���? ?��?��?��?�� ?���??? ?��?��)
		 */
		
	}
	public void leftLoad(){	
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
		/*
		 * fileChooser �븿�닔 �씠�슜�븯�뿬 data field�뿉 �엳�뒗 leftPath, leftTxt, leftFile �쓣 �?꾩썙�꽔�뼱二쇱�?�슂(�씗�긽)
		 */
	}
	
	public void rightLoad(){	
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
		
	}	
}
