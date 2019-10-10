package com.xd.zt.service.data;




import com.xd.zt.domain.business.flow.AnalyseProcess;
import com.xd.zt.domain.business.flow.JsPlumbBlock;
import com.xd.zt.domain.business.flow.JsPlumbConnect;
import com.xd.zt.domain.business.flow.LinkBlockInform;
import com.xd.zt.domain.data.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FlowService {
    String saveProcess(String processName, List<JsPlumbBlock> blocks, List<JsPlumbConnect> connects)throws Exception;
    String dlsaveProcess(String processName, List<DlJsPlumbBlock> blocks, List<DlJsplumbConnect> connects)throws Exception;
    String darsaveProcess(String processName, List<DarJsPlumbBlock> blocks,List<DarJsplumbConnect> connects)throws Exception;
    List<AnalyseProcess> getFlowList();
    List<DlJsPlumbBlock> getBlocks(int id);
    List<DlJsplumbConnect>getConnects(int id);
    String delSaveBlock(List<JsPlumbConnect> connects);
    void deleteProcessName(int Id);
    void deleteBlocksAndConnects(int Id);
    List<AnalyseProcess> getAllList(String name);
    void modifyLinkBlockInforms(LinkBlockInform linkBlockInform);
    void addLinkBlockInforms(LinkBlockInform linkBlockInform);
    LinkBlockInform getLinkBlockInform(String uuid);
    List<JsPlumbBlock> sceneidTOBlock(int sceneid);
    List<JsPlumbConnect>sceneidTOConnects(int sceneid);
    void deletesceneidTOBlock(int sceneid);
    void deletesceneidProcessName(int sceneid);
    void insertdlblock(int sceneid);
    void insertdlconnect(int sceneid);
    void insertdlannlyse(int sceneid);
    void insertdlpro(int sceneid);
    void deletdlanalyse(int sceneid);
    AnalyseProcess sceneidToId(int sceneid);
    void dLdeleteProcessName(int processid);
    void dardeleteProcessName(int processid);
    void dLdeleteBlocks(int processid);
    void dardeleteBlocks(int processid);

    List<DlJsplumbConnect> selectRepeatConnection();
    String selectMaxConnectId(@Param("pageSourceId") String pageSourceId,@Param("pageTargetId") String pageTargetId);
    String selectMinConnectId(@Param("pageSourceId") String pageSourceId,@Param("pageTargetId") String pageTargetId);
    void deleteGroupConnect(@Param("maxid")String maxid,@Param("minid")String minid,@Param("pageSourceId") String pageSourceId,@Param("pageTargetId") String pageTargetId);

    List<DarJsplumbConnect> dargetConnects(int processid);
    List<DlJsPlumbBlock> dlgetBlocks(int processid);
    List<DarJsPlumbBlock> dargetBlocks(int processid);
    List<DlJsplumbConnect> dlgetConnects(int processid);
    List<DarJsplumbConnect> processid(int processid);

    DatamodelLink processidTomodeid(int processid);
    void deletdatalinkToProcessid(int processid);
    List<DarJsPlumbBlock> darGetBlocks(int id);
    List<DarJsplumbConnect>darGetConnects(int id);

    List<DarJsplumbConnect> selectRepeatConnectionx();
    String selectMaxConnectIdx(@Param("pageSourceId") String pageSourceId,@Param("pageTargetId") String pageTargetId);
    String selectMinConnectIdx(@Param("pageSourceId") String pageSourceId,@Param("pageTargetId") String pageTargetId);
    void deleteGroupConnectx(@Param("maxid")String maxid,@Param("minid")String minid,@Param("pageSourceId") String pageSourceId,@Param("pageTargetId") String pageTargetId);

}
