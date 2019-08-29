package Common;

import java.sql.SQLException;

public class JdbcTest {
	public static void main(String[] args) {
		try {
			long id = JDBCUtils.executeInsert("INSERT INTO `ifuel-order`.`t_order_child` VALUES (id,'201908291014222351100', '32', '闲时', '200', '60', '160', '136', '320', '272', '1566987545000', '1566997545000', '0');");
			System.out.println(id);

//			 JDBCUtils.executeInsert("insert into
//			 T_IdNames(TypeName,Name,IsDeleted) values(?,?,0)", "test1",
//			 "name1");
//			 System.out.println(id);
//			 更新
			JDBCUtils.executeNonQuery("UPDATE `ifuel-order`.t_order SET status =2 where user_id=\"15654\" order by id " +
					"DESC LIMIT 1");
			String  obj = (String) JDBCUtils.querySingle("select order_id from `ifuel-order`.t_order where " +
					"user_id=\"15654\" order by id DESC LIMIT 1");
			System.out.println(obj);
//			//采取传参数必须是指定long类型 或者使用executeQuery这种方式
//			Long id=(Long) JDBCUtils.querySingle("SELECT id FROM `ifuel-coupon`.t_coupon WHERE `name`=(?)","测试新增优惠卷405");
//			System.out.println(id);
//			ResultSet rs = JDBCUtils.executeQuery("SELECT * FROM `ifuel-cec`.t_partners WHERE id=\"30\"");
//			ResultSet Couponid=JDBCUtils.executeQuery("SELECT * from `ifuel-coupon`.t_member_coupon WHERE remarks=(?)","测试1");
//
//			List<Object> list = new ArrayList<Object>();
//			while(Couponid.next()) {
////				System.out.println(Couponid.getInt("coupon_id"));
////				System.out.println(Couponid.getInt("id"));
//				list.add(Couponid.getInt("coupon_id"));
//			}
//			System.out.println(list);
//
//			List<Long> list2 = Arrays.asList(2L,3L);
//			System.out.println(list2);
//			if(list.size()!= list2.size()){
//				System.out.println("false");
//			}else {
//				for (int i = 0; i < list.size(); i++) {
//					if (
//							Integer.parseInt(String.valueOf(list.get(i))) != Integer.parseInt(String.valueOf(list2.get(i)))
//					) {
//						System.out.println("false");
//						break;
//					}else {
//						System.out.println("相等");
//					}
//				}
//			}
//			JDBCUtils.closeAll(Couponid);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
