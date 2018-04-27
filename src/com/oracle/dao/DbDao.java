package com.oracle.dao;

import com.oracle.vo.R_operation;
import com.oracle.vo.Root;
import com.oracle.vo.U_operation;
import com.oracle.vo.User;

public  class DbDao {
	
	/**
	 * 判断管理员账号密码是否输入正确
	 * @param r
	 * @return
	 */
	public static boolean R_Login(Root r){
		Root root =  (Root) CommonDao.getlistById(Root.class, r.getNumber());
		System.out.println("数据库中："+root.toString());
		System.out.println("当前输入："+r.toString());
		if(!r.getNumber().equals(root.getNumber())){
			return false;
		}else{
			if(root.getPwd().equals(r.getPwd())){
				r.setName(root.getName());
				CommonDao.save(new R_operation(root.getNumber(), root.getName(), "登录", "-----"));
				return true;
			}else{
				return false;
			}
		}
	}
	/**
	 * 判断用户账号密码是否输入正确
	 * @param r
	 * @return
	 */
	public static boolean U_Login(User r){
		User user =  (User) CommonDao.getlistById(User.class, r.getUserno());
		System.out.println("数据库中："+user.toString());
		System.out.println("当前输入："+r.toString());
		if(!r.getUserno().equals(user.getUserno())){
			return false;
		}else{
			if(user.getPwd().equals(r.getPwd())){
				r.setName(user.getName());
				r.setSum(user.getSum());
				CommonDao.save(new U_operation(user.getUserno(), "登录", "------", "------"));
				return true;
			}else{
				return false;
			}
		}
	}
	
}
