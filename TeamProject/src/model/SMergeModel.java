package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//๏ฟฝ๋?๏ฟฝ?๏ฟฝ??

public class SMergeModel {
	String leftPath;			//? ??ข็ญ์? ? ??็ญ๋กณ? ? ??? ?๋ตฌๅ ?๋ฒ? Path?ถ?? ? ?๋ผ็นน๋จฒ์	
	String rightPath;			//? ?๊ถ๏ฟฝ๋ชด๏ง๊พ ๊ฑน ? ??็ญ๋กณ? ? ??? ?๋ตฌๅ ?๋ฒ? Path?ถ?? ? ?๋ผ็นน๋จฒ์
	ArrayList<String> leftTxt;		//? ??ข็ญ์? ? ??็ญ๋กซ?ป๏ฟฝ๋ฒฅ ? ?๏ฟฝ๋ณฉ?๏ฟฝ๋ฎ? ?๋ฑๆถ?? ? ?๋ผ็นน๋จฒ์
	ArrayList<String> rightTxt;		//? ?๊ถ๏ฟฝ๋ชด๏ง๊พ ๊ฑน ? ??็ญ๋กซ?ป๏ฟฝ๋ฒฅ ? ?๏ฟฝ๋ณฉ?๏ฟฝ๋ฎ? ?๋ฑๆถ?? ? ?๋ผ?
	ArrayList<Boolean> leftBoolean;
	ArrayList<Boolean> rightBoolean;
	File leftFile;				//? ?๊ถ๏ฟฝ๋ชด๏ง๊พ ๊ฑน ? ??? ?๋ต? ๏ฟฝ๋ค๏ฟฝ๋ผ๏ฟฝ์?? ๏ฟ?
	File rightFile;				//? ??ข็ญ์? ? ??? ?๋ต? ๏ฟฝ๋ค๏ฟฝ๋ผ๏ฟฝ์?? ๏ฟ?
	
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
	public void setleftBoolean(ArrayList<Boolean> leftBoolean){this.leftBoolean=leftBoolean;}
	public void setrightBoolean(ArrayList<Boolean> rightBoolean){this.rightBoolean=rightBoolean;}
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
			FileWriter l_writer = new FileWriter(leftFile);// ? ??? ?๋ต? ?ถ?๋น็ฟ?๋ชดๅ ๏ฟ? ๏ฟฝ์๏ฟฝ๋?ฐ?ฝ??ฎ???๏ฟ?? ? ??ก? ?๋น็ญ๋กณ์ ? ??? ?๋ต? ? ??ญ? ?๋ฎ๏ฟฝ๋ชดๅ ๏ฟ? ? ???๊บฟ๋ซ?ฎ???๏ฟ??
		                                                   // ? ??ญ? ?๋ฎๆถ?? ????? ?? ?๊ป๋ฉ๏ฟฝ๋ญ ? ??? ?๋ต? ? ??ญ? ?๋ฎ๏ฟฝ๋ชดๅ ๏ฟ? ? ?๋ต ๅ ?? ? ??ธ? ?? ? ?๋งๅ ?์พ??
			for(int i=0;i<leftTxt.size();i++){
				l_writer.write(leftTxt.get(i)+"\r\n"); // ? ?๋งๅ ?์ฆฒๅ ??ญ ?ซ๊ฟธํ?ก่?๏ฟฝ ็ญ๋กซ??๏ฟฝ๊ฑ? ??ข? ?? ? ??ฃ? ?๋ฎ? ?ถ?๋ฎ้ฎ??๏ฟฝ๋ต  ? ??ง? ?์ญ?, ?ถ?๋ฎ๏ง??ง๏ง๊พฉ?ฝ๏ฟฝ๋ชด? ๏ฟ?  /r/n?ฅ?ฝ? ? ??ธ? ?? ? ?๏ฟฝ๋ณฉ?๏ฟฝ๋ฎ? ?๋ฑๅ ???? ?๋ฒ? ?ถ?๋ฎ๏ง??ง๏ง๊พฉ?ฝ?ถ?? ็ญ๋กซ??๏ฟฝ๊ฑ? ??ข? ??  ? ??? ?? ? ?์ญ? ( ? ??? ?๋ต? ?ฅ?ช๋ฎ่ซญ๏ฟ? ? ?๋งๅ ?๋ฅ? ?ถ?๋ฎ๏ง??ง๏ง๊พฉ?ฝ ๏ฟฝ๊ต?บ?๊ตก๏ฟฝ๋ชดๅ ๏ฟ? ???ฝ๏ฟฝ๏ฟฝ็ญ๋กณ? ? ?์ญๅ ?์พ?)
			}
			l_writer.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		
		}catch(IOException e){
			e.printStackTrace();
		}
		/*
		 * leftFile? ?? ? ?๋ผๆถ?? leftTxt๏ฟฝ๋ชด? ๏ฟ? ? ??ฑ?ฅ?ฝ? ? ????ฏ??๏ฟฝๅ ??(้คฮฟ์? ??น)
		 * fileWriter ? ?๋ต ๅ ?? (? ?๋ผ๏ฟฝ๋ชดๅ ๏ฟ? ? ?๋งๅ ??พ? ??ฐ? ?์ฆ? ? ?๋ง๏ฟฝ๊ฝดๅ ??? ๏ฟ? ? ??จ? ?๋ฒ?)
		 */
		
	}
	
	public void rightSave(ArrayList<String> rightTxt){
		this.rightTxt = rightTxt;
		
		try{
			FileWriter r_writer = new FileWriter(rightFile);// ? ??? ?๋ต? ?ถ?๋น็ฟ?๋ชดๅ ๏ฟ? ๏ฟฝ์๏ฟฝ๋?ฐ?ฝ??ฎ???๏ฟ?? ? ??ก? ?๋น็ญ๋กณ์ ? ??? ?๋ต? ? ??ญ? ?๋ฎ๏ฟฝ๋ชดๅ ๏ฟ? ? ???๊บฟ๋ซ?ฎ???๏ฟ??
		                                                   // ? ??ญ? ?๋ฎๆถ?? ????? ?? ?๊ป๋ฉ๏ฟฝ๋ญ ? ??? ?๋ต? ? ??ญ? ?๋ฎ๏ฟฝ๋ชดๅ ๏ฟ? ? ?๋ต ๅ ?? ? ??ธ? ?? ? ?๋งๅ ?์พ??
			for(int i=0;i<rightTxt.size();i++){
				r_writer.write(rightTxt.get(i)+"\r\n"); // ? ?๋งๅ ?์ฆฒๅ ??ญ ?ซ๊ฟธํ?ก่?๏ฟฝ ็ญ๋กซ??๏ฟฝ๊ฑ? ??ข? ?? ? ??ฃ? ?๋ฎ? ?ถ?๋ฎ้ฎ??๏ฟฝ๋ต  ? ??ง? ?์ญ?, ?ถ?๋ฎ๏ง??ง๏ง๊พฉ?ฝ๏ฟฝ๋ชด? ๏ฟ?  /r/n?ฅ?ฝ? ? ??ธ? ?? ? ?๏ฟฝ๋ณฉ?๏ฟฝ๋ฎ? ?๋ฑๅ ???? ?๋ฒ? ?ถ?๋ฎ๏ง??ง๏ง๊พฉ?ฝ?ถ?? ็ญ๋กซ??๏ฟฝ๊ฑ? ??ข? ??  ? ??? ?? ? ?์ญ? ( ? ??? ?๋ต? ?ฅ?ช๋ฎ่ซญ๏ฟ? ? ?๋งๅ ?๋ฅ? ?ถ?๋ฎ๏ง??ง๏ง๊พฉ?ฝ ๏ฟฝ๊ต?บ?๊ตก๏ฟฝ๋ชดๅ ๏ฟ? ???ฝ๏ฟฝ๏ฟฝ็ญ๋กณ? ? ?์ญๅ ?์พ?)
			}
			
			r_writer.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		
		}catch(IOException e){
			e.printStackTrace();	
		}
		/*
		 * rightFile? ?? ? ?๋ผๆถ?? rightTxt๏ฟฝ๋ชด? ๏ฟ? ? ??ฑ?ฅ?ฝ? ? ????ฏ??๏ฟฝๅ ?? (้คฮฟ์? ??น)
		 * fileWriter ? ?๋ต ๅ ?? (? ?๋ผ๏ฟฝ๋ชดๅ ๏ฟ? ? ?๋งๅ ??พ? ??ฐ? ?์ฆ? ? ?๋ง๏ฟฝ๊ฝดๅ ??? ๏ฟ? ? ??จ? ?๋ฒ?)
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
		 * fileChooser ๏ฟฝ๋๏ฟฝ๋ฅ๏ง์ฟ?๏ฟฝ๋ฅ๏ฟฝ๋นข ๏ฟฝ๋๏ฟฝ๋ฉ?พ์กพ?๏ฟฝ๋ฉ๏ฟฝ๋ฎ๏ฟฝ๋๏ฟฝ๋ฅ?ฑ๏ฟฝ๏ฟฝ?๏ฟฝ๋ฅ๏ฟฝ๋ฟฐ data field๏ฟฝ๋๏ฟฝ๋ฅ๏ฟฝ๋ฑบ ๏ฟฝ๋๏ฟฝ๋ฉ???๏ฟฝ๋ฅ่ฃ๏ฟฝ leftPath, leftTxt, leftFile ๏ฟฝ๋๏ฟฝ๋ฉ่ซ?๏ฟ? ๏ฆซ๋ณ??ฌ๊ณฃ๋ซ๏ฟฝ์ณ๏ฟฝ๋๏ฟฝ๋ก๏ฟฝ๋ง๏ฟฝ๋๏ฟฝ๋ฅ๏ฟฝ๊ฝ๏ฟฝ์๏ฟฝ์ฃ๏ฟฝ๋๏ฟฝ๋ป? ?ฒ?๏ฟฝ๋ฉ๏ฟฝ๋??(๏ฟฝ๋๏ฟฝ๋ฉ?พ๋บ?๏ฟฝ๋ก๏ง๏ฟฝ)
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
