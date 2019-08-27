//package Case;
//
//import Common.JDBCUtils;
//import Common.miaoshu;
//import Report.ZTestReport;
//import com.alibaba.fastjson.JSON;
//import com.ifuel.common.page.Pager;
//import com.ifuel.common.returnType.DataResult;
//import com.ifuel.common.returnType.ListResult;
//import com.ifuel.common.returnType.PageResult;
//import com.ifuel.common.returnType.Result;
//import com.ifuel.coupon.manager.client.domian.MemberCouponDTO;
//import com.ifuel.coupon.manager.client.service.MemberCouponService;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.testng.Assert;
//import org.testng.Reporter;
//import org.testng.annotations.Listeners;
//import org.testng.annotations.Test;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
////监听报告脚本类
//@Listeners({ZTestReport.class})
//public class Test_MemberCouponService {
//	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Case"+"/spring-consumer.xml");
//	MemberCouponService memberCouponService=context.getBean("MemberCouponService",MemberCouponService.class);
//
//	PageResult<MemberCouponDTO> pageResult=new PageResult<>();
//	DataResult<Long> dataResult=new DataResult<>();
//	ListResult<MemberCouponDTO> listResult= new ListResult<>();
//	Result result=new Result();
//	String remarks="自动化"+ (int) (Math.random() * 1000 + 1); //随机生成1-1000的数
//	String remarks2="批量自动化"+ (int) (Math.random() * 1000 + 1); //随机生成1-1000的数
//
//
//	@Test(description = "查询用户优惠劵列表:库中用户已存在")
//	public void test1_queryAll_right(){
//		Pager content=new Pager(0,1);
//		pageResult=memberCouponService.queryAll(1L,content);
//		String res= JSON.toJSONString(pageResult);
//		Reporter.log(miaoshu.txt("1L,0,1",res));
//		System.out.println(res);
//		Assert.assertTrue(res.contains("\"code\":0"));
//	}
//	@Test(description = "查询用户可使用的优惠劵列表:库中用户已存在-无站点限制")
//	public void test2_queryMemberCoupon_stationIdnull(){
//		listResult=memberCouponService.queryMemberCoupon(1L, null,1000);
//		String res= JSON.toJSONString(listResult);
//		Reporter.log(miaoshu.txt("1L,null,1000",res));
//		System.out.println(res);
//		Assert.assertTrue(res.contains("\"code\":0"));
//	}
//	@Test(description = "查询用户可使用的优惠劵列表:库中用户已存在-有站点限制")
//	public void test3_queryMemberCoupon_stationId(){
//		listResult=memberCouponService.queryMemberCoupon(1L, 10,1000);
//		String res= JSON.toJSONString(listResult);
//		Reporter.log(miaoshu.txt("1l,10,1000",res));
//		System.out.println(res);
//		Assert.assertTrue(res.contains("\"code\":0"));
//	}
//	@Test(description = "用户优惠劵发放:库中用户已存在-且库中有couponid")
//	public void test4_grantMemberCoupon_right() throws SQLException {
//		dataResult=memberCouponService.grantMemberCoupon(1L,9L,remarks,0L,"11111");
//		String res= JSON.toJSONString(dataResult);
//		Reporter.log(miaoshu.txt(String.format("(1L,9L,%s,0L,11111)",remarks),res));
//		System.out.println(res);
//		int coupon_id=(int) JDBCUtils.querySingle("SELECT coupon_id from `ifuel-coupon`.t_member_coupon WHERE remark=(?)",remarks );
//		Assert.assertEquals(9L,coupon_id,"未查询到发放的优惠卷,发放失败");
//		Assert.assertTrue(res.contains("\"code\":0"));
//	}
//	@Test(description = "用户优惠劵批量发放")
//	public void test5_grantMemberCouponList_right() throws SQLException{
//		//传list数组 格式为long类型
//		List<Long> list = Arrays.asList(9L, 24L);
//		result=memberCouponService.grantMemberCouponList(1L,list,remarks2,0L,"11111");
//		String res= JSON.toJSONString(result);
//		Reporter.log(miaoshu.txt(String.format("(1L,list,%s,0L,11111)",remarks2),res));
//		System.out.println(res);
//
//		ResultSet Couponid=JDBCUtils.executeQuery("SELECT * from `ifuel-coupon`.t_member_coupon WHERE remarks=(?)","测试1");
//		List<Object> list2 = new ArrayList<Object>();
//
//		Assert.assertTrue(res.contains("\"code\":0"));
//		Assert.assertEquals(list.size(), list2.size(),"批量发放的数量不一致");
//		while(Couponid.next()) {
//			list2.add(Couponid.getInt("coupon_id"));
//		}
//		for(int i=0;i<=list.size();i++){
//			Assert.assertEquals(Integer.parseInt(String.valueOf(list.get(i))),Integer.parseInt(String.valueOf(list2.get(i))),"批量发放的卷id不一致");
//				}
//	}
//	@Test(description = "消费用户优惠劵")
//	// 此处需查询数据库消费发放的最新一张 且消费成功 字段status改为1
//	public void test6_redeemMemberCoupon_right() throws SQLException{
//
//		result=memberCouponService.redeemMemberCoupon(8l,"0000");
//		String res= JSON.toJSONString(result);
//		Reporter.log(miaoshu.txt("8l,0000",res));
//		System.out.println(res);
//		int status=(int) JDBCUtils.querySingle("SELECT `status` from `ifuel-coupon`.t_member_coupon WHERE id=8");
//		Assert.assertTrue(res.contains("\"code\":0"));
//		Assert.assertEquals(1,status,"status不正确，消费失败");
//	}
//	@Test(description = "删除优惠卷:库中该优惠卷未删除")
//	//此处删除最新的一张 删除成功 字段deleted改为1
//	public void test7_deleteMemberCoupon_right() throws SQLException{
//		result=memberCouponService.deleteMemberCoupon(3L);
//		String res= JSON.toJSONString(result);
//		Reporter.log(miaoshu.txt("3L",res));
//		System.out.println(res);
//		int delect=(int) JDBCUtils.querySingle("SELECT `deleted` from `ifuel-coupon`.t_member_coupon WHERE id=8");
//		Assert.assertEquals(1,delect,"delect值不对，删除失败");
//		Assert.assertTrue(res.contains("\"code\":0"));
//	}
//}
