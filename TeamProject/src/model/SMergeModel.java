package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;



public class SMergeModel {
	String leftPath;			//왼쪽 화면 파일의 Path가 담김	
	String rightPath;			//오른쪽 화면 파일의 Path가 담김
	ArrayList<String> leftTxt;		//왼쪽 화면의 텍스트가 담김
	ArrayList<String> rightTxt;		//오른쪽 화면의 텍스트가 담
	ArrayList<Boolean> leftBoolean;
	ArrayList<Boolean> rightBoolean;
	File leftFile;				//오른쪽 파일 구조체
	File rightFile;				//왼쪽 파일 구조체
	
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
			FileWriter l_writer = new FileWriter(leftFile);// 파일 객체를 먼겨야함? 아니면 파일 패스를 넘겨야함?
		                                                   // 패스가 바뀔 경우 파일 패스를 이용해야 할듯?
			for(int i=0;i<leftTxt.size();i++){
				l_writer.write(leftTxt.get(i)+"\r\n"); // 윈도우 기반 메모장에서는 개항이 안됨, 개행문자를  /r/n로 해야 텍스트내의 개행문자가 메모장에 적용됨 ( 파일 로드 할때 개행문자 코드를 바꾸면 될듯)
			}
			l_writer.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		
		}catch(IOException e){
			e.printStackTrace();
		}
		/*
		 * leftFile에다가 leftTxt를 새로 써주세요(준홍)
		 * fileWriter 이용(다른 함수여도 상관은 없음)
		 */
		
	}
	
	public void rightSave(ArrayList<String> rightTxt){
		this.rightTxt = rightTxt;
		
		try{
			FileWriter r_writer = new FileWriter(rightFile);// 파일 객체를 먼겨야함? 아니면 파일 패스를 넘겨야함?
		                                                   // 패스가 바뀔 경우 파일 패스를 이용해야 할듯?
			for(int i=0;i<rightTxt.size();i++){
				r_writer.write(rightTxt.get(i)+"\r\n"); // 윈도우 기반 메모장에서는 개항이 안됨, 개행문자를  /r/n로 해야 텍스트내의 개행문자가 메모장에 적용됨 ( 파일 로드 할때 개행문자 코드를 바꾸면 될듯)
			}
			
			r_writer.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		
		}catch(IOException e){
			e.printStackTrace();	
		}
		/*
		 * rightFile에다가 rightTxt를 새로 써주세요 (준홍)
		 * fileWriter 이용(다른 함수여도 상관은 없음)
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
		 * fileChooser �븿�닔 �씠�슜�븯�뿬 data field�뿉 �엳�뒗 leftPath, leftTxt, leftFile �쓣 梨꾩썙�꽔�뼱二쇱꽭�슂(�씗�긽)
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
