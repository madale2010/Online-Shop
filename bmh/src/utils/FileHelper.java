package utils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;

import javax.imageio.ImageIO;
import javax.servlet.http.Part;

/**
 * @author madal
 *
 */
public class FileHelper extends Constants {

	/**
	 * 
	 */
	public FileHelper() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
     * Resizes an image to a absolute width and height (the image may not be
     * proportional)
     * @param inputImagePath Path of the original image
     * @param outputImagePath Path to save the resized image
     * @param scaledWidth absolute width in pixels
     * @param scaledHeight absolute height in pixels
     * @throws IOException
     */
    public static void resize(String inputImagePath, String outputImagePath, int scaledWidth, int scaledHeight)    throws IOException {
        // reads input image
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);
 
        // creates output image
        BufferedImage outputImage = new BufferedImage(scaledWidth, scaledHeight, inputImage.getType());
 
        // scales the input image to the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();
 
        // extracts extension of output file
        String formatName = outputImagePath.substring(outputImagePath.lastIndexOf(".") + 1);
 
        // writes to output file
        ImageIO.write(outputImage, formatName, new File(outputImagePath));
    }
 
    /**
     * Resizes an image by a percentage of original size (proportional).
     * @param inputImagePath Path of the original image
     * @param outputImagePath Path to save the resized image
     * @param percent a double number specifies percentage of the output image
     * over the input image.
     * @throws IOException
     */
    public static void resize(String inputImagePath, String outputImagePath, double percent) throws IOException {
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);
        int scaledWidth = (int) (inputImage.getWidth() * percent);
        int scaledHeight = (int) (inputImage.getHeight() * percent);
        resize(inputImagePath, outputImagePath, scaledWidth, scaledHeight);
    }
	/**
	 * Method will be used to copy additional images to server and exclude the
	 * primary image by only getting the part we are looking for
	 * 
	 * @param location
	 * @param collection
	 * @param id
	 * @param multiPartName 
	 * @return fileName
	 * @throws IOException
	 */
	public static String copyFilesByPart(Path location, Collection<Part> collection, String id, String multiPartName)
			throws IOException {
		if (!location.toFile().isDirectory()) {
			location.toFile().mkdirs();
			System.out.println("Created the following directories : " + location.toFile().getAbsolutePath());
		}
		StringBuffer tempString = new StringBuffer();
		for (Part part : collection) {
			String absoluteFileName = FileHelper.extractFileName(part);
			String partName = part.getName();
			String fileName = new File(absoluteFileName).getName();
			if (!absoluteFileName.isEmpty() && multiPartName.equalsIgnoreCase(partName)) {

				// Check to see if we have id and append to the name
				fileName = id + "_O_" + fileName;
				String fileExt = fileName.substring(fileName.lastIndexOf(".")+1);
				fileName=fileName.replace(fileExt, fileExt.toLowerCase());
				Path destPath = Paths.get(location + File.separator + fileName);
				// Path destPath =
				// Paths.get(File.separator+"bmh"+File.separator+"images"+File.separator+"Blog"+File.separator+fileName);
				File dest = destPath.toFile();
				part.write(dest.getAbsolutePath());
				part.delete();
				tempString.append(fileName + ";");
			}
		}
		if (tempString.length() > 0) {
			return tempString.toString();
		} else {
			return "";
		}
	}

	/**
	 * Copy all files to locations and append a unique id to the front of the file.
	 * 
	 * @param location
	 * @param collection
	 * @param id
	 * @throws IOException
	 */
	public static void copyFilesToServer(Path location, Collection<Part> collection, String id) throws IOException {
		if (!location.toFile().isDirectory()) {
			location.toFile().mkdirs();
			System.out.println("Created the following directories : " + location.toFile().getAbsolutePath());
		}
		for (Part part : collection) {
			String absoluteFileName = FileHelper.extractFileName(part);
			String fileName = new File(absoluteFileName).getName();
			if (!absoluteFileName.isEmpty()) {
				// Check to see if we have id and append to the name
				fileName = id + "_" + fileName;
				String fileExt = fileName.substring(fileName.lastIndexOf(".")+1);
				fileName=fileName.replace(fileExt, fileExt.toLowerCase());
				Path destPath = Paths.get(location + File.separator + fileName);
				// Path destPath =
				// Paths.get(File.separator+"bmh"+File.separator+"images"+File.separator+"Blog"+File.separator+fileName);
				File dest = destPath.toFile();
				part.write(dest.getAbsolutePath());
				part.delete();
			}
		}
	}
	/**
	 * Copy all files to locations and append a unique id to the front of the file.
	 * 
	 * @param location
	 * @param collection
	 * @param id
	 * @param filter 
	 * @throws IOException
	 */
	public static void copyFilteredFilesToServer(Path location, Collection<Part> collection, String id, String filter) throws IOException {
		if (!location.toFile().isDirectory()) {
			location.toFile().mkdirs();
			System.out.println("Created the following directories : " + location.toFile().getAbsolutePath());
		}
		for (Part part : collection) {
			String absoluteFileName = FileHelper.extractFileName(part);
			String partName=part.getName();
			String fileName = new File(absoluteFileName).getName();
			if (!absoluteFileName.isEmpty()) {
				//Filter for parts we need
				if(!absoluteFileName.isEmpty() && filter.equalsIgnoreCase(partName)){
					// Check to see if we have id and append to the name
					fileName = id + "_" + fileName;
					String fileExt = fileName.substring(fileName.lastIndexOf(".")+1);
					fileName=fileName.replace(fileExt, fileExt.toLowerCase());
					Path destPath = Paths.get(location + File.separator + fileName);
					// Path destPath =
					// Paths.get(File.separator+"bmh"+File.separator+"images"+File.separator+"Blog"+File.separator+fileName);
					File dest = destPath.toFile();
					part.write(dest.getAbsolutePath());
					part.delete();
				}
			}
		}
	}
	/**
	 * Copy single file to locations and append a unique id to the front of the file.
	 * 
	 * @param location
	 * @param part
	 * @param id
	 * @return newName
	 * @throws IOException
	 */
	public static String copyFileToServer(Path location, Part part, String id) throws IOException {
		if (!location.toFile().isDirectory()) {
			location.toFile().mkdirs();
			System.out.println("Created the following directories : " + location.toFile().getAbsolutePath());
		}
		String absoluteFileName = FileHelper.extractFileName(part);
		String fileName = new File(absoluteFileName).getName();
		// dev use only
		if (!absoluteFileName.isEmpty()) {
			// Check to see if we have id and append to the name
			if(id!=null)
			fileName = id + "_" + fileName;
			String fileExt = fileName.substring(fileName.lastIndexOf(".")+1);
			fileName=fileName.replace(fileExt, fileExt.toLowerCase());
			Path destPath = Paths.get(location + File.separator + fileName);
			// For local dev only if on server we shouldn't be concerned

			File dest = destPath.toFile();
			part.write(dest.getAbsolutePath());
		}
		return fileName;
	}
	
	/**
	 *  Copy file to locations and append a unique id to the front of the file.
	 *  Will return the name of the newly saved file as string.
	 * @param location
	 * @param part
	 * @param id
	 * @return fileName
	 */
	public static String copyPrimaryImage(Path location, Part part, String id) {
		if (!location.toFile().isDirectory()) {
			location.toFile().mkdirs();
			System.out.println("Created the following directories : " + location.toFile().getAbsolutePath());
		}
		String absoluteFileName = FileHelper.extractFileName(part);
		String fileName = new File(absoluteFileName).getName();
		// dev use only
		if (!absoluteFileName.isEmpty()) {
			// Check to see if we have id and append to the name
			fileName = id + "_P_" + fileName;
			String fileExt = fileName.substring(fileName.lastIndexOf(".")+1);
			fileName=fileName.replace(fileExt, fileExt.toLowerCase());
			
			Path destPath = Paths.get(location + File.separator + fileName);
			// For local dev only if on server we shouldn't be concerned

			File dest = destPath.toFile();
			try {
				part.write(dest.getAbsolutePath());
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return destPath.toString();
		} else {
			return "";
		}

	}
	/**
	 * 
	 * @param string
	 * @return time
	 */
	public static Timestamp dateStringToTimestamp(String string) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		Timestamp timestamp = null;
		java.util.Date parsedTimeStamp;

		try {
			parsedTimeStamp = dateFormat.parse(string);
			timestamp = new Timestamp(parsedTimeStamp.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return timestamp;
	}

	/**
	 * Extracts file name from HTTP header content-disposition
	 * @param part 
	 * @return fileName
	 */
	public static String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}
	/**
	 * 
	 * @param filter
	 * @param dir
	 * @return nameList
	 */
	public static ArrayList<String> findBlogImagesByPid(String filter, File dir) {
		ArrayList<String> fileNameList = new ArrayList<String>();
		final String regex = filter.replace("BMH", "");
		File[] groupFiles = dir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.contains(regex);
			}
		});
		if (groupFiles==null)
			return null;
		if (groupFiles.length == 0) {
			return null;
		} else {
			for (int i = 0; i < groupFiles.length; i++) {
				fileNameList.add(groupFiles[i].getAbsolutePath());
			}
			return fileNameList;
		}

	}
	/**
	 * 
	 * @param filter
	 * @param dir
	 * @return imageList
	 */
	public static ArrayList<String> findProductImageListByPid(String filter, File dir) {
		ArrayList<String> fileNameList = new ArrayList<String>();
		final String regex = filter.replace("BMH", "");
		File[] groupFiles = dir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.contains(regex);
			}
		});
		if (groupFiles.length == 0) {
			return null;
		} else {
			for (int i = 0; i < groupFiles.length; i++) {
				fileNameList.add(groupFiles[i].getAbsolutePath());
			}
			return fileNameList;
		}
	}
	/**
	 * 
	 * @param filter
	 * @param dir
	 * @return mainImage
	 */
	public static String findProductImageMain(String filter, File dir) {
		final String regex = filter.substring(3);
		File[] groupFiles = dir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.contains(regex) && name.contains("(1)");
			}
		});
		if (groupFiles.length == 0) {
			return "";
		} else {
			return groupFiles[0].getName();
		}
	}
	/**
	 * 
	 * @param filter
	 * @param dir
	 * @return product
	 */
	public static File[] findProductImagesByPid(String filter, File dir) {
		final String regex = filter.replace("BMH", "");
		File[] groupFiles = dir.listFiles(new FilenameFilter() {
			
			public boolean accept(File dir, String name) {
				return name.contains(regex);
			}
		});
		if (groupFiles.length == 0) {
			return null;
		} else {
			return groupFiles;
		}
	}
	/**
	 * 
	 * @param image
	 * @return encodedString
	 */
	public static String getEncodedImage(byte[] image) {
		String encodedString = image != null ? Base64.getEncoder().encodeToString(image) : "";
		return encodedString;

	}

	/**
	 * Utility method to get the file type extension
	 * @param fileName 
	 * @return fileExtension 
	 */
	public static String getFileExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());

	}

	/**
	 * Utility method to get file name from HTTP header content-disposition
	 * @param part 
	 * @return fileName
	 */
	public static String getFileName(Part part) {
		if (part == null) {
			return "";
		}
		String contentDisp = part.getHeader("content-disposition");
		// System.out.println("content-disposition header= "+contentDisp);
		String[] tokens = contentDisp.split(";");
		for (String token : tokens) {
			if (token.trim().startsWith("filename")) {
				return token.substring(token.indexOf("=") + 2, token.length() - 1);
			}
		}
		return "";
	}
	/**
	 * 
	 * @param requestParts
	 * @return imageList
	 */
	public static ArrayList<File> getImagesFromPage(Collection<Part> requestParts) {
		ArrayList<File> uploadList = new ArrayList<File>();
		for (Part part : requestParts) {
			String fileName = FileHelper.getFileName(part);
			if (!fileName.isEmpty()) {
				File temp = new File(fileName);
				uploadList.add(temp);
			}

		}
		if (uploadList.size() > 15) {
			System.out.println("TOO MANY FILES");
			return null;

		}
		return uploadList;
	}
	/**
	 * 
	 * @return method
	 */
	public static String getMethodName() {
		return " " + Thread.currentThread().getStackTrace()[2].getClassName() + "."
				+ Thread.currentThread().getStackTrace()[2].getMethodName() + "()";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#finalize()
	 */
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
