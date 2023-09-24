package com.bezkoder.springjwt.controllers;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Schedule {
	String key = "e987e18bddbb4a80bc3fe9e58ee54f4a";
	String cite = "https://open.neis.go.kr/hub/hisTimetable";
//	String schoolCode = "7530983";
//	String locationCode = "";
//	String semester = "";
//	String grade = "";
//	String ban = "";

	public List<String>[] GetScheduleInfo() {
		List<String> data = new ArrayList<String>();
		LocalDate date = LocalDate.now();
		String getdate = date.toString();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar ca = new GregorianCalendar();
		List<String>[] classList = new ArrayList[7];
				
		try {
			String url = cite + "?KEY=" + key + "&ATPT_OFCDC_SC_CODE=J10&SD_SCHUL_CODE=7530983&AY=2023&SEM=1&GRADE=3&CLASS_NM=2&TI_FROM_YMD"
					+ "=20230312&TI_TO_YMD=20230319";
			
			Document doc = Jsoup.connect(url).get();
			Elements elements = doc.select("row");
			Elements timeList = elements.select("PERIO");
			Elements classElement = elements.select("ITRT_CNTNT");
			int perio = 0;
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return classList;
	}
}
