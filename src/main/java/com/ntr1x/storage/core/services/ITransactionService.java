package com.ntr1x.storage.core.services;

import java.util.concurrent.Callable;

public interface ITransactionService {

	<T> T execute(Callable<T> callable);
	void execute(Runnable runnable);
}
