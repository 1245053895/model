package com.xd.zt.serviceImpl.data;


import com.xd.zt.domain.analyse.Algorithm;
import com.xd.zt.domain.business.BusinessQuestion;
import com.xd.zt.domain.data.*;
import com.xd.zt.mapper.data.SourceMapper;
import com.xd.zt.service.data.SourceService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SourceServiceImpl implements SourceService {
    @Autowired
    private SourceMapper sourceMapper;


    @Override
    public List<DatamodelSource> moHuDataSource(@Param("res") String sourcename) {
        List<DatamodelSource> datamodelSourceList = sourceMapper.moHuDataSource(sourcename);
        return datamodelSourceList;
    }

    @Override
    public List<DatamodelLink> dataModelLink(@Param("modeid") Integer modeid) {
        List<DatamodelLink> datamodelLinkList = sourceMapper.dataModelLink(modeid);
        return datamodelLinkList;
    }

    @Override
    public void deleteLink(Integer linkid) {
        sourceMapper.deleteLink(linkid);
    }

    @Override
    public List<DatamodelArea> selectModelArea(@Param("modeid") Integer modeid) {
        List<DatamodelArea> datamodelAreaList = sourceMapper.selectModelArea(modeid);
        return datamodelAreaList;
    }

    @Override
    public void deleteArea(Integer areaid) {
        sourceMapper.deleteArea(areaid);
    }

    @Override
    public List<DatamodelInfo> selectBlockName(Integer blockid) {
        List<DatamodelInfo> datamodelInfo = sourceMapper.selectBlockName(blockid);
        return datamodelInfo;
    }

    @Override
    public DatamodelInfo selectResultName(@Param("dataresultid") Integer dataresultid) {
        DatamodelInfo datamodelInfo = sourceMapper.selectResultName(dataresultid);
        return datamodelInfo;
    }


    @Override
    public void insertlinkModeid(@Param("modeid") Integer modeid, @Param("processid") Integer processid) {
        sourceMapper.insertlinkModeid(modeid, processid);
    }

    @Override
    public DatamodelLink allLinkByModeid(Integer modeid) {
        DatamodelLink datamodelLink = sourceMapper.allLinkByModeid(modeid);
        return datamodelLink;
    }

    @Override
    public Integer selectlastprocessid() {
        Integer processid = sourceMapper.selectlastprocessid();
        return processid;
    }

    @Override
    public void updateLinkprocessid(String modeid, String processid) {
        sourceMapper.updateLinkprocessid(modeid, processid);

    }

    @Override
    public DatamodelLink getLinkId(Integer linkid) {
        DatamodelLink datamodelLink = sourceMapper.getLinkId(linkid);
        return datamodelLink;
    }

    @Override
    public DatamodelArea getAreaId(Integer areaid) {
        DatamodelArea datamodelArea = sourceMapper.getAreaId(areaid);
        return datamodelArea;
    }

    @Override
    public DatamodelInfo areaAllExcel(Integer areaid) {
        DatamodelInfo datamodelInfo = sourceMapper.areaAllExcel(areaid);
        return datamodelInfo;
    }

    @Override
    public DatamodelArea modelIdByAreaid(Integer areaid) {
        DatamodelArea datamodelArea = sourceMapper.modelIdByAreaid(areaid);
        return datamodelArea;
    }

    @Override
    public List<DatamodelSource> datamodelSourceByModeId(@Param("modeid") String modeid,@Param("status") String status) {
        List<DatamodelSource> datamodelSourceList = sourceMapper.datamodelSourceByModeId(modeid,status);
        return datamodelSourceList;
    }

    @Override
    public void insertAreaModeid(Integer modeid, Integer processid) {
        sourceMapper.insertAreaModeid(modeid, processid);
    }

    /*    @Override
        public DatamodelInfo resultBaoName(Integer dataresultid) {
            DatamodelInfo datamodelInfo= sourceMapper.resultBaoName(dataresultid);
            return datamodelInfo;
        }*/
    @Override
    public List<BusinessQuestion> selectquesInfo() {
        List<BusinessQuestion> businessQuestionList = sourceMapper.selectquesInfo();
        return businessQuestionList;
    }

    @Override
    public List<BusinessQuestion> selectquesList(String questionid) {
        return sourceMapper.selectquesList(questionid);
    }

    @Override
    public List<Algorithm> selectAlgorithm() {
        return sourceMapper.selectAlgorithm();
    }


    @Override
    public DatamodelInfo selectBaoName(int baoid) {
        return sourceMapper.selectBaoName(baoid);
    }

    @Override
    public DatamodelBao selectBaoById(Integer baoid) {
        return sourceMapper.selectBaoById(baoid);
    }

    @Override
    public DatamodelBlock selectBlockById(Integer blockid) {
        return sourceMapper.selectBlockById(blockid);
    }


    //
    @Override
    public void insertlinkModeidx(DatamodelLink datamodelLink) {
        sourceMapper.insertlinkModeidx(datamodelLink);
    }

    @Override
    public void insertdataLinkx(String linkid, String processid, String modeid) {
        sourceMapper.insertdataLinkx(linkid,processid,modeid);
    }


    @Override
    public DlAnalyseProcess selectlastprocessidx() {
        DlAnalyseProcess dlAnalyseProcess = sourceMapper.selectlastprocessidx();
        return dlAnalyseProcess;
    }
    @Override
    public DarAnalyseProcess selectlastprocessidxx() {
        return sourceMapper.selectlastprocessidxx();
    }

    @Override
    public BusinessQuestion modelidToscenceid(Integer modeid) {
        return sourceMapper.modelidToscenceid(modeid);
    }

    @Override
    public DatamodelLink processidTomodeid(Integer processid) {
        DatamodelLink datamodelLink = sourceMapper.processidTomodeid(processid);
        return datamodelLink;
    }

    @Override
    public DatamodelArea darprocessidTomodeid(Integer processid) {
        return sourceMapper.darprocessidTomodeid(processid);
    }

    @Override
    public DatamodelName getQuestionId(@Param("modeid") Integer modelid) {
       DatamodelName datamodelName= sourceMapper.getQuestionId(modelid);
        return datamodelName;
    }

    @Override
    public List<DatamodelSource> getSourcesByStatusAndMoelId(Integer modeid) {
        List<DatamodelSource> datamodelSourceList= sourceMapper.getSourcesByStatusAndMoelId(modeid);
        return datamodelSourceList;
    }

    @Override
    public DatamodelArea areaByAreaId(Integer areaid) {
      DatamodelArea datamodelArea=  sourceMapper.areaByAreaId(areaid);
        return datamodelArea;
    }
}
