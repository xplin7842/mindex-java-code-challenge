package com.mindex.challenge.dao;

import com.mindex.challenge.data.Compensation;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 
 * @author xplin
 * Jun 24, 2020
 */
@Repository
public interface CompensationRepository extends MongoRepository<Compensation, String> {
	Compensation findByEmployeeId(String employeeId);
}
