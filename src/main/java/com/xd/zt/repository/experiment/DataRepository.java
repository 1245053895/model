package com.xd.zt.repository.experiment;

import com.xd.zt.domain.experiment.ExperimentData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends CrudRepository<ExperimentData,Integer> {
}
