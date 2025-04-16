package com.tobedisbursed;

import java.util.Calendar;
import java.util.TimeZone;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.CharEncoding;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = "com.tobedisbursed")
public class ToBeDisbursedApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(ToBeDisbursedApplication.class, args);
	}
	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ToBeDisbursedApplication.class);
	}

	public static void main1(String[] args) throws Exception {
		
		String 	secretKey ="oxLlxJVk6zNCHO/hXDAnDuB+xbyyBYw8I26hRfAqc12gJsWiC"; 	
		String appId = "9ce4f539d875";
		Long currentTime = Calendar.getInstance(TimeZone.getTimeZone("GMT")).getTimeInMillis();
		String timeStamp = appId + currentTime;
		String accessKey = calculateRFC2104HMACForString(secretKey, timeStamp);

		System.out.println("timeStamp :" + currentTime.toString());
		System.out.println("accessKey :" + accessKey);

	}
	
	private static final String HMAC_SHA256_ALGORITHM = "HmacSHA256";


	public static  String calculateRFC2104HMACForString(String secretKey, String requestString) throws Exception {
		String accessKey = null;
		StringBuilder dataToBeEncoded = new StringBuilder();
		dataToBeEncoded.append(requestString);
		if (StringUtils.isNotBlank(secretKey)) {
			try {
				SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes(), HMAC_SHA256_ALGORITHM);
				Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
				mac.init(signingKey);
				byte[] encodedBytes = Base64.encodeBase64(mac.doFinal(dataToBeEncoded.toString().getBytes()));
				accessKey = new String(encodedBytes, CharEncoding.UTF_8);
			} catch (Exception exception) {
				throw new Exception("Exception occured in calculateRFC2104HMACForString()during generation of HMAC ");
			}
		}
		return accessKey;

	}
}
