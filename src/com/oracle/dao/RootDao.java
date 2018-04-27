package com.oracle.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.oracle.util.SortByTime;
import com.oracle.vo.R_operation;
import com.oracle.vo.Root;
import com.oracle.vo.U_operation;
import com.oracle.vo.User;

public class RootDao {
	/**
	 * 管理员开户
	 * 
	 * @param u
	 */
	public static void U_Enroll(Root root, User user) {
		Random rd = new Random(System.currentTimeMillis());
		Integer cardid = null;
		while (true) {
			cardid = (int) (rd.nextInt() % (rd.nextLong() % 1234567));
			if (cardid < 0)
				cardid = -cardid;
			cardid += 100000;
			if (cardid >= 1000000)
				cardid /= 10;
			User u = (User) CommonDao.getlistById(User.class, cardid);
			if (!cardid.toString().equals(u.getUserno())) {
				user.setUserno(cardid.toString());
				break;
			}
		}
		System.out.println(user.toString());
		System.out.println(root.toString());
		CommonDao.save(user);
		CommonDao.save(new R_operation(root.getNumber(), root.getName(), "开户", user.getUserno()));
	}
	/**
	 * 管理员修改密码
	 * 
	 * @param root
	 * @param pwd
	 * @return
	 */
	public static boolean SetPwd(Root root, String pwd) {
		if (root.getPwd().equals(pwd)) {
			return false;
		}
		Root r=new Root();
		r=root;
		r.setPwd(pwd);
		if (CommonDao.update(r)) {
			root.setPwd(pwd);
			CommonDao.save(new R_operation(root.getNumber(), root.getName(),"修改密码", "------"));
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 查询某个用户
	 * 
	 * @param user
	 * @return
	 */
	public static boolean FindUser(User user) {
		User u = (User) CommonDao.getlistById(user.getClass(), user.getUserno());
		System.out.println(u);
		if (user.getUserno().equals(u.getUserno())) {
			user.setName(u.getName());
			user.setSum(u.getSum());
			user.setPwd("*******");
			System.out.println(user);
			return true;
		} else {
			return false;
		}
	}

	public static boolean DeleteUser(Root root, User user) {
		
		ArrayList<Object>list = CommonDao.getListBySome(U_operation.class, "userno", user.getUserno());
		System.out.println(list);
		for(Object obj:list){
			U_operation uop=(U_operation)obj;
			CommonDao.delete(uop, uop.getU_opno());
			System.out.println(uop.getClass().getSimpleName()+"表中删除记录："+uop.getU_opno());
		}
		if (CommonDao.delete(user, user.getUserno())) {
			CommonDao.save(new R_operation(root.getNumber(), root.getName(), "销户", user.getUserno()));
			return true;
		}

		else {
			System.out.println("系统发生错误！");
			return false;
		}
	}

	/**
	 * 查找全部操作流水
	 * @param root
	 * @return
	 */
	public static String Accounts(Root root) {
		StringBuffer sbf=new StringBuffer();
		ArrayList<Object> ls = CommonDao.getListBySome(R_operation.class, "number", root.getNumber());
		Collections.sort(ls,new SortByTime<Object>());
		 int count=0;
		for(Object obj:ls){
			count++;
			R_operation r =(R_operation)obj;
			sbf.append(count+".  "+r.Show()+"\n");
		}	
		sbf.append("                     共搜索到："+count+"条记录\n");
		return sbf.toString();
	}
	
	/**
	 * 查询用户流水信息
	 * @param root
	 * @return
	 */
	public static String User_Information() {
		StringBuffer sbf=new StringBuffer();
		List<Object> ls =CommonDao.getAll(User.class);
		 int count=0;
			for(Object obj:ls){
				count++;
				User r =(User)obj;
				sbf.append(count+".  "+r.Show_1()+"\n");
			}	
			sbf.append("                     共搜索到："+count+"条记录\n");
			return sbf.toString();
		}
	
	/**
	 * 按时间查找操作流水
	 * @param root
	 * @param time
	 * @return
	 */
	public static String Accounts(Root root,int time) {
		//转换时间
		time = -time;
		Date Nowtime = new Date(System.currentTimeMillis());
		Calendar CalenderSet = Calendar.getInstance();
		CalenderSet.setTime(Nowtime);
		CalenderSet.add(Calendar.DAY_OF_YEAR, time);
		 Date dt1=CalenderSet.getTime();
	    Timestamp UpDateTime = new Timestamp(System.currentTimeMillis());
	     UpDateTime.setTime(dt1.getTime());
		
		StringBuffer sbf=new StringBuffer();
		ArrayList<Object> ls = CommonDao.getListBySomeLimTime(R_operation.class, "number", root.getNumber(),UpDateTime);
		Collections.sort(ls,new SortByTime<Object>());
		int count=0;
		for(Object obj:ls){
			count++;
			R_operation r =(R_operation)obj;
			sbf.append(count+".  "+r.Show()+"\n");
		}
		sbf.append("                     共搜索到："+count+"条记录\n");
		return sbf.toString();

	}
	
	


}


