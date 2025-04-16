package com.tobedisbursed.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToBeDisbursedStoredDetails {
	
	@JsonProperty("prospectId")
	private String prospectId;
	
	@JsonProperty("crnt_stage")
	private String current_stage;
	
	@JsonProperty("crnt_stage_date")
	private String current_stage_date;

}
