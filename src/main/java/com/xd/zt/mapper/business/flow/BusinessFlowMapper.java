package com.xd.zt.mapper.business.flow;



import com.xd.zt.domain.business.flow.AnalyseProcess;
import com.xd.zt.domain.business.flow.JsPlumbBlock;
import com.xd.zt.domain.business.flow.JsPlumbConnect;
import com.xd.zt.domain.business.flow.LinkBlockInform;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BusinessFlowMapper {
    boolean saveBlocks(List<JsPlumbBlock> blocks);
    boolean delSaveBlock(List<JsPlumbConnect> connects);
    boolean saveConnects(List<JsPlumbConnect> connects);
    int saveProcess(AnalyseProcess analyseProcess);
    boolean saveProBlocks(@Param("processId") int processId, @Param("blocks") List<Integer> blockId);
    void deleteProcessName(@Param("Id") int Id);
    void deleteBlocksAndConnects(@Param("Id") int Id);
    List<AnalyseProcess> flowsList();
    List<JsPlumbBlock> getBlocks(int id);
    List<JsPlumbConnect>getConnects(int Id);
    List<AnalyseProcess>getAllList(@Param("name") String name);
    void updateLinkBlockInforms(LinkBlockInform linkBlockInform);
    void insertLinkBlockInforms(LinkBlockInform linkBlockInform);
    LinkBlockInform selectLinkBlockInform(@Param("uuid") String uuid);

    //    张立军
    List<JsPlumbConnect> selectRepeatConnection();
    String selectMaxConnectId(@Param("connectionId")String connectionId);
    String selectMinConnectId(@Param("connectionId")String connectionId);
    void deleteGroupConnect(@Param("maxid")String maxid, @Param("minid")String minid);
}
