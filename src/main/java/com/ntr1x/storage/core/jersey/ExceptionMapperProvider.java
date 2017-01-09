package com.ntr1x.storage.core.jersey;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.StatusType;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

import com.ntr1x.storage.core.transport.JsonStatus;

@Provider
@Component
public class ExceptionMapperProvider implements ExceptionMapper<Exception> {
	
	private static final Logger LOG = Logger.getLogger(ExceptionMapperProvider.class.getName());
	
	@Override
	public Response toResponse(Exception exception) {
		
		LOG.log(Level.INFO, exception.getMessage(), exception);
		
		StatusType info = exception instanceof WebApplicationException
			? ((WebApplicationException) exception).getResponse().getStatusInfo()
			: Status.INTERNAL_SERVER_ERROR
		;
			
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