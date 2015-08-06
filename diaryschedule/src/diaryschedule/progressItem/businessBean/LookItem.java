package diaryschedule.progressItem.businessBean;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import diaryschedule.DBoperator.ProgressItemDB;

/**
 * 
 * @author caiyao 
 *
 * @function 执行所有查看完成进度项功能
 */
public class LookItem {
	public String getProgressItemList(String phasejobId ) 
			throws InstantiationException, IllegalAccessException, 
			NoSuchMethodException, ClassNotFoundException, 
			InvocationTargetException, SQLException, 
			RuntimeException, JsonProcessingException{
		
		 ObjectMapper mapper = new ObjectMapper();  
         
	     String json = mapper.writeValueAsString((new ProgressItemDB()).getProgressItemList(phasejobId));  
		return  json;
	}
}
