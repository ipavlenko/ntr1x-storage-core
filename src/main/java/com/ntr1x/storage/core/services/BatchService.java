package com.ntr1x.storage.core.services;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import com.ntr1x.storage.core.services.IBatchService.Job.Call;

@Service
public class BatchService implements IBatchService {

	@Inject
	private ISerializationService serialization;
	
	@Override
	@Transactional
	public void execute(Context context, Call[] calls) {
		
		for (Call call : calls) {
			context.handle(call);
		}
	}
	
	@Override
	@Transactional
	public void execute(Context context, Job job) {
		execute(context, job.calls);
	}

	@Override
	@Transactional
	public void execute(Context context, URL url) {
		
		try (InputStream input = url.openStream()) {
			
			StringWriter writer = new StringWriter();
			IOUtils.copy(input, writer, "UTF-8");
			
			execute(context, writer.toString());
			
		} catch (IOException e) {
			
			throw new IllegalArgumentException(e);
		}
	}
	
	@Override
	@Transactional
	public void execute(Context context, String script) {
		
		Job job = serialization.parseJSONStringJackson(Job.class, script);
		execute(context, job);
	}
}
