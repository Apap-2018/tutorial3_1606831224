package com.apap.tutorial3.controller;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tutorial3.model.PilotModel;
import com.apap.tutorial3.service.PilotService;

@Controller
public class PilotController {
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping("/pilot/add")
	public String add(@RequestParam(value="id", required=true) String id,
			@RequestParam(value="licenseNumber", required=true) String licenseNumber,
			@RequestParam(value="name", required=true) String name,
			@RequestParam(value="flyHour", required=true) Integer flyHour) {
		
		PilotModel pilot = new PilotModel(id, licenseNumber, name, flyHour);

		pilotService.addPilot(pilot);
		return "add";
		
	}
	
	@RequestMapping("/pilot/view")
	public String view(@RequestParam("licenseNumber") String licenseNumber, Model model) {

		PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		
		model.addAttribute("pilot", archive);
		return "view-pilot";
	}
	
	@RequestMapping("/pilot/viewall")
	public String viewall(Model model) {
		List<PilotModel> archive = pilotService.getListPilot();
		
		model.addAttribute("listPilot", archive);
		return "viewall-pilot";
	}
	
	@RequestMapping(value= {"/pilot/view/license-number","/pilot/view/license-number/{licenseNumber}"})
	public String view_path(@PathVariable Optional<String> licenseNumber, Model model) {
		
		PilotModel archive = null;
		
		if (!licenseNumber.isPresent()) {
			model.addAttribute("error", "nomor license kosong");
			return "error";
		}
		else {
			archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber.get());
			if (archive == null) {
				model.addAttribute("error", "nomor license tidak ditemukan");
				return "error";
			}
		}
		
		model.addAttribute("pilot", archive);
		
		return "view-pilot";
	}
	
	@RequestMapping("/pilot/update/license-number/{licenseNumber}/fly-hour/{newLicenseNumber}")
	public String update_path(@PathVariable Optional<String> licenseNumber, @PathVariable String newLicenseNumber,
			Model model) {
		
		PilotModel archive = null;
		
		if (!licenseNumber.isPresent()) {
			model.addAttribute("error", "nomor license kosong");
			return "error";
		}
		else {
			archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber.get());
			if (archive == null) {
				model.addAttribute("error", "nomor license tidak ditemukan");
				return "error";
			}
		}
		// update data objek
		archive.setLicenseNumber(newLicenseNumber);
		
		return "update-license";
		
		
	}
	
	@RequestMapping("/pilot/delete/id/{id}")
	public String delete_path(@PathVariable Optional<String> id, Model model) {
		PilotModel archive = null;
		
		if (!id.isPresent()) {
			model.addAttribute("error", "nomor id kosong");
			return "error";
		}
		else {
			archive = pilotService.deleteById(id.get());
			if (archive == null) {
				model.addAttribute("error", "nomor id tidak ditemukan");
				return "error";
			}
		}
		
		return "delete-pilot";
	}
	
	
	
	

}
