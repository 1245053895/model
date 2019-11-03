package com.xd.zt.service.data;


import com.xd.zt.domain.data.DatamodelBao;
import com.xd.zt.domain.data.DatamodelBlock;
import com.xd.zt.domain.data.DatamodelInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DataBlockService {
    List<DatamodelBlock> selectBlockById(@Param("modelid") Integer modelid);

    List<DatamodelInfo> selectDataAreaResultById(@Param("modelid") Integer modelid);

    void saveDataBlockResult(@Param("modelid") String modelid, @Param("blockname") String blockname, @Param("flowchart") String flowchart,@Param("dataarea") String dataarea);

    void deleteblock(@Param("blockid") String blockid);

    DatamodelInfo selectDataAreaResultByDatablock(String blockid);

    List<DatamodelInfo> selectDataBloakById(Integer blockid);

    List<DatamodelBao> selectBaoById(@Param("modelid") Integer modelid);

    List<DatamodelInfo> selectDataBlockResultById(@Param("modelid") Integer modelid);

    String selectAreaName(Integer blockid);

    DatamodelBlock selectCreateProcess(Integer blockid);

    void saveDataBaoResult(@Param("modelid") String modelid, @Param("baoname") String baoname, @Param("blockid") String blockid);

    void deletebao(@Param("baoid") Integer baoid);

    void deletebaoFromInfo(@Param("baoid") Integer baoid);

    void saveDataBaoCsvResult(String dataresultname, String databao, String blockid, String modelid, String dataaddr);

    String selectDataBaoByBaoName(@Param("baoname") String baoname);

    String selectAreaId(@Param("dataresultid") String dataresultid);
    Integer maxBlockId();
    public Integer getAreaIdByBlockId(Integer blockid);
    void processBlockInfo(DatamodelInfo datamodelInfo);
    void BlockInstance(@Param("modelid") String modelid, @Param("modelinstancename") String modelinstancename, @Param("analyzmodel") String analyzmodel,@Param("blockid") String blockid);
    Integer maxBaoId();


}
