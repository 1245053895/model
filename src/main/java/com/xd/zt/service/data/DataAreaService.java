package com.xd.zt.service.data;


import com.xd.zt.domain.data.DatamodelInfo;
import com.xd.zt.domain.data.DatamodelSource;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;


@Service
public interface DataAreaService {
    void saveDataAreaResult(@Param("blockname") String blockname, @Param("flowchart") String flowchart,@Param("areaid") String areaid);

    String selectInstanceName(String modelinstancename);

    void insertExample(@Param("modelid") String modelid, @Param("modelinstancename") String modelinstancename, @Param("analyzmodel") String analyzmodel,@Param("areaid") Integer areaid);

    String selectLinkName(String areaid);

    DatamodelSource selectSourceById(@Param("sourceid") Integer sourceid);

    Integer selectAreaid();
    Integer maxAreaId();
    public Integer getLinkIdByAreaid(Integer areaid);
    public void  processAreaInfo(DatamodelInfo datamodelInfo);
}
