package org.dzq.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		try {
			boolean ismultipart = ServletFileUpload.isMultipartContent(request);
			if(ismultipart) {
				//FileItemFactory factory=new DiskFileItemFactory();
				DiskFileItemFactory factory=new DiskFileItemFactory();
				ServletFileUpload fileUpload=new ServletFileUpload(factory);
				
				//设置上传文件的临时文件（缓冲区）
				factory.setSizeThreshold(10240);
				factory.setRepository(new File("F:\\apache-tomcat-8.0.37\\uploadtemp"));
				//限制上传单个文件大小
				fileUpload.setSizeMax(20480);//单位为B（字节）
				//解析items
				List<FileItem> items = fileUpload.parseRequest(request);
				Iterator<FileItem> iter = items.iterator();
				while(iter.hasNext()) {
					FileItem item = iter.next();
					String itemName = item.getFieldName();
					if(item.isFormField()) {
						if(itemName.equals("sno")) {
							int sno = Integer.parseInt(item.getString("utf-8"));
						}else if(itemName.equals("sname")) {
							String sname = item.getString("utf-8");
						}
					}else {
						String fileName = item.getName();
						String ext = fileName.substring(fileName.indexOf(".")+1);
						//限制上传文件格式
						if(ext.equals("png")||ext.equals("gif") ||ext.equals("jpg")) {
							System.out.println("上传文件类型有误！");
							return ;
						}
						//String path =request.getSession().getServletContext().getRealPath("upload");
						String path ="F:\\apache-tomcat-8.0.37\\upload";
						File file=new File(path,fileName);
						item.write(file);
						return ;
					}
					
				}
			}
		}catch(FileUploadBase.FileSizeLimitExceededException e) {
			System.out.println("所选文件太大，无法上传！！");
		}catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
