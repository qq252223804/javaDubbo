//package Case;
//
//import Common.JDBCUtils;
//import Common.miaoshu;
//import Report.ZTestReport;
//import com.alibaba.fastjson.JSON;
//
//import com.ifuel.common.returnType.DataResult;
//import com.ifuel.common.returnType.Result;
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
//public class Test_OpenStationService {
//	ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("Case"+"/spring-consumer.xml");
//	OpenStationService openStationService=context.getBean("OpenStationService",OpenStationService.class);
//	DataResult<OpenStationDTO> dataResult=new DataResult<>();
//	Result result=new Result();
//
//	String name="现场调试点"+ (int) (Math.random() * 1000 + 1);
//	String stationId=String.valueOf((int) (Math.random() * 999999 + 100000));
//	String address="野风时代918";
//	String stationLng="120.089162";
//	String stationlat="30.288471";
//
//	@Test(description = "创建外部站点记录:库中不存在新站点——stationId")
//	public void test1_createOpenStation_right() throws SQLException {
//		String ownerId=(String) JDBCUtils.querySingle("SELECT operator_id from `ifuel-cec`.t_partners WHERE " +
//				"name=\"siwei\";");
//		Integer entityId=(Integer) JDBCUtils.querySingle("SELECT id from charging.cp_station WHERE name=(?)",address);
//		result=openStationService.createOpenStation(name,ownerId,stationId,address,stationLng,stationlat,50,entityId);
//		String res= JSON.toJSONString(result);
//		Reporter.log(miaoshu.txt(String.format("%s,%s,%s,%s,%s,%s,50,%d",name,ownerId,stationId,address,stationLng,stationlat,entityId),res));
//		System.out.println(res);
//		Assert.assertTrue(res.contains("\"code\":0"));
//	}
//	@Test(description = "创建外部站点记录:库中已存在新站点——stationId")
//	public  void test2_createOpenStation_stationIdAlready() throws SQLException {
//		String ownerId=(String) JDBCUtils.querySingle("SELECT operator_id from `ifuel-cec`.t_partners WHERE " +
//				"name=\"guowang\";");
//		Integer entityId=(Integer) JDBCUtils.querySingle("SELECT id from charging.cp_station WHERE name=(?)",address);
//		result=openStationService.createOpenStation(name,ownerId,"111111",address,stationLng,stationlat,50,entityId);
//		String res= JSON.toJSONString(result);
//		Reporter.log(miaoshu.txt(String.format("%s,%s,111111,%s,%s,%s,50,%d",name,ownerId,address,stationLng,stationlat,entityId),res));
//		System.out.println(res);
//		Assert.assertTrue(res.contains("\"code\":1001"));
//	}
//	@Test(description = "获取站点信息")
//	public void test3_queryByOwnerIdAndStationId_right() throws SQLException{
//		String ownerId=(String) JDBCUtils.querySingle("SELECT operator_id from `ifuel-cec`.t_partners WHERE " +
//				"name=\"guowang\";");
//		dataResult=openStationService.queryByOwnerIdAndStationId(ownerId,"310001001006");
//		String res=JSON.toJSONString(dataResult);
//		Reporter.log(miaoshu.txt(String.format("%s,310001001006",ownerId),res));
//		Assert.assertTrue(res.contains("\"code\":0"));
//	}
//}
