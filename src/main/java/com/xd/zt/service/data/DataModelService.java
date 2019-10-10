package com.xd.zt.service.data;


import com.xd.zt.domain.business.BusinessModel;
import com.xd.zt.domain.business.BusinessQuestion;
import com.xd.zt.domain.data.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DataModelService {
    public List<DatamodelName> selectdatamodel();

    Iterable<DatamodelName> findByModelnameLike(String res);

    //数据模型删除
    void dataModelDelete(Integer modelid);

    //查询数据模型是否存在
    DatamodelName isExitDataModel(Integer modelid);

    void savedatamodel(@Param("datamodelname") String datamodelname);

    List<DatamodelLink> dataModelLink(Integer modeid);

    List<DatamodelArea> dataModelLogic(Integer modeid);

    String dataModelLinkName(Integer linkid);

    String areaResultName(Integer areaid);

    List<String> blockResultName(@Param("blockid") String blockid);

    String blockResultAddr(Integer blockid);

    List<DatamodelBlock> dataModelBlock(Integer modeid);

    List<String> selectAreaName(Integer blockid);

    List<DatamodelBao> selectBaoId(Integer modeid);

    List<DatamodelInfo> selectBaoInfo(Integer baoid);

    List<DatamodelJi> selectDataCollect(Integer modeid);

    List<Integer> blockResultid(@Param("blockid") String blockid);

    String areaResultid(Integer areaid);

    //
    List<BusinessModel> allBusinessModel();
    List<BusinessQuestion> selectquestion(int businessid);
}
