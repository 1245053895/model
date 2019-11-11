package com.xd.zt.mapper.analyse;


import com.xd.zt.domain.analyse.*;
import com.xd.zt.domain.data.DatamodelInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Mapper
public interface AnalyseMapper {
    List<AnalyseModelProcess> selectAnalyseModel(@Param("analysemodelid") Integer analysemodelid);
    public void insertAnalyseModel(@Param("modelname") String modelname, @Param("modelprocess") String modelprocess,@Param("analysemodelid")String analysemodelid);
    void deleteModel(@Param("modelid") String modelid);
    public String selectAnalyseProcess(@Param("modelid") Integer modelid);
    public void updateAnalyse(@Param("modelid") String modelid, @Param("modelname") String modelname, @Param("modelprocess") String modelprocess);
    List<AnalyseInstance> selectAnalyseExample(@Param("modelid") Integer modelid);
    public Integer selectModelid(@Param("modelinstanceid") Integer modelinstanceid);
    String selectInstanceName(@Param("modelinstancename") String modelinstancename);

    void insertExample(@Param("modelid") String modelid, @Param("modelinstancename") String modelinstancename, @Param("parameters") String parameters);


    void deleteExample(@Param("modelinstanceid") String modelinstanceid);

    void deleteanalyseprocess(@Param("processid") Integer processid);

    String selectParams(@Param("modelinstanceid") String modelinstanceid);

    List<AnalyticsTask> selectTask();

    List<Algorithm> selectAlgorithm();

    Algorithm selectAlgorithmParams(@Param("algorithmname") String algorithmname);

    Integer  selectProcessid(@Param("flowprocessid")String flowprocessid);


    List<DatamodelInfo> selectDataResult(@Param("modelid")Integer modelid);

    void saveAnalyseCsv(AnalyseCsv analyseCsv);
    List<AnalyseCsv> selectCsvExit(@Param("modelinstanceid")Integer modelinstanceid);
    void deleteAnalyseCsv(@Param("modelinstanceid")Integer modelinstanceid);
    Integer selectIdInModelAnalyseProcess(@Param("modelid")Integer modelid);
}
