package Case;

import Common.JDBCUtils;
import Common.miaoshu;
import Report.ZTestReport;
import com.alibaba.fastjson.JSON;
import com.ifuel.common.returnType.DataResult;
import com.ifuel.common.returnType.ListResult;
import com.ifuel.common.returnType.Result;
import com.ifuel.dubboContextTool.DubboContext;
import com.ifuel.order.manager.client.domain.Bill;
import com.ifuel.order.manager.client.domain.TerminateOrder;
import com.ifuel.order.manager.client.service.OrderService;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.sql.SQLException;

//import com.ifuel.order.manager.client.domain.Bill;

@Listeners({ZTestReport.class})
public class Test_OrderService {
	ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("spring-consumer.xml");
	OrderService orderService=context.getBean("OrderService", OrderService.class);

	DataResult<String> dataResult=new DataResult<>();
	Result result=new Result();
	ListResult<Bill> listResult=new ListResult<>();



	@DataProvider(name = "content")
	public static Object[][] TestCreateOrder() {
		return new Object[][] {
				{ "","1601041","浙A99999","7","1","1","1" },
				{ "37","","浙A99999","7","1","1","1" },
				{ "37","1601041","","7","1","1","1" },
				{ "37","1601041","浙A99999","","1","1","1" },
				{ "37","1601041","浙A99999","7","","1","1" },
				{ "37","1601041","浙A99999","7","1","","1" },
				{ "37","1601041","浙A99999","7","1","1","" }};

	}
	@Test(description = "创建订单（信息正确）")
	public void   test1_createOrder_right(){
		DubboContext.setUserId("15654");
		dataResult=orderService.createOrder("37","1601041","浙A99999","7","1","1","1");
		String res= JSON.toJSONString(dataResult);
		Reporter.log(miaoshu.txt("{\"37\",\"1601041\",\"浙A99999\",\"7\",\"1\",\"1\",\"1\"}", res));
		System.out.println(res);
		Assert.assertTrue(res.contains("\"code\":0"));


	}
	@Test(description = "创建订单（参数缺少）",dataProvider = "content")
	public void test2_createOrder_missParameter(String stationId, String connectorId, String plateNo, String walletId,
										String areaCode, String channelUser, String payingUser){
		DubboContext.setUserId("15654");
		dataResult=orderService.createOrder(stationId, connectorId, plateNo, walletId,
				areaCode, channelUser, payingUser);
		String res= JSON.toJSONString(dataResult);
		Reporter.log(miaoshu.txt(String.format("(%s,%s,%s,%s,%s,%s,%s)",stationId, connectorId, plateNo, walletId,
				areaCode, channelUser, payingUser ),res));
		System.out.println(res);
		Assert.assertTrue(res.contains("\"code\":103422"));

	}
	@Test(description = "查询当前充电订单-枪号id为空")
	public void test3_queryCurrentOrderId_NoconnectorId(){
		dataResult = orderService.queryCurrentOrderId("");
		String res = JSON.toJSONString(dataResult);
		Reporter.log(miaoshu.txt("", res));
		System.out.println(res);
		Assert.assertTrue(res.contains("\"code\":0"));
	}
	@Test(description = "查询当前充电订单-不存在枪号")
	public void test4_queryCurrentOrderId_ErrorconnectorId(){
		dataResult = orderService.queryCurrentOrderId("11111");
		String res = JSON.toJSONString(dataResult);
		Reporter.log(miaoshu.txt("", res));
		System.out.println(res);
		Assert.assertTrue(res.contains("\"code\":0"));

	}
	@Test(description = "查询当前充电订单-存在枪号")
	public void test5_queryCurrentOrderId_right(){
		dataResult = orderService.queryCurrentOrderId("1601041");
		String res = JSON.toJSONString(dataResult);
		Reporter.log(miaoshu.txt("", res));
		System.out.println(res);
		Assert.assertTrue(res.contains("\"code\":0"));

	}

	@Test(description = "开始充电通知")
	public void test6_startChargingNotify() throws SQLException {
		String  orderid = (String) JDBCUtils.querySingle("select order_id from `ifuel-order`.t_order where " +
				"user_id=\"15654\" order by id DESC LIMIT 1");
		result=orderService.startChargingNotify(String.format("%s", orderid),1566806233832L);
		String res=JSON.toJSONString(result);
		Reporter.log(miaoshu.txt(String.format("(%s,1566806233832L)", orderid), res));
		System.out.println(res);
		Assert.assertTrue(res.contains("\"code\":0"));
	}
	@Test(description = "开始充电通知-NoOrderId")
	public void test7_startChargingNotify_NoOrderId(){
		result=orderService.startChargingNotify("",1566806233832L);
		String res=JSON.toJSONString(result);
		Reporter.log(miaoshu.txt("(\"\",1566806233832L)", res));
		System.out.println(res);
		Assert.assertTrue(res.contains("\"code\":103422"));
}
	@Test(description = "开始充电通知-NostarAt")
	public void test8_startChargingNotify_NostarAt(){
		result=orderService.startChargingNotify("201908261540260971144",0L);
		String res=JSON.toJSONString(result);
		Reporter.log(miaoshu.txt("(\"201908261540260971144\",0L)", res));
		System.out.println(res);
		Assert.assertTrue(res.contains("\"code\":0"));
	}
	@Test(description = "结束订单-订单信息正确-桩状态为充电中")
	public void test9_terminateOrder_right() throws SQLException {
		String  orderid = (String) JDBCUtils.querySingle("select order_id from `ifuel-order`.t_order where " +
				"user_id=\"15654\" order by id DESC LIMIT 1");
		long id = JDBCUtils.executeInsert(String.format("INSERT INTO `ifuel-order`.`t_order_child` VALUES (id,'%S', " +
				"'32', '闲时', '200', '60', '160', '136', '320', '272', '1566987545000', '1566997545000', '0')", orderid));
//		System.out.println(id);
//		System.out.println(orderid);
		DubboContext.setUserId("15654");
		TerminateOrder terminateOrder = new TerminateOrder();
		terminateOrder.setOrderId(String.format("%s", orderid));
//		terminateOrder.setOrderId("201908291014222351171");
		terminateOrder.setChargingNum(2.0F);
		terminateOrder.setChargingTime(0.6F);
		terminateOrder.setStartAt(1566987545000L);
		terminateOrder.setEndAt(System.currentTimeMillis());
		terminateOrder.setEndSoc(99.00F);
		String sb=JSON.toJSONString(terminateOrder);
		System.out.println(sb);

		result= orderService.terminateOrder(terminateOrder);
		String res=JSON.toJSONString(result);
		Reporter.log(miaoshu.txt(sb,res));
		System.out.println(res);
		Assert.assertTrue(res.contains("\"code\":0"));
	}
	@Test(description = "结束订单-订单信息跟userid不一致")
	public void test_10_terminateOrder_errrorUserid(){
		DubboContext.setUserId("15654");
		TerminateOrder terminateOrder = new TerminateOrder();
		terminateOrder.setOrderId("201908261540260971144");
		terminateOrder.setChargingNum(6.5F);
		terminateOrder.setChargingTime(3.5F);
		terminateOrder.setStartAt(1566983066000L);
		terminateOrder.setEndAt(System.currentTimeMillis());
		terminateOrder.setEndSoc(88.00F);
		String sb=JSON.toJSONString(terminateOrder);
//		System.out.println(sb);
		result= orderService.terminateOrder(terminateOrder);
		String res=JSON.toJSONString(result);
		Reporter.log(miaoshu.txt(sb,res));
		System.out.println(res);
		Assert.assertTrue(res.contains("\"code\":103403"));
	}

	@Test(description = "结束订单-订单不在充电中状态")
	public void test_11_terminateOrder_errroStatus() throws  SQLException{
		DubboContext.setUserId("1");
		TerminateOrder terminateOrder = new TerminateOrder();
		terminateOrder.setOrderId("201908280958480367691");
		terminateOrder.setChargingNum(6.5F);
		terminateOrder.setChargingTime(3.5F);
		terminateOrder.setStartAt(1566983066000L);
		terminateOrder.setEndAt(System.currentTimeMillis());
		terminateOrder.setEndSoc(88.00F);
		String sb=JSON.toJSONString(terminateOrder);
		System.out.println(sb);

		result= orderService.terminateOrder(terminateOrder);
		String res=JSON.toJSONString(result);
		Reporter.log(miaoshu.txt(sb,res));
		System.out.println(res);
		Assert.assertTrue(res.contains("\"code\":103403"));
	}

	@Test(description = "结束订单-状态已结束")
	public void test_12_terminateOrder_status3() {
		DubboContext.setUserId("1");
		TerminateOrder terminateOrder = new TerminateOrder();
		terminateOrder.setOrderId("201908261540260971144");
		terminateOrder.setChargingNum(6.5F);
		terminateOrder.setChargingTime(3.5F);
		terminateOrder.setStartAt(1566983066000L);
		terminateOrder.setEndAt(System.currentTimeMillis());
		terminateOrder.setEndSoc(88.00F);
		String sb=JSON.toJSONString(terminateOrder);
		System.out.println(sb);

		result= orderService.terminateOrder(terminateOrder);
		String res=JSON.toJSONString(result);
		Reporter.log(miaoshu.txt(sb,res));
		System.out.println(res);
		Assert.assertTrue(res.contains("\"code\":103403"));
	}

	@Test(description = "查询是否有未支付账单-用户无未支付订单")
	public void test_13_queryUnpayedBillByUserId_noPay(){
		DubboContext.setUserId("1");
		listResult=orderService.queryUnpayedBillByUserId();
		String res=JSON.toJSONString(listResult);
		Reporter.log(miaoshu.txt("15654", res));
		System.out.println(res);
		Assert.assertTrue(res.contains("\"code\":0"));
	}
	@Test(description = "查询是否有未支付账单-用户有未支付订单")
	public void test_14_queryUnpayedBillByUserId_haveNoPay() {
		DubboContext.setUserId("15654");
		listResult = orderService.queryUnpayedBillByUserId();
		String res=JSON.toJSONString(listResult);
		System.out.println(res);
		Reporter.log(miaoshu.txt("1", res));
		Assert.assertTrue(res.contains("\"code\":0"));
		}
	@Test(description = "查询是否有未支付账单-无userid")
	public void test_15_queryUnpayedBillByUserId_NoUserid() {
		DubboContext.setUserId("");
		listResult = orderService.queryUnpayedBillByUserId();
		String res=JSON.toJSONString(listResult);
		Reporter.log(miaoshu.txt("0000", res));
		System.out.println(res);
		Assert.assertTrue(res.contains("\"code\":0"));
	}

}
