package soap;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class SoapTest {

	private static String uri = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx";

	private static String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
			+ "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">"
				+ "<soap12:Body>"
					+ "<getWeatherbyCityName xmlns=\"http://WebXml.com.cn/\">"
						+ "<theCityName>"
						+ "南京"
						+ "</theCityName>"
					+ "</getWeatherbyCityName>"
				+ "</soap12:Body>"
			+ "</soap12:Envelope>";

	public static void main(String[] args) throws Exception {
		URL url = URI.create(uri).toURL();
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/soap+xml; charset=utf-8");

		// 发送数据
		OutputStream outputStream = connection.getOutputStream();
		outputStream.write(xml.getBytes());

		// 接收数据
		InputStream inputStream = connection.getInputStream();
		StringBuffer result = new StringBuffer();
		byte[] bytes = new byte[1024];
		int length = 0;
		while(length != -1) {
			length = inputStream.read(bytes, 0, 1024);
			result.append(new String(bytes));
		}
		System.out.println(result);
	}

}
