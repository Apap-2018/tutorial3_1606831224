package com.apap.tutorial3.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.apap.tutorial3.model.PilotModel;


@Service
public class PilotInMemoryService implements PilotService{
	private List<PilotModel> archivePilot;
	
	public PilotInMemoryService() {
		archivePilot = new ArrayList<>();
	}

	@Override
	public void addPilot(PilotModel pilot) {
		// TODO Auto-generated method stub
		archivePilot.add(pilot);
	}

	@Override
	public List<PilotModel> getListPilot() {
		// TODO Auto-generated method stub
		return archivePilot;
	}

	@Override
	public PilotModel getPilotDetailByLicenseNumber(String licenseNumber) {
		// TODO Auto-generated method stub
		PilotModel pilot = null;
		int banyakPilot = archivePilot.size();
		
		for (int i = 0; i < banyakPilot; i++) {
			// membandingkan mobil id di list archive car dengan id yang dicari  
			if (archivePilot.get(i).getLicenseNumber().equals(licenseNumber)) {
				pilot = archivePilot.get(i);
				break;
			}
		}

		return pilot;
	}

	@Override
	public PilotModel deleteById(String id) {
		// TODO Auto-generated method stub
		
		PilotModel pilot = null;
		
		// melakukan loop untuk mencari 
		for (int i = 0; i < archivePilot.size(); i++) {
			// kalo ketemu pilot dengan id yang sama
			if (archivePilot.get(i).getId().equals(id)) {
				pilot = archivePilot.get(i);
				archivePilot.remove(i);
			}
		}
		return pilot;
	}
	


}
