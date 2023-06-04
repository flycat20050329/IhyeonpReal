package kr.co.tobby48.medihand.controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.tobby48.medihand.models.Management;
import kr.co.tobby48.medihand.payload.response.management.ManagementCommonResponse;
import kr.co.tobby48.medihand.payload.response.management.ManagementResponse;
import kr.co.tobby48.medihand.payload.response.management.ManagementStep;
import kr.co.tobby48.medihand.repository.ManagementRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/management")
public class ManagementController {
	@Autowired
	private ManagementRepository managementRepository;

	@GetMapping("/{userId}")
	public List<ManagementResponse> getManagementByUsers(@PathVariable Long userId) {
		List<ManagementResponse> responses = new ArrayList<ManagementResponse>();
		
		List<Management> managements = managementRepository.findByUser_Id(userId);
		Map<Integer, List<Management>> grouped = managements.stream().sorted(Comparator.comparing(Management::getStatus)).collect(Collectors.groupingBy(Management::getStatus));
		for(Entry<Integer, List<Management>> entry : grouped.entrySet()) {
			String step = ManagementStep.values()[entry.getKey()-1].name();
			ManagementResponse res = new ManagementResponse(step);
			List<ManagementCommonResponse> tasks = new ArrayList<ManagementCommonResponse>();
			for(Management m : entry.getValue()) {
//				title, String description, String status
				ManagementCommonResponse n = new ManagementCommonResponse(m, step, new ArrayList<>());
				tasks.add(n);
			}
			res.setTasks(tasks);
			responses.add(res);
		}
		return responses;
	}
	
}
