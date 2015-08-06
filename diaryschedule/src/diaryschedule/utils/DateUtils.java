package diaryschedule.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author caiyao 
 *
 * @function 关于时间的通用功能
 */
public class DateUtils {
	/**
	 * 获取格式化的当前时间
	 * @param format
	 * @return
	 */
	public static String getFormatDate(String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);  
		  // 将时间字符串转换成java.util.Date对象  
        String dateString = sdf.format(new Date()) ; 
		return dateString ;
	}
}
