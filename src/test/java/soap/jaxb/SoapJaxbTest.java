package soap.jaxb;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;

import org.w3c.dom.Document;

public class SoapJaxbTest {

	private static String uri = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx";

	public static void main(String[] args) throws Exception {
		URL url = URI.create(uri).toURL();
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/soap+xml; charset=utf-8");

		// 发送数据
		OutputStream outputStream = connection.getOutputStream();

		Document requestDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		Marshaller marshaller = JAXBContext.newInstance(GetWeatherbyCityName.class).createMarshaller();
		marshaller.marshal(GetWeatherbyCityName.create().theCityName("南京"), requestDocument);
		SOAPMessage requestSOAPMessage = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL).createMessage();
		SOAPBody soapBody = requestSOAPMessage.getSOAPBody();
		soapBody.addDocument(requestDocument);
		SOAPEnvelope soapEnvelope = requestSOAPMessage.getSOAPPart().getEnvelope();
		soapEnvelope.removeNamespaceDeclaration("env");
		soapEnvelope.addNamespaceDeclaration("soap12", "http://www.w3.org/2003/05/soap-envelope");
		soapEnvelope.addNamespaceDeclaration("xsi", "http://www.w3.org/2001/XMLSchema-instance");
		soapEnvelope.addNamespaceDeclaration("xsd", "http://www.w3.org/2001/XMLSchema");
		soapEnvelope.setPrefix("soap12");
		soapEnvelope.removeChild(soapEnvelope.getHeader());
		soapBody.setPrefix("soap12");
		requestSOAPMessage.writeTo(outputStream);

		// 接收数据
		InputStream inputStream = connection.getInputStream();

		SOAPMessage responseSOAPMessage = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL).createMessage(null, inputStream);
//		responseSOAPMessage.writeTo(System.out);
		Unmarshaller unmarshaller = JAXBContext.newInstance(GetWeatherbyCityNameResponse.class).createUnmarshaller();
		JAXBElement<GetWeatherbyCityNameResponse> jaxbElement = unmarshaller.unmarshal(responseSOAPMessage.getSOAPBody().extractContentAsDocument(), GetWeatherbyCityNameResponse.class);
		GetWeatherbyCityNameResponse response = jaxbElement.getValue();
		System.out.println(response.getStrings());

		outputStream.close();
		inputStream.close();
		connection.disconnect();
	}

}
