package com.xd.zt.service.business;




import com.xd.zt.domain.business.flow.AnalyseProcess;
import com.xd.zt.domain.business.flow.JsPlumbBlock;
import com.xd.zt.domain.business.flow.JsPlumbConnect;
import com.xd.zt.domain.business.flow.LinkBlockInform;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BusinessFlowService {
    String saveProcess(String processName, List<JsPlumbBlock> blocks, List<JsPlumbConnect> connects)throws Exception;
    List<AnalyseProcess> getFlowList();
    List<JsPlumbBlock> getBlocks(int id);
    List<JsPlumbConnect>getConnects(int id);
    String delSaveBlock(List<JsPlumbConnect> connects);
    void deleteProcessName(int Id);
    void deleteBlocksAndConnects(int Id);
    List<AnalyseProcess> getAllList(String name);
    void modifyLinkBlockInforms(LinkBlockInform linkBlockInform);
    void addLinkBlockInforms(LinkBlockInform linkBlockInform);
    LinkBlockInform getLinkBlockInform(String uuid);

    //    张立军
    List<JsPlumbConnect> selectRepeatConnection();
    String selectMaxConnectId(@Param("connectionId")String connectionId);
    String selectMinConnectId(@Param("connectionId")String connectionId);
    void deleteGroupConnect(@Param("maxid")String maxid, @Param("minid")String minid);
}
