package com.rsrf.lib.debug;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

import com.rsrf.lib.debug.LoggerMessage.Priority;
import com.rsrf.lib.util.SimpleConstants;

public class Logger {
	
	private static Logger instance;
	private Queue<LoggerMessage> debugLog;
	private int priorityLevel;

	private Logger() {
		this.debugLog = new PriorityBlockingQueue<LoggerMessage>(SimpleConstants.messageLogCount, 
				Comparator.comparing(LoggerMessage::getPriorityValue)
				.thenComparing(LoggerMessage::getTimestampValue));
		this.priorityLevel = 0;
	}
	
	public static Logger initialize() {
		if (instance == null) {
			instance = new Logger();
		}
		return instance;
	}
	
	public void setMinimumPriority(Priority priority) {
		this.priorityLevel = LoggerMessage.priorityMapping.get(priority);
	}
	
	public void addMessage(LoggerMessage message, boolean display) {
		this.debugLog.add(message);
		if (display) {
			this.publishMostRecentMessage(true);
		}
	}
	
	public void addMessage(LoggerMessage message) {
		this.addMessage(message, true);
	}
	
	public void publishMostRecentMessage(boolean remove) {
		try {
			if (remove) {
				this.showMessage(this.debugLog.remove());
			} else {
				this.showMessage(this.debugLog.element());
			}
		} catch (NoSuchElementException e) {
			System.out.println("No messages left!");
			e.printStackTrace();
		}
	}
	
	private void showMessage(LoggerMessage message) {
		if (message.getPriorityValue() >= this.priorityLevel) {
			System.out.println(message.prettyPrint());
		}
	}
	
}
