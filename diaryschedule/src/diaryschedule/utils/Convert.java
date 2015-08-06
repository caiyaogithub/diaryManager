package diaryschedule.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.sun.rowset.CachedRowSetImpl;

/**
 * 
 * @author caiyao 
 *
 * @function ����ת��
 */
public class Convert {
	/**
	 * ��CachedRowSetImpl�еĶ�����¼�洢��List��
	 * @param cachedRS CachedRowSetImpl�����
	 * @param c List��Ԫ������
	 * @return ArrayList����
	 * @throws SQLException  cachedRS.next()���µ��쳣
	 * @throws RuntimeException 
	 * @throws InvocationTargetException 
	 * @throws ClassNotFoundException 
	 * @throws NoSuchMethodException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static <T> ArrayList<T> RStoList(CachedRowSetImpl cachedRS , Class<T> c)
			throws SQLException, InstantiationException, IllegalAccessException, 
			NoSuchMethodException, ClassNotFoundException, InvocationTargetException, 
			RuntimeException{
		ArrayList<T> store = new ArrayList<T>() ;
		while(cachedRS.next()){
			T object = RStoObject(cachedRS , c ) ;
			store.add(object) ;
		}
		return store ;
	}
	/**
	 * ��ResultSet���͵Ľ����ת����Object���͵Ķ���
	 * ע��
	 * 1. �÷�����֧��RS�򼯺����͵�ת��������List
	 * 2. rs������еõ���ֵxxx��������Ҫ����Ӧ��setXxx����
	 * @param rs �����
	 * @param c  Class���͵�ֵ������ͨ��Class.forName(����)��ȡ��Ҫȷ���������޲εĹ��췽����
	 * 			 ��������InstantiationException�쳣
	 * @return ת��֮��Ķ���
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws SQLException 
	 * @throws RuntimeException 
	 * @throws NoSuchMethodException 
	 * @throws ClassNotFoundException 
	 * @throws InvocationTargetException 
	 */
	public static <T> T RStoObject(CachedRowSetImpl rs , Class<T> c)
			throws InstantiationException, IllegalAccessException, 
			SQLException, NoSuchMethodException, RuntimeException, 
			ClassNotFoundException, InvocationTargetException{
		
		 // Ҫȷ���������޲εĹ��췽��
		T instance = c.newInstance() ;
		// ��ȡԪ���ݣ��Ա�õ��е���Ϣ
		ResultSetMetaData metaData = rs.getMetaData() ;
		// ��ȡ����
		int columnNum = metaData.getColumnCount() ;
		
		for(int i = 1 ; i <= columnNum ; i ++ ){
			String columnName = metaData.getColumnName(i) ;
			Class columnClassType = SQLTypeToClass(metaData.getColumnType(i)) ;
			String columnTypeName = columnTypeToGetter(metaData.getColumnTypeName(i)) ;
			// �����ȡ�����set����
			Method objectMethod = c.getMethod(
							"set"+columnName.substring(0,1).toUpperCase()+columnName.substring(1), 
							columnClassType
							) ;
			// �����ȡCachedRowSetImpl��get����
			Method RSGetter = CachedRowSetImpl.class.getMethod(
							"get"+columnTypeName.substring(0,1)+columnTypeName.substring(1).toLowerCase(), 
							 int.class) ;
			// ִ��RS��get������ȡ����ֵ
			Object value = RSGetter.invoke(rs,i) ;
			// ִ��Object��set����Ϊ����ֵ
			objectMethod.invoke(instance, value) ;
		}
		// ���ض���
		return instance ;
	}
	public static String columnTypeToGetter(String columnType){
		/**
		 * mysql���ݿ��д洢�ַ���ֻ��varchar������ResultSet�ӿ���û�ж���getVarchar�����������Խ�varcharת����String���ɣ�
		 * û�п����������ݿ�
		 */
		switch(columnType){
		case "VARCHAR" : return "String" ;
		}
		return columnType ;
	}
	/**
	 * ����װ���ԭʼ��������ת����ԭʼ���͵�class
	 * @param columnType ��װ��Ķ�����������"java.lang.Integer" , "java.lang.Float" , "java.lang.Double"
	 * @return ԭʼ����class���� ����int.class float.class double.class
	 * @throws ClassNotFoundException 
	 */
	public static Class getColumnClassType(String columnType) throws ClassNotFoundException{
		/**
		 * ���ݿ��е�int��float��double��ԭʼ�����͵�ͨ��getColumnClassName()������ȡʱ���Ὣԭʼ���ʹ������������ͷ�װ���� 
		 * ���� int --> Integer float --> Float double --> Double
		 * �÷�����Ϊ�˽���ȡ�Ļ������ͷ�װ����ת����ԭʼ���͵�class
		 */
		switch(columnType){
		case "java.lang.Integer" : return int.class ;
		case "java.lang.Float" : return float.class ;
		case "java.lang.Double" : return double.class ;
		}
		return Class.forName(columnType) ;
	}
	/**
	 * ��java.sql.Types����ת������Ӧ��Java�ж�Ӧ��Class
	 * @param SQLType java.sql.Types����
	 * @return Class���͵�ʵ������ int.class
	 */
	public static  Class SQLTypeToClass(int SQLType){
		switch(SQLType){
		case java.sql.Types.ARRAY : return String.class ;
		
		case java.sql.Types.BIGINT : return int.class ;
		
		case java.sql.Types.BIT : return byte.class ;
		
		case java.sql.Types.BOOLEAN : return boolean.class ;
		
		case java.sql.Types.CHAR : return char.class ;
		
		case java.sql.Types.DOUBLE : return double.class ;
		
		case java.sql.Types.FLOAT : return float.class ;
		
		case java.sql.Types.INTEGER : return int.class ;
		
		case java.sql.Types.LONGNVARCHAR : return String.class ;
		
		case java.sql.Types.LONGVARCHAR : return String.class ;
		
		case java.sql.Types.NCHAR : return String.class ;
		
		case java.sql.Types.NVARCHAR :return String.class ;
		
		case java.sql.Types.VARCHAR : return String.class ;
		// ͨ��rs.getDate()��ȡ����java.sql.Date�ܹ�ֱ�ӽ��丳ֵ��java.util.Date������Զ�ת��
		case java.sql.Types.DATE : return Date.class ;
		
		default :return String.class ;
		}
	}
	public static Date StringTodate(String dateString , String format ) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(format);  
		  // ��ʱ���ַ���ת����java.util.Date����  
        Date date = sdf.parse(dateString);  
		return date ;
	}
	public static java.sql.Date utildateTosqldate(Date date){
	    return new java.sql.Date(date.getTime());
	}
}
