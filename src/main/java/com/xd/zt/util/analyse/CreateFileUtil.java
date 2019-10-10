package com.xd.zt.util.analyse;



import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;


public class CreateFileUtil {


    /**
     * 生成.json格式文件
     */
    public static boolean createJsonFile(String jsonString, String filePath, String fileName) {
        // 标记文件生成是否成功
        boolean flag = true;

        // 拼接文件完整路径
        String fullPath = filePath + File.separator + fileName + ".json";

        // 生成json格式文件
        try {
            // 保证创建一个新文件
            File file = new File(fullPath);
            if (!file.getParentFile().exists()) { // 如果父目录不存在，创建父目录
                file.getParentFile().mkdirs();
            }
            if (file.exists()) { // 如果已存在,删除旧文件
                file.delete();
            }
            file.createNewFile();


           /* //以下创建json格式内容
            //创建一个json对象，相当于一个容器
            JSONObject object = new JSONObject();
            object.put("name","XGBRegressor");
            JSONObject par=new JSONObject();
             par.put("input","csv路径");
            par.put("max_depth",7);
            par.put("learning_rate",0.1);
            par.put("n_estimator",142);
            par.put("output","csv输出路径");
            object.put("params",par);*/

            // 格式化json字符串
            jsonString = JsonFormatTool.formatJson(jsonString);


            // 将格式化后的字符串写入文件

            Writer write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            write.write(jsonString);
            write.flush();
            write.close();
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        }

        // 返回是否成功的标记
        return flag;
    }
}
