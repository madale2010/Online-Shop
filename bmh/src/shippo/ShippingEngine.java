/**
 * 
 */
package shippo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shippo.Shippo;
import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;
import com.shippo.model.Address;
import com.shippo.model.Parcel;
import com.shippo.model.Rate;
import com.shippo.model.Shipment;

import product.Product;
import product.ProductHelper;
import product.ProductShippingDetails;
import utils.CustomException;
import utils.Constants.Mail;

/**
 * @author madal Private key 9f72a28d538d2d168bf1eb3ab7b495ef27efeffc Public key
 *         0c1efbb67ed462ba84a92ceb020cbc04c4ef72fa
 */
public class ShippingEngine {
 

	public ShippingEngine(){
		Shippo.setApiKey("9f72a28d538d2d168bf1eb3ab7b495ef27efeffc");
	}
		
	/**
	 * @param productId
	 * @param inputZip
	 * @return
	 */
	public static ArrayList<RateDetails> getShippingRates(String productId, String inputZip) {
		List<Rate> rateList = null;
		Shipment shippingObject = new Shipment();
		shippingObject.setAddressFrom(Mail.getReturnAddress());
		Address toAdd = new Address();
		toAdd.setZip(inputZip);
		toAdd.setCountry("US");
		shippingObject.setAddressTo(toAdd);
		shippingObject.setObject_purpose("QUOTE");
		Product product = ProductHelper.loadSingleProduct(productId);
		ProductShippingDetails shipDetails=product.getShipDetails();
		shippingObject.setParcel(shipDetails.getParcelObject());
		try {
			shippingObject=createShippingInstance(shippingObject);
			rateList =shippingObject.getRatesList();
		} catch (AuthenticationException | InvalidRequestException | APIConnectionException | APIException e) {
			CustomException.processError(e);
		}
		ArrayList<RateDetails> serviceList = new ArrayList<RateDetails>();
		Collections.reverse(rateList);

		for(Rate rate: rateList){
			//Because we do not provide overnight shipping remove it from the list
			if(!rate.getServicelevelName().toString().equalsIgnoreCase("Priority Mail Express")){
			RateDetails detailRate=new RateDetails();
			detailRate.setAmount(rate.getAmount().toString());
			detailRate.setCurrency(rate.getCurrency().toString());
			detailRate.setDays(rate.getDays().toString());
			detailRate.setDurationTerms(rate.getDuration_terms().toString());
			detailRate.setInsurance(rate.getInsurance().toString());
			detailRate.setInsuranceAmount(rate.getInsuranceAmount().toString());
			detailRate.setProvider(rate.getProvider().toString());
			detailRate.setProviderImage(rate.getProviderImage75().toString());
			detailRate.setMessages(rate.getMessages().toString());
			detailRate.setServiceName(rate.getServicelevelName().toString());
			detailRate.setTrackable(rate.getTrackable().toString());
			
			serviceList.add(detailRate);
			}
		}
		return serviceList;
	}	
	/**
	 * Method will calculate a configured shipment object and get the rates,
	 * based on zip code.
	 * @param productId
	 * @param inputZip
	 * @return
	 */
	public static ArrayList<RateDetails> getShippingRates(Parcel item, String inputZip) {
		List<Rate> rateList = null;
		Shipment shippingObject = new Shipment();
		shippingObject.setAddressFrom(Mail.getReturnAddress());
		Address toAdd = new Address();
		toAdd.setZip(inputZip);
		toAdd.setCountry("US");
		shippingObject.setAddressTo(toAdd);
		shippingObject.setObject_purpose("QUOTE");
		shippingObject.setParcel(item);
		try {
			shippingObject=createShippingInstance(shippingObject);
			rateList =shippingObject.getRatesList();
		} catch (AuthenticationException | InvalidRequestException | APIConnectionException | APIException e) {
			CustomException.processError(e);
		}
		ArrayList<RateDetails> serviceList = new ArrayList<RateDetails>();
		Collections.reverse(rateList);

		for(Rate rate: rateList){
			//Because we do not provide overnight shipping remove it from the list
			if(!rate.getServicelevelName().toString().equalsIgnoreCase("Priority Mail Express")){
			RateDetails detailRate=new RateDetails();
			detailRate.setAmount(rate.getAmount().toString());
			detailRate.setCurrency(rate.getCurrency().toString());
			detailRate.setDays(rate.getDays().toString());
			detailRate.setDurationTerms(rate.getDuration_terms().toString());
			detailRate.setInsurance(rate.getInsurance().toString());
			detailRate.setInsuranceAmount(rate.getInsuranceAmount().toString());
			detailRate.setProvider(rate.getProvider().toString());
			detailRate.setProviderImage(rate.getProviderImage75().toString());
			detailRate.setMessages(rate.getMessages().toString());
			detailRate.setServiceName(rate.getServicelevelName().toString());
			detailRate.setTrackable(rate.getTrackable().toString());
			detailRate.setRateObjectId(rate.getObjectId().toString());
			serviceList.add(detailRate);
			}
		}
		return serviceList;
	}

	/**
	 * @param shippingObject
	 * @return
	 * @throws APIException 
	 * @throws APIConnectionException 
	 * @throws InvalidRequestException 
	 * @throws AuthenticationException 
	 */
	private static Shipment createShippingInstance(Shipment shippingObject) throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
		//Convert back to stupid map list from neat objects
		
		Shippo.setApiKey("9f72a28d538d2d168bf1eb3ab7b495ef27efeffc");
	
		// Optional defaults to false
		//Shippo.setDEBUG(true);
		Address toAdd = (Address) shippingObject.getAddressTo();
		Address fromAdd =(Address) shippingObject.getAddressFrom();
		// to address
		Map<String, Object> toAddressMap = new HashMap<String, Object>();
		toAddressMap.put("object_purpose", toAdd.getObjectPurpose());
		toAddressMap.put("name", toAdd.getName());
		toAddressMap.put("company",toAdd.getCompany());
		toAddressMap.put("street1", toAdd.getStreet1());
		toAddressMap.put("city", toAdd.getCity());
		toAddressMap.put("state",toAdd.getState());
		toAddressMap.put("zip", toAdd.getZip());
		toAddressMap.put("country", toAdd.getCountry());
		toAddressMap.put("phone", toAdd.getPhone());
		toAddressMap.put("email", toAdd.getEmail());
	
		// from address
		Map<String, Object> fromAddressMap = new HashMap<String, Object>();
		fromAddressMap.put("object_purpose", fromAdd.getObjectPurpose());
		fromAddressMap.put("name", fromAdd.getName());
		fromAddressMap.put("company", fromAdd.getCompany());
		fromAddressMap.put("street1", fromAdd.getStreet1());
		fromAddressMap.put("city", fromAdd.getCity());
		fromAddressMap.put("state", fromAdd.getState());
		fromAddressMap.put("zip", fromAdd.getZip());
		fromAddressMap.put("country", fromAdd.getCountry());
		fromAddressMap.put("email", fromAdd.getEmail());
		fromAddressMap.put("phone",fromAdd.getPhone());
		fromAddressMap.put("metadata", fromAdd.getMetadata());
	
		// parcel
		Parcel parcel = (Parcel) shippingObject.getParcel();
		Map<String, Object> parcelMap = new HashMap<String, Object>();
		parcelMap.put("length", parcel.getLength());
		parcelMap.put("width", parcel.getWidth());
		parcelMap.put("height", parcel.getHeight());
		parcelMap.put("distance_unit", parcel.getDistanceUnit());
		parcelMap.put("weight",parcel.getWeight());
		parcelMap.put("mass_unit", parcel.getMassUnit());
	
		Map<String, Object> shipmentMap = new HashMap<String, Object>();
		shipmentMap.put("address_to", toAddressMap);
		shipmentMap.put("address_from", fromAddressMap);
		shipmentMap.put("parcel", parcelMap);
		shipmentMap.put("object_purpose", shippingObject.getObject_purpose());
		shipmentMap.put("async", false);

		return Shipment.create(shipmentMap);
	}
}
