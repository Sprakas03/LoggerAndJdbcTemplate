package com.tobedisbursed.repo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tobedisbursed.model.ToBeDisbCurrentStageReq;
import com.tobedisbursed.model.ToBeDisbursedStoredDetails;

@Repository
public class ToBeProcessedRepo {

	private static final Logger logger = LogManager.getLogger(ToBeProcessedRepo.class);

	private final JdbcTemplate jdbcTemplate;

	public ToBeProcessedRepo(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<ToBeDisbursedStoredDetails> fetchData(ToBeDisbCurrentStageReq req) {

		String sql =" select PROSPECTID,current_stage,current_stage_date \r\n"
				+ " from to_be_disbursed_stage  where ProspectId = '" + req.getRefID() + "' \r\n"
				+ " order by current_stage_date desc ";

		RowMapper<ToBeDisbursedStoredDetails> rowMapper = new RowMapper<ToBeDisbursedStoredDetails>() {

			@Override
			public ToBeDisbursedStoredDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
				ToBeDisbursedStoredDetails req = new ToBeDisbursedStoredDetails();
				req.setProspectId(rs.getString("ProspectId"));
				req.setCurrent_stage(rs.getString("current_stage"));
				req.setCurrent_stage_date(rs.getString("current_stage_date"));
				return req;
			}
		};

		List<ToBeDisbursedStoredDetails> result = jdbcTemplate.query(sql, rowMapper);
		return result;

	}


}
