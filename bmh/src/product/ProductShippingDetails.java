package product;

import com.shippo.model.Parcel;

import shippo.RateDetails;

public class ProductShippingDetails {
	private double size;
	private double pounds;
	private double ounces;
	private double length;
	private double height;
	private double width;
	private int profileId;
	private String profileName;
	private int productId;
	private double totalWeight;
	private Parcel parcelObject;
	private RateDetails rateObject;

	public ProductShippingDetails(){
		parcelObject=new Parcel();
		profileId=0;
		size=0.0;
		pounds=0.0;
		ounces=0.0;
		length=0.0;
		width=0.0;
		height=0.0;
	}
	/**
	 * @return the size
	 */
	public double getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(double size) {
		this.size = size;
	}
	/**
	 * @return the pounds
	 */
	public double getPounds() {
		return pounds;
	}
	/**
	 * @param pounds the pounds to set
	 */
	public void setPounds(double pounds) {
		this.pounds = pounds;
	}
	/**
	 * @return the ounces
	 */
	public double getOunces() {
		return ounces;
	}
	/**
	 * @param ounces the ounces to set
	 */
	public void setOunces(double ounces) {
		this.ounces = ounces;
	}
	/**
	 * @return the length
	 */
	public double getLength() {
		return length;
	}
	/**
	 * @param length the length to set
	 */
	public void setLength(double length) {
		this.length = length;
	}
	/**
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}
	/**
	 * @param height the height to set
	 */
	public void setHeight(double height) {
		this.height = height;
	}
	/**
	 * @return the width
	 */
	public double getWidth() {
		return width;
	}
	/**
	 * @param width the width to set
	 */
	public void setWidth(double width) {
		this.width = width;
	}
	/**
	 * @return the profileId
	 */
	public int getProfileId() {
		return profileId;
	}
	/**
	 * @param profileId the profileId to set
	 */
	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}
	/**
	 * @return the profileName
	 */
	public String getProfileName() {
		return profileName;
	}
	/**
	 * @param profileName the profileName to set
	 */
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	/**
	 * @return the productId
	 */
	public int getProductId() {
		return productId;
	}
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public String getCalculatedWeight(){
		Double convertedOz=Double.valueOf(this.ounces)/16;
		totalWeight=this.pounds+convertedOz;
		return String.valueOf(totalWeight);
	}
	/**
	 * @return the parcelObject
	 */
	public Parcel getParcelObject() {
		this.parcelObject.setHeight(height);
		this.parcelObject.setLength(length);
		this.parcelObject.setWidth(width);
		this.parcelObject.setDistanceUnit("in");
		if(this.pounds==0 && this.ounces<=16){
			this.parcelObject.setMassUnit("oz");
			this.parcelObject.setWeight(this.ounces);
		}
		else if(this.ounces==0 && this.pounds>0) { 
			this.parcelObject.setMassUnit("lb");
			this.parcelObject.setWeight(this.pounds);
		}
		else {
			this.parcelObject.setMassUnit("lb");
			this.parcelObject.setWeight(getCalculatedWeight());
		}
		return parcelObject;
	}
	/**
	 * @return the rateObject
	 */
	public RateDetails getRateObject() {
		return rateObject;
	}
	/**
	 * @param rateObject the rateObject to set
	 */
	//@XmlElement(name="rateObject")
	public void setRateObject(RateDetails rateObject) {
		this.rateObject = rateObject;
	}

	
}
