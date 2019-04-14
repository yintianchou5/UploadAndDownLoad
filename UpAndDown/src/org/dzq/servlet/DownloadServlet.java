package org.dzq.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//0.获取jsp中的文件名
		String fileName = request.getParameter("fileName");
		System.out.println(fileName);
		//1.下载需要设置消息头
		response.addHeader("content-Type", "application/ocatet-stream");//MIME类型：二进制文件
		response.addHeader("content-Disposition", "attachment;fileName="+URLEncoder.encode(fileName, "utf-8"));//URLEncoder.encode(fileName, "utf-8")解决中文名乱码问题
		//2.将文件转成输入流
		InputStream is = getServletContext().getResourceAsStream("/res/"+fileName);
		//3.通过输出流将文件传给用户
		ServletOutputStream os = response.getOutputStream();
		byte[] bs=new byte[10];
		int len =-1;
		while((len=is.read(bs))!=-1) {
			os.write(bs,0,len);
		}
		is.close();
		os.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
