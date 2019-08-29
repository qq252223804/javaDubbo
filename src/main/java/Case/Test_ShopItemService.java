package Case;

import Common.miaoshu;
import Report.ZTestReport;
import com.alibaba.fastjson.JSON;
import com.ifuel.common.returnType.DataResult;
import com.ifuel.shop.manager.client.domain.ItemDTO;
import com.ifuel.shop.manager.client.service.ShopItemService;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({ZTestReport.class})
public class Test_ShopItemService {
	ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("spring-consumer.xml");
	ShopItemService shopItemService=context.getBean("ShopItemService",ShopItemService.class);

	DataResult<ItemDTO> dataResult=new DataResult<>();
	DataResult<Integer> dataResult2=new DataResult<>();

	@Test(description = "新建拼团信息-会员活动(商品id已存在)")
	public void test1_createItem_right(){
		dataResult2=shopItemService.createItem(5,15654L,1);
		String res= JSON.toJSONString(dataResult2);
		Reporter.log(miaoshu.txt("5,15654L,1",res));
		System.out.println(res);
		Assert.assertTrue(res.contains("\"code\":0"));

	}
	@Test(description = "新建拼团信息-会员活动(商品id不存在)")
	public void test2_createItem_NogondsId(){
		dataResult2=shopItemService.createItem(999999,15654L,1);
		String res= JSON.toJSONString(dataResult2);
		Reporter.log(miaoshu.txt("999999,15654L,1",res));
		System.out.println(res);
		Assert.assertTrue(res.contains("\"code\":20201"));

	}
	@Test(description = "查询拼团信息-拼团id已存在")
	public void test3_queryItemById_right(){
		dataResult=shopItemService.queryItemById(8);
		String res= JSON.toJSONString(dataResult);
		Reporter.log(miaoshu.txt(8,res));
		System.out.println(res);
		Assert.assertTrue(res.contains("\"code\":0"));

	}
	@Test(description = "查询拼团信息-拼团id不存在")
	public void test4_queryItemById_NoitemId(){
		dataResult=shopItemService.queryItemById(0);
		String res= JSON.toJSONString(dataResult);
		Reporter.log(miaoshu.txt(0,res));
		System.out.println(res);
		Assert.assertTrue(res.contains("\"code\":20201"));

	}
}
