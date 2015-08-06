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
 * @function �����ļ�������һЩ����
 */
public class FileUtils {
	public static boolean WriteStringToFile(String content , String targetFilePath) throws IOException{
			// �����ļ���
		File folder = new File(targetFilePath.substring(0,targetFilePath.lastIndexOf("/"))) ;
		if(!folder.exists()){
			folder.mkdirs() ;
		}
		// ����html�ļ�
	    File file = new File(targetFilePath) ;  
	    if(!file.exists()){
	    	file.createNewFile() ;
	    }
	    PrintStream ps = new PrintStream(new FileOutputStream(file));  
	    ps.print(content);// ���ļ���д���ַ���  
	    ps.flush() ;
	    ps.close() ; 
	    return true ;
	}
	/**
	 * ��ȡ�ļ�����
	 * @param filePath �ļ�����·��
	 * @return String���͵��ļ����� ������ļ������ڷ���null
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
