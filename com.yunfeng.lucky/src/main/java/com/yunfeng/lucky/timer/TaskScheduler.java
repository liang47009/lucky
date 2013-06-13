package com.yunfeng.lucky.timer;

import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Component;

import com.yunfeng.util.Log;

@Component
public class TaskScheduler {
	private static AtomicInteger schedulerId = new AtomicInteger(0);
	private final ScheduledThreadPoolExecutor taskScheduler;
	private final String serviceName;

	public TaskScheduler() {
		this(1);
	}

	public TaskScheduler(int threadPoolSize) {
		this.serviceName = ("TaskScheduler-" + schedulerId.getAndIncrement());
		this.taskScheduler = new ScheduledThreadPoolExecutor(threadPoolSize);
	}

	public void init(Object o) {

	}

	public void destroy(Object o) {
		List<Runnable> awaitingExecution = this.taskScheduler.shutdownNow();
		Log.info(this.serviceName + " stopping. Tasks awaiting execution: "
				+ awaitingExecution.size());
	}

	public String getName() {
		return this.serviceName;
	}

	public ScheduledFuture<?> schedule(Runnable task, int delay, TimeUnit unit) {
		Log.debug("Task scheduled: " + task + ", " + delay + " " + unit);
		return this.taskScheduler.schedule(task, delay, unit);
	}

	public ScheduledFuture<?> scheduleAtFixedRate(Runnable task,
			int initialDelay, int period, TimeUnit unit) {
		return this.taskScheduler.scheduleAtFixedRate(task, initialDelay,
				period, unit);
	}

	public void cancelTask(ScheduledFuture<?> task) {
		task.cancel(true);
	}

	public void resizeThreadPool(int threadPoolSize) {
		this.taskScheduler.setCorePoolSize(threadPoolSize);
	}

	public int getThreadPoolSize() {
		return this.taskScheduler.getCorePoolSize();
	}

}