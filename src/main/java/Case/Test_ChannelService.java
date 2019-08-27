//package Case;
//
//import Common.OrderNo;
//import Common.miaoshu;
//import Report.ZTestReport;
//import com.alibaba.fastjson.JSON;
//import com.ifuel.common.returnType.DataResult;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.testng.Assert;
//import org.testng.Reporter;
//import org.testng.annotations.Listeners;
//import org.testng.annotations.Test;
//
////监听报告脚本类
//@Listeners({ZTestReport.class})
//public class Test_ChannelService {
//	ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("Case"+"/spring-consumer.xml");
//	ChannelService channelService=context.getBean("ChannelService",ChannelService.class);
//	DataResult<String> dataResult=new DataResult<>();
//	String outTradeN0= OrderNo.doOrderNum();
//	@Test(description = "微信 APP 方式支付")
//	public void test1_payByWxPayApp_right(){
//		dataResult=channelService.payByWxPayApp("2019072210111971669562");
//		String res= JSON.toJSONString(dataResult);
//		Reporter.log(miaoshu.txt(outTradeN0,res));
//		System.out.println(res);
//		Assert.assertTrue(res.contains("\"code\":0"));
//	}
//}
