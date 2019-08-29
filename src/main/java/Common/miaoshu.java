package Common;

import com.ifuel.order.manager.client.domain.TerminateOrder;

public class miaoshu {
	public static String txt(long params, String res){
		return "请求参数:"+params+"\r\n"+"返回内容:"+res;
	}
	public static String txt(String params, String res){
		return "请求参数:"+params+"\r\n"+"返回内容"+res;
	}
	public static String txt(int params,String res){
		return "请求参数:"+params+"\r\n"+"返回内容"+res;
	}


	public static String txt(TerminateOrder terminateOrder,String res) {
		return "请求参数:"+terminateOrder+"\r\n"+"返回内容"+res;
	}
}
