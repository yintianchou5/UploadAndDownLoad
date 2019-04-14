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
		//0.��ȡjsp�е��ļ���
		String fileName = request.getParameter("fileName");
		System.out.println(fileName);
		//1.������Ҫ������Ϣͷ
		response.addHeader("content-Type", "application/ocatet-stream");//MIME���ͣ��������ļ�
		response.addHeader("content-Disposition", "attachment;fileName="+URLEncoder.encode(fileName, "utf-8"));//URLEncoder.encode(fileName, "utf-8")�����������������
		//2.���ļ�ת��������
		InputStream is = getServletContext().getResourceAsStream("/res/"+fileName);
		//3.ͨ����������ļ������û�
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
