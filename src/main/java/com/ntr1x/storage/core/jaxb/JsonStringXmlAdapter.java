package com.ntr1x.storage.core.jaxb;

import java.io.StringWriter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.xml.transform.StringSource;
import org.w3c.dom.Node;

public class JsonStringXmlAdapter extends XmlAdapter<Object, String> {

	@Override
	public String unmarshal(Object v) throws Exception {
		
		if (v instanceof Node) {
			
//			((Node)v).getTextContent()
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			StreamResult result = new StreamResult(new StringWriter());
			DOMSource source = new DOMSource((Node) v);
			transformer.transform(source, result);
			System.out.println(result.getWriter().toString());
			return result.getWriter().toString();
		}
		
		return null;
	}

	@Override	
	public Object marshal(String v) throws Exception {
		
		if (v != null) {
			
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			StringSource source = new StringSource(v);
			DOMResult result = new DOMResult();
			transformer.transform(source, result);
			return result.getNode();
		}
		
		return v;
	}
}
