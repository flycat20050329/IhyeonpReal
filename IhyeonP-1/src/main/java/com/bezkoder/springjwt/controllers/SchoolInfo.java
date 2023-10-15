package com.bezkoder.springjwt.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SchoolInfo {
	String key = "e987e18bddbb4a80bc3fe9e58ee54f4a";
	String cite = "https://open.neis.go.kr/hub/schoolInfo";

	public List<String> GetSchoolInfo(String initName) {
		try {
			String url = cite + "?KEY=" + key + "?SCHUL_NM=" + initName;
			List<String> nameList = new ArrayList<String>();
			
			Document doc = Jsoup.connect(url).get();
			Elements elements = doc.select("row");
			Elements names = elements.select("SCHUL_NM");
			
			for(Element name : names) {
				nameList.add(name.text());
			}
			
			return nameList;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
