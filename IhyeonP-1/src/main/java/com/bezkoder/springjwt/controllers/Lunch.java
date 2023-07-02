package com.bezkoder.springjwt.controllers;

import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//KEY : 6b5153fb92974f91a5bbe3c94e42fd7a
//교육청코드 : J10
//이현고코드 : 7530983
//기본 url : https://open.neis.go.kr/hub/mealServiceDietInfo
//?KEY=6b5153fb92974f91a5bbe3c94e42fd7a&TYPE=xml&ATPT_OFCDC_SC_CODE=J10&SD_SCHUL_CODE=7530983&MLSV_FROM_YMD=20230611

public class Lunch {

//	public static void main(String[] args) {
//		Lunch lunch = new Lunch();
//		System.out.println(lunch.getLunchInfo());
//		
//		System.out.println(Lunch.getCurMonday());
//	}

	public static String getCurMonday() {

		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy.MM.dd");

		Calendar c = Calendar.getInstance();

		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

		return formatter.format(c.getTime());

	}

	// tag값의 정보를 가져오는 메소드
	private static String getTagValue(String tag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);
		if (nValue == null)
			return null;
		return nValue.getNodeValue();
	}

	public List<List<String>> getLunchInfo() {
		try {
			// parsing할 url 지정(API 키 포함해서)
			String url = "https://open.neis.go.kr/hub/mealServiceDietInfo";
			String serviceKey = "6b5153fb92974f91a5bbe3c94e42fd7a";
			String decodeServiceKey = URLDecoder.decode(serviceKey, "UTF-8");
			String atptCode = "J10";
			String schoolCode = "7530983";
			String type = "xml";
			String date = Lunch.getCurMonday();
			
			List<List<String>> lunchList = new ArrayList<List<String>>();

			Document documentInfo = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.parse(url + "?KEY=" + decodeServiceKey + "&ATPT_OFCDC_SC_CODE=" + atptCode + "&SD_SCHUL_CODE="
							+ schoolCode + "&TYPE=" + type + "&MLSV_FROM_YMD=" + date);

			// root tag
			documentInfo.getDocumentElement().normalize();
//			System.out.println("Root element :" + documentInfo.getDocumentElement().getNodeName());

			// 파싱할 tag
			NodeList nList = documentInfo.getElementsByTagName("row");
//							System.out.println("파싱할 리스트 수 : "+ nList.getLength());

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
//					System.out.println(getTagValue("MLSV_YMD", eElement)); //날짜
//					System.out.println(getTagValue("DDISH_NM", eElement)); //급식정보

					List<String> lunchInfo = new ArrayList<String>();
					lunchInfo.add(getTagValue("MLSV_YMD", eElement)); // 날짜
					lunchInfo.add(getTagValue("DDISH_NM", eElement)); // 급식정보

					lunchList.add(lunchInfo);

				}
			}
			return lunchList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
