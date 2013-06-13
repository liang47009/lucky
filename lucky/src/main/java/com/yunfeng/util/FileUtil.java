package com.yunfeng.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	/**
	 * 写入文件
	 * 
	 * @param logo
	 * @param fileName
	 * @throws Exception
	 */
	public static void writeFile(MultipartFile logo, String fileName)
			throws Exception {
		InputStream in = logo.getInputStream();
		byte[] bytes = new byte[in.available()];
		in.read(bytes);
		File file = new File(System.getProperty("web.root") + fileName);
		FileOutputStream fw = new FileOutputStream(file);
		fw.write(bytes);
		fw.flush();
	}

	/**
	 * 生成文件名
	 * 
	 * @param logo
	 * @return
	 */
	public static String generateFileName(MultipartFile logo) {
		return "upload/" + logo.getName() + System.currentTimeMillis() + "."
				+ logo.getContentType().split("/")[1];
	}

}
