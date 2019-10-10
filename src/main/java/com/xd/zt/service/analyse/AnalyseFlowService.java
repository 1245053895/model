package com.xd.zt.service.analyse;



import com.xd.zt.domain.analyse.*;
import com.xd.zt.domain.business.flow.AnalyseProcess;
import com.xd.zt.domain.business.flow.JsPlumbBlock;
import com.xd.zt.domain.business.flow.JsPlumbConnect;
import com.xd.zt.domain.business.flow.LinkBlockInform;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AnalyseFlowService {
    String saveProcess(String processName, List<JsPlumbBlock> blocks, List<JsPlumbConnect> connects, int modelid)throws Exception;

    String saveunitflowPage(String processName, List<JsPlumbBlock> blocks, List<JsPlumbConnect> connects, int unitid)throws Exception;

    List<AnalyseProcess> getFlowList();
    List<JsPlumbBlock> getBlocks(int Id);
    List<JsPlumbConnect>getConnects(int Id);
    String delSaveBlock(List<JsPlumbConnect> connects);
    void deleteProcessName(int Id);
    void deleteBlocksAndConnects(int Id);
    List<AnalyseProcess> getAllList(String name);
    void modifyLinkBlockInforms(LinkBlockInform linkBlockInform);
    void addLinkBlockInforms(LinkBlockInform linkBlockInform);
    LinkBlockInform getLinkBlockInform(String uuid);
    AnalyseFlowprocess slectProcessidToModelid(int Id);
    void insertProcessid(@Param("processid") Integer processid, @Param("unitid") Integer unitid);
//    AnalyseUnit selectAnalyseUnit(@Param("unitid") Integer unitid);

//    AnalyseUnit selectUnitid(int Id);
}
