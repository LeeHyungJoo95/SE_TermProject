package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;



public class SMergeModel {
	String leftPath;			//?Όμͺ? ?λ©? ??Ό? Pathκ°? ?΄κΉ?	
	String rightPath;			//?€λ₯Έμͺ½ ?λ©? ??Ό? Pathκ°? ?΄κΉ?
	ArrayList<String> leftTxt;		//?Όμͺ? ?λ©΄μ ??€?Έκ°? ?΄κΉ?
	ArrayList<String> rightTxt;		//?€λ₯Έμͺ½ ?λ©΄μ ??€?Έκ°? ?΄
	ArrayList<Boolean> leftBoolean;
	ArrayList<Boolean> rightBoolean;
	File leftFile;				//?€λ₯Έμͺ½ ??Ό κ΅¬μ‘°μ²?
	File rightFile;				//?Όμͺ? ??Ό κ΅¬μ‘°μ²?
	
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
			FileWriter l_writer = new FileWriter(leftFile);// ??Ό κ°μ²΄λ₯? λ¨Όκ²¨?Ό?¨? ??λ©? ??Ό ?¨?€λ₯? ?κ²¨μΌ?¨?
		                                                   // ?¨?€κ°? λ°λ?? κ²½μ° ??Ό ?¨?€λ₯? ?΄?©?΄?Ό ? ?―?
			for(int i=0;i<leftTxt.size();i++){
				l_writer.write(leftTxt.get(i)+"\r\n"); // ???° κΈ°λ° λ©λͺ¨?₯??? κ°ν­?΄ ??¨, κ°νλ¬Έμλ₯?  /r/nλ‘? ?΄?Ό ??€?Έ?΄? κ°νλ¬Έμκ°? λ©λͺ¨?₯? ? ?©?¨ ( ??Ό λ‘λ ? ? κ°νλ¬Έμ μ½λλ₯? λ°κΎΈλ©? ? ?―)
			}
			l_writer.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		
		}catch(IOException e){
			e.printStackTrace();
		}
		/*
		 * leftFile??€κ°? leftTxtλ₯? ?λ‘? ?¨μ£ΌμΈ?(μ€??)
		 * fileWriter ?΄?©(?€λ₯? ?¨??¬? ?κ΄??? ??)
		 */
		
	}
	
	public void rightSave(ArrayList<String> rightTxt){
		this.rightTxt = rightTxt;
		
		try{
			FileWriter r_writer = new FileWriter(rightFile);// ??Ό κ°μ²΄λ₯? λ¨Όκ²¨?Ό?¨? ??λ©? ??Ό ?¨?€λ₯? ?κ²¨μΌ?¨?
		                                                   // ?¨?€κ°? λ°λ?? κ²½μ° ??Ό ?¨?€λ₯? ?΄?©?΄?Ό ? ?―?
			for(int i=0;i<rightTxt.size();i++){
				r_writer.write(rightTxt.get(i)+"\r\n"); // ???° κΈ°λ° λ©λͺ¨?₯??? κ°ν­?΄ ??¨, κ°νλ¬Έμλ₯?  /r/nλ‘? ?΄?Ό ??€?Έ?΄? κ°νλ¬Έμκ°? λ©λͺ¨?₯? ? ?©?¨ ( ??Ό λ‘λ ? ? κ°νλ¬Έμ μ½λλ₯? λ°κΎΈλ©? ? ?―)
			}
			
			r_writer.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		
		}catch(IOException e){
			e.printStackTrace();	
		}
		/*
		 * rightFile??€κ°? rightTxtλ₯? ?λ‘? ?¨μ£ΌμΈ? (μ€??)
		 * fileWriter ?΄?©(?€λ₯? ?¨??¬? ?κ΄??? ??)
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
		 * fileChooser οΏ½λΈΏοΏ½λ οΏ½μ οΏ½μοΏ½λΈ―οΏ½λΏ¬ data fieldοΏ½λΏ οΏ½μ³οΏ½λ leftPath, leftTxt, leftFile οΏ½μ£ ο§?κΎ©μοΏ½κ½οΏ½λΌ±δΊμ±κ½?οΏ½μ(οΏ½μοΏ½κΈ½)
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
