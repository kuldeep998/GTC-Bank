package com.gtcsys.code.utility;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.servlet.http.HttpServletRequest;

/*
 * Description :- this class is used to defined common method operation
 * Name :- Kuldeep 
 * Date :- 28/07/2022 
 * 
 * */
public class Utility {

	public static String callingurl(HttpServletRequest paramHttpServletRequest) {
		return paramHttpServletRequest.getScheme() + "://" + paramHttpServletRequest.getServerName() + ":"
				+ paramHttpServletRequest.getServerPort() + paramHttpServletRequest.getRequestURI();

	}

	public static Timestamp getDate() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		System.out.println(timestamp);
		return timestamp;

	}

	public static String getCurrentDate() {
		DateFormat dform = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		Date obj = new Date();
		return dform.format(obj);
	}

	public static byte[] compress(String data) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length());
		GZIPOutputStream gzip = new GZIPOutputStream(bos);
		gzip.write(data.getBytes());
		gzip.close();
		byte[] compressed = bos.toByteArray();
		bos.close();
		return compressed;
	}

	public static String decompress(byte[] compressed) throws IOException {
		ByteArrayInputStream bis = new ByteArrayInputStream(compressed);
		GZIPInputStream gis = new GZIPInputStream(bis);
		BufferedReader br = new BufferedReader(new InputStreamReader(gis, "UTF-8"));
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		br.close();
		gis.close();
		bis.close();
		return sb.toString();
	}

}
