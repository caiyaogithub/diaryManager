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
 * @function 类型转换
 */
public class Convert {
	/**
	 * 将CachedRowSetImpl中的多条记录存储进List中
	 * @param cachedRS CachedRowSetImpl结果集
	 * @param c List中元素类型
	 * @return ArrayList对象
	 * @throws SQLException  cachedRS.next()导致的异常
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
	 * 将ResultSet类型的结果集转换成Object类型的对象
	 * 注：
	 * 1. 该方法不支持RS向集合类型的转化，例如List
	 * 2. rs结果集中得到的值xxx，在类中要有相应的setXxx方法
	 * @param rs 结果集
	 * @param c  Class类型的值，可以通过Class.forName(类名)获取，要确定该类有无参的构造方法，
	 * 			 否则会出现InstantiationException异常
	 * @return 转换之后的对象
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
		
		 // 要确定该类有无参的构造方法
		T instance = c.newInstance() ;
		// 获取元数据，以便得到列的信息
		ResultSetMetaData metaData = rs.getMetaData() ;
		// 获取列数
		int columnNum = metaData.getColumnCount() ;
		
		for(int i = 1 ; i <= columnNum ; i ++ ){
			String columnName = metaData.getColumnName(i) ;
			Class columnClassType = SQLTypeToClass(metaData.getColumnType(i)) ;
			String columnTypeName = columnTypeToGetter(metaData.getColumnTypeName(i)) ;
			// 反射获取对象的set方法
			Method objectMethod = c.getMethod(
							"set"+columnName.substring(0,1).toUpperCase()+columnName.substring(1), 
							columnClassType
							) ;
			// 反射获取CachedRowSetImpl的get方法
			Method RSGetter = CachedRowSetImpl.class.getMethod(
							"get"+columnTypeName.substring(0,1)+columnTypeName.substring(1).toLowerCase(), 
							 int.class) ;
			// 执行RS的get方法获取属性值
			Object value = RSGetter.invoke(rs,i) ;
			// 执行Object的set方法为对象赋值
			objectMethod.invoke(instance, value) ;
		}
		// 返回对象
		return instance ;
	}
	public static String columnTypeToGetter(String columnType){
		/**
		 * mysql数据库中存储字符串只有varchar，但是ResultSet接口中没有定义getVarchar（）方法所以将varchar转换成String即可，
		 * 没有考虑其他数据库
		 */
		switch(columnType){
		case "VARCHAR" : return "String" ;
		}
		return columnType ;
	}
	/**
	 * 将封装后的原始类型名称转换成原始类型的class
	 * @param columnType 封装后的对象名称例如"java.lang.Integer" , "java.lang.Float" , "java.lang.Double"
	 * @return 原始类型class类型 例如int.class float.class double.class
	 * @throws ClassNotFoundException 
	 */
	public static Class getColumnClassType(String columnType) throws ClassNotFoundException{
		/**
		 * 数据库中的int、float、double等原始列类型当通过getColumnClassName()方法获取时，会将原始类型打包成其基础类型封装对象 
		 * 例如 int --> Integer float --> Float double --> Double
		 * 该方法是为了将获取的基础类型封装类名转换成原始类型的class
		 */
		switch(columnType){
		case "java.lang.Integer" : return int.class ;
		case "java.lang.Float" : return float.class ;
		case "java.lang.Double" : return double.class ;
		}
		return Class.forName(columnType) ;
	}
	/**
	 * 将java.sql.Types类型转化成相应的Java中对应的Class
	 * @param SQLType java.sql.Types类型
	 * @return Class类型的实例例如 int.class
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
		// 通过rs.getDate()获取到的java.sql.Date能够直接将其赋值到java.util.Date会进行自动转换
		case java.sql.Types.DATE : return Date.class ;
		
		default :return String.class ;
		}
	}
	public static Date StringTodate(String dateString , String format ) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(format);  
		  // 将时间字符串转换成java.util.Date对象  
        Date date = sdf.parse(dateString);  
		return date ;
	}
	public static java.sql.Date utildateTosqldate(Date date){
	    return new java.sql.Date(date.getTime());
	}
}
