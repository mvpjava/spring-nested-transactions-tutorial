package com.mvpjava.transactions;


import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TimedCommandsDao {

	private static final Logger logger = LoggerFactory.getLogger(TimedCommandsDao.class);
	private final JdbcTemplate jdbcTemplate;
	private boolean demoFailureMode;

	@Autowired
	public TimedCommandsDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Transactional(propagation = Propagation.NESTED)
	public int[] uplink(String[] timedCommands) {
		int[] updateCounts = jdbcTemplate.batchUpdate(timedCommands);
		
		//Just for demo purposes
		if (demoFailureMode) {
			throwRandomException();
		}
		
		return updateCounts;
	}
	
	/*
	 * If 'nested.tx.fail' is set to true in application.properties, 
	 * it will purposely fail a random nested transaction for demo purposes
	 */
	@Value("#{new Boolean('${nested.tx.fail:false}')}") 
	public void setDemoFailureMode(boolean demoFailureMode) {
		this.demoFailureMode = demoFailureMode;
	}	
	
	/* Simulate a really bad connection */
	private void throwRandomException() {
		if (new Random().nextInt(3) == 1) {
			logger.info("throwing a random exception to demo rolling back NESTED Transactions");
			throw new RuntimeException();	
		}
	}
}