package diaryschedule.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author caiyao 
 *
 * @function ����ʱ���ͨ�ù���
 */
public class DateUtils {
	/**
	 * ��ȡ��ʽ���ĵ�ǰʱ��
	 * @param format
	 * @return
	 */
	public static String getFormatDate(String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);  
		  // ��ʱ���ַ���ת����java.util.Date����  
        String dateString = sdf.format(new Date()) ; 
		return dateString ;
	}
}
