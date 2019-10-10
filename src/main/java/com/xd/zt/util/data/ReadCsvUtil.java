package com.xd.zt.util.data;

import com.csvreader.CsvReader;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/*
 ** readCSV类：实现CSV文件内容的读取
 */
public class ReadCsvUtil {

    // 参数：文件路径
    private String filePath;

    // 构造函数
    public ReadCsvUtil(String filePath){
        this.filePath = filePath;
    }

    // getter and setter
    public String getfilePath() {
        return filePath;
    }

    public void setfilePath(String filePath) {
        this.filePath = filePath;
    }

    // read()函数实现具体的读取CSV文件内容的方法
    public List<String> read() {

        List<String> result = new ArrayList<>();

        try {
            // 创建CSV读对象
            CsvReader csvReader = new CsvReader(filePath,',', Charset.forName("utf-8"));
            while (csvReader.readRecord()){
                // 读取每一行数据，依次存入result
                // System.out.println(csvReader.getRawRecord());
                result.add(csvReader.getRawRecord());
            }
            csvReader.close();
            return result;

        } catch (IOException e) {
            e.printStackTrace();
            return result;
        }
    }

}