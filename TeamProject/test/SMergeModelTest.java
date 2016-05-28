package test;
import static org.junit.Assert.*;
import java.io.File;
import org.junit.Test;

import model.SMergeModel;

public class SMergeModelTest {
	private SMergeModel model = new SMergeModel();

	@Test
	public void testLoad() {
		File file1 = new File("C:\\Users\\rhs12\\Desktop\\1.txt");
		File file2 = new File("C:\\Users\\rhs12\\Desktop\\2.txt");
		//테스트하려는 텍스트파일의 위치를 입력
		//1과2는 같은텍스트, 커서가 어디있든지 상관이 없음
		model.setleftFile(file1);
		model.leftLoad();
		model.setrightFile(file2);
		model.rightLoad();
		assertEquals(model.getleftTxt(),model.getrightTxt());
	}
	
	@Test
	public void testRepeatLoad() {
		File file1 = new File("C:\\Users\\rhs12\\Desktop\\1.txt");
		File file2 = new File("C:\\Users\\rhs12\\Desktop\\2.txt");
		//테스트하려는 텍스트파일의 위치를 입력
		model.setleftFile(file1);
		model.leftLoad();
		model.leftLoad();
		//같은텍스트를 하나는 두번 불러들임
		model.setrightFile(file2);
		model.rightLoad();
		assertEquals(model.getleftTxt(),model.getrightTxt());
		//변경전에는 fail, 지금은 정상적 
	}
}

