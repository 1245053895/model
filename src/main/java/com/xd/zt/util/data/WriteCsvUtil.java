package com.xd.zt.util.data;

import com.csvreader.CsvWriter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/*
 ** readCSV类：实现CSV文件内容的读取
 */
public class WriteCsvUtil {

    // 参数：文件路径
    private String filePath;

    //表头数据
    private String[] csvHeaders;
    //csv文件内容
    private List<String[]> listContent;


    // 构造函数
    public WriteCsvUtil(String filePath,String[] csvHeaders,List<String[]> listContent){
        this.filePath = filePath;
        this.csvHeaders = csvHeaders;
        this.listContent = listContent;
    }

    // getter and setter
    public String[] getCsvHeaders() {
        return csvHeaders;
    }

    public void setCsvHeaders(String[] csvHeaders) {
        this.csvHeaders = csvHeaders;
    }

    public List<String[]> getListContent() {
        return listContent;
    }

    public void setListContent(List<String[]> listContent) {
        this.listContent = listContent;
    }
    public String getfilePath() {
        return filePath;
    }

    public void setfilePath(String filePath) {
        this.filePath = filePath;
    }

    // read()函数实现具体的读取CSV文件内容的方法
    public void write() {

        try {
            // 创建CSV写对象 例如:CsvWriter(文件路径，分隔符，编码格式);
            CsvWriter csvWriter = new CsvWriter(filePath, ',', Charset.forName("UTF-8"));
            // 写表头
            csvWriter.writeRecord(csvHeaders);
            // 写内容
            for (int i = 0; i < listContent.size(); i++) {
//                String[] csvContent = {(String) list.get(0).get(i), (String) list.get(1).get(i)};
                csvWriter.writeRecord(listContent.get(i));
            }
            csvWriter.close();
            System.out.println("--------CSV文件已经写入--------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}