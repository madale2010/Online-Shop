package management;

import java.util.ArrayList;

import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.RelatedResources;
import com.paypal.api.payments.Sale;
import com.paypal.api.payments.ShippingAddress;
import com.paypal.api.payments.Transaction;

import financial.Customer;
import financial.InventoryHelper;
import financial.Order;
import product.Product;
import product.ProductHelper;
import shippo.RateDetails;
import utils.Alert;
import utils.Cart;
import utils.FileHelper;

public class InventoryHandler {
	private Cart customerCart;
	private Order order;
	private Sale sale;
	private ShippingAddress addressInfo;
	private PayerInfo payerInfo;
	private Payment payment;
	private RelatedResources resource;
	private Transaction transaction;
	private ItemList itemsList;
	private RateDetails rateDetails;
	
	public InventoryHandler(Payment pay, Sale saleObject){
		setPayment(pay);
		setTransaction(pay.getTransactions().get(0));
		setItemsList(transaction.getItemList());
		setResources(!transaction.getRelatedResources().isEmpty() ? transaction.getRelatedResources().get(0): null);
		setSale(saleObject);
		setPayerInfo(payment.getPayer().getPayerInfo());
		setAddressInfo(payerInfo.getShippingAddress());
		setCustomerCart(InventoryHelper.rebuildShoppingCart(itemsList));
		customerCart.setNumCartItems(customerCart.getCartItems().size());
	}
	public void updateProductQuantity(){
		ArrayList<Product> productList=this.customerCart.getCartItems();
		
		for(Product item:productList){
			String productId=item.getPid();
			Product product=ProductHelper.loadSingleProduct(productId);
			int orderedQuantity=Integer.valueOf(item.getQuantity());
			int quantity=Integer.valueOf(product.getQuantity());
			int remainingQuantity=quantity-orderedQuantity;
			if(remainingQuantity>0){
				product.setQuantity(String.valueOf(remainingQuantity));
				ProductHelper.updateProduct(product);
			}
			else if(remainingQuantity==0){
				//Generate an alert for restock and remove items from being ordered.
				Alert alert = new Alert("SOLDOUT","You have sold out of item "+productId,"SEVERE");
				product.setQuantity(String.valueOf(remainingQuantity));
				product.setProductAvaliable("NO");
				ProductHelper.updateProduct(product);
				alert.setAlertLink(productId);
				alert.addNewAlert();
			}
			else if(remainingQuantity<0){
				Alert alert = new Alert("OVERSOLD","Product "+productId+" has managed to sell more than listed causing back order.","ERROR");
				alert.setAlertLink(productId);
				alert.addNewAlert();
			}
		}
	}
	public Cart getCustomerCart() {
		return customerCart;
	}
	public void setCustomerCart(Cart customerCart) {
		this.customerCart = customerCart;
	}
	public void generateOrder() {
		payerInfo=this.getPayerInfo();
		customerCart=this.getCustomerCart();
		sale=this.getSale();
		addressInfo=this.getAddressInfo();
		Order setOrder = new Order();
		setOrder.setPaymentMode(sale.getPaymentMode());
		setOrder.setProtectionEligibility(sale.getProtectionEligibility());
		setOrder.setProtectionEligibilityType(sale.getProtectionEligibilityType());
		setOrder.setPaymentStatus(sale.getState());
		setOrder.setBuyerName(payerInfo.getPayerId());
		setOrder.setFullName(payerInfo.getFirstName()+" "+payerInfo.getMiddleName()+" "+payerInfo.getLastName());
		setOrder.setFirstName(payerInfo.getFirstName());
		setOrder.setLastName(payerInfo.getLastName());
		setOrder.setTotalItems(customerCart.getNumCartItems());
		setOrder.setOrderDate(FileHelper.dateStringToTimestamp(sale.getCreateTime()));
		setOrder.setOrderId(sale.getId());
		setOrder.setAmount(sale.getAmount());
		setOrder.setFmfDetails(sale.getFmfDetails());
		setOrder.setOrderTotal(sale.getAmount().getTotal());
		setOrder.setTransactionFee(sale.getTransactionFee().getValue());
		setOrder.setReasonCode(sale.getReasonCode());
		setOrder.setShippingCost(sale.getAmount().getDetails().getShipping());
		setOrder.setSalesTax(sale.getAmount().getDetails().getTax());
		setOrder.setSubTotal(sale.getAmount().getDetails().getSubtotal());
		setOrder.setShipCity(addressInfo.getCity());
		setOrder.setShipCountry(addressInfo.getCountryCode());
		setOrder.setShipState(addressInfo.getState());
		setOrder.setShipZipCode(addressInfo.getPostalCode());
		setOrder.setShipStreet1(addressInfo.getLine1());
		setOrder.setShipStreet2(addressInfo.getLine2());
		setOrder.setPhone(payerInfo.getPhone());
		setOrder.setOrderStatus("created");
		setOrder.setEmail(payerInfo.getEmail());
		setOrder.setRateDetails(this.getRateDetails());
		setOrder(setOrder);
		//Also we need to create a new alert and email
		Alert alert = new Alert("NEWORDER","Order "+order.getOrderId()+" has been created and waiting for action.","ACTION");
		alert.setAlertLink(order.getOrderId());
		alert.addNewAlertWithEmail();
	}
	/**
	 * @return the order
	 */
	public Order getOrder() {
		return order;
	}
	/**
	 * @param order the order to set
	 */
	public void setOrder(Order order) {
		this.order = order;
	}
	/**
	 * @return the sale
	 */
	public Sale getSale() {
		return sale;
	}
	/**
	 * @param sale the sale to set
	 */
	public void setSale(Sale sale) {
		this.sale = sale;
	}
	/**
	 * Will take the existing customer information and update it with
	 * any sale and order information generated after payment has been
	 * sent back from paypal.
	 * 
	 * @param customer
	 * @return customer
	 */
	public Customer updateCustomer(Customer customer) {
		customer.setAddressInfo(addressInfo);
		customer.setCart(customerCart);
		customer.setFirstName(payerInfo.getFirstName());
		customer.setMiddleName(payerInfo.getMiddleName());
		customer.setLastName(payerInfo.getLastName());
		customer.setEmail(payerInfo.getEmail());
		customer.setFullName(order.getFullName());
		customer.setInvoiceNumber(order.getOrderId());
		customer.setOrderAmount(order.getAmount().getTotal());
		customer.setOrderDate(order.getOrderDate().toString());
		customer.setPayerId(payerInfo.getPayerId());
		customer.setAddressInfo(addressInfo);
		customer.setShipToCity(addressInfo.getCity());
		customer.setShipToStreet(addressInfo.getLine1());
		customer.setShipToStreet2(addressInfo.getLine2());
		customer.setShipToState(addressInfo.getState());
		customer.setShipToZip(addressInfo.getPostalCode());
		customer.setShipToName(addressInfo.getRecipientName());
		customer.setSaleId(sale.getId());
		customer.setPhoneNumber(addressInfo.getPhone());
		customer.setCart(customerCart);
		customer.setSale(sale);
		customer.setOrder(order);	
		return customer;
	}
	/**
	 * @return the addressInfo
	 */
	public ShippingAddress getAddressInfo() {
		return addressInfo;
	}
	/**
	 * @param addressInfo the addressInfo to set
	 */
	public void setAddressInfo(ShippingAddress addressInfo) {
		this.addressInfo = addressInfo;
	}
	/**
	 * @return the payerInfo
	 */
	public PayerInfo getPayerInfo() {
		return payerInfo;
	}
	/**
	 * @param payerInfo the payerInfo to set
	 */
	public void setPayerInfo(PayerInfo payerInfo) {
		this.payerInfo = payerInfo;
	}
	/**
	 * @return the payment
	 */
	public Payment getPayment() {
		return payment;
	}
	/**
	 * @param payment the payment to set
	 */
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	/**
	 * @return the resources
	 */
	public RelatedResources getResources() {
		return resource;
	}
	/**
	 * @param resources the resources to set
	 */
	public void setResources(RelatedResources resources) {
		this.resource = resources;
	}
	/**
	 * @return the transaction
	 */
	public Transaction getTransaction() {
		return transaction;
	}
	/**
	 * @param transaction the transaction to set
	 */
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	/**
	 * @return the itemsList
	 */
	public ItemList getItemsList() {
		return itemsList;
	}
	/**
	 * @param itemsList the itemsList to set
	 */
	public void setItemsList(ItemList itemsList) {
		this.itemsList = itemsList;
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
