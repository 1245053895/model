package com.xd.zt.mapper.data;


import com.xd.zt.domain.analyse.Algorithm;
import com.xd.zt.domain.business.BusinessQuestion;
import com.xd.zt.domain.data.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
public interface SourceMapper {

    public List<DatamodelSource> moHuDataSource(String sourcename);


    public List<DatamodelLink> dataModelLink(@Param("modeid") Integer modeid);

    public void  deleteLink(@Param("linkid") Integer linkid);


    public List<DatamodelArea> selectModelArea(@Param("modeid") Integer modeid);

    public void  deleteArea(@Param("areaid") Integer areaid);

    public List<DatamodelInfo> selectBlockName(Integer blockid);
    public DatamodelInfo selectResultName(Integer dataresultid);
    public DatamodelBlock selectBlockById(Integer blockid);

    public DatamodelInfo selectBaoName(Integer baoid);
    public List<DatamodelInfo> selectBaoName1(Integer baoid);
/*    public DatamodelInfo resultBaoName(Integer dataresultid);*/
    public DatamodelBao selectBaoById(Integer baoid);
    public void insertlinkModeid(@Param("modeid") Integer modeid, @Param("processid") Integer processid);
    public void insertAreaModeid(@Param("modeid") Integer modeid, @Param("processid") Integer processid);
    public DatamodelLink allLinkByModeid(Integer modeid);
    DatamodelInfo selectBaoName(int baoid);
    public Integer selectlastprocessid();
    public void updateLinkprocessid(@Param("modeid") String modeid, @Param("processid") String processid);

    public DatamodelLink getLinkId(Integer linkid);
    DatamodelArea getAreaId(Integer areaid);
    DatamodelInfo areaAllExcel(@Param("areaid") Integer areaid);

    DatamodelArea modelIdByAreaid(@Param("areaid") Integer areaid);
    List<DatamodelSource> datamodelSourceByModeId(@Param("modeid") String modeid,@Param("status") String status);
    //List<DatamodelSource> datamodelSourceByModeId(@Param("modeid") String modeid);
    List<BusinessQuestion> selectquesInfo();
    List<BusinessQuestion> selectquesList(String questionid);
    List<Algorithm> selectAlgorithm();

    //
    void insertlinkModeidx(DatamodelLink datamodelLink);
    void insertdataLinkx(@Param("linkid")String linkid,@Param("processid")String processid,@Param("modeid")String modeid);
    public DlAnalyseProcess selectlastprocessidx();
    DarAnalyseProcess selectlastprocessidxx();
    BusinessQuestion modelidToscenceid(Integer modeid);
    DatamodelLink processidTomodeid(Integer processid);
    DatamodelArea darprocessidTomodeid(Integer processid);

     DatamodelName getQuestionId(Integer modelid);
     public List<DatamodelSource> getSourcesByStatusAndMoelId(@Param("modeid") Integer modeid);
     public DatamodelArea areaByAreaId(@Param("areaid") Integer areaid);

     DatamodelSource selectSourceById(@Param("sourceid") Integer sourceid);


    List<DatamodelInfo> infoByAreaId(@Param("areaid")Integer areaid);

    List<DatamodelInfo> selectInfoList(@Param("modelid")Integer modelid);
}
