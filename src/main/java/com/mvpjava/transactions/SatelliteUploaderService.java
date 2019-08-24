package com.mvpjava.transactions;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

@Service 
public class SatelliteUploaderService implements UploaderService{
	private static final Logger logger = LoggerFactory.getLogger(SatelliteUploaderService.class);
	private final TimedCommandsDao timedCommandsDao;
		
	@Autowired
	public SatelliteUploaderService (TimedCommandsDao timedCommandsDao) {
		this.timedCommandsDao = timedCommandsDao;
	}
	
	@Override
	public void upload(List<String> timedCommands) {
		int DESIRED_PARTITION_SIZE = 3; 
		List<List<String>> allPartitionedLists = Lists.partition(timedCommands, DESIRED_PARTITION_SIZE);

		for (List<String> subPartitionList : allPartitionedLists) {
			try {
				String[] subPartitionAsArray = Iterables.toArray(subPartitionList, String.class);
				timedCommandsDao.uplink(subPartitionAsArray);
			} catch (RuntimeException e) {
				logger.warn("The Transaction will commit regardless of failure in NESTED transaction", e);
			}
		}
	}
}
