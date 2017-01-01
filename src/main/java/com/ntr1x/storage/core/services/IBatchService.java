package com.ntr1x.storage.core.services;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.ntr1x.storage.core.services.IBatchService.Job.Call;

public interface IBatchService {
	
	void execute(Context context, Job job);
	void execute(Context context, Call[] calls);
	void execute(Context context, String script);
	void execute(Context context, URL url);
	
	public class Job {
		
		public Call[] calls;
		
		public static class Call {
			
			public String action;
			public ArrayNode args;
	    }
	}
	
	public class Context {
		
		private Map<String, Handler> handlers = new HashMap<>();
		
		public Context on(String name, Handler handler) {
			this.handlers.put(name, handler);
			return this;
		}
		
		@FunctionalInterface
		public interface Handler {
			void handle(ArrayNode args);
		}

		public void handle(Call call) {
			this.handlers.get(call.action).handle(call.args);
		}
	}
}
