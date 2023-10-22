package com.bezkoder.springjwt.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SchoolInfo {
	String key = "e987e18bddbb4a80bc3fe9e58ee54f4a";
	String cite = "https://open.neis.go.kr/hub/schoolInfo";

	public Map<String, String> GetSchoolInfo() {
		try {
			String url = cite + "?KEY=" + key;
			Map<String, String> schoolInfo = new HashMap<String, String>();
			
			Document doc = Jsoup.connect(url).get();
			Elements elements = doc.select("row");
			Elements locationElements = elements.select("LCTN_SC_NM");
			Elements nameElements = elements.select("SCHUL_NM");
			
			for(int i = 0; i < elements.size(); i++) {
				if(!locationElements.get(i).equals("") && !nameElements.get(i).equals("")) {
					schoolInfo.put(nameElements.get(i).text(), locationElements.get(i).text());
				}
			}
			
			return schoolInfo;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
