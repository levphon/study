package soap.jaxb;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GetWeatherbyCityName {

	@XmlElement
	private String theCityName;

	private GetWeatherbyCityName() {
	}

	public static GetWeatherbyCityName create() {
		return new GetWeatherbyCityName();
	}

	public GetWeatherbyCityName theCityName(String theCityName) {
		this.theCityName = theCityName;
		return this;
	}

}
