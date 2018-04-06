package com.cafe24.jblog2.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
	
	private static String SAVE_PATH = "/uploads";
	private static String PREFIX_URL = "/uploads/";
//	private static String fileName = ".jpg";
	
	public String restore(MultipartFile multipartFile) {
		String fileName = genSaveFileName();
		try {
			writeFile(multipartFile, fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return PREFIX_URL+fileName;
	}
	
	/*public MultipartFile imgChange(MultipartFile multipartFile) {
		
	}*/
	
	private void writeFile (
			MultipartFile multipartFile, String fileName ) throws IOException{
		byte[] fileData = multipartFile.getBytes();
		File file = new File(SAVE_PATH);
		if (!file.exists()) {
			file.mkdirs();
		}
		FileOutputStream fos = new FileOutputStream(SAVE_PATH+"/"+fileName);
		fos.write(fileData);
		fos.close();
	}
	
	private String genSaveFileName() {
		String filename = "";
		Calendar calendar = Calendar.getInstance();
		filename += "logo_";
		filename += calendar.get(Calendar.YEAR);
		filename += calendar.get(Calendar.MONTH);
		filename += calendar.get(Calendar.DATE);
		filename += calendar.get(Calendar.HOUR);
		filename += calendar.get(Calendar.MINUTE);
		filename += calendar.get(Calendar.SECOND);
		filename += calendar.get(Calendar.MILLISECOND);
		
		return filename+".jpg";
	}
	
}
