package com.bazra.usermanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bazra.usermanagement.model.Settings;
import com.bazra.usermanagement.repository.SettingRepository;
import com.bazra.usermanagement.request.CreateSettingRequest;
import com.bazra.usermanagement.request.SettingRequest;
import com.bazra.usermanagement.response.ResponseError;
import com.bazra.usermanagement.response.SettingResponse;

@Service
public class SettingService {

	@Autowired
	private SettingRepository settingRepository;
	

	public ResponseEntity<?> setEntity(SettingRequest settingRequest) {

		Boolean exist = settingRepository.findBysettingName(settingRequest.getSettingName()).isPresent();
		if (exist) {
			Settings set = settingRepository.findBysettingName(settingRequest.getSettingName()).get();
			set.setValue(settingRequest.getValue());
			set.setLevels(settingRequest.getLevel());
			settingRepository.save(set);
			
		}

		return ResponseEntity.ok(new SettingResponse("Successfully updated setting"));
	}

	public ResponseEntity<?> createSetting(CreateSettingRequest createSettingRequest) {
		Boolean exist = settingRepository.findBysettingName(createSettingRequest.getSettingName()).isPresent();
		if (exist) {
			return ResponseEntity.badRequest().body(new ResponseError("Setting already exist!"));
		}
		Settings settings = new Settings(createSettingRequest.getSettingName(), createSettingRequest.getValue(),createSettingRequest.getLevels());
		settingRepository.save(settings);
		return ResponseEntity
				.ok(new SettingResponse(settings.getSettingName(), settings.getValue(), "Setting added successfully"));
	}
	
}
