package Case;

import Common.miaoshu;
import Report.ZTestReport;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.Arrays;

//导入生成报告脚本

//监听报告脚本类
@Listeners({ZTestReport.class})
public class tesy {

	@BeforeMethod(description = "测试方法前初始化")
	public void beforeMethod(Method m) {
		if ("testDemo5".equals(m.getName())) {
			int a = 1 / 0;
			System.out.println(a);
		}
	}

	@Test(description = "测试DEMO1")
	public void testDemo1() {
		Reporter.log("this is demo!");
		Assert.assertEquals("b", "b", "should be equals.");
	}

	@Test()
	public void testDemo2() {
		int[] c = new int[5];

		//循环数组赋值
		for (int i = 0; i < c.length; i++) {
			//Math.random() 生成【0,1)的随机数 1不包含
			c[i] = (int) (Math.random() * 6 + 5); //随机生成5-10的数
			System.out.println(c[i]);
		}
		System.out.println(Arrays.toString(c));
	}
	@Test()
	public void testDemo3(){
		System.out.print(miaoshu.txt(String.format("(%s,100,7,2,\"\",0,0,0,1564474771L,1564474771L,0L) ", "111"),"data:"));
		Reporter.log(miaoshu.txt(String.format("%s,100,7,2,\"\",0,0,0,1564474771L,1564474771L,0L", "111"),"data:{}"));

	}
	@Test()
	public void  testDemo4(){
		String stationId=String.valueOf((int) (Math.random() * 999999 + 100000));
		System.out.println(stationId);
	}
}