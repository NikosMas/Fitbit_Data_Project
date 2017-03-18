package com.grad.data.req;

import java.io.IOException;

import org.codehaus.jackson.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * profile-lifetime-frequence-data-request class.
 * 
 * @author nikos_mas
 *
 */

@Service
public class FitbitOtherData {

	// URI for each data. body part
	private static final String URI_PROFILE = "https://api.fitbit.com/1/user/-/profile.json";
	private static final String URI_FREQUENCE = "https://api.fitbit.com/1/user/-/activities/frequence.json";
	private static final String URI_LIFETIME = "https://api.fitbit.com/1/user/-/activities.json";
	// MongoDB collection name
	private static final String PROFILE = "profile";
	private static final String ACTIVITIES_LIFETIME = "activities_lifetime";
	private static final String ACTIVITIES_FREQUENCE = "activities_frequence";
	//
	private static final String PROFILE_USER = "user";
	private static final String FREQUENCE_CATEGORIES = "categories";

	@Autowired
	private RestTemplate restTemplateGet;

	@Autowired
	private FitbitDataSave fdata;

	public void profile() throws JsonProcessingException, IOException {
		ResponseEntity<String> data = restTemplateGet.exchange(URI_PROFILE, HttpMethod.GET, fdata.getEntity(),
				String.class);
		fdata.dataTypeInsert(data, PROFILE, PROFILE_USER);
	}

	public void lifetime() throws JsonProcessingException, IOException {
		ResponseEntity<String> data = restTemplateGet.exchange(URI_LIFETIME, HttpMethod.GET, fdata.getEntity(),
				String.class);
		fdata.dataTypeInsert(data, ACTIVITIES_LIFETIME, null);
	}

	public void frequence() throws JsonProcessingException, IOException {
		ResponseEntity<String> data = restTemplateGet.exchange(URI_FREQUENCE, HttpMethod.GET, fdata.getEntity(),
				String.class);
		fdata.dataTypeInsert(data, ACTIVITIES_FREQUENCE, FREQUENCE_CATEGORIES);
	}
}