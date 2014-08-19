package soap.jaxb;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLFilterImpl;

public class NamespacePrefixFilter extends XMLFilterImpl {

	public NamespacePrefixFilter(XMLReader parent) {
		super(parent);
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes atts) throws SAXException {
		super.startElement(uri, localName, qName.contains(":") ? qName.split(":")[1] : qName, atts);
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		super.endElement(uri, localName, qName.contains(":") ? qName.split(":")[1] : qName);
	}

	public static void main(String[] args) {
//		XMLFilter xmlFilter = new NamespacePrefixFilter(SAXParserFactory.newInstance().newSAXParser().getXMLReader());
//		UnmarshallerHandler unmarshallerHandler = JAXBContext.newInstance(clazz).createUnmarshaller().getUnmarshallerHandler();
//		xmlFilter.setContentHandler(unmarshallerHandler);
//		xmlFilter.parse(inputSource);
//		unmarshallerHandler.getResult();
	}

}
