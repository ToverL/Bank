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
 * ͨ��jdbc�����
 * @author ����ΰ
 *
 */
public class CommonDao {
	/**
	 * ��������
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
	 * �ر���Դ
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
	 * ���в���һ��vo����
	 * 
	 * @param obj
	 */
	public static void save(Object obj) {
		boolean isGeneratedKey=false;//�Ƿ񷵻�����
		Class<?> c = obj.getClass();
		String table_name=c.getSimpleName();
		if(c.isAnnotationPresent(Table.class)){
			table_name=c.getAnnotation(Table.class).value();
		}
		
		StringBuffer sql = new StringBuffer("insert into " + table_name+ "(");
		StringBuffer sql_v = new StringBuffer("values(");
		Field fd[] = c.getDeclaredFields();
		
		Field keyField = null;//��������
		for (int i = 0; i < fd.length; i++) {
			Field f=fd[i];
			sql.append(f.getName());
			//�Ƿ�������
			if(f.isAnnotationPresent(Identity.class)){
				//����Ƿ񷵻�����
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
		//ִ��sql; ��ֵ;��������ֵ;�������ɵ�������
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
				rs.next();//�ƶ��α�
			Object key=rs.getInt(1);//����
				keyField.set(obj, key);
			}
		
			System.out.println("��ɲ������" + obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonDao.close(rs);
			CommonDao.close(ps);
			CommonDao.close(conn);
		}
	}


	 /**
     * ����ĳЩ�ض�ֵ����ѯ����Ҫ��Ķ��󼯺�
     * @param cl ��ѯ�Ķ����Class����
     * @param str ��Ҫ��ѯ������
     * @param ob ��������Ӧ��ֵ
     * @return ��ѯ������Ҫ��Ķ��󼯺�
     */
    public static ArrayList<Object> getListBySome(Class<?> cl,String str,Object ob){
        //���󼯺�
        ArrayList<Object> al=new ArrayList<Object>();
        //��ȡ����
        Connection conn=getCconnection();
    	String table_name=cl.getSimpleName();
		if(cl.isAnnotationPresent(Table.class)){
			table_name=cl.getAnnotation(Table.class).value();
		}
        
        PreparedStatement ps=null;
        ResultSet rs=null;
        //��ѯ����������Object���͵�
        Object object=null;
        Field[] fields=cl.getDeclaredFields();
        //���ݿ��ѯsql���
        String sql="select * from "+table_name+" where "+str+" =?";
        System.out.println(sql);
        try{
            //��ѯ���ݿ�
            ps=conn.prepareStatement(sql);
            ps.setObject(1, ob);
            rs=ps.executeQuery();
            //��ѯ������
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
     *����ʱ�䷶Χ���Ҽ�¼��ר�к�����ͨ��
     * @param cl
     * @param str
     * @param ob
     * @return
     */
    public static ArrayList<Object> getListBySomeLimTime(Class<?> cl,String str,Object ob,Timestamp time){
        //���󼯺�
        ArrayList<Object> al=new ArrayList<Object>();
        //��ȡ����
        Connection conn=getCconnection();
       	String table_name=cl.getSimpleName();
    		if(cl.isAnnotationPresent(Table.class)){
    			table_name=cl.getAnnotation(Table.class).value();
    		}
        PreparedStatement ps=null;
        ResultSet rs=null;
        //��ѯ����������Object���͵�
        Object object=null;
        Field[] fields=cl.getDeclaredFields();
        //���ݿ��ѯsql���
        String date="date";
        if(cl.getSimpleName().equals(R_operation.class.getSimpleName()))
        	date="r_date";
        if(cl.getSimpleName().equals(U_operation.class.getSimpleName()))
        date="u_date";
        String sql="select * from "+table_name+" where "+str+" =? and "+date+" between "+"?and? ";
        System.out.println(sql);
        try{
            //��ѯ���ݿ�
            ps=conn.prepareStatement(sql);
            ps.setObject(1, ob);
            ps.setObject(2, time);
            ps.setObject(3, new Timestamp(System.currentTimeMillis()));
            rs=ps.executeQuery();
            //��ѯ������
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
     * ���ܸ��·���
     * @param object �����µĶ���
     * @return �Ƿ���³ɹ�
     */
    public static boolean update(Object object){
        //�����־��
        boolean flag=false;
        //��ȡ����
        Connection conn=getCconnection();
        PreparedStatement ps=null;
        //��ȡClass����
        Class<?>cl=object.getClass();
        
      	String table_name=cl.getSimpleName();
		if(cl.isAnnotationPresent(Table.class)){
			table_name=cl.getAnnotation(Table.class).value();
		}
        //��ȡClass������������
        Field[] fields=cl.getDeclaredFields();
        //����sql�ַ���ƴ��
        ////update table set name = ?,age = ?,anid = ? where id = ?
        ArrayList<String> cols=getColumnsByRSMD(cl.getSimpleName());
        //��ȡ�����б�
        //��ȡid������
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
            //��id���и�ֵ
            idName.setAccessible(true);
            ps.setObject(fields.length,idName.get(object));
            int a=ps.executeUpdate();
            if (a>0){
                flag=true;
                System.out.println("�޸ĳɹ�");
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
     * ����ɾ������
     * @param object ��Ҫɾ�������ݵ�POJO�����
     * @param id ��Ҫɾ�������ݵ�id
     * @return ɾ���Ƿ�ɹ�
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
        //��ȡ�����б�
        Field[] fields=cl.getDeclaredFields();
        //��ȡid������
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
     * ��ѯ����
     * @param c
     * @return
     */
	public static <T> List getAll(Class<T>c){
		//����sql
		String sql="select * from "+c.getSimpleName();
		Field [] fs=c.getDeclaredFields();
		List<Object> list = new ArrayList<Object>();
		//jdbc��Դ
		Connection conn=getCconnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		//��resultSetת��Ϊlist
		try {
			//ִ��sql
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			//����¼���������ȡ�����浽list��
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
     * ʹ��DataBaseMetaData����ȡ���ݿ��в�ѯ���ı����������
     * DataBaseMetaData���ݿ�Ԫ���ݶ���Ҳ�����Ϊ���ݿ����ݶ���
     * DataBaseMetaDataֵ���Ի�ȡ����ǰ��ѯ����������
     * ���������Ի�ȡ
     * @param tableName
     * @return
     */
    public static ArrayList<String> getColumnsByDBMD(String tableName){
        ArrayList<String> al=new ArrayList<String>();
        Connection conn=getCconnection();
        try{
            DatabaseMetaData dmd=conn.getMetaData();
            //��ȡ�����������������Ϣ����
            ResultSet rs=dmd.getColumns(null,"%",tableName,"%");
            //�������ϣ���ȡ����
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
     * ʹ��ResultSetMetaData����ȡ���ݿ��в�ѯ���ı����������
     * ���Բ�ѯ���鵽�������ݵ����������������Բ�ѯ������ص�����
     * @param tableName ��ѯ����
     * @return ���������ļ���
     */
    public static ArrayList<String> getColumnsByRSMD(String tableName){
        ArrayList<String> al=new ArrayList<String>();
        Connection conn=getCconnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        //sql��䣬�����ѯ���κ����ݣ�ֻ�᷵�ز�ѯ��������Ϣ
        String sql="select * from "+tableName+" where 1=2";
        try{
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            //��ȡResultSetMetaData����
            ResultSetMetaData msd=rs.getMetaData();
            //��ȡ��ѯ�����е�����
            int n=msd.getColumnCount();
            //��ȡ�������������뵽���ϵ���ȥ,�кŴ�1��ʼ
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
     * ��ѯ������������
     * select * Ч��̫�ͣ���Ϊselect��������
     * @param cl �����ʵ����Class����
     * @return ��ѯ���ı������ݼ���
     */
    public static ArrayList getList(Class cl){
        ArrayList al=new ArrayList();
        Connection conn=getCconnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        //��ȡ���е���������
        ArrayList<String> cols=getColumnsByRSMD(cl.getSimpleName());
        //��ȡ���Լ���
        Field[] fields=cl.getDeclaredFields();
        //ƴ��sql�ַ���
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
     * ����id����ѯ��Ӧ������
     * @param cl ��ѯ�ı��POJO�����
     * @param id ��ѯ�����id
     * @return ��ѯ����id
     */
    public static Object getlistById(Class<?> cl,Object id){
    	//boolean isGeneratedKey=false;//�Ƿ񷵻�����
        //��ȡ���ݿ�����
        Connection conn=getCconnection();
        //��ѯ���Ķ�����Object���͵�
		String table_name=cl.getSimpleName();
		if(cl.isAnnotationPresent(Table.class)){
			table_name=((Table) cl.getAnnotation(Table.class)).value();
		}
        Object ob=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        ArrayList<String> cols=getColumnsByRSMD(cl.getSimpleName());
        //��ȡ�����б�
        Field[] fields=cl.getDeclaredFields();
        //��ȡid������
        Field idName=null;
        for(int i=0;i<fields.length;i++){
        	Field f = fields[i];
        	if(f.isAnnotationPresent(Identity.class)){
        		//isGeneratedKey=f.getAnnotation(Identity.class).value();
        		idName=f;
        	}
        }
        StringBuilder sb=new StringBuilder();
        //���ݿ��ѯ���
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
            //��ѯ������
            ob=cl.newInstance();
            if (rs.next()){
            	System.out.println("���Բ鵽����");
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
            	System.out.println("�鲻������");
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
 *ͬʱ�����������ݣ����������������໥������ת�ˣ�
 * @param object
 * @param object2
 * @return
 */
    public static boolean update(Object object,Object object2){
        //�����־��
        boolean flag=false;
        //��ȡ����
        Connection conn=getCconnection();
        PreparedStatement ps=null;
        PreparedStatement ps2=null;
        //��ȡClass����
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
        //��ȡClass������������
        Field[] fields=cl.getDeclaredFields();
        Field[] fields2=cl2.getDeclaredFields();
        //����sql�ַ���ƴ��
        ////update table set name = ?,age = ?,anid = ? where id = ?
        ArrayList<String> cols=getColumnsByRSMD(cl.getSimpleName());
        ArrayList<String> cols2=getColumnsByRSMD(cl2.getSimpleName());
        //��ȡ�����б�
        //��ȡid������
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
            //��id���и�ֵ
            idName.setAccessible(true);
            idName2.setAccessible(true);
            ps.setObject(fields.length,idName.get(object));
            ps2.setObject(fields2.length,idName2.get(object2));
            int a=ps.executeUpdate();
            int a2=ps2.executeUpdate();
            if (a>0&&a2>0){
                flag=true;
                System.out.println("�޸ĳɹ�");
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
