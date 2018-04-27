package com.oracle.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import com.oracle.util.SortByTime;
import com.oracle.util.SortByTime2;
import com.oracle.vo.U_operation;
import com.oracle.vo.User;

/**
 * 用户的基础操作方法
 * 
 * @author 刘佳伟
 *
 */
public class UserDao {

	/**
	 * 用户存钱
	 * @param user
	 * @param money
	 * @return
	 */
	public static boolean SaveMoney(User user, double money) {
		User u = user;
		u.setSum(u.getSum()+money);
		if (CommonDao.update(u)) {
			user.setSum(u.getSum());
			CommonDao.save(new U_operation(user.getUserno(), "存款", "+"+money+"￥", "------"));
			return true;
		} else {
			return false;
		}
	}
	
	
	/**
	 * 用户取钱
	 * @param user
	 * @param money
	 * @return
	 */
	public static boolean getMoney(User user, double money) {
		User u = user;
		if((u.getSum()-money)<0)
			return false;
		u.setSum(u.getSum()-money);
		if (CommonDao.update(u)) {
			user.setSum(u.getSum());
			CommonDao.save(new U_operation(user.getUserno(), "取款", "-"+money+"￥", "------"));
			return true;
		} else {
			return false;
		}
	}
	
	
	/**
	 * 用户转账
	 * @param user 付款人
	 * @param user2 收款人
	 * @param money 金额
	 * @return 1:余额不足 2：收款人信息有误 3：数据库操作错误 0：转账成功
	 */
	public static int SendMoney(User user,User user2, double money) {
		User u = user;
		if((u.getSum()-money)<0)
			return 1;//余额不足
		User u2=(User) CommonDao.getlistById(User.class, user2.getUserno());
		if(!user2.getUserno().equals(u2.getUserno())||!user2.getName().equals(u2.getName())){
			return 2;//转账信息填写有误
		}
		System.out.println(u);
		System.out.println(u2);
		u.setSum(u.getSum()-money);
		u2.setSum(u2.getSum()+money);
		System.out.println(u);
		System.out.println(u2);
		if (CommonDao.update(u,u2)) {
			user.setSum(u.getSum());
			CommonDao.save(new U_operation(user.getUserno(), "转账", "-"+money+"￥",u2.getUserno()));
			CommonDao.save(new U_operation(u2.getUserno(), "转账", "+"+money+"￥",user.getUserno()));
			return 0;//转账成功
		} else {
			return 3;//数据库操作错误
		}
	}
	

	/**
	 * 用户修改密码
	 * 
	 * @param user
	 * @param pwd
	 * @return
	 */
	public static boolean SetPwd(User user, String pwd) {
		if (user.getPwd().equals(pwd)) {
			System.out.println("前后密码一样");
			return false;
		}
		User u =new User();
		u=user;
		u.setPwd(pwd);
		if (CommonDao.update(u)) {
			user.setPwd(pwd);
			CommonDao.save(new U_operation(user.getUserno(), "修改密码", "------", "------"));
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 查找全部操作流水
	 * 
	 * @param root
	 * @return
	 */
	public static String Accounts(User user) {
		StringBuffer sbf = new StringBuffer();
		ArrayList<Object> ls = CommonDao.getListBySome(U_operation.class, "userno", user.getUserno());
		Collections.sort(ls, new SortByTime2<Object>());
		int count=0;
		for (Object obj : ls) {
			U_operation r = (U_operation) obj;
			count++;
			sbf.append(count+".  "+r.Show1() + "\n");
		}
		sbf.append("                     共搜索到："+count+"条记录\n");
		return sbf.toString();
	}

	/**
	 * 按时间查找操作流水
	 * 
	 * @param root
	 * @param time
	 * @return
	 */
	public static String Accounts(User user, int time) {
		// 转换时间
		time = -time;
		Date Nowtime = new Date(System.currentTimeMillis());
		Calendar CalenderSet = Calendar.getInstance();
		CalenderSet.setTime(Nowtime);
		CalenderSet.add(Calendar.DAY_OF_YEAR, time);
		Date dt1 = CalenderSet.getTime();
		Timestamp UpDateTime = new Timestamp(System.currentTimeMillis());
		UpDateTime.setTime(dt1.getTime());

		StringBuffer sbf = new StringBuffer();
		ArrayList<Object> ls = CommonDao.getListBySomeLimTime(U_operation.class, "userno", user.getUserno(),
				UpDateTime);
		Collections.sort(ls, new SortByTime2<Object>());
		int count=0;
		for (Object obj : ls) {
			count++;
			U_operation r = (U_operation) obj;
			sbf.append(count+".  "+r.Show1() + "\n");
		}
		sbf.append("                     共搜索到："+count+"条记录\n");
		return sbf.toString();

	}

}
