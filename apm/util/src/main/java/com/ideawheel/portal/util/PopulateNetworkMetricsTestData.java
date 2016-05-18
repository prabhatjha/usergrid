package com.ideawheel.portal.util;

import java.util.Calendar;

import com.ideawheel.common.model.ApigeeMobileAPMConstants;
import com.ideawheel.common.model.ApplicationConfigurationModel;
import com.ideawheel.common.model.App;

import org.apache.usergrid.apm.service.ApplicationService;
import org.apache.usergrid.apm.service.NetworkTestData;
import org.apache.usergrid.apm.service.ServiceFactory;




public class PopulateNetworkMetricsTestData {

   /**
    * @param args
    */
   public static void main(String[] args) {		 


      //while (true)  {
      System.out.println ("#################################");
      System.out.println ("#                               #");
      System.out.println ("#  Going to populate test data.  #");
      System.out.println ("#      opsFuse Inc              #");
      System.out.println ("#                               #");
      System.out.println ("#################################");

      System.out.println();
      System.out.println();
      
      System.out.println("Adding test network metrics for app1 and app2");
      ApplicationService appService = ServiceFactory.getApplicationService();

      App app1 = appService.getApplication(1L);
      App app2 = appService.getApplication(2L);
      
      if (app1 == null) {	   

         app1 = new App ();
         app1.setAppOwner("user1");
         app1.setAppName("App1");
         app1.setDefaultAppConfig(new ApplicationConfigurationModel(ApigeeMobileAPMConstants.CONFIG_TYPE_DEFAULT));
         app1 = appService.createApplication(app1);
         System.out.println ("App added with id " + app1.getInstaOpsApplicationId() + " name " + app1.getAppName() + " owner " + app1.getAppOwner());
      }

      if (app2 == null) {
         app2 = new App ();
         app2.setAppName("App2");
         app2.setAppOwner("user2");
         app2.setDefaultAppConfig(new ApplicationConfigurationModel(ApigeeMobileAPMConstants.CONFIG_TYPE_DEFAULT));
         app2 = appService.createApplication (app2);
         System.out.println ("App added with id " + app2.getInstaOpsApplicationId() + " name " + app2.getAppName() + " owner " + app2.getAppOwner());
   
      }    
      
      System.out.println ("Populating Metrics for both apps");
      Calendar startTime = Calendar.getInstance();
      startTime.add(Calendar.HOUR_OF_DAY, -1);
      NetworkTestData.populateDataForMinutes(200, startTime,5, app1.getInstaOpsApplicationId());
      NetworkTestData.populateDataForMinutes(200, startTime,5, app2.getInstaOpsApplicationId());     



      System.out.println ("##############################");
      System.out.println ("#                            #");
      System.out.println ("#   And, WE ARE DONE         #");
      System.out.println ("#         ENJOY              #");
      System.out.println ("#                            #");
      System.out.println ("##############################");

   }	

}