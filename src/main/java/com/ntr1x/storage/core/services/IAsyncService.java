package com.ntr1x.storage.core.services;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public interface IAsyncService {

	<V> ScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit unit);
	ScheduledFuture<?> schedule(Runnable runnable, long delay, TimeUnit unit);
	<V> Future<V> submit(Callable<V> callable);
	Future<?> submit(Runnable runnable);	
}
