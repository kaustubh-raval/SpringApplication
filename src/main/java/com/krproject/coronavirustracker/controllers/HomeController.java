package com.krproject.coronavirustracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.krproject.coronavirustracker.models.LocationStats;
import com.krproject.coronavirustracker.services.CoronaVirusDataServices;

@Controller
public class HomeController {
	
	@Autowired
	CoronaVirusDataServices coronaVirusDataService;
	
	@GetMapping("/")
	public String home(Model model) {
		List<LocationStats> allStats = coronaVirusDataService.getAllStats();
		int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
		int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
		model.addAttribute("locationStats", allStats);
		model.addAttribute("totalReportedCases", totalReportedCases);
		model.addAttribute("totalNewCases", totalNewCases);
		return "home";
	}
}
