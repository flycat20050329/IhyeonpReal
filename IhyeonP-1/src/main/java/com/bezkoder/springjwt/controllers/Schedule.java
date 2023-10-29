package com.bezkoder.springjwt.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Schedule {
	String key = "e987e18bddbb4a80bc3fe9e58ee54f4a";
	String cite = "https://open.neis.go.kr/hub/hisTimetable";
//	String schoolCode = "7530983";
//	String locationCode = "";
//	String semester = "";
//	String grade = "";
//	String ban = "";

	public List<String> getWeek() {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMdd");
		Calendar c = Calendar.getInstance();
		Date d;
		String sun;
		String sat;

		c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		d = c.getTime();
		sun = formatter.format(d);

		c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		d = c.getTime();
		sat = formatter.format(d);

		System.out.println(sun);
		System.out.println(sat);

		List<String> s = new ArrayList<String>();
		s.add(sun);
		s.add(sat);

		return s;
	}

	public List<List<String>> getScheduleInfo() {
		List<List<String>> classTable = new ArrayList<List<String>>();
		List<String> dateBound = getWeek();
		String sun = dateBound.get(0);
		String sat = dateBound.get(1);
		try {
			String url = cite + "?KEY=" + key
					+ "&ATPT_OFCDC_SC_CODE=J10&SD_SCHUL_CODE=7530112&AY=2023&SEM=2&GRADE=3&CLASS_NM=1&TI_FROM_YMD" + "="
					+ sun + "&TI_TO_YMD=" + sat;

			Document doc = Jsoup.connect(url).get();
			Elements elements = doc.select("row");
			Elements timeElement = elements.select("ALL_TI_YMD");
			Elements classElement = elements.select("ITRT_CNTNT");
			Elements perioElement = elements.select("PERIO");
			String curDate = null;
			String prevInfo = "";
			List<String> classList = new ArrayList<String>();
			List<String> perioList = new ArrayList<String>();
			int perio = 0;

			for (int i = 0; i < classElement.size(); i++) {
				if(prevInfo.equals(perioElement.get(i).text() + classElement.get(i).text())) {
					continue;
				}
				else {
					prevInfo = perioElement.get(i).text() + classElement.get(i).text();
					System.out.println(prevInfo);
				}
				
				if (curDate == null) {	//월요일 1교시
					curDate = timeElement.get(i).text();
				}
				
				if (!curDate.equals(timeElement.get(i).text()) || perio == 7) {
					perioList.add(Integer.toString(perio));
					perio = 0;
					curDate = timeElement.get(i).text();
				}
				perio += 1;
				classList.add(classElement.get(i).text());
			}
			// perioList.add(Integer.toString(perio));

			classTable.add(perioList);
			classTable.add(classList);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return classTable;
	}
}
