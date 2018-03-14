package com.tinamica.tunnelc.publisherdata.utils;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;

public class ServiceUtils {
    public static final String _PROJECT_ID = "tunelcuriosity";
    public static final String _DATASET_NAME = "envelope";
    public static final String _TABLE_ID = "TCA";
    public static final String _TABLE_ID2 = "TCA";
    public static final String _TABLE_ID3 = "TCA";
    public static final String _TABLE_ID4 = "TCA";

	public static long getTimeCurrentMilis() throws Exception {
		long time;
		try {
			time = Calendar.getInstance().getTimeInMillis();
		} catch (Exception e) {
			throw e;
		}
		return time;
	}

	public static String getDateString() throws Exception {
		Date resultdate = null;
		SimpleDateFormat sdf = null;
		try {
			sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			resultdate = new Date(getTimeCurrentMilis());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return sdf.format(resultdate);
	}
	
	public static BigQuery getBigQuery() throws Exception {
		BigQuery bigquery;
		try {
			bigquery = BigQueryOptions.newBuilder().setProjectId(_PROJECT_ID)
					.setCredentials(ServiceAccountCredentials.fromStream(new FileInputStream(
							"/home/linux/platform/delivery/keys/TunnelCuriosity-61e8b0342800.json")))
					.build().getService();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return bigquery;
	}

}
