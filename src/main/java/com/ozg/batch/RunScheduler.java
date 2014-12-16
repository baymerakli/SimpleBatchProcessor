package com.ozg.batch;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RunScheduler {
	private static final Logger LOGGER = LoggerFactory.getLogger(FileToDBJobExecutionListener.class);
	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;

	public void run() {

		try {

			String dateParam = new Date().toString();
			JobParameters param = new JobParametersBuilder().addString("date", dateParam).toJobParameters();

			LOGGER.info("Job will be executed at "+dateParam);
			
			JobExecution execution = jobLauncher.run(job, param);
			LOGGER.info("Job Exit Status : " + execution.getStatus());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}