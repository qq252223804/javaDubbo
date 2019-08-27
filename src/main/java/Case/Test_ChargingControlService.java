//package Case;
//
//import Common.HttpClientUtils;
//import Common.jsonstring;
//import Common.miaoshu;
//import Report.ZTestReport;
//import com.alibaba.fastjson.JSON;
//import com.ifuel.common.returnType.Result;
//import com.ifuel.stationGateway.client.domain.StartChargingResult;
//import com.ifuel.stationGateway.client.service.ChargingControlService;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.testng.Reporter;
//import org.testng.annotations.Listeners;
//import org.testng.annotations.Test;
//
//import java.util.HashMap;
//import java.util.Map;
////监听报告脚本类
//@Listeners({ZTestReport.class})
//public class Test_ChargingControlService {
//	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Case" + "/spring-consumer.xml");
//	ChargingControlService chargingControlService = context.getBean("ChargingControlService", ChargingControlService.class);
//	Result result = new Result();
//
//	StartChargingResult startChargingResult = new StartChargingResult();
//
//	@Test(description = "启动充电-响应信息")
//	public void test1_startCharging_sendStartChargingResult_code0() {
//		//这是启动充电接口 startCharging
//		result = chargingControlService.startCharging("1000", "47", "6800010", "1");
//		String res = JSON.toJSONString(result);
//		System.out.println(res);
//		//sendStartChargingResult这是模拟返回的imp接口 得自己封装http接口然后调用
//		String url="http://127.0.0.1:8080/example/sendStartChargingResult";
//		Map<String,Object> body = new HashMap<>();
//		body.put("code","0");
//		body.put("requestId","1000");
//		System.out.println(body);
//		Map<String, Object> headers=new HashMap<>();
//		headers.put("Content-Type","application/x-www-form-urlencoded");
//		Object result= HttpClientUtils.httpPostRequest(url,headers,body);
//		String res1 = JSON.toJSONString(result);
//		System.out.println(res1);
//		Reporter.log(miaoshu.txt("\"1000\", \"47\", \"6800010\", \"1\"",res));
//		Reporter.log(miaoshu.txt(String.format("%s",body),res1));
//		int code=Integer.valueOf(jsonstring.getValueByKey(res1,"code").toString());
//		System.out.println(code);
//		int requestId=Integer.valueOf(jsonstring.getValueByKey(res1,"requestId").toString());
//		Assert.assertTrue(res.contains("\"code\":0"));
//		Assert.assertEquals(0,code,"响应的code不正确");
//		Assert.assertEquals(1000,requestId,"响应的requestId不正确");
//
//	}
//
//
//
//}
