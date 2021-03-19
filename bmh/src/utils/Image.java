package utils;

/**
 * 
 */
public class Image extends Constants {
//	private String PID;
//	private File PRIMARY_IMAGE;
//	private ArrayList<File> ADDITIONAL_IMAGES;
//	private String ENCODED_PRIMARY_IMAGE;

	
	/**
	 * Constructor
	 */
	public Image(){
		
	}
//	/**
//	 * @return the pID
//	 */
//	public String getProductId() {
//		return PID;
//	}
//	/**
//	 * @param pID the pID to set
//	 */
//	public void setProductId(String pID) {
//		PID = pID;
//	}
//	/**
//	 * @return the pRIMARY_IMAGE
//	 */
//	public File getPrimaryImage() {
//		return PRIMARY_IMAGE;
//	}
//	/**
//	 * @param pRIMARY_IMAGE the pRIMARY_IMAGE to set
//	 */
//	public void setPrimaryImage(String image) {
//		PRIMARY_IMAGE = createImageFile(image);
//	}
//	
//
//	public File createImageFile(String filePath){
//		return new File(filePath);
//	}
//	public static FileInputStream getImageStream(File file) throws FileNotFoundException{
//		if(file.isFile()){
//			return new FileInputStream(file);
//		} else {
//			
//			return null;
//		} 
//	}
//
//	/**
//	 * @return the eNCODED_PRIMARY_IMAGE
//	 */
//	public String getEncodedPrimaryImage() {
//		return ENCODED_PRIMARY_IMAGE;
//	}
//	/**
//	 * @param eNCODED_PRIMARY_IMAGE the eNCODED_PRIMARY_IMAGE to set
//	 */
//	public void setEncodedPrimaryImage(String eNCODED_PRIMARY_IMAGE) {
//		ENCODED_PRIMARY_IMAGE = eNCODED_PRIMARY_IMAGE;
//	}
//	
//	
//	/**
//	 * 	 Method will gather needed data from the database and convert the data
//	 *	needed into a base64 string that can be displayed by jsp. This will
//	 *	encapsulate the returned objects into string objects within the image 
//	 *	object ready for use.
//	 *	<img src="data:image/gif;base64,${primaryImageEncoded}"/>
//	 *  request.setAttribute("yourBase64EncodedBytesString", yourBase64EncodedBytesString);
//	 *
//	 * @param id
//	 * @return
//	 * @throws SQLException
//	 */
//	public static Image getProductImagesById(String id){
//		//TODO: REMOVE
//		Connection conn =null;
//		Image imageObj = new Image();
//		try {
//			conn = DBHelper.create();
//
//		
//		imageObj.setProductId(id);
//		PreparedStatement statement = conn.prepareStatement("SELECT * FROM BMH_PRODUCT_IMAGES WHERE PID='test'");
//		ResultSet resultSet = statement.executeQuery();
//		
//		byte[] bPrimary = null;
//
//		if(resultSet.next()) {		
//			bPrimary = resultSet.getBytes("PRIMARY_IMAGE");
//		}
//		conn.close();
//		String basePrimaryString = bPrimary!=null ? Base64.getEncoder().encodeToString(bPrimary) :"";
//
//		imageObj.setEncodedPrimaryImage(basePrimaryString);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return imageObj;
//		
//	}
//	/**
//	 * @return the aDDITIONAL_IMAGES
//	 */
//	public ArrayList<File> getImageList(){
//		return ADDITIONAL_IMAGES;
//	}
//	/**
//	 * @param aDDITIONAL_IMAGES the aDDITIONAL_IMAGES to set
//	 */
//	public void setImageList(String imgList) {
//		String[] splitList=imgList.split(";");
//		for(String image:splitList){
//			ADDITIONAL_IMAGES.add(new File(image));
//		}
//	}
}
