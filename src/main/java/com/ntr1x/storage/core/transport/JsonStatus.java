package com.ntr1x.storage.core.transport;

import javax.ws.rs.core.Response.Status;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@XmlRootElement
@NoArgsConstructor
@AllArgsConstructor
public class JsonStatus {
    
    public int code;
    public Status status;
    public Status.Family family;
    public String reason;
    public String message;
}