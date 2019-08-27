package Case;

import Common.jsonstring;
import Common.miaoshu;
import Report.ZTestReport;
import com.alibaba.fastjson.JSON;
import com.ifuel.common.returnType.DataResult;
import com.ifuel.common.returnType.ListResult;
import com.ifuel.common.returnType.Result;
import com.ifuel.wallet.manager.client.domain.WalletDTO;
import com.ifuel.wallet.manager.client.service.WalletService;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

//监听报告脚本类
@Listeners({ZTestReport.class})
public class Test_WalletService {
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Case"+ "/spring" +
			"-consumer.xml");
	WalletService  walletService=context.getBean("WalletService",WalletService.class);

	DataResult<WalletDTO> dataResult=new DataResult<>();
	ListResult<WalletDTO> listResult=new ListResult<>();
	DataResult<Long> dataResult2=new DataResult<>();
	Result result=new Result();

	@Test(description = "获取用户钱包信息:cityId已存在")
	public void test1_queryByCityIdAndWalletId_right(){
		dataResult= walletService.queryByCityIdAndWalletId(1l,3310010);
		String res= JSON.toJSONString(dataResult);
		Reporter.log(miaoshu.txt("1l,3310010",res));
		System.out.println(res);
		//将取出来的object类型转为string类型 然后再转成int类型 跟预期值对比
		int cityId= Integer.valueOf(jsonstring.getDictValueByKey(res,"data","cityId").toString());
		int walletId= Integer.valueOf(jsonstring.getDictValueByKey(res,"data","walletId").toString());
		Assert.assertEquals(3310010,cityId,"城市id不正确");
		Assert.assertEquals(1,walletId,"钱包id不正确");
		Assert.assertTrue(res.contains("\"code\":0"));

	}
	@Test(description = "获取用户钱包信息:cityId不存在")
	public void test2_queryByCityIdAndWalletId_NocityId() {
		dataResult = walletService.queryByCityIdAndWalletId(1l, 1000000);
		String res = JSON.toJSONString(dataResult);
		Reporter.log(miaoshu.txt("1l,1000000",res));
		System.out.println(res);
		Assert.assertTrue(res.contains("\"code\":0"));
	}
	@Test(description = "获取用户钱包信息:walletid不存在")
	public void test3_queryByCityIdAndWalletId_NowalletId(){
		dataResult= walletService.queryByCityIdAndWalletId(9999l,3310010);
		String res= JSON.toJSONString(dataResult);
		Reporter.log(miaoshu.txt("9999l,3310010",res));
		System.out.println(res);
		Assert.assertTrue(res.contains("\"code\":20801"));

	}
	@Test(description = "获取用户钱包列表")
	public void test4_queryByWalletId_right(){
		listResult=walletService.queryByWalletId(1L);
		String res= JSON.toJSONString(listResult);
		Reporter.log(miaoshu.txt(1L,res));
		System.out.println(res);
		Assert.assertTrue(res.contains("\"code\":0"));
	}
	@Test(description = "创建钱包:传值type为1")
	public void test5_createWallet_Type1(){
		dataResult2=walletService.createWallet(1);
		String res= JSON.toJSONString(dataResult2);
		Reporter.log(miaoshu.txt(1,res));
		System.out.println(res);
		Assert.assertTrue(res.contains("\"code\":0"));
	}
	@Test(description = "创建钱包:传值type为0")
	public void test6_createWallet_Type0(){
		dataResult2=walletService.createWallet(0);
		String res= JSON.toJSONString(dataResult2);
		Reporter.log(miaoshu.txt(0,res));
		System.out.println(res);
		Assert.assertTrue(res.contains("\"code\":0"));
	}
	@Test(description = "创建钱包:传值type为99")
	public void test7_createWallet_Type99(){
		dataResult2=walletService.createWallet(99);
		String res= JSON.toJSONString(dataResult2);
		Reporter.log(miaoshu.txt(99,res));
		System.out.println(res);
	}
	@Test(description = "更新钱包支付类型")
	public void test8_updateWalletType_right(){
		result=walletService.updateWalletType(1L,88);
		String res= JSON.toJSONString(result);
		Assert.assertTrue(res.contains("\"code\":0"));
		Reporter.log(miaoshu.txt("1l,88",res));
		System.out.println(res);
		Assert.assertTrue(res.contains("\"code\":0"));
	}

}
