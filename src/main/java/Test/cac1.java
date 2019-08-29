package Test;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class cac1 {
	public static void main(String[] args) {

		// json格式的字符串
		String str = "{'success':true,'message':null,'obj':{'id':1,'name':'锅炉1','id2':2,'name2':'锅炉2'}}";
//		jsonstring转JSONObject对象
		JSONObject json= JSONObject.parseObject(str );
//		JSONObject对象转jsonstring
		System.out.println(JSON.toJSONString(json));

		//获取json对象的某一字段值/ 某一字典中的一个key的值
		System.out.println(json.get("obj"));
		System.out.println(json.getJSONObject("obj").getString("name"));
		System.out.println("success: " +json.get("success"));


//		json字符串 里面是list数组
		String str2 = "{'success':true,'message':null,'obj':[{'age':18,'name':'张三'},{'age':20,'name':'李四'}]}";
//		取list数组中第一个age 和name的值
		JSONObject json2= JSONObject.parseObject(str2 );
		System.out.println(json2.get("obj"));

		//获取json对象的所有values
		System.out.println(json2.values());
//		获取json对象的某一数组的 key值  必须先转换成list数组 获取数组值
		JSONArray list=json2.getJSONArray("obj");
		System.out.println(list.getJSONObject(0).getString("age"));


//		File f1 =new File("C:\\Users\\p\\Desktop\\java66ifuel\\src\\main\\java\\Report");
//		System.out.println(f1.getAbsolutePath());
//
//		System.out.println(System.getProperty("user.dir")+File.separator+"Report\\"+"report.html");
//		System.out.println(System.currentTimeMillis());
//		System.out.println(System.getProperty("user.dir")+File.separator+"template1");

	}


}
