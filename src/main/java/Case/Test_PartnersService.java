//package Case;
//
//import com.alibaba.fastjson.JSON;
//import com.ifuel.cec.manager.client.domain.PartnersDTO;
//import com.ifuel.cec.manager.client.service.PartnersService;
//import com.ifuel.common.returnType.DataResult;
//import com.ifuel.common.returnType.ListResult;
//import com.ifuel.common.returnType.Result;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.testng.Assert;
//import org.testng.Reporter;
//import org.testng.SkipException;
//import org.testng.annotations.Listeners;
//import org.testng.annotations.Test;
//import Report.ZTestReport;
//
////监听报告脚本类
//@Listeners({ZTestReport.class})
//public class Test_PartnersService {
//	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Case"+ "/spring" +
//			"-consumer.xml");
//	PartnersService partnersService=context.getBean("PartnersService",PartnersService.class);
//	ListResult<PartnersDTO> ListResult=new ListResult<>();
//	DataResult<PartnersDTO> dataResult=new DataResult<>();
//	Result result=new Result();
//
//	@Test(description = "创建新的合作伙伴:库中不存在的合作商")
//	public void test1_createPartners_right(){
//		throw new SkipException("skip the test");
////		dataResult=partnersService.createPartners("359705330","111111111");
////		String res= JSON.toJSONString(dataResult);
////		Reporter.log(res);
////		System.out.println(res);
////		Assert.assertTrue(res.contains("\"code\":0"));
//	}
//
//	@Test(description = "根据合作商标识获取合作伙伴信息:库中已存在的合作商")
//	public void test2_queryPartnersByOperatorId_right() {
//		dataResult = partnersService.queryPartnersByOperatorId("745467123");
//		String res= JSON.toJSON(dataResult).toString();
//		System.out.println(res);
//		Reporter.log(res);
//		Assert.assertTrue(res.contains("\"code\":0"));
//	}
//	@Test(description = "删除合作商:库中已存在合作伙伴ID")
//	public void test3_deletePartners_right() {
//		result=partnersService.deletePartners(6);
//		String res= JSON.toJSONString(result);
//		System.out.println(res);
//		Reporter.log(res);
//		Assert.assertTrue(res.contains("\"code\":0"));
//	}
//	@Test(description = "查询全部运营商列表")
//	public void test4_queryAll_right(){
//		ListResult=partnersService.queryAll();
//		String res=JSON.toJSONString(ListResult);
//		Reporter.log(res);
//		System.out.println(res);
//		Assert.assertTrue(res.contains("\"code\":0"));
//
//	}
//
//}
