package com.xd.zt.service.business;


import com.xd.zt.util.business.FileUpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/*
 * @Description:    上传文件返回上传路径
 * @Author:         XiaYiTiao
 * @CreateDate:     2019/3/12 15:36
 * @Bug：尝试参数写活，返回相对路径
 */
@Service
public class PictureService {

    @Autowired
    private QuestionBuildService questionBuildService;


    public String[] Upload(@RequestParam("file") MultipartFile picture) {
        /*
         * 功能描述:上传文件，返回文件多的名称、路径和的大小信息
         * @param: [file]
         * @return: java.lang.String[]
         */

        String[] fileInformation = new String[3];
        if(!picture.isEmpty()) {
            // 获取文件名称,包含后缀//image.png
            String fileName = picture.getOriginalFilename();

            // 存放在这个路径下：该路径是该工程目录下的static文件下：(注：该文件可能需要自己创建)
            // 放在static下的原因是，存放的是静态文件资源，即通过浏览器输入本地服务器地址，加文件名时是可以访问到的
            //获取项目类加载器下的资源路径
            String path = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/";
            //String path = LOCAL_UPFILE_PATH;//尝试用参数启动，改为本地路径
            path = path.substring(1,path.length());
            try {
                // 该方法是对文件写入的封装，在util类中，导入该包即可使用，后面会给出方法
                FileUpUtil.fileupload(picture.getBytes(), path, fileName);
            } catch (IOException e) {

                e.printStackTrace();
            }

            // 接着创建对应的实体类，将以下路径进行添加，然后通过数据库操作方法写入
            // FileAndUpload upSuccessFile = new FileAndUpload();


            String filePath = path + fileName;
            fileInformation[0]=fileName;
            fileInformation[1] = path;
            fileInformation[2] = filePath;
           /* fileInformation[0] = fileName;
            fileInformation[1] = filePath;*/

            //upSuccessFile.setFliePath("http://localhost:8082/"+fileName);//服务器访问路径
            //upSuccessFile.setFliePath(ACCESS_PATH);//尝试用参数启动，改为访问路径
            //fileRepository.save(upSuccessFile);
           // questionBuildService.updatePicture(businessQuestion);
        }
        return fileInformation;

    }

    public void deleteFile(String filePath){
        File file = new File(filePath);
        file.delete();
    }


    //读数据进入数据库
    public void csvToDatabase(){

    }

    //读取csv文件
//    public List<String> readCsvFile(String filePath){
//        ReadCsvUtil readCsvUtil = new ReadCsvUtil(filePath);
//        List<String> result =readCsvUtil.read();
//        return result;
//    }

}

