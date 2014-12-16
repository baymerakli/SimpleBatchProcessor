package com.ozg.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Service {
    private static final Logger LOGGER = LoggerFactory.getLogger(Service.class);
    public static void main(String[] args) {
	@SuppressWarnings("unused")
	ApplicationContext context = new ClassPathXmlApplicationContext("spring/batch/jobs/job-descs.xml");
	LOGGER.info("File Processor Started....");
    }
}
