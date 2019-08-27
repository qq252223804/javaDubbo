//package Case;
//
//import Common.OrderNo;
//import Common.miaoshu;
//import Report.ZTestReport;
//import com.alibaba.fastjson.JSON;
//import com.ifuel.common.returnType.DataResult;
//import com.ifuel.common.returnType.Result;
//import com.ifuel.wallet.manager.client.domain.WalletRecordDTO;
//import com.ifuel.wallet.manager.client.service.WalletRecordService;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.testng.Assert;
//import org.testng.Reporter;
//import org.testng.SkipException;
//import org.testng.annotations.Listeners;
//import org.testng.annotations.Test;
//
////监听报告脚本类
//@Listeners({ZTestReport.class})
//public class Test_WalletRecordService {
//	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Case"+ "/spring" +
//			"-consumer.xml");
//	WalletRecordService walletRecordService=context.getBean("WalletRecordService",WalletRecordService.class);
//
//	DataResult<WalletRecordDTO> dataResult=new DataResult<>();
//	DataResult<Long> dataResult2=new DataResult<>();
//	Result result=new Result();
//	String TradeN0= OrderNo.doOrderNum();
//	@Test(description = "创建充值钱包记录:充值金额为正数")
//	public void test1_createWalletRechargeRecord_zhengshu(){
//		dataResult2=walletRecordService.createWalletRechargeRecord(7l,3310000,TradeN0,1000,10000);
//		String res= JSON.toJSONString(dataResult2);
//		Reporter.log(miaoshu.txt(String.format("(7l,3310000,%s,1000,0)",TradeN0 ),res));
//		System.out.println(res);
//		Assert.assertTrue(res.contains("\"code\":0"));
//	}
//	@Test(description = "创建充值钱包记录:充值金额为负数")
//	public void test2_createWalletRechargeRecord_fushu(){
//		dataResult2=walletRecordService.createWalletRechargeRecord(7l,3310000,TradeN0,-1000,0);
//		String res= JSON.toJSONString(dataResult2);
//		Reporter.log(miaoshu.txt(String.format("(7l,3310000,%s,-1000,0)",TradeN0 ),res));
//		System.out.println(res);
//		Assert.assertTrue(res.contains("\"code\":0"));
//	}
//	@Test(description = "创建充值钱包记录:充值金额为0")
//	public void test3_createWalletRechargeRecord_0(){
//		dataResult2=walletRecordService.createWalletRechargeRecord(7l,3310000,TradeN0,0,0);
//		String res= JSON.toJSONString(dataResult2);
//		Reporter.log(miaoshu.txt(String.format("(7l,3310000,%s,0,0)",TradeN0 ),res));
//		System.out.println(res);
//		Assert.assertTrue(res.contains("\"code\":0"));
//	}
//	@Test(description = "创建消费钱包记录:消费金额为正数")
//	public void test4_createWalletConsumeRecord_zhengshu(){
//		dataResult2=walletRecordService.createWalletConsumeRecord(7l,3310000,TradeN0,100,0);
//		String res= JSON.toJSONString(dataResult2);
//		Reporter.log(miaoshu.txt(String.format("(7l,3310000,%s,100,0)",TradeN0 ),res));
//		System.out.println(res);
//		Assert.assertTrue(res.contains("\"code\":0"));
//
//	}
//	@Test(description = "创建消费钱包记录:消费金额为负数")
//	public void test5_createWalletConsumeRecord_fushu(){
//		dataResult2=walletRecordService.createWalletConsumeRecord(7l,3310000,TradeN0,-100,0);
//		String res= JSON.toJSONString(dataResult2);
//		Reporter.log(miaoshu.txt(String.format("(7l,3310000,%s,-100,0)",TradeN0 ),res));
//		System.out.println(res);
//		Assert.assertTrue(res.contains("\"code\":0"));
//
//	}
//	@Test(description = "创建消费钱包记录:消费金额为0")
//	public void test6_createWalletConsumeRecord_0(){
//		dataResult2=walletRecordService.createWalletConsumeRecord(7l,3310000,TradeN0,0,0);
//		String res= JSON.toJSONString(dataResult2);
//		Reporter.log(miaoshu.txt(String.format("(7l,3310000,%s,0,0)",TradeN0 ),res));
//		System.out.println(res);
//		Assert.assertTrue(res.contains("\"code\":0"));
//
//	}
//
//
//	@Test(description ="根据订单和类型查询钱包记录:订单号已存在")
//	public void test7_queryByTradeNo_right(){
//		dataResult= walletRecordService.queryByTradeNo(TradeN0);
//		String res= JSON.toJSONString(dataResult);
//		Reporter.log(miaoshu.txt(TradeN0,res));
//		System.out.println(res);
//		Assert.assertTrue(res.contains("\"code\":0"));
//
//	}
//	@Test(description ="根据订单和类型查询钱包记录:订单号不存在")
//	public void test8_queryByTradeNo_NoTradeNo(){
//		dataResult= walletRecordService.queryByTradeNo("999999");
//		String res= JSON.toJSONString(dataResult);
//		Reporter.log(miaoshu.txt("999999",res));
//		System.out.println(res);
//		Assert.assertTrue(res.contains("\"code\":1001"));
//
//	}
//
//	@Test(description = "删除钱包记录:数据库已存在该记录")
//	public void test9_deleteWalletRecord_right(){
//		throw new SkipException("t_wallet_record 表中没有记录");
////		result=walletRecordService.deleteWalletRecord(1l);
////		String res= JSON.toJSONString(result);
////		Reporter.log(res);
////		System.out.println(res);
////		Assert.assertTrue(res.contains("\"code\":0"));
//
//	}
//	@Test(priority =10,description = "删除钱包记录:数据库不存在该记录")
//	public void test10_deleteWalletRecord_NoRecord(){
//		result=walletRecordService.deleteWalletRecord(999999l);
//		String res= JSON.toJSONString(result);
//		Reporter.log(res);
//		System.out.println(res);
//		Assert.assertTrue(res.contains("\"code\":1001"));
//
//	}
//
//}
