package com.ntr1x.storage.core.transport;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.domain.Page;

import lombok.AllArgsConstructor;

@XmlRootElement
@AllArgsConstructor
public class PageResponse<T> {
	
	public long count;
    public int page;
    public int size;

    @XmlElement
    public List<T> content;
    
    public PageResponse(Page<T> p) {
    	
    	this.content = p.getContent();
    	this.count = p.getTotalElements();
    	this.page = p.getNumber();
    	this.size = p.getSize();
    }
}
