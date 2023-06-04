package kr.co.tobby48.medihand.controllers;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/home")
public class HomeController {

	@GetMapping("/yelp/{businessId}/{apikey}")
	public String allAccess(@PathVariable String businessId, @PathVariable String apikey) {
		HttpClient client = HttpClients.createDefault();
		HttpGet request = new HttpGet(String.format("https://api.yelp.com/v3/businesses/%s/reviews", businessId));
		try {
			//	필요에 따라서는 헤더 추가
			request.addHeader("Authorization", String.format("Bearer %s", apikey));
			
			//	요청
			HttpResponse response = client.execute(request);
			
			//	응답
			String result = EntityUtils.toString(response.getEntity());
			return result;
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		return "";
	}

}
