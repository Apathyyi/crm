package com.sy.crm.Utils;
import java.util.UUID;
public class UploadUtils {
	public static String getrandomFilename(String filename){
		int sub=filename.lastIndexOf(".");
		String extions = filename.substring(sub);
		return UUID.randomUUID().toString().replace("-", "")+extions;
	}
	public static String getpath(String uuidFilename){
		int code1 = uuidFilename.hashCode();
		int dir1 = code1 & 0xf;//一级目录
		int code2= code1>>>4;
		int dir2=code2 & 0xf;//二级目录
		return "/"+dir1+"/"+dir2;
	}	
}
