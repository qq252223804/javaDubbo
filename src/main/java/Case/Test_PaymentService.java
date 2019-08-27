//package Case;
//
//import Common.JDBCUtils;
//import Common.OrderNo;
//import Common.miaoshu;
//import Report.ZTestReport;
//import com.alibaba.fastjson.JSON;
//import com.ifuel.common.returnType.DataResult;
//import com.ifuel.common.returnType.Result;
//import com.ifuel.pay.manager.client.service.PaymentService;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.testng.Assert;
//import org.testng.Reporter;
//import org.testng.annotations.Listeners;
//import org.testng.annotations.Test;
//
//import java.sql.SQLException;
//
////监听报告脚本类
//@Listeners({ZTestReport.class})
//public class Test_PaymentService {
//	ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("Case"+"/spring-consumer.xml");
//	PaymentService paymentService=context.getBean("PaymentService",PaymentService.class);
//
//	DataResult<Long>  dataResult=new DataResult<>();
//	Result result=new Result();
//	Result dataResult2=new DataResult<>();
//	String outTradeN0= OrderNo.doOrderNum();
//	String outTradeN02= OrderNo.doOrderNum();
//
//
//	@Test(description ="创建支付订单:充值订单")
//	public void test1_createPayment_type1(){
//		dataResult= paymentService.createPayment(1,1000,outTradeN0,"测试dubbo","测试dubbo");
//		String res= JSON.toJSONString(dataResult);
//		Reporter.log(miaoshu.txt(String.format("(1,1000,%s,\"测试dubbo\",\"测试dubbo\")", outTradeN0),res));
//		System.out.println(res);
//		Assert.assertTrue(res.contains("\"code\":0"));
//
//	}
//	@Test(description ="创建支付订单:商品消费订单")
//	public void test2_createPayment_type2(){
//		dataResult= paymentService.createPayment(2,1000,outTradeN02,"测试dubbo","测试dubbo");
//		String res= JSON.toJSONString(dataResult);
//		Reporter.log(miaoshu.txt(String.format("(1,1000,%s,\"测试dubbo\",\"测试dubbo\")", outTradeN0),res));
//		System.out.println(res);
//		Assert.assertTrue(res.contains("\"code\":0"));
//
//	}
//	@Test(description ="创建支付订单:订单号为空")
//	public void test3_createPayment_NOoutTradeNO(){
//		dataResult= paymentService.createPayment(2,1000,"","测试dubbo","测试dubbo");
//		String res= JSON.toJSONString(dataResult);
//		Reporter.log(miaoshu.txt("2,1000,\"\",\"测试dubbo\",\"测试dubbo\"",res));
//		System.out.println(res);
//		Assert.assertTrue(res.contains("\"code\":0"));
//
//	}
//
//	@Test(description =" 获取支付订单详情:库中已存在")
//	public void test4_queryPaymentByTradeNo_right(){
//		result= paymentService.queryPaymentByTradeNo("2019072210111971669562");
//		String res= JSON.toJSONString(result);
//		Reporter.log(miaoshu.txt("2019072210111971669562",res));
//		System.out.println(res);
//		Assert.assertTrue(res.contains("\"code\":0"));
//
//	}
//	@Test(description =" 获取支付订单详情:订单号不存在")
//	public void test5_queryPaymentByTradeNo_NoTradeNo(){
//		result= paymentService.queryPaymentByTradeNo("99999");
//		String res= JSON.toJSONString(result);
//		Reporter.log(miaoshu.txt("99999",res));
//		System.out.println(res);
//		Assert.assertTrue(res.contains("\"code\":203001"));
//
//	}
//	@Test(description =" 获取支付订单详情:订单号为空")
//	public void test6_queryPaymentByTradeNo_NULL(){
//		result= paymentService.queryPaymentByTradeNo("");
//		String res= JSON.toJSONString(result);
//		Reporter.log(miaoshu.txt("",res));
//		System.out.println(res);
//		Assert.assertTrue(res.contains("\"code\":203001"));
//
//	}
//	@Test(description ="删除支付订单:库中已存在")
//	public void test7_deletePayment() throws SQLException {
//		dataResult2= paymentService.deletePayment(outTradeN0);
//		String res= JSON.toJSONString(dataResult2);
//		Reporter.log(miaoshu.txt(outTradeN0,res));
//		System.out.println(res);
//		int deleted=(int) JDBCUtils.querySingle("SELECT `deleted` from `ifuel-payment`.t_payment WHERE " +
//				"out_trade_no=(?)",outTradeN0);
//		Assert.assertEquals(1,deleted,"订单删除失败，状态不一致");
//		Assert.assertTrue(res.contains("\"code\":0"));
//
//	}
//	@Test(description ="删除支付订单:库中订单号不存在")
//	public void test8_deletePayment_NoTradeNo(){
//		dataResult2= paymentService.deletePayment("99999");
//		String res= JSON.toJSONString(dataResult2);
//		Reporter.log(miaoshu.txt("99999",res));
//		System.out.println(res);
//		Assert.assertTrue(res.contains("\"code\":203001"));
//
//	}
//	@Test(description ="删除支付订单:订单号为空")
//	public void test9_deletePayment_NULL(){
//		dataResult2= paymentService.deletePayment("");
//		String res= JSON.toJSONString(dataResult2);
//		Reporter.log(miaoshu.txt("",res));
//		System.out.println(res);
//		Assert.assertTrue(res.contains("\"code\":203001"));
//
//	}
//
//
//}
