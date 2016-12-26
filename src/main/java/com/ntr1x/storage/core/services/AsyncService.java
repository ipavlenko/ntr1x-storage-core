package com.ntr1x.storage.core.services;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class AsyncService implements IAsyncService {

	private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
	
	@Override
	public <V> ScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit unit) {
		return executor.schedule(callable, delay, unit);
	}
	
	@Override
	public ScheduledFuture<?> schedule(Runnable runnable, long delay, TimeUnit unit) {
		return executor.schedule(runnable, delay, unit);
	}
	
	@Override
	public <V> Future<V> submit(Callable<V> callable) {
		return executor.submit(callable);
	}
	
	@Override
	public Future<?> submit(Runnable runnable) {
		return executor.submit(runnable);
	}
}
