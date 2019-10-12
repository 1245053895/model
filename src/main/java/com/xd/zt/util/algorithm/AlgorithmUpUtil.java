package com.xd.zt.util.algorithm;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 * @Description:    文件上传工具类
 * @Author:         XiaYiTiao
 * @CreateDate:     2019/3/12 15:13
 * @Bug
 */
public class AlgorithmUpUtil {
    //静态方法：三个参数：文件的二进制，文件路径，文件名
    //通过该方法将在指定目录下添加指定文件
    public static void algorithmUp(byte[] file,String algorithmPath,String algorithmName) throws IOException {
        //目标目录
        File targetFile = new File(algorithmPath);
        if(targetFile.exists()) {
            targetFile.mkdirs();
        }

        //二进制流写入
        FileOutputStream out = new FileOutputStream(algorithmPath+algorithmName);
        out.write(file);
        out.flush();
        out.close();
    }
}
