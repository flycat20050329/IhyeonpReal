package com.bezkoder.springjwt.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SchoolInfo {
	String key = "e987e18bddbb4a80bc3fe9e58ee54f4a";
	String cite = "https://open.neis.go.kr/hub/schoolInfo";
	
	public String GetLocationCode(String name) {
		
		String url = cite + "?KEY" + key + "&SCHUL_NM=" + name;
		
		try {
			Document doc = Jsoup.connect(url).get();
			Elements elements = doc.select("row");
			
			String code = elements.select("ATPT_OFCDC_SC_CODE").get(0).text();
			return code;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
	}
	
	public List<String> GetSchoolName(String loca, String name) {
		try {
			String url = cite + "?KEY=" + key + "&ATPT_OFCDC_SC_CODE=" + loca + "&SCHUL_NM=" + name;
			List<String> schoolInfo = new ArrayList<String>();
			
			Document doc = Jsoup.connect(url).get();
			Elements elements = doc.select("row");
			Elements locationElements = elements.select("LCTN_SC_NM");
			Elements nameElements = elements.select("SCHUL_NM");
			
			for(int i = 0; i < elements.size(); i++) {
					schoolInfo.add(nameElements.get(i).text());
			}
			
			return schoolInfo;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public String GetSchoolcode(String name) {
		try {
			String url = cite + "?KEY=" + key + "&SCHUL_NM=" + name;
			List<String> schoolInfo = new ArrayList<String>();
			
			Document doc = Jsoup.connect(url).get();
			Elements elements = doc.select("row");
			Elements codeElement = elements.select("SD_SCHUL_CODE");
			
			return codeElement.get(0).text();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
