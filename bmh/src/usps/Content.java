package usps;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="Content")
@XmlType(propOrder = {"contentType","contentDescription"})
public class Content {
	private String contentType;
	private String contentDescription;
	public Content(){
		
	}
	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}
	/**
	 * @param contentType the contentType to set
	 */
	@XmlElement(name="ContentType")
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	/**
	 * @return the contentDescription
	 */
	public String getContentDescription() {
		return contentDescription;
	}
	/**
	 * @param contentDescription the contentDescription to set
	 */
	@XmlElement(name="ContentDescription")
	public void setContentDescription(String contentDescription) {
		this.contentDescription = contentDescription;
	}
}
