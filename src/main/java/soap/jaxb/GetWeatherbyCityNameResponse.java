package soap.jaxb;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GetWeatherbyCityNameResponse {

	@XmlElementWrapper(name = "getWeatherbyCityNameResult")
	@XmlElement(name = "string")
	private List<String> strings = new ArrayList<String>();

	public List<String> getStrings() {
		return strings;
	}

}
