package Common;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class jsonstring {
	public static Object getValueByKey(String jsonStr, String key) {
		// json字符串转 对象
		JSONObject jsonObject = JSONObject.parseObject(jsonStr);
		// 获取到 key 对应的值 但为object类型
		return jsonObject.get(key);
	}
	public static Object getDictValueByKey(String jsonStr, String key1,String key2) {
		// json字符串转 对象
		JSONObject jsonObject = JSONObject.parseObject(jsonStr);
		// 获取到 key 对应的值
		return jsonObject.getJSONObject(key1).getString(key2);
	}
	public static Object getListValueByKey(String jsonStr,String key1, int index,String key2) {
		// json字符串转 对象
		JSONObject jsonObject = JSONObject.parseObject(jsonStr);
		// 获取到 key 对应的值
		JSONArray list=jsonObject.getJSONArray(key1);
		return list.getJSONObject(index).getString(key2);
	}

}
