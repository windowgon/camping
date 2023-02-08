package com.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/fileDownload")
public class FileDownload extends HttpServlet {
	
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String campingfile = "c:/camping_file";
		String no = request.getParameter("no");
		String path = request.getParameter("path");
		String fileName = request.getParameter("imageFileName");
		File downloadFile = new File(campingfile + "/" + path + "/" + no, fileName);
		
		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Content-disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "utf-8"));
		
		try (OutputStream out = response.getOutputStream(); FileInputStream in = new FileInputStream(downloadFile);) {
			byte[] buffer = new byte[1024 * 8];
			int count = 0;
			while ((count = in.read(buffer)) != -1) {
				out.write(buffer, 0, count);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
