/**
 * 
 */
package shippo;

/**
 * @author madal
 *
 */
public class RateDetails {
	private String rateObjectId;
	private String amount;
	private String currency;
	private String provider;
	private String providerImage;
	private String serviceName;
	private String days;
	private String durationTerms;
	private String trackable;
	private String insurance;
	private String insuranceAmount;
	private String messages;
	public RateDetails(){
		
	}
	/**
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}
	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}
	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	/**
	 * @return the provider
	 */
	public String getProvider() {
		return provider;
	}
	/**
	 * @param provider the provider to set
	 */
	public void setProvider(String provider) {
		this.provider = provider;
	}
	/**
	 * @return the providerImage
	 */
	public String getProviderImage() {
		return providerImage;
	}
	/**
	 * @param providerImage the providerImage to set
	 */
	public void setProviderImage(String providerImage) {
		this.providerImage = providerImage;
	}
	/**
	 * @return the serviceName
	 */
	public String getServiceName() {
		return serviceName;
	}
	/**
	 * @param serviceName the serviceName to set
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	/**
	 * @return the days
	 */
	public String getDays() {
		return days;
	}
	/**
	 * @param days the days to set
	 */
	public void setDays(String days) {
		this.days = days;
	}
	/**
	 * @return the durationTerms
	 */
	public String getDurationTerms() {
		return durationTerms;
	}
	/**
	 * @param durationTerms the durationTerms to set
	 */
	public void setDurationTerms(String durationTerms) {
		this.durationTerms = durationTerms;
	}
	/**
	 * @return the trackable
	 */
	public String getTrackable() {
		return trackable;
	}
	/**
	 * @param trackable the trackable to set
	 */
	public void setTrackable(String trackable) {
		this.trackable = trackable;
	}
	/**
	 * @return the insurance
	 */
	public String getInsurance() {
		return insurance;
	}
	/**
	 * @param insurance the insurance to set
	 */
	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}
	/**
	 * @return the insuranceAmount
	 */
	public String getInsuranceAmount() {
		return insuranceAmount;
	}
	/**
	 * @param insuranceAmount the insuranceAmount to set
	 */
	public void setInsuranceAmount(String insuranceAmount) {
		this.insuranceAmount = insuranceAmount;
	}
	/**
	 * @return the messages
	 */
	public String getMessages() {
		return messages;
	}
	/**
	 * @param messages the messages to set
	 */
	public void setMessages(String messages) {
		this.messages = messages;
	}
	/**
	 * @return the rateObjectId
	 */
	public String getRateObjectId() {
		return rateObjectId;
	}
	/**
	 * @param rateObjectId the rateObjectId to set
	 */
	//@XmlElement(name="rateObjectId")
	public void setRateObjectId(String rateObjectId) {
		this.rateObjectId = rateObjectId;
	}
}
