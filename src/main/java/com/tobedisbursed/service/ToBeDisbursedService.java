package com.tobedisbursed.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tobedisbursed.model.ToBeDisbCurrentStageReq;
import com.tobedisbursed.model.ToBeDisbursedStoredDetails;
import com.tobedisbursed.repo.ToBeProcessedRepo;

@Service
public class ToBeDisbursedService {

	@Autowired
	ToBeProcessedRepo toBeProcessedRepo;
	
	

	public Map<String, Object> disbursedStaus(ToBeDisbCurrentStageReq req) {

		Map<String, Object> response = new HashMap<>();

		
			
			List<ToBeDisbursedStoredDetails> storedDetails = toBeProcessedRepo.fetchData(req);
			
			if(storedDetails.size()>0) {
				for(ToBeDisbursedStoredDetails res : storedDetails) {
					response.put("prospectId", res.getProspectId()) ;
					response.put("crn_satge", res.getCurrent_stage());
					response.put("crn_satge_dt", res.getCurrent_stage_date());
					break;
				}
				return response;
			}else {
				response.put("statusCode", 600);
				response.put("message", "No response!");
			}
			
		return response;
	}

}
