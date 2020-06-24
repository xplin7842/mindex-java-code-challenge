
package com.mindex.challenge.controller;
import com.mindex.challenge.data.ReportStructure;
import com.mindex.challenge.service.ReportStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 
 * @author xplin
 * Jun 24, 2020
 */
@RestController
public class ReportStructureController {
	private static final Logger LOG = LoggerFactory.getLogger(ReportStructureController.class);

	@Autowired
	private ReportStructureService ReportStructureService;

	@GetMapping("/reportstructure/{id}")
	public ReportStructure read(@PathVariable String id) {
		LOG.error("Received ReportStructure create request for id [{}]", id);
		return ReportStructureService.read(id);
	}

}
