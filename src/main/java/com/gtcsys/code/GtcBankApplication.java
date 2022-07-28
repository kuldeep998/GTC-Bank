package com.gtcsys.code;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GtcBankApplication {
	private static final Logger logger = Logger.getLogger(GtcBankApplication.class);

	public static void main(String[] args) {
		logger.info("Main Application Start ...");
		SpringApplication.run(GtcBankApplication.class, args);
	}

}
