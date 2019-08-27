//package Case;
//
//import Common.JDBCUtils;
//import Common.jsonstring;
//import Common.miaoshu;
//import Report.ZTestReport;
//import com.alibaba.fastjson.JSON;
//import com.ifuel.common.page.Pager;
//import com.ifuel.common.returnType.DataResult;
//import com.ifuel.common.returnType.PageResult;
//import com.ifuel.common.returnType.Result;
//import com.ifuel.coupon.manager.client.domian.CouponInfoDTO;
//import com.ifuel.coupon.manager.client.service.CouponService;
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
//public class Test_CouponService {
//	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Case"+"/spring-consumer.xml");
//	CouponService  couponService=context.getBean("CouponService",CouponService.class);
//
//	PageResult<CouponInfoDTO> pageResult=new PageResult<>();
//	DataResult<CouponInfoDTO> dataResult=new DataResult<>();
//	DataResult<Long> dataResultS=new DataResult<>();
//	Result result=new Result();
//	String CouponName="测试新增优惠卷"+ (int) (Math.random() * 1000 + 1); //随机生成1-1000的数
//
//	@Test(description = "查询单个优惠卷:库中已存在")
//	public void test1_queryCouponById_right(){
//		dataResult=couponService.queryCouponById(2L);
//		//将输出的对象转换为jsonstring才能打印
//		String res= JSON.toJSONString(dataResult);
//		Reporter.log(miaoshu.txt(2L,res));
//		System.out.println(res);
//		//将取出来的object类型转为string类型 然后再转成int类型 跟预期值对比
//		int couponid= Integer.valueOf(jsonstring.getDictValueByKey(res,"data","couponId").toString());
//		Assert.assertTrue(res.contains("\"code\":0"));
//		Assert.assertEquals(2,couponid,"返回data数据错误");
//	}
//
//
//	@Test(description = "查询单个优惠卷:库中不存在")
//	public void test2_queryCouponById_NOCouponid(){
//		dataResult=couponService.queryCouponById(99999L);
//		//将输出的对象转换为jsonstring才能打印
//		String res= JSON.toJSONString(dataResult);
//		Reporter.log(miaoshu.txt(99999L,res));
//		System.out.println(res);
//		Assert.assertTrue(res.contains("\"code\":20901"));
//	}
//	@Test(description = "查询所有优惠卷")
//	public void test3_queryAll_right(){
//		Pager content=new Pager(0,1);
//
////		Pager c = new Pager();
////		c.setLength(1);
////		c.setPage(0);
//		pageResult=couponService.queryAll(content);
//		String res= JSON.toJSONString(pageResult);
//		Reporter.log(miaoshu.txt("0,1",res));
//		System.out.println();
//		Assert.assertTrue(res.contains("\"code\":0"));
//	}
//	@Test(description = "新增优惠卷")
//	public void test4_createCoupon_right(){
//		dataResultS=couponService.createCoupon(CouponName,100,7,2,"",10,0,0,1564474771L,1564474771L,0L);
//		String res= JSON.toJSONString(dataResultS);
//		Reporter.log(miaoshu.txt(String.format("(%s,100,7,2,\"\",0,0,0,1564474771L,1564474771L,0L)", CouponName),res));
//		System.out.println(res);
//		Assert.assertTrue(res.contains("\"code\":0"));
//	}
//	@Test(description = "禁用优惠劵:库中已存在")
//	public void test5_disableCoupon_right() throws SQLException {
//		Long id= (Long) JDBCUtils.querySingle("SELECT id FROM `ifuel-coupon`.t_coupon WHERE `name`=(?)",CouponName);
//		result=couponService.disableCoupon(id);
//		String res= JSON.toJSONString(result);
//		Reporter.log(miaoshu.txt(id,res));
//		System.out.println(res);
//		int is_used=(int) JDBCUtils.querySingle("SELECT is_used FROM `ifuel-coupon`.t_coupon WHERE `name`=(?)",CouponName);
//		Assert.assertEquals(1,is_used,"预期禁用状态为1");
//		Assert.assertTrue(res.contains("\"code\":0"));
//	}
//	@Test(description = "启动优惠劵:库中已存在")
//	public void test6_enableCoupon_right()throws SQLException{
//		Long id= (Long) JDBCUtils.querySingle("SELECT id FROM `ifuel-coupon`.t_coupon WHERE `name`=(?)",CouponName);
//		result=couponService.enableCoupon(id);
//		String res= JSON.toJSONString(result);
//		Reporter.log(miaoshu.txt(id,res));
//		System.out.println(res);
//		int is_used=(int) JDBCUtils.querySingle("SELECT is_used FROM `ifuel-coupon`.t_coupon WHERE `name`=(?)",CouponName);
//		Assert.assertEquals(0,is_used,"预期启用状态为0");
//		Assert.assertTrue(res.contains("\"code\":0"));
//	}
//	@Test(description = "删除优惠卷:库中已存在")
//	public void test7_deleteCoupon_right() throws SQLException{
//		Long id= (Long) JDBCUtils.querySingle("SELECT id FROM `ifuel-coupon`.t_coupon WHERE `name`=(?)",CouponName);
//		result=couponService.deleteCoupon(id);
//		String res= JSON.toJSONString(result);
//		Reporter.log(miaoshu.txt(id,res));
//		System.out.println(res);
//		int deleted=(int) JDBCUtils.querySingle("SELECT is_used FROM `ifuel-coupon`.t_coupon WHERE `name`=(?)",CouponName);
//		Assert.assertEquals(1,deleted,"预期删除状态为1");
//		Assert.assertTrue(res.contains("\"code\":0"));
//	}
//}
