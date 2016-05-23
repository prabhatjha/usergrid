package org.apache.usergrid.apm.service.service;

import java.util.Calendar;

import org.apache.usergrid.apm.model.App;

import junit.framework.TestCase;
import org.apache.usergrid.apm.service.MetricsInjestionService;
import org.apache.usergrid.apm.service.NetworkMetricsDBService;
import org.apache.usergrid.apm.service.NetworkTestData;
import org.apache.usergrid.apm.service.ServiceFactory;

public class TestDataTest extends TestCase {

	protected void setUp() throws Exception {
		TestUtil.deleteLocalDB();
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Populates test data for 5 number of minutes. Then increments 3 more minutes
	 */
	public void testPopulateDataForMinutes()
	{
		
		Calendar startTime = Calendar.getInstance();
		Calendar incrementedStartTime = (Calendar)startTime.clone();
		
		NetworkTestData.populateDataForMinutes(5, incrementedStartTime, 1, 10L);
		
		NetworkMetricsDBService dbService = ServiceFactory.getMetricsDBServiceInstance();
		
		System.out.println("Num Rows after 5 min: " + dbService.getRowCount());
		
		NetworkTestData.populateDataForMinutes(3, incrementedStartTime, 1, 10L);
		
		System.out.println("Num Rows after 8 min: " + dbService.getRowCount());
		
		/*List<ClientNetworkMetrics> metrics = dbService.getAllWebServicesRecordsList();
		
		System.out.println("\n\n Data Dump:");
		for(ClientNetworkMetrics metric : metrics )
		{
			System.out.println(metric.toString());
		}*/
		
	}
	
	/**
	 * Populates test data for 5 number of minutes. Then increments 3 more minutes
	 */
	public void testPopulateDataForOneHour()
	{
		
		Calendar startTime = Calendar.getInstance();
		Calendar incrementedStartTime = (Calendar)startTime.clone();
		//changing num device to 1 for quicker test 1. Change it to higher for local testing
		NetworkTestData.populateDataForMinutes(60, incrementedStartTime, 1, 10L);
		
		NetworkMetricsDBService dbService = ServiceFactory.getMetricsDBServiceInstance();
		
		System.out.println("Num Rows after 60 min: " + dbService.getRowCount());
		
//		NetworkTestData.populateDataForMinutes(3, incrementedStartTime, 10, 10L);
//		
//		System.out.println("Num Rows after 8 min: " + dbService.getRowCount());
//		
//		List<ClientNetworkMetrics> metrics = dbService.getAllWebServicesRecordsList();
//		
//		System.out.println("\n\n Data Dump:");
//		for(ClientNetworkMetrics metric : metrics )
//		{
//			System.out.println(metric.toString());
//		}
		
	}
	
	
	/**
	 * End2End test of sending and injesting of data.
	 * @throws InterruptedException 
	 */
	public void testPopulateDataToSQS() throws InterruptedException
	{
		
		Calendar startTime = Calendar.getInstance();
		Calendar incrementedStartTime = (Calendar)startTime.clone();
		
		NetworkTestData.populateSQSWithTestData(1, incrementedStartTime, 1, new App()); //Pass proper app
		
		MetricsInjestionService injestionService = ServiceFactory.getMetricsInjestionServiceInstance();
		
		Thread.sleep(60000);
		
		injestionService.injestMetrics(10L, "blah");
		
//		NetworkTestData.populateDataForMinutes(3, incrementedStartTime, 10, 10L);
//		
//		System.out.println("Num Rows after 8 min: " + dbService.getRowCount());
//		
//		List<ClientNetworkMetrics> metrics = dbService.getAllWebServicesRecordsList();
//		
//		System.out.println("\n\n Data Dump:");
//		for(ClientNetworkMetrics metric : metrics )
//		{
//			System.out.println(metric.toString());
//		}
		
	}
	
}
