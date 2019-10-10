package com.xd.zt.service.data;

import com.xd.zt.domain.data.DatamodelBao;
import com.xd.zt.domain.data.DatamodelJi;
import com.xd.zt.domain.data.DatamodelName;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DataAggregationService {
    void saveDataPack(@Param("jiname") String jiname, @Param("jitype") String jitype, @Param("jitime") String jitime, @Param("baoid") String baoid, @Param("modelid") String modelid);

    DatamodelJi selectDataCollect(Integer jiid);

    DatamodelBao selectDataBaoByBaoId(Integer baoid);

    List<DatamodelJi> selectDataCollec(Integer modelid);

    List<DatamodelBao> selectDataPack(Integer modelid);
    //按名称进行模糊搜索
    List<DatamodelBao> findByPacknameLike(@Param("modelid") String modelid, String name);

    void deletDataCollect(@Param("datacollecid") Integer datacollecid);
    public void saveModelName(String modelname);

    //
    String addDatamoddelName(DatamodelName datamodelName);
}
