package com.xd.zt.mapper.analyse;


import com.xd.zt.domain.analyse.*;
import com.xd.zt.domain.business.flow.AnalyseProcess;
import com.xd.zt.domain.business.flow.JsPlumbBlock;
import com.xd.zt.domain.business.flow.JsPlumbConnect;
import com.xd.zt.domain.business.flow.LinkBlockInform;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AnalyseFlowMapper {
    boolean saveBlocks(List<JsPlumbBlock> blocks);
    boolean delSaveBlock(List<JsPlumbConnect> connects);
    boolean saveConnects(List<JsPlumbConnect> connects);
    int saveProcess(AnalyseProcess analyseProcess);

    boolean saveAnalyseModelController(AnalyseFlowprocess analyseFlowprocess);
    boolean saveProBlocks(@Param("processId") int processId, @Param("blocks") List<Integer> blockId);
    void deleteProcessName(@Param("Id") int Id);
    void deleteBlocksAndConnects(@Param("Id") int Id);
    List<AnalyseProcess> flowsList();
    List<JsPlumbBlock> getBlocks(int id);
    List<JsPlumbConnect>getConnects(int Id);
    List<AnalyseProcess>getAllList(@Param("name") String name);
    void updateLinkBlockInforms(LinkBlockInform linkBlockInform);
    void updateAnalyseFlowprocess(AnalyseFlowprocess analyseFlowprocess);
    void insertLinkBlockInforms(LinkBlockInform linkBlockInform);
    LinkBlockInform selectLinkBlockInform(@Param("uuid") String uuid);
    AnalyseFlowprocess slectProcessidToModelid(@Param("Id") int Id);
//    AnalyseUnit selectAnalyseUnit(@Param("unitid") Integer unitid);
    void insertProcessid(@Param("processid") Integer processid, @Param("unitid") Integer unitid);
//    AnalyseUnit selectUnitid(int Id);

}
