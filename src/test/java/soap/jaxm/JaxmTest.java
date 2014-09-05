package soap.jaxm;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.messaging.URLEndpoint;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import soap.jaxb.GetWeatherbyCityName;
import soap.jaxb.GetWeatherbyCityNameResponse;

public class JaxmTest {

	private static String uri = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx";

	public static void main(String[] args) throws Exception {
		SOAPConnectionFactory factory = SOAPConnectionFactory.newInstance();
		SOAPConnection connection = factory.createConnection();

		SOAPMessage requestMessage = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL).createMessage();
		Document requestDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		Marshaller marshaller = JAXBContext.newInstance(GetWeatherbyCityName.class).createMarshaller();
		marshaller.marshal(GetWeatherbyCityName.create().theCityName("南京"), requestDocument);
		requestMessage.getSOAPBody().addDocument(requestDocument);
		SOAPEnvelope soapEnvelope = requestMessage.getSOAPPart().getEnvelope();
		soapEnvelope.removeNamespaceDeclaration("env");
		soapEnvelope.addNamespaceDeclaration("soap12", "http://www.w3.org/2003/05/soap-envelope");
		soapEnvelope.addNamespaceDeclaration("xsi", "http://www.w3.org/2001/XMLSchema-instance");
		soapEnvelope.addNamespaceDeclaration("xsd", "http://www.w3.org/2001/XMLSchema");
		soapEnvelope.setPrefix("soap12");
		soapEnvelope.removeChild(soapEnvelope.getHeader());
		requestMessage.getSOAPBody().setPrefix("soap12");
		System.out.println("REQUEST:");
		requestMessage.writeTo(System.out);

		URLEndpoint endpoint = new URLEndpoint(uri);

		SOAPMessage responseMessage = connection.call(requestMessage, endpoint);
		System.out.println("\nRESPONSE:");
		responseMessage.writeTo(System.out);

		Unmarshaller unmarshaller = JAXBContext.newInstance(GetWeatherbyCityNameResponse.class).createUnmarshaller();
		JAXBElement<GetWeatherbyCityNameResponse> jaxbElement = unmarshaller.unmarshal(responseMessage.getSOAPBody().extractContentAsDocument(), GetWeatherbyCityNameResponse.class);
		GetWeatherbyCityNameResponse response = jaxbElement.getValue();
		System.out.println("\nUnmarshaller:");
		System.out.println(response.getStrings());

//		TransformerFactory transformerFactory = TransformerFactory.newInstance();
//		Transformer transformer = transformerFactory.newTransformer();
//		Source sourceContent = responseMessage.getSOAPPart().getContent();
//		System.out.println("\nTransformer:");
//		transformer.transform(sourceContent, new StreamResult(System.out));

		connection.close();
	}

}
