package test;
import static org.junit.Assert.*;
import java.io.File;
import org.junit.Test;

import model.SMergeModel;

public class SMergeModelTest {
	private SMergeModel model = new SMergeModel();

	@Test
	public void testleftLoad() {
		File file = new File("/Users/LeeHyungJoo/Downloads/test.txt");
		//테스트하려는 텍스트파일의 위치를 입력
		model.setleftFile(file);
		model.leftLoad();
		StringBuilder testString=new StringBuilder("111\n");
		//테스트 하려는 텍스트 파일의 내용을 입력
		//assertEquals(testString,model.getleftTxt());
		//안됨 강의자료 참고

		assertTrue(testString.toString().equals(model.getleftTxt().toString()));
		//testSting과 leftTxt를 모두 string형으로 변경, 비교
	}
}

