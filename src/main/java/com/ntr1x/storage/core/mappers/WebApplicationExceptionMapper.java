package com.ntr1x.storage.core.mappers;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.StatusType;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

import com.ntr1x.storage.core.transport.JsonStatus;

@Provider
@Component
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {
	
	private static final Logger LOG = Logger.getLogger(WebApplicationExceptionMapper.class.getName());
	
	@Override
	public Response toResponse(WebApplicationException exception) {
		
		LOG.log(Level.INFO, exception.getMessage(), exception);
		
		StatusType info = exception.getResponse().getStatusInfo();
		JsonStatus status = new JsonStatus(
			info.getStatusCode(),
			Response.Status.fromStatusCode(info.getStatusCode()),
			info.getFamily(),
			info.getReasonPhrase(),
			exception.getMessage()
		);
		
		return Response.status(status.status)
			.entity(status)
			.type(MediaType.APPLICATION_JSON_TYPE)
			.build()
	    ;
	}
}