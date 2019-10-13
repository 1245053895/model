package com.xd.zt.serviceImpl.data;



import com.xd.zt.domain.data.DatamodelInfo;
import com.xd.zt.domain.data.DatamodelSource;
import com.xd.zt.mapper.data.DataAreaMapper;
import com.xd.zt.service.data.DataAreaService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DataAreaServiceImpl implements DataAreaService {

@Autowired
private DataAreaMapper dataAreaMapper;

    @Override
    public void saveDataAreaResult(@Param("blockname") String blockname,@Param("flowchart") String flowchart,@Param("areaid") String areaid) {
        dataAreaMapper.saveDataAreaResult(blockname,flowchart,areaid);
    }

    @Override
    public String selectInstanceName(String modelinstancename) {
        return dataAreaMapper.selectInstanceName(modelinstancename);
    }

    @Override
    public void insertExample(@Param("modelid") String modelid, @Param("modelinstancename") String modelinstancename, @Param("analyzmodel") String analyzmodel,@Param("areaid") Integer areaid) {
        dataAreaMapper.insertExample(modelid,modelinstancename,analyzmodel,areaid);
    }

    @Override
    public String selectLinkName(String areaid) {
        return dataAreaMapper.selectLinkName( areaid);
    }

    @Override
    public DatamodelSource selectSourceById(Integer sourceid) {
        return dataAreaMapper.selectSourceById(sourceid);
    }

    @Override
    public Integer selectAreaid() {
        return dataAreaMapper.selectAreaid();
    }

    @Override
    public Integer maxAreaId() {
       Integer areaid= dataAreaMapper.maxAreaId();
        return areaid;
    }

    @Override
    public Integer getLinkIdByAreaid(Integer areaid) {
      Integer datalink=  dataAreaMapper.getLinkIdByAreaid(areaid);
        return datalink;
    }

    @Override
    public void processAreaInfo(DatamodelInfo datamodelInfo) {
        dataAreaMapper.processAreaInfo(datamodelInfo);

    }
}
