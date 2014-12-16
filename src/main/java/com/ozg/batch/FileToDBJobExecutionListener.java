package com.ozg.batch;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.UnexpectedJobExecutionException;

public class FileToDBJobExecutionListener implements JobExecutionListener {
	private static final Logger LOGGER = LoggerFactory.getLogger(FileToDBJobExecutionListener.class);
	private String directoryPath;

	@Override
	public void beforeJob(JobExecution jobExecution) {
		LOGGER.info("job completed");
		
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		 if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			 File dir = new File(directoryPath);
			    if (dir.exists()) {
					
			        File[] files = dir.listFiles();
			        for (int i = 0; i < files.length; i++) {
			            boolean deleted = files[i].delete();
			    	    if (!files[i].exists()) {
			    	    	try {
								files[i].createNewFile();
							} catch (IOException e) {
								e.printStackTrace();
							}
			    		    }
			            if (!deleted) {
			            	LOGGER.error("Could not delete file.");
			                throw new UnexpectedJobExecutionException("Could not delete file " +
			                                                          files[i].getPath());
			            }
			        }
			    }
		 }
	}

	public String getDirectoryPath() {
		return directoryPath;
	}

	public void setDirectoryPath(String directoryPath) {
		this.directoryPath = directoryPath;
	}

}
