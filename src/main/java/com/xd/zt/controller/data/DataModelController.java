package com.xd.zt.controller.data;



import com.xd.zt.domain.business.BusinessModel;
import com.xd.zt.domain.business.BusinessQuestion;
import com.xd.zt.domain.data.*;
import com.xd.zt.repository.data.FileRepository;
import com.xd.zt.service.data.DataAggregationService;
import com.xd.zt.service.data.DataAreaService;
import com.xd.zt.service.data.DataModelService;
import com.xd.zt.service.data.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.*;

@RequestMapping("/data")
@Controller

public class DataModelController {

    @Autowired
    private DataModelService dataModelService;
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private DataAreaService dataAreaService;
    @Autowired
    private SourceService sourceService;

    @Autowired
    private DataAggregationService dataAggregationService;
    @Autowired
    private SessionRepository sessionRepository;
    @RequestMapping("/index")
    public ModelAndView selectAnalyz(Model model,@RequestParam(value = "sessionId")String sessionId) {
        Session session = sessionRepository.findById(sessionId);
        System.out.printf("\n\n数据建模的sessionId:"+sessionId);
        try{
            String SessionId = session.getAttribute("SessionId");
            System.out.printf("\n\n检查sessionId："+SessionId);
            model.addAttribute("SessionId",SessionId);
            model.addAttribute("dataModelList", dataModelService.selectdatamodel());
            return new ModelAndView("data/index", "modelModel", model);
        }
        catch (Exception e){
//            return "redirect:http://10.101.201.154:8080/#/home/index";
            System.out.printf("\n\n检查sessionId不存在");
            return new ModelAndView(new RedirectView("http://10.101.201.173:80/login"));
        }

    }

    @RequestMapping("/welcome")
    public ModelAndView welcome(Model model){
        ModelAndView modelAndView = new ModelAndView("/welcome");
        List<BusinessModel> allbusinessmodle = dataModelService.allBusinessModel();
        model.addAttribute("allbusinessmodle",allbusinessmodle);
        return new ModelAndView ("data/welcome","Model",model);

    }


    @RequestMapping("/dataPreprocess")
    public String dataPreprocess() {
        return "data/dataPreprocess";
    }


    @RequestMapping("/dataPacket")
    public String dataPacket() {
        return "data/dataPacket";
    }

    @PostMapping(value = "/savedatamodel")
    @ResponseBody
    public String datamodel(@RequestBody String data) {
        //System.out.println(data);
        dataModelService.savedatamodel(data);
        return null;
    }

    @RequestMapping("/dataModelManage")
    public ModelAndView dataModelManage(Model model) {
        List<DatamodelName> dataModelList = dataModelService.selectdatamodel();
        model.addAttribute("dataModelList", dataModelList);
        return new ModelAndView("data/dataModelManage", "modelModel", model);

    }


    //数据模型数据源显示
//    @GetMapping(value = "/dataSourceManage/{modeid}")
//    public ModelAndView lookFile(Model model, @PathVariable("modeid") Integer modeid) {
//        Iterable<DatamodelSource> datamodelSourceList = fileRepository.findByModeid(modeid);
//        model.addAttribute("datamodelSourceList", datamodelSourceList);
//        model.addAttribute("modeid", modeid);
//        return new ModelAndView("data/dataSourceManage", "modelModel", model);
//    }
    //数据模型数据链回显
//    @RequestMapping("/dataLinkList/{modeid}")
//    public ModelAndView dataLinkList(Model model, @PathVariable("modeid") Integer modeid) {
//        //System.out.println(modeid);
//        List<DatamodelLink> datamodelLinkList = dataModelService.dataModelLink(modeid);
//        model.addAttribute("datamodelLinkList", datamodelLinkList);
//        model.addAttribute("modeid", modeid);
//        return new ModelAndView("data/linkReview", "modelModel", model);
//    }
    //数据模型数据逻辑关系图回显
    @RequestMapping("/dataLogicList/{modeid}")
    public ModelAndView dataLogicList(Model model, @PathVariable("modeid") Integer modeid) {
        //System.out.println(modeid);
        List<DatamodelArea> dataLogicList = dataModelService.dataModelLogic(modeid);
        for (DatamodelArea datamodelArea : dataLogicList) {
            // System.out.println(datamodelArea.getAreaname());
            Integer linkid = datamodelArea.getLinkid();
            String dataLinkName = dataModelService.dataModelLinkName(linkid);
            datamodelArea.setLinkname(dataLinkName);
        }
        model.addAttribute("dataLogicList", dataLogicList);
        model.addAttribute("modeid", modeid);
        return new ModelAndView("data/logicReview", "modelModel", model);
    }
    //数据模型数据区结果回显
    @RequestMapping("/dataArea/{modeid}")
    public ModelAndView dataArea(Model model, @PathVariable("modeid") Integer modeid) {
        //System.out.println(modeid);
        List<DatamodelArea> dataAreaList = dataModelService.dataModelLogic(modeid);
        for (DatamodelArea datamodelArea : dataAreaList) {
            //System.out.println(datamodelArea.getAreaname());
            Integer areaid = datamodelArea.getAreaid();
            String dataResultName = dataModelService.areaResultName(areaid);
            //System.out.println(dataResultName);
            String dataResulid = dataModelService.areaResultid(areaid);
            datamodelArea.setDataresultname(dataResultName);
            datamodelArea.setDataresultid(dataResulid);
        }
        model.addAttribute("dataAreaList", dataAreaList);
        model.addAttribute("modeid", modeid);
        return new ModelAndView("data/areaResultReview", "modelModel", model);
    }

    @RequestMapping("/modelReview/{modeid}")
    public ModelAndView dataModelManage(Model model, @PathVariable("modeid") Integer modeid) {
        //数据链
        List<DatamodelLink> datamodelLinkList = sourceService.dataModelLink(modeid);
        model.addAttribute("datamodelLinkList", datamodelLinkList);
        //数据区
        List<DatamodelArea> datamodelAreaList = sourceService.selectModelArea(modeid);
            for (DatamodelArea datamodelArea:datamodelAreaList){
                String linkName = dataAreaService.selectLinkName(datamodelArea.getAreaid()+"");
                datamodelArea.setLinkname(linkName);
            }
        model.addAttribute("datamodelAreaList", datamodelAreaList);
        //数据模型数据块回显
        List<DatamodelBlock> dataBlockList = dataModelService.dataModelBlock(modeid);
       /* for (DatamodelBlock datamodelBlock: dataBlockList) {
            // System.out.println(datamodelArea.getAreaname());
            Integer blockid =  datamodelBlock.getBlockid();
            String areaName = dataBlockService.selectAreaName(blockid);
            datamodelBlock.setAreaname(areaName);
            List<String>  dataResultName = dataModelService.blockResultName(blockid);

            //System.out.println(dataResultName);
            String  dataaddr = dataModelService.blockResultAddr(blockid);
            datamodelBlock.setDataresultname(dataResultName);
            datamodelBlock.setDataaddr(dataaddr);
        }*/
        List<String> datablockid = new ArrayList<>();
        List<String> datablockname = new ArrayList<>();
        List<String> createprocess = new ArrayList<>();
        List<String> areaname = new ArrayList<>();
        List<String> dataResultName = new ArrayList<>();
        List<Integer> dataResultid = new ArrayList<>();
        List<DatamodelBlock> result = new ArrayList<>();
        for (DatamodelBlock datamodelBlock : dataBlockList) {
            datablockid.add(datamodelBlock.getBlockid().toString());
            datablockname.add(datamodelBlock.getBlockname());
            createprocess.add(datamodelBlock.getCreateprocess());
            areaname = (dataModelService.selectAreaName(datamodelBlock.getBlockid()));
            dataResultName = dataModelService.blockResultName((String.valueOf(datamodelBlock.getBlockid())));
            dataResultid = dataModelService.blockResultid((String.valueOf(datamodelBlock.getBlockid())));
            /*System.out.println(datablockname);
            System.out.println(dataResultName);
            System.out.println(createprocess);
            System.out.println(areaname);*/
            for (String blockid : datablockid) {
                for (String datablock : datablockname) {
                    for (String process : createprocess) {
                        for (String dataarea : areaname) {
                            if (dataResultName == null || dataResultName.size() == 0) {
                                DatamodelBlock db = new DatamodelBlock();
                                db.setBlockid(Integer.parseInt(blockid));
                                db.setBlockname(datablock);
                                db.setCreateprocess(process);
                                db.setAreaname(dataarea);
                                db.setDataresultname("无数据块");
                                result.add(db);
                            } else {
                                for (String datares : dataResultName) {
                                    for (Integer resultid : dataResultid) {
                                        DatamodelBlock db = new DatamodelBlock();
                                        db.setBlockname(datablock);
                                        db.setCreateprocess(process);
                                        db.setAreaname(dataarea);
                                        db.setDataresultname(datares);
                                        db.setDataresultid(resultid);
                                        result.add(db);
                                    }

                                }
                            }
                        }

                    }

                }
            }
            datablockid.clear();
            datablockname.clear();
            areaname.clear();
            createprocess.clear();
            dataResultName.clear();
            dataResultid.clear();
        }
        /*for (DatamodelBlock datamodelBlock: result) {
            System.out.println(datamodelBlock.getBlockname());
            System.out.println(datamodelBlock.getDataresultname());
        }*/
        model.addAttribute("dataBlockList", result);

        //数据模型数据包回显
        List<DatamodelBao> datamodelBaoList = dataModelService.selectBaoId(modeid);
        for (DatamodelBao datamodelBao : datamodelBaoList) {
            String blockNames1="";
            Integer baoid = datamodelBao.getBaoid();
            List<DatamodelInfo> datamodelInfoList = dataModelService.selectBaoInfo(baoid);
            for (DatamodelInfo datamodelInfo : datamodelInfoList) {
                datamodelBao.setDataresultid(datamodelInfo.getDataresultid());
                String[] blockids = datamodelInfo.getDatablock().split(",");
                for (int i = 0; i < blockids.length; i++) {
                    DatamodelBlock datamodelBlock = sourceService.selectBlockById(Integer.parseInt(blockids[i]));
                    blockNames1 = blockNames1+datamodelBlock.getBlockname()+" ";
                }
                datamodelBao.setBlockname(blockNames1);
            }
                model.addAttribute("datamodelBaoList", datamodelBaoList);

        }
        //数据模型数据集回显
        List<DatamodelJi> datamodelJi = dataModelService.selectDataCollect(modeid);
        for (DatamodelJi ji : datamodelJi) {
            String baoid_list=ji.getBaoid();
            // System.out.println(baoid);
            String[] baoids = baoid_list.split(";");
            String baoName="";
            for (int i = 0; i <baoids.length ; i++) {
                DatamodelBao datamodelBao = dataAggregationService.selectDataBaoByBaoId(Integer.parseInt(baoids[i]));
                baoName=baoName+datamodelBao.getBaoname()+" ";
            }
            ji.setBaoname(baoName);
        }
        model.addAttribute("datamodelJi", datamodelJi);
        model.addAttribute("modeid", modeid);
        return new ModelAndView("data/dataModelReview", "modelModel", model);
    }

    @GetMapping(value = "/dataModelSearch")
    public ModelAndView dataModelSearch(Model model, @RequestParam("search_text") String search_text) {
        //拼接模糊查询的通配符
        String res = "%" + search_text + "%";
       // System.out.println(res);
        //返回查到的list
        Iterable<DatamodelName> dataModelList = dataModelService.findByModelnameLike(res);
      //  System.out.println(((List<DatamodelName>) dataModelList).size());
        model.addAttribute("dataModelList", dataModelList);
        return new ModelAndView("data/dataModelManage", "model", model);
    }

    @ResponseBody
    @RequestMapping("/dataModelDelete")
    public Map<String, Object> dataModelDelete(@RequestParam("modelid") String modelid) {
        Map<String, Object> map = new HashMap<>();
        Integer id = Integer.valueOf(modelid);//将string类型转为Integer类型
        /*System.out.println(id);*/
        dataModelService.dataModelDelete(id);
        DatamodelName dataModel = dataModelService.isExitDataModel(id);
        if (dataModel == null) {
            map.put("code", 1);
        } else {
            map.put("code", 0);
        }
        return map;
    }

    @ResponseBody
    @RequestMapping("/selectQuestion")
    public Map<String,Object> selectQuestion(@RequestParam Integer businessid){
        List<BusinessQuestion> listQuestion = dataModelService.selectquestion(businessid);
        Map<String,Object> map = new HashMap<>();
        Iterator<BusinessQuestion> iterator = listQuestion.iterator();
        while (iterator.hasNext()){
            BusinessQuestion bq = iterator.next();
            map.put(String.valueOf(bq.getQuestionid()),bq.getQuestioname());
        }
        return map;
    }
}


