package com.ntr1x.storage.core.services;

import java.util.concurrent.Callable;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
public class TransactionService implements ITransactionService {

	@Override
	@Transactional
	public <T> T execute(Callable<T> callable) {
		try {
			return callable.call();
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	@Transactional
	public void execute(Runnable runnable) {
		try {
			runnable.run();
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
}
