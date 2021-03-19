package utils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import com.shippo.model.Parcel;
import com.shippo.model.Shipment;

import product.Product;
import shippo.ShippingEngine;
import shippo.RateDetails;
import usps.PackageResponse;
import usps.UspsServices;
import utils.Constants.Mail;

public class Cart {
	private ArrayList<Product> cartItems;
	private Product item;
	private BigDecimal subTotal;
	private BigDecimal tax;
	private BigDecimal standardShiping;
	private BigDecimal shippingTotal= new BigDecimal("0.00");
	private BigDecimal handlingFee = new BigDecimal("0.00");
	private BigDecimal total;
	private int destinationZip=0;
	private String shippingMethod;

	private int numCartItems;
	// Shippo objects
	private Shipment shipmentObject;
	private ArrayList<RateDetails> rateObject;
	private Parcel parcelObject;
	private RateDetails rateDetails;
	// USPS objects
	private PackageResponse packageDetails;
	private BigDecimal uspsShippingEst = new BigDecimal("0.00");
	
	/**
	 * Constuctor
	 */
	public Cart() {
		cartItems = new ArrayList<Product>();
		shipmentObject= new Shipment();
		parcelObject= new Parcel();
		rateObject=new ArrayList<RateDetails>();
		shipmentObject.setAddressFrom(Mail.getReturnAddress());
		shipmentObject.setObject_purpose("QUOTE");
	}
	/**
	 * Get cart items
	 * @return
	 */
	public ArrayList<Product> getCartItems() {
		return cartItems;
	}
	/**
	 * Remove cart item
	 * @param index
	 */
	public void removeItem(int index) {
		cartItems.remove(index);
	}
	/**
	 * @return the item
	 */
	public Product getItem() {
		return item;
	}
	/**
	 * @param item
	 *            the item to set
	 */
	public void setItem(Product item) {
		this.item = item;
	}
	/**
	 * @return the subTotal
	 */
	public BigDecimal getSubTotal() {
		return subTotal;
	}
	/**
	 * @param subTotal2
	 *            the subTotal to set
	 */
	public void setSubTotal(BigDecimal subTotal2) {
		this.subTotal = subTotal2;
	}
	/**
	 * @return the tax
	 */
	public BigDecimal getTax() {
		return tax;
	}
	/**
	 * @param totalTax
	 *            the tax to set
	 */
	public void setTax(BigDecimal totalTax) {
		this.tax = totalTax;
	}
	/**
	 * @return the standardShiping
	 */
	public BigDecimal getStandardShiping() {
		return standardShiping;
	}
	/**
	 * @param standardShiping
	 *            the standardShiping to set
	 */
	public void setStandardShiping(BigDecimal standardShiping) {
		this.standardShiping = standardShiping;
	}
	/**
	 * @return the shippingTotal
	 */
	public BigDecimal getShippingTotal() {
		return shippingTotal;
	}
	/**
	 * @param totalshipping
	 *            the shippingTotal to set
	 */
	public void setShippingTotal(BigDecimal totalshipping) {
		this.shippingTotal = totalshipping;
	}
	/**
	 * @return the total
	 */
	public BigDecimal getTotal() {
		return total;
	}
	/**
	 * @param total2
	 *            the total to set
	 */
	public void setTotal(BigDecimal total2) {
		this.total = total2;
	}
	/**
	 * @return the destinationZip
	 */
	public int getDestinationZip() {
		return destinationZip;
	}
	/**
	 * @param destinationZip
	 *            the destinationZip to set
	 */
	public void setDestinationZip(int destinationZip) {
		this.destinationZip = destinationZip;
	}
	/**
	 * @return the shippingMethod
	 */
	public String getShippingMethod() {
		return shippingMethod;
	}
	/**
	 * @param shippingMethod
	 *            the shippingMethod to set
	 */
	public void setShippingMethod(String shippingMethod) {
		this.shippingMethod = shippingMethod;
	}
	/**
	 * @return the packageDetails
	 */
	public PackageResponse getPackageDetails() {
		return packageDetails;
	}
	/**
	 * @param packageDetails
	 *            the packageDetails to set
	 */
	public void setPackageDetails(PackageResponse packageDetails) {
		this.packageDetails = packageDetails;
	}
	/**
	 * @return the handlingFee
	 */
	public BigDecimal getHandlingFee() {
		return handlingFee;
	}
	/**
	 * @param handlingFee
	 *            the handlingFee to set
	 */
	public void setHandlingFee(BigDecimal handlingFee) {
		this.handlingFee = handlingFee;
	}
	/**
	 * Formated amounts for display
	 */
	public String getStringTotal() {
		return this.total != null ? "$" + String.valueOf(this.total) : null;
	}
	/**
	 * Formated amounts for display
	 */
	public String getStringShippingTotal() {
		return this.shippingTotal != null ? "$" + String.valueOf(this.shippingTotal) : null;
	}
	/**
	 * Formated amounts for display
	 */
	public String getStringHandlingFee() {
		return this.handlingFee != null ? "$" + String.valueOf(this.handlingFee) : null;
	}
	/**
	 * Formated amounts for display
	 */
	public String getStringTax() {
		return this.tax != null ? "$" + String.valueOf(this.tax) : null;
	}
	/**
	 * Formated amounts for display
	 */
	public String getStringSubTotal() {
		return this.subTotal != null ? "$" + String.valueOf(this.subTotal) : null;
	}
	/**
	 * @return the numCartItems
	 */
	public int getNumCartItems() {
		return numCartItems;
	}
	/**
	 * @param numCartItems
	 *            the numCartItems to set
	 */
	public void setNumCartItems(int numCartItems) {
		this.numCartItems = numCartItems;
	}
	/**
	 * @return the shipmentObject
	 */
	public Shipment getShipmentObject() {
		return shipmentObject;
	}
	/**
	 * @param shipmentObject
	 *            the shipmentObject to set
	 */
	// @XmlElement(name="shipmentObject")
	public void setShipmentObject(Shipment shipmentObject) {
		this.shipmentObject = shipmentObject;
	}
	/**
	 * @return the parcelObject
	 */
	public Parcel getParcelObject() {
		return parcelObject;
	}
	/**
	 * @param parcelObject the parcelObject to set
	 */
	//@XmlElement(name="parcelObject")
	public void setParcelObject(Parcel parcelObject) {
		this.parcelObject = parcelObject;
	}

	/**
	 * @return the rateObject
	 */
	public ArrayList<RateDetails> getRateObject() {
		return rateObject;
	}

	/**
	 * @param rateObject the rateObject to set
	 */
	public void setRateObject(ArrayList<RateDetails> rateObject) {
		this.rateObject = rateObject;
	}
	/**
	 * Checks for existing item in cart.
	 * 
	 * @param productId
	 * @return
	 */
	public boolean checkforItem(String productId) {
		for (Product item : this.getCartItems()) {
			if (item.getPid().equalsIgnoreCase(productId)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * Add item to cart
	 * @param item
	 */
	public void addToCart(Product item) {
		setItem(item);
		item.getName();
		item.getPid();
		item.getPrice();
		item.getImageURL();
		item.getDescription();
		cartItems.add(item);
	}
	/**
	 * Use this if the cart has more than one item and need to adjust the size and weight.
	 */
	public void calculateParcelDetails(){
		Double length=0.00;
		Double height=0.00;
		Double width=0.00;
		Double ounces=0.00;
		Double pounds=0.00;
		for(Product item: this.getCartItems()){
				pounds=+Double.valueOf(item.getShipDetails().getPounds());
				ounces=+Double.valueOf(item.getShipDetails().getOunces());
				length=+Double.valueOf(item.getShipDetails().getLength());
				height=+Double.valueOf(item.getShipDetails().getHeight());
				width=+Double.valueOf(item.getShipDetails().getWidth());
				
		}
		this.parcelObject.setHeight(height);
		this.parcelObject.setLength(length);
		this.parcelObject.setWidth(width);
		this.parcelObject.setDistanceUnit("in");
		if(pounds==0 && ounces<=16){
			this.parcelObject.setMassUnit("oz");
			this.parcelObject.setWeight(ounces);
		}
		else if(ounces==0 && pounds>0) { 
			this.parcelObject.setMassUnit("lb");
			this.parcelObject.setWeight(pounds);
		}
		else {
			this.parcelObject.setMassUnit("lb");
			Double convertedOz=Double.valueOf(ounces)/16;
			this.parcelObject.setWeight(pounds+convertedOz);
		}
	}
	/**
	 * Takes the current list and updates the quantity if user has selected to
	 * add to cart again
	 * 
	 * @param productId
	 */
	public void updateExistingItem(String productId) {
		for (Product item : this.getCartItems()) {
			if (item.getPid().equalsIgnoreCase(productId)) {
				int updatedQuantity = Integer.valueOf(item.getQuantity()) + 1;
				if (updatedQuantity <= Integer.valueOf(item.getMaxQuantity())) {
					item.setQuantity(String.valueOf(updatedQuantity));
				}
			}
		}
	}
	/**
	 * Calculate any totals
	 */
	public void calculateTotals() {
		// Calculate any taxes and fees
		BigDecimal subTotal = new BigDecimal("0.00");
		BigDecimal totalTax = new BigDecimal("0.00");
		BigDecimal totalshipping = new BigDecimal("0.00");
		BigDecimal total = new BigDecimal("0.00");
		for (Product item : this.cartItems) {
			// Do some clean up of the price
			String price = item.getPrice().replace("$", "");
			BigDecimal bigPrice = BigDecimal.valueOf(Double.valueOf(price));
			BigDecimal bprice = bigPrice.setScale(2, BigDecimal.ROUND_CEILING);
			subTotal = subTotal.add(bprice);
		}
		totalshipping = this.shippingTotal;
		//if the zip is out of state then we do not set sales tax
		if(this.destinationZip!=0){
			boolean inState=false;
			try {
				UspsServices service = new UspsServices();
				inState=service.validateInState(String.valueOf(this.destinationZip));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(!inState){
				totalTax=new BigDecimal("0.00");
			}
			else {
				totalTax = totalTax.add(subTotal.multiply(new BigDecimal("0.053")));
			}
		}
		 else {
			 totalTax = totalTax.add(subTotal.multiply(new BigDecimal("0.053")));
		}
		total = total.add(subTotal).add(totalTax).add(totalshipping);
		subTotal = subTotal.setScale(2, BigDecimal.ROUND_CEILING);
		totalTax = totalTax.setScale(2, BigDecimal.ROUND_CEILING);
		totalshipping = totalshipping.setScale(2, BigDecimal.ROUND_CEILING);
		total = total.setScale(2, BigDecimal.ROUND_CEILING);
		this.setShippingTotal(totalshipping);
		this.setTax(totalTax);
		this.setTotal(total);
		this.setSubTotal(subTotal);
	}
	/**
	 * Method will take existing cart and recalulate values for giving user's
	 * input base on helper utilites from usps services.
	 * 
	 * @param inputZip
	 * @param shippingMethod
	 */
	public void reCalculateShipping(int inputZip) {
		this.destinationZip = inputZip;
		//First we need to loop the cart and add the contents and weight
		calculateParcelDetails();
		//then we will request and set shipping rates
		this.rateObject=ShippingEngine.getShippingRates(parcelObject, String.valueOf(inputZip));
		//Grab the first value so we can have one value calculated.
		this.shippingTotal=new BigDecimal(this.rateObject.get(0).getAmount().toString());
		this.calculateTotals();
	}
	/**
	 * @return the uspsShippingEst
	 */
	public BigDecimal getUspsShippingEst() {
		return uspsShippingEst;
	}
	/**
	 * @param uspsShippingEst the uspsShippingEst to set
	 */
	//@XmlElement(name="uspsShippingEst")
	public void setUspsShippingEst(BigDecimal uspsShippingEst) {
		this.uspsShippingEst = uspsShippingEst;
	}
	/**
	 * @return the rateDetails
	 */
	public RateDetails getRateDetails() {
		return rateDetails;
	}
	/**
	 * @param rateDetails the rateDetails to set
	 */
	//@XmlElement(name="rateDetails")
	public void setRateDetails(RateDetails rateDetails) {
		this.rateDetails = rateDetails;
	}
}
