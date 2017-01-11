package com.rsrf.lib.debug;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class LoggerMessage {
		
	public enum Priority {
		URGENT,
		HIGH,
		MEDIUM,
		LOW,
		STATUS
	};
	
	protected static Map<Priority, Integer> priorityMapping;
	
	static {
		LoggerMessage.priorityMapping = new HashMap<Priority, Integer>();;
		LoggerMessage.priorityMapping.put(Priority.URGENT, 4);
		LoggerMessage.priorityMapping.put(Priority.HIGH, 3);
		LoggerMessage.priorityMapping.put(Priority.MEDIUM, 2);
		LoggerMessage.priorityMapping.put(Priority.LOW, 1);
		LoggerMessage.priorityMapping.put(Priority.STATUS, 0);
	}
	
	private Priority priority;
	private String message, className;
	private Date timestamp;
	private DateFormat timeFormat;
	
	public LoggerMessage(Priority priority, String message, Date time) {
		this(priority, message, time, null);
	}
	
	public LoggerMessage(Priority priority, String message, Date time, Class<?> superClass) {
		if (superClass != null) {
			this.className = superClass.getName();
		} else {
			this.className = "";
		}
		this.priority = priority;
		this.message = message;
		this.timestamp = time;
		this.timeFormat = new SimpleDateFormat("HH:mm:ss:SSSS");
	}
	
	protected int getPriorityValue() {
		return LoggerMessage.priorityMapping.get(this.priority);
	}
	
	protected long getTimestampValue() {
		return this.timestamp.getTime();
	}
	
	public Priority getPriority() {
		return this.priority;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public Date getTimeStamp() {
		return this.timestamp;
	}
	
	public void setMessage(String newMessage) {
		this.message = newMessage;
	}
	
	public String prettyPrint() {
		String prettyString = "";
		prettyString += "[" + this.priority.toString() + "] ";
		prettyString += this.className + " @ ";
		prettyString += this.timeFormat.format(this.timestamp) + " : ";
		prettyString += this.message;
		return prettyString;
	}
	
}
