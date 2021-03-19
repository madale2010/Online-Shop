package servlets;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import utils.Constants.ClassPaths;
import utils.FileHelper;

/**
 * Servlet implementation class FileUploader
 */
@MultipartConfig(fileSizeThreshold=1024*1024*10,    // 10 MB 
maxFileSize=1024*1024*50,          // 50 MB
maxRequestSize=1024*1024*100)
public class UploadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadFile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		Part tmpFile;
//		tmpFile=request.getPart("image");
//		
//		String tmpFilePath = FileHelper.copyFileToServer(Paths.get(ClassPaths.getBasePath()+File.separator+"upload"), tmpFile, "tmp");
//		
//		response.getWriter().println(tmpFilePath);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Part tmpFile;
		tmpFile=request.getPart("fileUploader");
		String tmpFilePath="";
		Date today = new Date();
		
		 for (Part part : request.getParts()) {
	            String fileName = FileHelper.extractFileName(part);
	           if(fileName!=null){
	        	   tmpFilePath=fileName;
	        	   tmpFile=part;
	           }
	        }
		String ext = FileHelper.getFileExtension(tmpFilePath);
		if (ext.equals("pdf")) {
			tmpFilePath = FileHelper.copyFileToServer(ClassPaths.getDownLoadLocation(), tmpFile, null);
			response.getWriter().println(ClassPaths.getDownLoadLocation() + File.separator + tmpFilePath);
		} else {

			tmpFilePath = FileHelper.copyFileToServer(Paths.get(ClassPaths.getBasePath() + File.separator + "upload"),tmpFile, String.valueOf(today.getTime()));
			response.getWriter().println("images" + File.separator + "upload" + File.separator + tmpFilePath);
		}
		System.out.println("Finished java call " + tmpFilePath);
	}

}
