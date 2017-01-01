package com.ntr1x.storage.core.jaxb;

import java.io.StringWriter;

import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.annotation.DomHandler;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;

public class JsonStringParameterHandler implements DomHandler<String, StreamResult> {

	private StringWriter xmlWriter = new StringWriter();
	
	@Override
	public StreamResult createUnmarshaller(ValidationEventHandler errorHandler) {
		return new StreamResult(xmlWriter);
	}

	@Override
	public String getElement(StreamResult rt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Source marshal(String n, ValidationEventHandler errorHandler) {
		// TODO Auto-generated method stub
		return null;
	}

//    private static final String PARAMETERS_START_TAG = "<parameters>";
//    private static final String PARAMETERS_END_TAG = "</parameters>";
//    private StringWriter xmlWriter = new StringWriter(); 
//
//    public StreamResult createUnmarshaller(ValidationEventHandler errorHandler) {
//        return new StreamResult(xmlWriter);
//    }
//
//    public String getElement(StreamResult rt) {
//        String xml = rt.getWriter().toString();
//        int beginIndex = xml.indexOf(PARAMETERS_START_TAG) + PARAMETERS_START_TAG.length();
//        int endIndex = xml.indexOf(PARAMETERS_END_TAG);
//        return xml.substring(beginIndex, endIndex);
//    }
//
//    public Source marshal(String n, ValidationEventHandler errorHandler) {
//        try {
//            String xml = PARAMETERS_START_TAG + n.trim() + PARAMETERS_END_TAG;
//            StringReader xmlReader = new StringReader(xml);
//            return new StreamSource(xmlReader);
//        } catch(Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

}