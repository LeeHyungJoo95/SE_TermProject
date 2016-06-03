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
		//�׽�Ʈ�Ϸ��� �ؽ�Ʈ������ ��ġ�� �Է�
		//1��2�� �����ؽ�Ʈ, Ŀ���� ����ֵ��� ����� ����
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
		//�׽�Ʈ�Ϸ��� �ؽ�Ʈ������ ��ġ�� �Է�
		model.setleftFile(file1);
		model.leftLoad();
		model.leftLoad();
		//�����ؽ�Ʈ�� �ϳ��� �ι� �ҷ�����
		model.setrightFile(file2);
		model.rightLoad();
		assertEquals(model.getleftTxt(),model.getrightTxt());
		//���������� fail, ������ ������ 
	}
}

