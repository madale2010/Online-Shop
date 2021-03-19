package servlets;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Constants.ClassPaths;

public class DisplayImage extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public final String startPattern = "//bmh//images//";

    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    	
    	String URLAfterWebDomain = request.getRequestURI();
        
        String imagesBase;
		try {
        //Append url dir if needed
		if(URLAfterWebDomain.contains("/null")){
			return;
		}
        if(URLAfterWebDomain.contains("/Products/")){
        	imagesBase=ClassPaths.getImageLocation().toString();
        }
        else if(URLAfterWebDomain.contains("/Blog/")){
        	imagesBase=ClassPaths.getBlogLocation().toString();
        } 
        else if(URLAfterWebDomain.contains("/upload/")){
        	imagesBase=ClassPaths.getUploadLocation().toString();
        }
        else {
        	imagesBase=ClassPaths.getBasePath().toString();
        }
        //Only accept mappings as src="/images/whatever.jpg", even if web.xml has other mappings to this servlet.
        if(URLAfterWebDomain.startsWith("/bmh/images")==false)
        	if(URLAfterWebDomain.startsWith("/bmh/manager/images")==false)
            return;

        //get the image name, or even directory and image, e.g. /images/music/beethoven.jpg:
        String relativeImagePath = URLAfterWebDomain.substring(URLAfterWebDomain.lastIndexOf("/")+1);  //will get "music/beethoven.jpg"
        //remove spaces
        relativeImagePath=relativeImagePath.replace("%20", " ");
        //Just for case senstive files
        String pathExt = relativeImagePath.substring(relativeImagePath.lastIndexOf("."));
        relativeImagePath=relativeImagePath.replace(pathExt, pathExt.toLowerCase());
        
        String absoulteFileName = imagesBase+File.separator+relativeImagePath;
        //System.out.println("\nFetching image from "+absoulteFileName);
        File checkFile = new File(absoulteFileName);

        response.setContentType("image/jpeg"); //as far as I know, this works for PNG as well. You might want to change the mapping to /images/*.jpg if it's giving problems
        //Path temp = Paths.get(absoulteFileName);
        //System.out.println(temp.toFile().getAbsolutePath()+"\n Is a file ? "+temp.toFile().isFile());
        ServletOutputStream outStream;
        outStream = response.getOutputStream();
        if(!checkFile.exists()){
        	System.out.println("Image not found: "+checkFile.getAbsolutePath());
        	absoulteFileName=ClassPaths.getBasePath().toString()+File.separator+"noImage.jpg";
        }
        FileInputStream fin = new FileInputStream(absoulteFileName);      
        BufferedInputStream bin = new BufferedInputStream(fin);
        BufferedOutputStream bout = new BufferedOutputStream(outStream);


        
        int ch =0; ;
        while((ch=bin.read())!=-1)
            bout.write(ch);
        bin.close();
        fin.close();
        bout.close();
        outStream.close();
		} catch(Exception e){
			//we.printStackTrace();
//			request.setAttribute(e.getClass().getName(), e);
//			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
    }
}
