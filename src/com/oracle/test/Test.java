package com.oracle.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.oracle.dao.CommonDao;
import com.oracle.dao.DbDao;
import com.oracle.dao.RootDao;
import com.oracle.dao.UserDao;
import com.oracle.vo.R_operation;
import com.oracle.vo.Root;
import com.oracle.vo.User;

public class Test {
	public static void main(String[] args) throws ParseException {
		
		Root root=new Root("root","root","ÍõÄáÂê");
		User user = new User("admin","admin","ÕÅÈ«µ°");
		
		RootDao.DeleteUser(root, user);

		
		     
	}
}
