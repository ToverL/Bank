package com.oracle.dao;

import java.lang.reflect.Field;
import java.security.KeyFactory;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.Statement;
import com.oracle.vo.*;
/**
 * 通用jdbc设计类
 * @author 刘佳伟
 *
 */
public class CommonDao {
	/**
	 * 建立连接
	 * 
	 * @return
	 */
	public static Connection getCconnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 关闭资源
	 * 
	 * @param auto
	 */
	private static void close(AutoCloseable auto) {
		if (auto != null) {
			try {
				auto.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 表中插入一个vo对象
	 * 
	 * @param obj
	 */
	public static void save(Object obj) {
		boolean isGeneratedKey=false;//是否返回主键
		Class<?> c = obj.getClass();
		String table_name=c.getSimpleName();
		if(c.isAnnotationPresent(Table.class)){
			table_name=c.getAnnotation(Table.class).value();
		}
		
		StringBuffer sql = new StringBuffer("insert into " + table_name+ "(");
		StringBuffer sql_v = new StringBuffer("values(");
		Field fd[] = c.getDeclaredFields();
		
		Field keyField = null;//保存主键
		for (int i = 0; i < fd.length; i++) {
			Field f=fd[i];
			sql.append(f.getName());
			//是否是主键
			if(f.isAnnotationPresent(Identity.class)){
				//获得是否返回主键
				isGeneratedKey=f.getAnnotation(Identity.class).value();
				keyField=f;
				keyField.setAccessible(true);
				if(isGeneratedKey)
				sql_v.append("null");
				else
					sql_v.append("?");
			}else{
				sql_v.append("?");
			}
			if(i==fd.length-1){
				sql.append(")");
				sql_v.append(")");
			}else{
				sql.append(",");
				sql_v.append(",");			
			}
		}
		sql.append(sql_v);
		System.out.println(sql);
		//执行sql; 赋值;主键不赋值;返回生成的主键；
		Connection conn = CommonDao.getCconnection();
		PreparedStatement ps = null;
		ResultSet rs=null;
		try {
			if(isGeneratedKey){
				ps = conn.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS);	
			}else{
				ps = conn.prepareStatement(sql.toString());
			}
			int i=1;
			for (Field f:fd) {
				f.setAccessible(true);
				if(isGeneratedKey){
				if(!f.isAnnotationPresent(Identity.class)){
					ps.setObject(i++, f.get(obj));
				}
				}else{
					ps.setObject(i++, f.get(obj));
					}
				}
			ps.execute();
			if(isGeneratedKey){
				rs=ps.getGeneratedKeys();
				rs.next();//移动游标
			Object key=rs.getInt(1);//主键
				keyField.set(obj, key);
			}
		
			System.out.println("完成插入操作" + obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonDao.close(rs);
			CommonDao.close(ps);
			CommonDao.close(conn);
		}
	}


	 /**
     * 根据某些特定值来查询符号要求的对象集合
     * @param cl 查询的对象的Class类型
     * @param str 所要查询的列名
     * @param ob 列名所对应的值
     * @return 查询到符合要求的对象集合
     */
    public static ArrayList<Object> getListBySome(Class<?> cl,String str,Object ob){
        //对象集合
        ArrayList<Object> al=new ArrayList<Object>();
        //获取连接
        Connection conn=getCconnection();
    	String table_name=cl.getSimpleName();
		if(cl.isAnnotationPresent(Table.class)){
			table_name=cl.getAnnotation(Table.class).value();
		}
        
        PreparedStatement ps=null;
        ResultSet rs=null;
        //查询到的数据是Object类型的
        Object object=null;
        Field[] fields=cl.getDeclaredFields();
        //数据库查询sql语句
        String sql="select * from "+table_name+" where "+str+" =?";
        System.out.println(sql);
        try{
            //查询数据库
            ps=conn.prepareStatement(sql);
            ps.setObject(1, ob);
            rs=ps.executeQuery();
            //查询到数据
            while(rs.next()){
                object=cl.newInstance();
                for (Field f:fields
                     ) {
                    f.setAccessible(true);
                    f.set(object,rs.getObject(f.getName()));
                }
                al.add(object);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close(rs);
            close(ps);
            close(conn);
        }
        return al;
    }
	
    /**
     *按照时间范围查找记录，专有函数不通用
     * @param cl
     * @param str
     * @param ob
     * @return
     */
    public static ArrayList<Object> getListBySomeLimTime(Class<?> cl,String str,Object ob,Timestamp time){
        //对象集合
        ArrayList<Object> al=new ArrayList<Object>();
        //获取连接
        Connection conn=getCconnection();
       	String table_name=cl.getSimpleName();
    		if(cl.isAnnotationPresent(Table.class)){
    			table_name=cl.getAnnotation(Table.class).value();
    		}
        PreparedStatement ps=null;
        ResultSet rs=null;
        //查询到的数据是Object类型的
        Object object=null;
        Field[] fields=cl.getDeclaredFields();
        //数据库查询sql语句
        String date="date";
        if(cl.getSimpleName().equals(R_operation.class.getSimpleName()))
        	date="r_date";
        if(cl.getSimpleName().equals(U_operation.class.getSimpleName()))
        date="u_date";
        String sql="select * from "+table_name+" where "+str+" =? and "+date+" between "+"?and? ";
        System.out.println(sql);
        try{
            //查询数据库
            ps=conn.prepareStatement(sql);
            ps.setObject(1, ob);
            ps.setObject(2, time);
            ps.setObject(3, new Timestamp(System.currentTimeMillis()));
            rs=ps.executeQuery();
            //查询到数据
            while(rs.next()){
                object=cl.newInstance();
                for (Field f:fields
                     ) {
                    f.setAccessible(true);
                    f.set(object,rs.getObject(f.getName()));
                }
                al.add(object);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close(rs);
            close(ps);
            close(conn);
        }
        return al;
    }
    
    /**
     * 万能更新方法
     * @param object 待更新的对象
     * @return 是否更新成功
     */
    public static boolean update(Object object){
        //定义标志字
        boolean flag=false;
        //获取连接
        Connection conn=getCconnection();
        PreparedStatement ps=null;
        //获取Class对象
        Class<?>cl=object.getClass();
        
      	String table_name=cl.getSimpleName();
		if(cl.isAnnotationPresent(Table.class)){
			table_name=cl.getAnnotation(Table.class).value();
		}
        //获取Class对象属性数组
        Field[] fields=cl.getDeclaredFields();
        //更新sql字符串拼接
        ////update table set name = ?,age = ?,anid = ? where id = ?
        ArrayList<String> cols=getColumnsByRSMD(cl.getSimpleName());
        //获取属性列表
        //获取id的列名
        Field idName=null;
        for(int i=0;i<fields.length;i++){
        	Field f = fields[i];
        	if(f.isAnnotationPresent(Identity.class)){
        		//isGeneratedKey=f.getAnnotation(Identity.class).value();
        		idName=f;
        	}
        }
        
        StringBuilder sb=new StringBuilder();
        sb.append("update "+table_name+" set ");
        for (int i=1;i<fields.length;i++){
            sb.append(fields[i].getName()+" = ?");
            if (i!=fields.length-1){
                sb.append(",");
            }
        }
        sb.append(" where "+idName.getName()+" = ?");
        System.out.println(sb.toString());
        try{
            ps=conn.prepareStatement(sb.toString());
            for (int i=1;i<fields.length;i++){
                fields[i].setAccessible(true);
                ps.setObject(i,fields[i].get(object));
            }
            //对id进行赋值
            idName.setAccessible(true);
            ps.setObject(fields.length,idName.get(object));
            int a=ps.executeUpdate();
            if (a>0){
                flag=true;
                System.out.println("修改成功");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
        	close(ps);
           close(conn);
        }
        return flag;
    }
	 
    /**
     * 万能删除方法
     * @param object 所要删除的数据的POJO类对象
     * @param id 所要删除的数据的id
     * @return 删除是否成功
     */
    public static boolean delete(Object object,Object id){
        boolean flag=false;
        Connection conn=getCconnection();
    	Class <?>cl=object.getClass();
    	String table_name=cl.getSimpleName();
    
		if(cl.isAnnotationPresent(Table.class)){
			table_name=((Table) cl.getAnnotation(Table.class)).value();
		}
        PreparedStatement ps=null;
        ResultSet rs=null;
        ArrayList<String> cols=getColumnsByRSMD(cl.getSimpleName());
        //获取属性列表
        Field[] fields=cl.getDeclaredFields();
        //获取id的列名
        Field idName=null;
        for(int i=0;i<fields.length;i++){
        	Field f = fields[i];
        	if(f.isAnnotationPresent(Identity.class)){
        		//isGeneratedKey=f.getAnnotation(Identity.class).value();
        		idName=f;
        	}
        }
        StringBuilder sb=new StringBuilder();
        sb.append("delete from "+table_name+" where "+idName.getName()+" = ?");
        try {
            ps=conn.prepareStatement(sb.toString());
            ps.setObject(1, id);
            int a=ps.executeUpdate();
            if (a>0){
                flag=true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close(ps);
            close(conn);
        }
        return flag;
    }
    
    /**
     * 查询所有
     * @param c
     * @return
     */
	public static <T> List getAll(Class<T>c){
		//生成sql
		String sql="select * from "+c.getSimpleName();
		Field [] fs=c.getDeclaredFields();
		List<Object> list = new ArrayList<Object>();
		//jdbc资源
		Connection conn=getCconnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		//将resultSet转化为list
		try {
			//执行sql
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			//将记录集里的数据取出来存到list里
			while(rs.next()){
				T obj=c.newInstance();
				for(Field f : fs){
					f.setAccessible(true);
					f.set(obj, rs.getObject(f.getName()));
				}
				list.add(obj);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rs);
			close(ps);
			close(conn);
		}
		return list;
	}
	 /**
     * 使用DataBaseMetaData来获取数据库中查询到的表的所有列名
     * DataBaseMetaData数据库元数据对象，也可理解为数据库数据对象
     * DataBaseMetaData值可以获取到当前查询表的相关数据
     * 即单表属性获取
     * @param tableName
     * @return
     */
    public static ArrayList<String> getColumnsByDBMD(String tableName){
        ArrayList<String> al=new ArrayList<String>();
        Connection conn=getCconnection();
        try{
            DatabaseMetaData dmd=conn.getMetaData();
            //获取表中所有类的属性信息集合
            ResultSet rs=dmd.getColumns(null,"%",tableName,"%");
            //遍历集合，获取数据
            while (rs.next()){
                al.add(rs.getString("COLUMN_NAME"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return al;
    }
    /**
     * 使用ResultSetMetaData来获取数据库中查询到的表的所有列名
     * 可以查询到查到返回数据的所有列名，即可以查询到多表返回的列名
     * @param tableName 查询表名
     * @return 表中列名的集合
     */
    public static ArrayList<String> getColumnsByRSMD(String tableName){
        ArrayList<String> al=new ArrayList<String>();
        Connection conn=getCconnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        //sql语句，不会查询到任何数据，只会返回查询到的列信息
        String sql="select * from "+tableName+" where 1=2";
        try{
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            //获取ResultSetMetaData对象
            ResultSetMetaData msd=rs.getMetaData();
            //获取查询到的列的总数
            int n=msd.getColumnCount();
            //获取所有类名并加入到集合当中去,列号从1开始
            for (int i=1;i<=n;i++){
                al.add(msd.getColumnName(i));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
        	close(rs);
        	close(ps);
           close(conn);
        }
        return al;
    }
	 /**
     * 查询表中所有数据
     * select * 效率太低，改为select具体列名
     * @param cl 表对象实体类Class对象
     * @return 查询到的表中数据集合
     */
    public static ArrayList getList(Class cl){
        ArrayList al=new ArrayList();
        Connection conn=getCconnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        //获取表中的列名集合
        ArrayList<String> cols=getColumnsByRSMD(cl.getSimpleName());
        //获取属性集合
        Field[] fields=cl.getDeclaredFields();
        //拼接sql字符串
        StringBuilder sb=new StringBuilder();
        sb.append("select ");
        for (int i=0;i<cols.size();i++){
            sb.append(cols.get(i));
            if (i!=cols.size()-1){
                sb.append(",");
            }
        }
        sb.append(" from "+cl.getSimpleName());
        try{
            ps=conn.prepareStatement(sb.toString());
            rs=ps.executeQuery();
            while (rs.next()){
                Object ob=cl.newInstance();
                for (String str:cols) {
                    for (Field f:fields){
                        if (f.getName().equals(str)){
                            f.setAccessible(true);
                            f.set(ob,rs.getObject(str));
                            break;
                        }
                    }
                }
                al.add(ob);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
           close(rs);
           close(ps);
           close(conn);
        }
        return al;
    }
    /**
     * 根据id来查询对应的数据
     * @param cl 查询的表的POJO类对象
     * @param id 查询对象的id
     * @return 查询到的id
     */
    public static Object getlistById(Class<?> cl,Object id){
    	//boolean isGeneratedKey=false;//是否返回主键
        //获取数据库连接
        Connection conn=getCconnection();
        //查询到的对象是Object类型的
		String table_name=cl.getSimpleName();
		if(cl.isAnnotationPresent(Table.class)){
			table_name=((Table) cl.getAnnotation(Table.class)).value();
		}
        Object ob=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        ArrayList<String> cols=getColumnsByRSMD(cl.getSimpleName());
        //获取属性列表
        Field[] fields=cl.getDeclaredFields();
        //获取id的列名
        Field idName=null;
        for(int i=0;i<fields.length;i++){
        	Field f = fields[i];
        	if(f.isAnnotationPresent(Identity.class)){
        		//isGeneratedKey=f.getAnnotation(Identity.class).value();
        		idName=f;
        	}
        }
        StringBuilder sb=new StringBuilder();
        //数据库查询语句
       sb.append("select ");
        for (int i=0;i<cols.size();i++){
            sb.append(cols.get(i));
            
            if (i!=cols.size()-1){
                sb.append(",");
            }
        }
        sb.append(" from "+ table_name+" where("+idName.getName().toString()+"=?)");
        System.out.println(sb.toString());
        try{
            ps=conn.prepareStatement(sb.toString());
            ps.setString(1, id.toString());
            rs=ps.executeQuery();
            //查询到数据
            ob=cl.newInstance();
            if (rs.next()){
            	System.out.println("可以查到数据");
               // ob=cl.newInstance();
                for (String str:cols){
                    for (Field f:fields){
                        if (f.getName().equals(str)){
                            f.setAccessible(true);
                            f.set(ob,rs.getObject(str));
                            break;
                        }
                    }
                }
            }else{
            	System.out.println("查不到数据");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close(rs);
            close(ps);
            close(conn);
        }
        return ob;
    }
    
    
/**
 *同时更新两组数据，并且这两组数据相互依赖（转账）
 * @param object
 * @param object2
 * @return
 */
    public static boolean update(Object object,Object object2){
        //定义标志字
        boolean flag=false;
        //获取连接
        Connection conn=getCconnection();
        PreparedStatement ps=null;
        PreparedStatement ps2=null;
        //获取Class对象
        Class<?>cl=object.getClass();
        Class<?>cl2=object2.getClass();
      	String table_name=cl.getSimpleName();
		if(cl.isAnnotationPresent(Table.class)){
			table_name=cl.getAnnotation(Table.class).value();
		}
		String table_name2=cl2.getSimpleName();
		if(cl2.isAnnotationPresent(Table.class)){
			table_name2=cl2.getAnnotation(Table.class).value();
		}
        //获取Class对象属性数组
        Field[] fields=cl.getDeclaredFields();
        Field[] fields2=cl2.getDeclaredFields();
        //更新sql字符串拼接
        ////update table set name = ?,age = ?,anid = ? where id = ?
        ArrayList<String> cols=getColumnsByRSMD(cl.getSimpleName());
        ArrayList<String> cols2=getColumnsByRSMD(cl2.getSimpleName());
        //获取属性列表
        //获取id的列名
        Field idName=null;
        Field idName2=null;
        for(int i=0;i<fields.length;i++){
        	Field f = fields[i];
        	if(f.isAnnotationPresent(Identity.class)){
        		//isGeneratedKey=f.getAnnotation(Identity.class).value();
        		idName=f;
        	}
        }
        for(int i=0;i<fields2.length;i++){
        	Field f = fields2[i];
        	if(f.isAnnotationPresent(Identity.class)){
        		//isGeneratedKey=f.getAnnotation(Identity.class).value();
        		idName2=f;
        	}
        }
        StringBuilder sb=new StringBuilder();
        StringBuilder sb2=new StringBuilder();
        sb.append("update "+table_name+" set ");
        sb2.append("update "+table_name2+" set ");
        for (int i=1;i<fields.length;i++){
            sb.append(fields[i].getName()+" = ?");
            if (i!=fields.length-1){
                sb.append(",");
            }
        }
        for (int i=1;i<fields2.length;i++){
            sb2.append(fields2[i].getName()+" = ?");
            if (i!=fields2.length-1){
                sb2.append(",");
            }
        }
        sb.append(" where "+idName.getName()+" = ?");
        sb2.append(" where "+idName2.getName()+" = ?");
        System.out.println(sb.toString());
        System.out.println(sb2.toString());
        try{
        	
        	conn.setAutoCommit(false);
            ps=conn.prepareStatement(sb.toString());
            ps2=conn.prepareStatement(sb2.toString());
            for (int i=1;i<fields.length;i++){
                fields[i].setAccessible(true);
                ps.setObject(i,fields[i].get(object));
            }
            for (int i=1;i<fields2.length;i++){
                fields2[i].setAccessible(true);
                ps2.setObject(i,fields2[i].get(object2));
            }
            //对id进行赋值
            idName.setAccessible(true);
            idName2.setAccessible(true);
            ps.setObject(fields.length,idName.get(object));
            ps2.setObject(fields2.length,idName2.get(object2));
            int a=ps.executeUpdate();
            int a2=ps2.executeUpdate();
            if (a>0&&a2>0){
                flag=true;
                System.out.println("修改成功");
            }
            conn.commit();
        }catch (Exception e){
        	try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
            e.printStackTrace();
        }finally {
        	close(ps);
        	close(ps2);
           close(conn);
        }
        return flag;
    }
    

}
