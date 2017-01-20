package com.ntr1x.storage.core.services;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Scope("singleton")
@Slf4j
public class AsyncService implements IAsyncService {

	private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
	
	@Override
	public <V> ScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit unit) {
		
		return executor.schedule(() -> {
			
			try {
				return callable.call();
			} catch (Exception e) {
				log.warn("Exception in the async task", e);
				throw e;
			}
			
		}, delay, unit);
	}
	
	@Override
	public ScheduledFuture<?> schedule(Runnable runnable, long delay, TimeUnit unit) {
		
		return executor.schedule(() -> {
			
			try {
				runnable.run();
			} catch (Exception e) {
				log.warn("Exception in the async task", e);
				throw e;
			}
		}, delay, unit);
	}
	
	@Override
	public <V> Future<V> submit(Callable<V> callable) {
		
		return executor.submit(() -> {
			
			try {
				return callable.call();
			} catch (Exception e) {
				log.warn("Exception in the async task", e);
				throw e;
			}
		});
	}
	
	@Override
	public Future<?> submit(Runnable runnable) {
		
		return executor.submit(() -> {
			
			try {
				runnable.run();
			} catch (Exception e) {
				log.warn("Exception in the async task", e);
				throw e;
			}
		});
	}
}
