package com.xd.zt.serviceImpl.business;


import com.xd.zt.domain.business.BusinessQuestion;
import com.xd.zt.domain.business.BusinessScene;
import com.xd.zt.domain.business.flow.JsPlumbBlock;
import com.xd.zt.mapper.business.QuestionBuildMapper;
import com.xd.zt.service.business.QuestionBuildService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionBuildServiceImpl implements QuestionBuildService {
    @Autowired
    public QuestionBuildMapper questionBuildMapper;

    @Override
    public List<BusinessQuestion> selectbusinessquestion(@Param("businessid") String businessid) {
        List<BusinessQuestion> businessQuestionList = questionBuildMapper.selectbusinessquestion(businessid);
        return businessQuestionList;
    }

    @Override
    public List <BusinessScene> selectAllSceneNode(Integer businessid) {
        return questionBuildMapper.selectAllSceneNode(businessid);
    }

    @Override
    public String selectObjectNode(int blockid) {
        return questionBuildMapper.selectObjectNode(blockid);
    }

    /*@Override
    public String selectChildNodes() {
        List<JsPlumbBlock> list= questionBuildMapper.selectChildNodes();
        int status = 0;
        String info = "";
        String data = "[";
        for (int i=0;i<list.size();i++){
            data += "{"+list.get(i).toString()+"},";
        }
        if(list==null){
            status = 200;
            info="查询无此数据";
        }else{
            status = 200;
            info="返回成功";
        }
        data = data.substring(0,data.length() - 1);
        data+="]";
        return JsonInfo.JsonInfo(status,info,data);
    }*/
    public List<JsPlumbBlock> selectChildNodes(int sceneid) {
       return questionBuildMapper.selectChildNodes(sceneid);
    }

    @Override
    public String selectsceneid(@Param("blockid") String blockid) {
        String sceneid = questionBuildMapper.selectsceneid(blockid);
        return sceneid;
    }

    @Override
    public void insertquestion(@Param("businessid")String businessid, @Param("blockid")String blockid,@Param("sceneid") String sceneid) {
   questionBuildMapper.insertquestion(businessid,blockid,sceneid);
    }

    @Override
    public void savedescribe(@Param("questioname")String questioname,@Param("questiondes")String questiondes,@Param("formulatdes") String formulatdes,@Param("blockid") String blockid) {
        questionBuildMapper.savedescribe(questioname,questiondes,formulatdes,blockid);
    }

    @Override
    public void deletequestion(@Param("questionid") String questionid) {
        questionBuildMapper.deletequestion(questionid);
    }

    @Override
    public BusinessQuestion selectquestionbyblockid(@Param("blockid") String blockid) {
        BusinessQuestion businessQuestion =questionBuildMapper.selectquestionbyblockid(blockid);
        return businessQuestion;
    }

    @Override
    public void updatePicture(BusinessQuestion businessQuestion) {
        questionBuildMapper.updatePicture(businessQuestion);
    }

    @Override
    public BusinessQuestion getPictureByQestionId(@Param("questionid") String questionid) {
      BusinessQuestion businessQuestion=  questionBuildMapper.getPictureByQestionId(questionid);
        return businessQuestion;
    }


}
