package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//�뀋�뀋�뀋

public class SMergeModel {
	String leftPath;			//占쎌뇢筌잞옙 占쎌넅筌롳옙 占쎈솁占쎌뵬占쎌벥 Path揶쏉옙 占쎈뼖繹먲옙	
	String rightPath;			//占쎌궎�몴紐꾠걹 占쎌넅筌롳옙 占쎈솁占쎌뵬占쎌벥 Path揶쏉옙 占쎈뼖繹먲옙
	ArrayList<String> leftTxt;		//占쎌뇢筌잞옙 占쎌넅筌롫똻�벥 占쎈�볩옙�뮞占쎈뱜揶쏉옙 占쎈뼖繹먲옙
	ArrayList<String> rightTxt;		//占쎌궎�몴紐꾠걹 占쎌넅筌롫똻�벥 占쎈�볩옙�뮞占쎈뱜揶쏉옙 占쎈뼖
	ArrayList<Boolean> leftBoolean;
	ArrayList<Boolean> rightBoolean;
	File leftFile;				//占쎌궎�몴紐꾠걹 占쎈솁占쎌뵬 �뤃�듼�쒙㎗占�
	File rightFile;				//占쎌뇢筌잞옙 占쎈솁占쎌뵬 �뤃�듼�쒙㎗占�
	
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
			FileWriter l_writer = new FileWriter(leftFile);// 占쎈솁占쎌뵬 揶쏆빘猿쒐몴占� �솒�눊爰쇽옙鍮욑옙釉�? 占쎈툡占쎈빍筌롳옙 占쎈솁占쎌뵬 占쎈솭占쎈뮞�몴占� 占쎄퐜野꺿뫁鍮욑옙釉�?
		                                                   // 占쎈솭占쎈뮞揶쏉옙 獄쏅뗀占쏙옙 野껋럩�뒭 占쎈솁占쎌뵬 占쎈솭占쎈뮞�몴占� 占쎌뵠占쎌뒠占쎈퉸占쎈튊 占쎈막占쎈쾹?
			for(int i=0;i<leftTxt.size();i++){
				l_writer.write(leftTxt.get(i)+"\r\n"); // 占쎌맊占쎈즲占쎌뒭 疫꿸퀡而� 筌롫뗀�걟占쎌삢占쎈퓠占쎄퐣占쎈뮉 揶쏆뮉鍮놅옙�뵠 占쎈툧占쎈쭡, 揶쏆뮉六얕눧紐꾩쁽�몴占�  /r/n嚥∽옙 占쎈퉸占쎈튊 占쎈�볩옙�뮞占쎈뱜占쎄땀占쎌벥 揶쏆뮉六얕눧紐꾩쁽揶쏉옙 筌롫뗀�걟占쎌삢占쎈퓠 占쎌읅占쎌뒠占쎈쭡 ( 占쎈솁占쎌뵬 嚥≪뮆諭� 占쎈막占쎈르 揶쏆뮉六얕눧紐꾩쁽 �굜遺얜굡�몴占� 獄쏅떽��筌롳옙 占쎈쭍占쎈쾹)
			}
			l_writer.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		
		}catch(IOException e){
			e.printStackTrace();
		}
		/*
		 * leftFile占쎈퓠占쎈뼄揶쏉옙 leftTxt�몴占� 占쎄퉱嚥∽옙 占쎈쑅雅뚯눘苑�占쎌뒄(餓ο옙占쎌냹)
		 * fileWriter 占쎌뵠占쎌뒠(占쎈뼄�몴占� 占쎈맙占쎈땾占쎈연占쎈즲 占쎄맒�꽴占쏙옙占� 占쎈씨占쎌벉)
		 */
		
	}
	
	public void rightSave(ArrayList<String> rightTxt){
		this.rightTxt = rightTxt;
		
		try{
			FileWriter r_writer = new FileWriter(rightFile);// 占쎈솁占쎌뵬 揶쏆빘猿쒐몴占� �솒�눊爰쇽옙鍮욑옙釉�? 占쎈툡占쎈빍筌롳옙 占쎈솁占쎌뵬 占쎈솭占쎈뮞�몴占� 占쎄퐜野꺿뫁鍮욑옙釉�?
		                                                   // 占쎈솭占쎈뮞揶쏉옙 獄쏅뗀占쏙옙 野껋럩�뒭 占쎈솁占쎌뵬 占쎈솭占쎈뮞�몴占� 占쎌뵠占쎌뒠占쎈퉸占쎈튊 占쎈막占쎈쾹?
			for(int i=0;i<rightTxt.size();i++){
				r_writer.write(rightTxt.get(i)+"\r\n"); // 占쎌맊占쎈즲占쎌뒭 疫꿸퀡而� 筌롫뗀�걟占쎌삢占쎈퓠占쎄퐣占쎈뮉 揶쏆뮉鍮놅옙�뵠 占쎈툧占쎈쭡, 揶쏆뮉六얕눧紐꾩쁽�몴占�  /r/n嚥∽옙 占쎈퉸占쎈튊 占쎈�볩옙�뮞占쎈뱜占쎄땀占쎌벥 揶쏆뮉六얕눧紐꾩쁽揶쏉옙 筌롫뗀�걟占쎌삢占쎈퓠 占쎌읅占쎌뒠占쎈쭡 ( 占쎈솁占쎌뵬 嚥≪뮆諭� 占쎈막占쎈르 揶쏆뮉六얕눧紐꾩쁽 �굜遺얜굡�몴占� 獄쏅떽��筌롳옙 占쎈쭍占쎈쾹)
			}
			
			r_writer.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		
		}catch(IOException e){
			e.printStackTrace();	
		}
		/*
		 * rightFile占쎈퓠占쎈뼄揶쏉옙 rightTxt�몴占� 占쎄퉱嚥∽옙 占쎈쑅雅뚯눘苑�占쎌뒄 (餓ο옙占쎌냹)
		 * fileWriter 占쎌뵠占쎌뒠(占쎈뼄�몴占� 占쎈맙占쎈땾占쎈연占쎈즲 占쎄맒�꽴占쏙옙占� 占쎈씨占쎌벉)
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
		 * fileChooser �뜝�럥留쇿뜝�럥�빢 �뜝�럩逾졾뜝�럩�뮔�뜝�럥由��뜝�럥�뿰 data field�뜝�럥�뱺 �뜝�럩肉녑뜝�럥裕� leftPath, leftTxt, leftFile �뜝�럩諭� 嶺뚳옙熬곣뫗�쐳�뜝�럡�맜�뜝�럥�꽑�썒�슣�닔�땻占썲뜝�럩�뭵(�뜝�럩逾뺝뜝�럡留�)
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
