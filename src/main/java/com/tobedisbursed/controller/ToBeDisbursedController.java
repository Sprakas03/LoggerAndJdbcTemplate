package com.tobedisbursed.controller;


import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tobedisbursed.model.ToBeDisbCurrentStageReq;
import com.tobedisbursed.model.ToBeDisbursedStoredDetails;
import com.tobedisbursed.service.ToBeDisbursedService;


@RestController
@RequestMapping("/api")
public class ToBeDisbursedController {
	
	private static final Logger logger = LogManager.getLogger(ToBeDisbursedController.class);
	
	@Autowired
	ToBeDisbursedService toBeDisbursedService;

	@GetMapping(path ="/crntsatgestatus")
	public ResponseEntity<ToBeDisbursedStoredDetails> getStages(@RequestBody ToBeDisbCurrentStageReq req){
		
		logger.info(" got req: "+ req.toString());
		
		Map<String, Object> response =  toBeDisbursedService.disbursedStaus(req);

		ToBeDisbursedStoredDetails res = new ToBeDisbursedStoredDetails();
		
		
		res.setProspectId((String) response.get("prospectId"));
		res.setCurrent_stage((String) response.get("crn_satge"));
		res.setCurrent_stage_date((String) response.get("crn_satge_dt"));
		
		logger.info(" Final res:"+ res.toString());
		
		return ResponseEntity.ok(res);
	}
	
	@GetMapping(path = "/test")
	public String test() {
		return "Working!!!";
	}
}
