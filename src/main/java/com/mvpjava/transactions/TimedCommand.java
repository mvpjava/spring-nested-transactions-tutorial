package com.mvpjava.transactions;

import java.time.Instant;

public class TimedCommand {
	private int id;
	private final Instant timeOfExecution; //UTC
	private final String command;
	
	public TimedCommand (final Instant timeOfExecution, final String command) {
		this.timeOfExecution = timeOfExecution;
		this.command = command;
	}

	public Instant getTimeOfExecution() {
		return timeOfExecution;
	}

	public String getCommand() {
		return command;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((command == null) ? 0 : command.hashCode());
		result = prime * result + ((timeOfExecution == null) ? 0 : timeOfExecution.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TimedCommand other = (TimedCommand) obj;
		if (command == null) {
			if (other.command != null)
				return false;
		} else if (!command.equals(other.command))
			return false;
		if (timeOfExecution == null) {
			if (other.timeOfExecution != null)
				return false;
		} else if (!timeOfExecution.equals(other.timeOfExecution))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TimedCommand [timeOfExecution=" + timeOfExecution + ", command=" + command + "]";
	}
	
}
	
