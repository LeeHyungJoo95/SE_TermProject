package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;



public class SMergeModel {
	String leftPath;			//?™¼ìª? ?™”ë©? ?ŒŒ?¼?˜ Pathê°? ?‹´ê¹?	
	String rightPath;			//?˜¤ë¥¸ìª½ ?™”ë©? ?ŒŒ?¼?˜ Pathê°? ?‹´ê¹?
	ArrayList<String> leftTxt;		//?™¼ìª? ?™”ë©´ì˜ ?…?Š¤?Š¸ê°? ?‹´ê¹?
	ArrayList<String> rightTxt;		//?˜¤ë¥¸ìª½ ?™”ë©´ì˜ ?…?Š¤?Š¸ê°? ?‹´
	ArrayList<Boolean> leftBoolean;
	ArrayList<Boolean> rightBoolean;
	File leftFile;				//?˜¤ë¥¸ìª½ ?ŒŒ?¼ êµ¬ì¡°ì²?
	File rightFile;				//?™¼ìª? ?ŒŒ?¼ êµ¬ì¡°ì²?
	
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
			FileWriter l_writer = new FileWriter(leftFile);// ?ŒŒ?¼ ê°ì²´ë¥? ë¨¼ê²¨?•¼?•¨? ?•„?‹ˆë©? ?ŒŒ?¼ ?Œ¨?Š¤ë¥? ?„˜ê²¨ì•¼?•¨?
		                                                   // ?Œ¨?Š¤ê°? ë°”ë?? ê²½ìš° ?ŒŒ?¼ ?Œ¨?Š¤ë¥? ?´?š©?•´?•¼ ?• ?“¯?
			for(int i=0;i<leftTxt.size();i++){
				l_writer.write(leftTxt.get(i)+"\r\n"); // ?œˆ?„?š° ê¸°ë°˜ ë©”ëª¨?¥?—?„œ?Š” ê°œí•­?´ ?•ˆ?¨, ê°œí–‰ë¬¸ìë¥?  /r/në¡? ?•´?•¼ ?…?Š¤?Š¸?‚´?˜ ê°œí–‰ë¬¸ìê°? ë©”ëª¨?¥?— ? ?š©?¨ ( ?ŒŒ?¼ ë¡œë“œ ?• ?•Œ ê°œí–‰ë¬¸ì ì½”ë“œë¥? ë°”ê¾¸ë©? ? ?“¯)
			}
			l_writer.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		
		}catch(IOException e){
			e.printStackTrace();
		}
		/*
		 * leftFile?—?‹¤ê°? leftTxtë¥? ?ƒˆë¡? ?¨ì£¼ì„¸?š”(ì¤??™)
		 * fileWriter ?´?š©(?‹¤ë¥? ?•¨?ˆ˜?—¬?„ ?ƒê´??? ?—†?Œ)
		 */
		
	}
	
	public void rightSave(ArrayList<String> rightTxt){
		this.rightTxt = rightTxt;
		
		try{
			FileWriter r_writer = new FileWriter(rightFile);// ?ŒŒ?¼ ê°ì²´ë¥? ë¨¼ê²¨?•¼?•¨? ?•„?‹ˆë©? ?ŒŒ?¼ ?Œ¨?Š¤ë¥? ?„˜ê²¨ì•¼?•¨?
		                                                   // ?Œ¨?Š¤ê°? ë°”ë?? ê²½ìš° ?ŒŒ?¼ ?Œ¨?Š¤ë¥? ?´?š©?•´?•¼ ?• ?“¯?
			for(int i=0;i<rightTxt.size();i++){
				r_writer.write(rightTxt.get(i)+"\r\n"); // ?œˆ?„?š° ê¸°ë°˜ ë©”ëª¨?¥?—?„œ?Š” ê°œí•­?´ ?•ˆ?¨, ê°œí–‰ë¬¸ìë¥?  /r/në¡? ?•´?•¼ ?…?Š¤?Š¸?‚´?˜ ê°œí–‰ë¬¸ìê°? ë©”ëª¨?¥?— ? ?š©?¨ ( ?ŒŒ?¼ ë¡œë“œ ?• ?•Œ ê°œí–‰ë¬¸ì ì½”ë“œë¥? ë°”ê¾¸ë©? ? ?“¯)
			}
			
			r_writer.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		
		}catch(IOException e){
			e.printStackTrace();	
		}
		/*
		 * rightFile?—?‹¤ê°? rightTxtë¥? ?ƒˆë¡? ?¨ì£¼ì„¸?š” (ì¤??™)
		 * fileWriter ?´?š©(?‹¤ë¥? ?•¨?ˆ˜?—¬?„ ?ƒê´??? ?—†?Œ)
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
		 * fileChooser ï¿½ë¸¿ï¿½ë‹” ï¿½ì” ï¿½ìŠœï¿½ë¸¯ï¿½ë¿¬ data fieldï¿½ë¿‰ ï¿½ì—³ï¿½ë’— leftPath, leftTxt, leftFile ï¿½ì“£ ï§?ê¾©ì™ï¿½ê½”ï¿½ë¼±äºŒì‡±ê½?ï¿½ìŠ‚(ï¿½ì”—ï¿½ê¸½)
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
