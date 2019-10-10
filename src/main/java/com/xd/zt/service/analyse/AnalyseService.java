package com.xd.zt.service.analyse;


import com.xd.zt.domain.analyse.Algorithm;
import com.xd.zt.domain.analyse.AnalyseInstance;
import com.xd.zt.domain.analyse.AnalyseModelProcess;
import com.xd.zt.domain.analyse.AnalyticsTask;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AnalyseService {
    List<AnalyseModelProcess> selectAnalyseModel(@Param("analysemodelid") Integer analysemodelid);

    public void insertAnalyseModel(@Param("modelname") String modelname, @Param("modelprocess") String modelprocess,@Param("analysemodelid")String analysemodelid);

    void deleteModel(@Param("modelid") String modelid);

    public String selectAnalyseProcess(@Param("modelid") Integer modelid);

    public void updateAnalyse(@Param("modelid") String modelid, @Param("modelname") String modelname, @Param("modelprocess") String modelprocess);

    public Integer selectModelid(@Param("modelinstanceid") Integer modelinstanceid);

    String selectInstanceName(@Param("modelinstancename") String modelinstancename);


    List<AnalyseInstance> selectAnalyseExample(@Param("modelid") Integer modelid);

    void insertExample(@Param("modelid") String modelid, @Param("modelinstancename") String modelinstancename, @Param("parameters") String parameters);

    void deleteExample(@Param("modelinstanceid") String modelinstanceid);

    String selectParams(@Param("modelinstanceid") String modelinstanceid);

    List<AnalyticsTask> selectTask();


    List<Algorithm> selectAlgorithm();

    //通过算法名查找算法参数
    Algorithm selectAlgorithmParams(@Param("algorithmname") String algorithmname);

//    List<AnalyseProcess> selectAnalyseId(@Param("id") Integer id);
    void deleteanalyseprocess(@Param("processid") Integer processid);

    Integer  selectProcessid(@Param("flowprocessid")String flowprocessid);

}
