package com.bezkoder.springjwt.controllers;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.bezkoder.springjwt.models.User;

//KEY : 6b5153fb92974f91a5bbe3c94e42fd7a
//교육청코드 : J10
//이현고코드 : 7530983
//기본 url : https://open.neis.go.kr/hub/mealServiceDietInfo
//?KEY=6b5153fb92974f91a5bbe3c94e42fd7a&TYPE=xml&ATPT_OFCDC_SC_CODE=J10&SD_SCHUL_CODE=7530983&MLSV_FROM_YMD=20230611

public class Lunch {

	public static String getCurMonday() {

		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMdd");

		Calendar c = Calendar.getInstance();

		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

		return formatter.format(c.getTime());

	}

	public List<String> getWeek() {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMdd");
		Calendar c = Calendar.getInstance();
		Date d;
		String mon;
		String fri;

		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		d = c.getTime();
		mon = formatter.format(d);

		c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		d = c.getTime();
		fri = formatter.format(d);

//		System.out.println(sun);
//		System.out.println(sat);

		List<String> s = new ArrayList<String>();
		s.add(mon);
		s.add(fri);

		return s;
	}

	// tag값의 정보를 가져오는 메소드
	private static String getTagValue(String tag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);
		if (nValue == null)
			return null;
		return nValue.getNodeValue();
	}
	
	public int getDayOfWeek(String date) {		//요일 가져오는 함수
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate localDate = LocalDate.parse(date, format);
        
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        return dayOfWeek.getValue() - 1;
	}

	public List<List<String>> getLunchInfo(User user) {
		try {
			// parsing할 url 지정(API 키 포함해서)
			String url = "https://open.neis.go.kr/hub/mealServiceDietInfo";
			String serviceKey = "6b5153fb92974f91a5bbe3c94e42fd7a";
			String decodeServiceKey = URLDecoder.decode(serviceKey, "UTF-8");
			String type = "xml";
			List<String> emptyList = new ArrayList<>();
			int i = 0;

			List<List<String>> lunchList = new ArrayList<List<String>>();
			
			for(int j = 0; j < 5; j++) {
				lunchList.add(emptyList);
			}

			Document documentInfo = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.parse(url + "?KEY=" + decodeServiceKey + "&ATPT_OFCDC_SC_CODE=" + user.getLocaCode()
							+ "&SD_SCHUL_CODE=" + user.getSchoolCode() + "&TYPE=" + type + "&MLSV_FROM_YMD="
							+ "20240101" + "&MLSV_TO_YMD=" + "20240105");

			// root tag
			documentInfo.getDocumentElement().normalize();
//			System.out.println("Root element :" + documentInfo.getDocumentElement().getNodeName());

			// 파싱할 tag
			NodeList nList = documentInfo.getElementsByTagName("row");
//							System.out.println("파싱할 리스트 수 : "+ nList.getLength());

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(i);
				Element eElement = (Element) nNode;
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					List<String> lunchInfo = new ArrayList<String>();
					lunchInfo.add(getTagValue("MLSV_YMD", eElement)); // 날짜
					lunchInfo.add(getTagValue("DDISH_NM", eElement).replaceAll("<br/>", "")); // 급식정보
					
					String curDate = getTagValue("MLSV_YMD", eElement);
					int index = getDayOfWeek(curDate);

					lunchList.add(index, lunchInfo);
					lunchList.remove(lunchList.size() - 1);
					i += 1;
				}

			}
			for (List<String> k : lunchList) {
				System.out.println(k);
			}

			return lunchList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
