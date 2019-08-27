package Common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class OrderNo {
	public static String doOrderNum()  {
		Random random = new Random();
		SimpleDateFormat allTime = new SimpleDateFormat("YYYYMMddHHmmSS");
		String subjectno = allTime.format(new Date())+random.nextInt(10);
		System.out.println(subjectno+random.nextInt(10));
    	return subjectno+random.nextInt(10);

	}

	public static void main(String[] args) {
		doOrderNum();
	}


}
