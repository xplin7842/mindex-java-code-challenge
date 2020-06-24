package com.mindex.challenge.service;

import com.mindex.challenge.data.Compensation;

/**
 * 
 * @author xplin
 * Jun 24, 2020
 */

public interface CompensationService {
	Compensation create(Compensation comp);

	Compensation read(String id);
}
