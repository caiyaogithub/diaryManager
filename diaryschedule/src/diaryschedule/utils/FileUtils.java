package diaryschedule.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * 
 * @author caiyao 
 *
 * @function 关于文件操作的一些功能
 */
public class FileUtils {
	public static boolean WriteStringToFile(String content , String targetFilePath) throws IOException{
			// 创建文件夹
		File folder = new File(targetFilePath.substring(0,targetFilePath.lastIndexOf("/"))) ;
		if(!folder.exists()){
			folder.mkdirs() ;
		}
		// 创建html文件
	    File file = new File(targetFilePath) ;  
	    if(!file.exists()){
	    	file.createNewFile() ;
	    }
	    PrintStream ps = new PrintStream(new FileOutputStream(file));  
	    ps.print(content);// 往文件里写入字符串  
	    ps.flush() ;
	    ps.close() ; 
	    return true ;
	}
	/**
	 * 获取文件内容
	 * @param filePath 文件绝对路径
	 * @return String类型的文件内容 ，如果文件不存在返回null
	 * @throws IOException 
	 */
	public static String getFileContent(String filePath) throws IOException{
		String content = "" ;
		File sourceFile = new File(filePath) ;
		if(!sourceFile.exists()){
			return null ;
		}
		FileInputStream in = new FileInputStream(filePath) ;
		byte[] buffer = new byte[1024] ;
		int length ;
		while((length = in.read(buffer)) != -1){
			content += new String(buffer , 0 , length) ;
		}
		return content ;
	}
}
