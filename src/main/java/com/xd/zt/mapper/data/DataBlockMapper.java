package com.xd.zt.mapper.data;


import com.xd.zt.domain.data.DatamodelBao;
import com.xd.zt.domain.data.DatamodelBlock;
import com.xd.zt.domain.data.DatamodelInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DataBlockMapper {
    List<DatamodelBlock> selectBlockById(@Param("modelid") Integer modelid);

    List<DatamodelInfo> selectDataAreaResultById(@Param("modelid") Integer modelid);

    void saveDataBlockResult(@Param("modelid") String modelid, @Param("blockname") String blockname, @Param("flowchart") String flowchart,@Param("dataarea") String dataarea);

    void deleteblock(@Param("blockid") String blockid);

    DatamodelInfo selectDataAreaResultByDatablock(@Param("datablock") String datablock);

    List<DatamodelBao> selectBaoById(@Param("modelid") Integer modelid);

    List<DatamodelInfo> selectDataBlockResultById(@Param("modelid") Integer modelid);

    String selectAreaName(Integer blockid);

    DatamodelBlock selectCreateProcess(Integer blockid);

    void deletebao(@Param("baoid") Integer baoid);
    void deletebaoFromInfo(@Param("baoid") Integer baoid);
    String selectDataBaoByBaoName(@Param("baoname") String baoname);
    public  List<DatamodelInfo> selectBaoName(int baoid);
    void saveDataBaoResult(@Param("modelid") String modelid, @Param("baoname") String baoname, @Param("blockid") String blockid);
    void saveDataBaoCsvResult(@Param("dataresultname") String dataresultname, @Param("databao") String databao, @Param("blockid") String blockid, @Param("modelid") String modelid, @Param("dataaddr") String dataaddr);
    String selectAreaId(@Param("dataresultid") String dataresultid);
}
