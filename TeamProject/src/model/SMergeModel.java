package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//?��?���뗰?���뗰?����

public class SMergeModel {
	String leftPath;			//�뜝�럩�눁嶺뚯?���삕 �뜝�럩�꼨嶺뚮?��?�� �뜝�럥�냱�뜝�럩?�у뜝�럩踰� Path�뤆�룊�삕 �뜝�럥?��뽫뭐?��?�삕	
	String rightPath;			//�뜝�럩沅롳?��紐댐쭗袁좉국 �뜝�럩�꼨嶺뚮?��?�� �뜝�럥�냱�뜝�럩?�у뜝�럩踰� Path�뤆�룊�삕 �뜝�럥?��뽫뭐?��?�삕
	ArrayList<String> leftTxt;		//�뜝�럩�눁嶺뚯?���삕 �뜝�럩�꼨嶺뚮?���샍?��?���? �뜝�럥?��?��?���삕?��?��츩�?���럥�??��뤆�룊�?�� �뜝�럥?��뽫뭐?��?�삕
	ArrayList<String> rightTxt;		//�뜝�럩沅롳?��紐댐쭗袁좉국 �뜝�럩�꼨嶺뚮?���샍?��?���? �뜝�럥?��?��?���삕?��?��츩�?���럥�??��뤆�룊�?�� �뜝�럥?���?
	ArrayList<Boolean> txtBoolean;
	File leftFile;				//�뜝�럩沅롳?��紐댐쭗袁좉국 �뜝�럥�냱�뜝�럩?�� ?��?��쨨占?��벣占?��뮋�?���뜝?���?
	File rightFile;				//�뜝�럩�눁嶺뚯?���삕 �뜝�럥�냱�뜝�럩?�� ?��?��쨨占?��벣占?��뮋�?���뜝?���?
	
	public String getleftPath(){return leftPath;}
	public String getrightPath(){return rightPath;}
	public ArrayList<String> getleftTxt(){return leftTxt;}
	public ArrayList<String> getrightTxt(){return rightTxt;}
	public ArrayList<Boolean> gettxtBoolean(){return txtBoolean;}
	public File getleftFile(){return leftFile;}
	public File getrightFile(){return rightFile;}
	
	public void setleftPath(String leftPath){this.leftPath=leftPath;}
	public void setrightPath(String rightPath){this.rightPath=rightPath;}
	public void setleftTxt(ArrayList<String> leftTxt){this.leftTxt=leftTxt;}
	public void setrightTxt(ArrayList<String> rightTxt){this.rightTxt=rightTxt;}
	public void setleftBoolean(ArrayList<Boolean> txtBoolean){this.txtBoolean=txtBoolean;}
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
			FileWriter l_writer = new FileWriter(leftFile);// �뜝�럥�냱�뜝�럩?�� �뤆�룇?��?��?���뮁紐닷?��?���? ?��?��?��?��?��?���댆�눦�삕�뜮�쉻�삕�뇡?���?? �뜝�럥�닡�뜝�럥?��?���?濡녹?�� �뜝�럥�냱�뜝�럩?�� �뜝�럥�넮�뜝�럥裕욑?��紐닷?��?���? �뜝�럡�맂�뇦?��?��쳛�?���쉻�삕�뇡?���??
		                                                   // �뜝�럥�넮�뜝�럥裕욄뤆�룊�?�� �뛾�룆���뜝�룞�삕 �뇦?��?��?��?��?���? �뜝�럥�냱�뜝�럩?�� �뜝�럥�넮�뜝�럥裕욑?��紐닷?��?���? �뜝�럩?�졾?���럩�뮔�뜝�럥�돵�뜝�럥�뒍 �뜝�럥留됧?���럥?���??
			for(int i=0;i<leftTxt.size();i++){
				l_writer.write(leftTxt.get(i)+"\r\n"); // �뜝�럩留듿?���럥利꿨?���럩��?? �뼨轅명�↑�뚳?�� 嶺뚮?����?��?��콫�?���럩�궋�뜝�럥�뱺�뜝�럡�맋�뜝�럥裕� �뤆�룇裕됮?���냵�삕?��?��?�� �뜝�럥�닱�뜝�럥彛�, �뤆�룇裕됵쭛�뼍�?��筌뤾?���겱?��?��?���뜝?���?  /r/n�슖�댙�삕 �뜝�럥�돵�뜝�럥�뒍 �뜝�럥?��?��?���삕?��?��츩�?���럥�??��?���럡���뜝�럩踰� �뤆�룇裕됵쭛�뼍�?��筌뤾?���겱�뤆�룊�삕 嶺뚮?����?��?��콫�?���럩�궋�뜝�럥�뱺 �뜝�럩�쓤�뜝�럩�뮔�뜝�럥彛� ( �뜝�럥�냱�뜝�럩?�� �슖�돦裕녻?��?���? �뜝�럥留됧?���럥?���? �뤆�룇裕됵쭛�뼍�?��筌뤾?���겱 ?��?��??�겫�뼔?��?��?��紐닷?��?���? �뛾�룆�뼺?��?��?��嶺뚮?��?�� �뜝�럥彛띶?���럥?���?)
			}
			l_writer.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		
		}catch(IOException e){
			e.printStackTrace();
		}
		/*
		 * leftFile�뜝�럥�뱺�뜝�럥?��꾣뤆�룊�삕 leftTxt?��?��?���뜝?���? �뜝�럡�돮�슖�댙�삕 �뜝�럥�몗�썒�슣�닔�땻?��?��?���럩��??(繞벿?��?���뜝�럩�꺓)
		 * fileWriter �뜝�럩?�졾?���럩�뮔(�뜝�럥?��꾬옙紐닷?��?���? �뜝�럥留쇿?���럥�빢�뜝�럥�뿰�뜝�럥利� �뜝�럡留�???��?��?��?���룞�삕�뜝?���? �뜝�럥�뵪�뜝�럩踰�)
		 */
		
	}
	
	public void rightSave(ArrayList<String> rightTxt){
		this.rightTxt = rightTxt;
		
		try{
			FileWriter r_writer = new FileWriter(rightFile);// �뜝�럥�냱�뜝�럩?�� �뤆�룇?��?��?���뮁紐닷?��?���? ?��?��?��?��?��?���댆�눦�삕�뜮�쉻�삕�뇡?���?? �뜝�럥�닡�뜝�럥?��?���?濡녹?�� �뜝�럥�냱�뜝�럩?�� �뜝�럥�넮�뜝�럥裕욑?��紐닷?��?���? �뜝�럡�맂�뇦?��?��쳛�?���쉻�삕�뇡?���??
		                                                   // �뜝�럥�넮�뜝�럥裕욄뤆�룊�?�� �뛾�룆���뜝�룞�삕 �뇦?��?��?��?��?���? �뜝�럥�냱�뜝�럩?�� �뜝�럥�넮�뜝�럥裕욑?��紐닷?��?���? �뜝�럩?�졾?���럩�뮔�뜝�럥�돵�뜝�럥�뒍 �뜝�럥留됧?���럥?���??
			for(int i=0;i<rightTxt.size();i++){
				r_writer.write(rightTxt.get(i)+"\r\n"); // �뜝�럩留듿?���럥利꿨?���럩��?? �뼨轅명�↑�뚳?�� 嶺뚮?����?��?��콫�?���럩�궋�뜝�럥�뱺�뜝�럡�맋�뜝�럥裕� �뤆�룇裕됮?���냵�삕?��?��?�� �뜝�럥�닱�뜝�럥彛�, �뤆�룇裕됵쭛�뼍�?��筌뤾?���겱?��?��?���뜝?���?  /r/n�슖�댙�삕 �뜝�럥�돵�뜝�럥�뒍 �뜝�럥?��?��?���삕?��?��츩�?���럥�??��?���럡���뜝�럩踰� �뤆�룇裕됵쭛�뼍�?��筌뤾?���겱�뤆�룊�삕 嶺뚮?����?��?��콫�?���럩�궋�뜝�럥�뱺 �뜝�럩�쓤�뜝�럩�뮔�뜝�럥彛� ( �뜝�럥�냱�뜝�럩?�� �슖�돦裕녻?��?���? �뜝�럥留됧?���럥?���? �뤆�룇裕됵쭛�뼍�?��筌뤾?���겱 ?��?��??�겫�뼔?��?��?��紐닷?��?���? �뛾�룆�뼺?��?��?��嶺뚮?��?�� �뜝�럥彛띶?���럥?���?)
			}
			
			r_writer.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		
		}catch(IOException e){
			e.printStackTrace();	
		}
		/*
		 * rightFile�뜝�럥�뱺�뜝�럥?��꾣뤆�룊�삕 rightTxt?��?��?���뜝?���? �뜝�럡�돮�슖�댙�삕 �뜝�럥�몗�썒�슣�닔�땻?��?��?���럩��?? (繞벿?��?���뜝�럩�꺓)
		 * fileWriter �뜝�럩?�졾?���럩�뮔(�뜝�럥?��꾬옙紐닷?��?���? �뜝�럥留쇿?���럥�빢�뜝�럥�뿰�뜝�럥利� �뜝�럡留�???��?��?��?���룞�삕�뜝?���? �뜝�럥�뵪�뜝�럩踰�)
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
		 * fileChooser ?��?��?��?��?��?��筌띿?���쐻?��?��?��?��?���? ?��?��?��?��?��?���얠±�쐻?��?��?��?��?��츛占?��?��?��?��?���뵳?��?��?���쐻?��?��?��?��?��?�� data field?��?��?��?��?��?��?��?���? ?��?��?��?��?��?���굢��?��?��?��?��?��?��뺧옙 leftPath, leftTxt, leftFile ?��?��?��?��?��?��?���占�? ?��?��?���삕�넭?�ｋ쳴占?��맫占?��?��?��?��?��?��?��쭨占?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��빝�?���뜴�쐻?��?��?��?��?����(?��?��?��?��?��?���얜?���쐻?��?��?��筌랃?��)
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
