package com.apigee.apm.rest;

import java.util.Calendar;

import org.apache.usergrid.apm.model.ApigeeMobileAPMConstants;
import org.apache.usergrid.apm.model.ChartCriteria;
import org.apache.usergrid.apm.model.ChartCriteria.PeriodType;

public class ApmUtil {
	
	/**
	 * 
	 * @param cq chart criteria that has instaOpsAppId
	 * @param period  1h or 3h or 6h or 12h or 24h or 1wk. See ApigeeMobileAPMConstants
	 * @param reference YESTERDAY or LAST_WEEK. See @ApigeeMobileAPMConstants
	 * @return chart criteria with proper start time and end time based on period and reference
	 */
	public static ChartCriteria getChartCriteriaWithTimeRange (ChartCriteria cq, String period, String reference) {
		
		Calendar startTime = Calendar.getInstance();
		Calendar endTime = Calendar.getInstance();
		cq.setPeriodType(PeriodType.SET_PERIOD);
		
		//go back yesterday or last week
		if (reference.equalsIgnoreCase(ApigeeMobileAPMConstants.CHART_DATA_REFERENCE_POINT_YESTERDAY)) {
			 startTime.add(Calendar.DAY_OF_YEAR, -1);
			 endTime.add(Calendar.DAY_OF_YEAR, -1);
		} else if (reference.equalsIgnoreCase(ApigeeMobileAPMConstants.CHART_DATA_REFERENCE_POINT_LAST_WEEK)) {
			 startTime.add(Calendar.DAY_OF_YEAR, -7);
			 endTime.add(Calendar.DAY_OF_YEAR, -7);
		}
		//now  go back 1h or 3h or 6h or 12h or 24h or 1wk for starting point
		if (period.equalsIgnoreCase(ApigeeMobileAPMConstants.CHART_PERIOD_1HR))
			startTime.add(Calendar.HOUR_OF_DAY, -1);
		else if (period.equalsIgnoreCase(ApigeeMobileAPMConstants.CHART_PERIOD_3HR))
			startTime.add(Calendar.HOUR_OF_DAY, -3);
		else if (period.equalsIgnoreCase(ApigeeMobileAPMConstants.CHART_PERIOD_6HR))
			startTime.add(Calendar.HOUR_OF_DAY, -6);
		else if (period.equalsIgnoreCase(ApigeeMobileAPMConstants.CHART_PERIOD_12HR))
			startTime.add(Calendar.HOUR_OF_DAY, -12);
		else if (period.equalsIgnoreCase(ApigeeMobileAPMConstants.CHART_PERIOD_24HR))
			startTime.add(Calendar.HOUR_OF_DAY, -24);
		else if (period.equalsIgnoreCase(ApigeeMobileAPMConstants.CHART_PERIOD_1WK))
			startTime.add(Calendar.DAY_OF_YEAR, -7);
			
		cq.setStartDate(startTime.getTime());
		cq.setEndDate(endTime.getTime());
		return cq;
		
		
	}
	
	/**
	 * returns minute since epoch
	 * @param timeInMilliseconds
	 * @return
	 */
	
	public static Long getMinuteFromTime(Long timeInMilliseconds) {
		return timeInMilliseconds/(1000*60);
	}
}
