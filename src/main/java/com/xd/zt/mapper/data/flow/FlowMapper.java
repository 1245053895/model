package com.xd.zt.mapper.data.flow;

import com.xd.zt.domain.business.flow.AnalyseProcess;
import com.xd.zt.domain.business.flow.JsPlumbBlock;
import com.xd.zt.domain.business.flow.JsPlumbConnect;
import com.xd.zt.domain.business.flow.LinkBlockInform;
import com.xd.zt.domain.data.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FlowMapper {
//    boolean saveBlocks(List<JsPlumbBlock> blocks);
//    boolean delSaveBlock(List<JsPlumbConnect> connects);
//    boolean saveConnects(List<JsPlumbConnect> connects);
//    int saveProcess(AnalyseProcess analyseProcess);
//    boolean saveProBlocks(@Param("processId") int processId, @Param("blocks") List<Integer> blockId);
//    void deleteProcessName(@Param("Id") int Id);
//    void deleteBlocksAndConnects(@Param("Id") int Id);
//    List<AnalyseProcess> flowsList();
//    List<JsPlumbBlock> getBlocks(int id);
//    List<JsPlumbConnect>getConnects(int Id);
//    List<AnalyseProcess>getAllList(@Param("name") String name);
//    void updateLinkBlockInforms(LinkBlockInform linkBlockInform);
//    void insertLinkBlockInforms(LinkBlockInform linkBlockInform);
//    LinkBlockInform selectLinkBlockInform(@Param("uuid") String uuid);

    boolean saveBlocks(List<JsPlumbBlock> blocks);
    boolean dlsaveBlocks(List<DlJsPlumbBlock> blocks);
    boolean darsaveBlocks(List<DarJsPlumbBlock> blocks);
    boolean darsaveConnects(List<DarJsplumbConnect> connects);

    boolean delSaveBlock(List<JsPlumbConnect> connects);
    boolean saveConnects(List<JsPlumbConnect> connects);
    boolean dlsaveConnects(List<DlJsplumbConnect> connects);

    int saveProcess(AnalyseProcess analyseProcess);
    int dlsaveProcess(DlAnalyseProcess dlAnalyseProcess);
    int darsaveProcess(DarAnalyseProcess darAnalyseProcess);
    boolean saveProBlocks(@Param("processId") int processId, @Param("blocks") List<Integer> blockId);

    boolean dlsaveProBlocks(@Param("dlprocessId") int dlprocessId,@Param("blocks") List<Integer> blockId);

    boolean barsaveProBlocks(@Param("processId") int processId,@Param("blocks") List<Integer> blockId);


    void deleteProcessName(@Param("Id") int Id);

    void dLdeleteProcessName(int processid);


    void dardeleteProcessName(int processid);

    void deleteBlocksAndConnects(@Param("Id") int Id);

    void dLdeleteBlocks(int processid);
    void dardeleteBlocks(int processid);

    List<AnalyseProcess> flowsList();
    List<DlJsPlumbBlock> getBlocks(int id);
    List<DarJsPlumbBlock> darGetBlocks(int id);
    List<DarJsplumbConnect>darGetConnects(int id);

    List<DlJsPlumbBlock> dlgetBlocks(int processid);
    List<DarJsPlumbBlock> dargetBlocks(int processid);
    List<DlJsplumbConnect> dlgetConnects(int processid);
    List<DarJsplumbConnect> dargetConnects(int processid);

    List<DlJsplumbConnect>getConnects(int Id);
    List<AnalyseProcess>getAllList(@Param("name") String name);
    void updateLinkBlockInforms(LinkBlockInform linkBlockInform);
    void insertLinkBlockInforms(LinkBlockInform linkBlockInform);
    LinkBlockInform selectLinkBlockInform(@Param("uuid") String uuid);
    void deletesceneidTOBlock(int sceneid);
    void deletesceneidProcessName(int sceneid);
    List<JsPlumbBlock>  sceneidTOBlock(int sceneid);
    List<JsPlumbConnect>sceneidTOConnects(int sceneid);
    void insertdlblock(int sceneid);
    void insertdlconnect(int sceneid);
    void insertdlannlyse(int sceneid);
    void insertdlpro(int sceneid);
    void deletdlanalyse(int sceneid);
    AnalyseProcess sceneidToId(int sceneid);

    List<DlJsplumbConnect> selectRepeatConnection();
    String selectMaxConnectId(@Param("pageSourceId") String pageSourceId,@Param("pageTargetId") String pageTargetId);
    String selectMinConnectId(@Param("pageSourceId") String pageSourceId,@Param("pageTargetId") String pageTargetId);
    void deleteGroupConnect(@Param("maxid")String maxid,@Param("minid")String minid,@Param("pageSourceId") String pageSourceId,@Param("pageTargetId") String pageTargetId);


    List<DarJsplumbConnect> selectRepeatConnectionx();
    String selectMaxConnectIdx(@Param("pageSourceId") String pageSourceId,@Param("pageTargetId") String pageTargetId);
    String selectMinConnectIdx(@Param("pageSourceId") String pageSourceId,@Param("pageTargetId") String pageTargetId);
    void deleteGroupConnectx(@Param("maxid")String maxid,@Param("minid")String minid,@Param("pageSourceId") String pageSourceId,@Param("pageTargetId") String pageTargetId);


    DatamodelLink processidTomodeid(int processid);
    void deletdatalinkToProcessid(int processid);
}
