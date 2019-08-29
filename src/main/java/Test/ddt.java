package Test;

import Common.miaoshu;
import Report.ZTestReport;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

//监听报告脚本类
@Listeners({ZTestReport.class})
public class ddt {
	@DataProvider(name = "tes1")
	public static Object[][] TestNgLearn2() {
		return new Object[][] { { 2, "1" }, { 6, "12" } };

	}

	@Test(description = "ddt",dataProvider = "tes1")
	public void TestNgLearn1(Integer inputNumber, String expectedResult) {
		System.out.println(inputNumber + " " + expectedResult);
		String res="123";
		Reporter.log(miaoshu.txt(String.format("(%s,%s)", inputNumber,expectedResult),res));
//		Reporter.log(miaoshu.txt("{ 6, \"12\" }",res));

//		Assert.assertEquals(expectedResult, inputNumber);
	}



}