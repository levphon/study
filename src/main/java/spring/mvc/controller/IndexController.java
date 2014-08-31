package spring.mvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import rest.resteasy.shop.domain.Customer_xml;

/**
 * @see AbstractMessageConverterMethodProcessor.writeWithMessageConverters
 * @author Hebaceous
 */
@Controller
@RequestMapping("/")
public class IndexController {

	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "index";
	}

	@ResponseBody
	@RequestMapping(value = "/json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
	public Customer_xml json() {
		return new Customer_xml();
	}

	@ResponseBody
	@RequestMapping(value = "/jsons", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
	public List<Customer_xml> jsons() {
		List<Customer_xml> customers = new ArrayList<Customer_xml>();
		customers.add(new Customer_xml());
		customers.add(new Customer_xml());
		return customers;
	}

	@ResponseBody
	@RequestMapping(value = "/xml", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
	public Customer_xml xml() {
		return new Customer_xml();
	}

	/**
	 * JAXB2 not supports collection
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/xmls", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
	public List<Customer_xml> xmls() {
		List<Customer_xml> customers = new ArrayList<Customer_xml>();
		customers.add(new Customer_xml());
		customers.add(new Customer_xml());
		return customers;
	}

}
