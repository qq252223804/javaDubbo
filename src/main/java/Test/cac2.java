package Test;

import Common.jsonstring;
import org.testng.Assert;

public class cac2 {
	public static void main(String[] args) {
		// json格式的字符串
		String str = "{'success':true,'message':null,'obj':{'id':1,'name':'锅炉1','id2':2,'name2':'锅炉2'}}";

//		json字符串 里面是list数组
		String str2 = "{'success':true,'message':null,'obj':[{'age':18,'name':'张三'},{'age':20,'name':'李四'}]}";

		System.out.println(jsonstring.getValueByKey(str,"success"));
		System.out.println(jsonstring.getDictValueByKey(str,"obj","name"));
		System.out.println(jsonstring.getListValueByKey(str2,"obj",0,"name"));

		String str3="{\"requestId\":null,\"code\":0,\"message\":null}";
		System.out.println(jsonstring.getValueByKey(str3,"code"));
		int code=Integer.valueOf(jsonstring.getValueByKey(str3,"code").toString());
		Assert.assertEquals(0,code,"响应的code不正确");
	}
}
