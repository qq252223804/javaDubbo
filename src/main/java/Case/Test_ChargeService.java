//package Case;
//
//import Common.JDBCUtils;
//import Common.OrderNo;
//import Common.miaoshu;
//import Report.ZTestReport;
//import com.alibaba.fastjson.JSON;
//import com.ifuel.cec.manager.client.domain.ChargeStatusDTO;
//import com.ifuel.cec.manager.client.service.ChargeService;
//import com.ifuel.common.returnType.DataResult;
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
//public class Test_ChargeService {
//	ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("Case"+"/spring-consumer.xml");
//	ChargeService chargeService=context.getBean("ChargeService",ChargeService.class);
//	DataResult<String> dataResult=new DataResult<>();
//	DataResult<ChargeStatusDTO> dataResult2=new DataResult<ChargeStatusDTO>();
//
//	String order_id= OrderNo.doOrderNum();
//
//	@Test(description = "获取合作伙伴认证token：库中已存在operatorId")
//	public void test1_queryToken_right(){
//		dataResult=chargeService.queryToken("791604862");
//		String res= JSON.toJSONString(dataResult);
//		Reporter.log(miaoshu.txt("791604862",res));
//		System.out.println(res);
//		Assert.assertTrue(res.contains("\"code\":0,\"data\":"));
//	}
//	@Test(description = "获取合作伙伴认证token：库中不存在operatorId")
//	public void test2_queryToken_N0operatorId(){
//		dataResult=chargeService.queryToken("111111");
//		String res= JSON.toJSONString(dataResult);
//		Reporter.log(miaoshu.txt("111111",res));
//		System.out.println(res);
//		Assert.assertTrue(res.contains("\"code\":20401"));
//
//	}
//	@Test(description = "获取合作伙伴认证token：operatorId为空")
//	public void test3_queryToken_operatorId_null(){
//		dataResult=chargeService.queryToken("");
//		String res= JSON.toJSONString(dataResult);
//		Reporter.log(miaoshu.txt("",res));
//		System.out.println(res);
//		Assert.assertTrue(res.contains("\"code\":20401"));
//	}
//	@Test(description = "启动充电服务:订单号正确且库中已存在的pileid")
//	public void test4_queryStartCharge_right() throws SQLException {
//		String pile_id=(String) JDBCUtils.querySingle("SELECT pile_id FROM charging.cp_pile WHERE station_id=(SELECT " +
//				"id FROM charging.cp_station WHERE `name`=\"城北天阳亲子广场\") AND sort=1");
//		int pileid = Integer.valueOf(pile_id);
//		dataResult2=chargeService.queryStartCharge("791604862",order_id,pileid);
//		String res=JSON.toJSONString(dataResult2);
//		Reporter.log(miaoshu.txt(String.format("\"791604862\",%s,%s",order_id,pile_id),res));
//		System.out.println(res);
//		Assert.assertTrue(res.contains("\"code\":0"));
//	}
//	@Test(description = "启动充电服务:库中不存在的pileid")
//	public void test5_queryStartCharge_NoPileid() throws SQLException {
//
//		dataResult2=chargeService.queryStartCharge("791604862",order_id,0000);
//		String res=JSON.toJSONString(dataResult2);
//		Reporter.log(miaoshu.txt(String.format("\"791604862\",%s,0000",order_id),res));
//		System.out.println(res);
//		Assert.assertTrue(res.contains("\"code\":0"));
//	}
//
//	@Test(description = "停止充电服务:订单号正确且库中已存在的pileid")
//	public void test6_queryStopCharge_right() throws SQLException {
//		String pile_id=(String) JDBCUtils.querySingle("SELECT pile_id FROM charging.cp_pile WHERE station_id=(SELECT " +
//				"id FROM charging.cp_station WHERE `name`=\"城北天阳亲子广场\") AND sort=1");
//		int pileid = Integer.valueOf(pile_id);
//		dataResult2=chargeService.queryStopCharge("791604862",order_id,pileid);
//		String res=JSON.toJSONString(dataResult2);
//		Reporter.log(miaoshu.txt(String.format("\"791604862\",%s,0000",order_id),res));
//		System.out.println(res);
//		Assert.assertTrue(res.contains("\"code\":0"));
//	}
//	@Test(description = "停止充电服务:库中不存在的pileid")
//	public void test7_queryStopCharge_NoPileid() throws SQLException {
//
//		dataResult2=chargeService.queryStopCharge("791604862",order_id,0000);
//		String res=JSON.toJSONString(dataResult2);
//		Reporter.log(miaoshu.txt(String.format("\"791604862\",%s,0000",order_id),res));
//		System.out.println(res);
//		Assert.assertTrue(res.contains("\"code\":0"));
//	}
//}
