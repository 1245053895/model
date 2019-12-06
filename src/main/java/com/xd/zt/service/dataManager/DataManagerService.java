package com.xd.zt.service.dataManager;

import com.xd.zt.domain.dataManage.DataManage;
import com.xd.zt.domain.dataManage.MysqlData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DataManagerService {
    List<DataManage> selectDataList();
    List<DataManage> selectDataList1();
    List<DataManage> selectDataList2();

    public List<DataManage> moHuDataList(@Param("res") String res);
    public List<DataManage> moHuDataList1(@Param("res") String res);
    public List<DataManage> moHuDataList2(@Param("res") String res);



    List<MysqlData>selectMysqlDataList();


    void MysqlDataDelete(@Param("modelid")Integer modelid);
    void insertData(@Param("modeid") int modeid,@Param("sourcename") String sourcename,@Param("sourcepath") String sourcepath,@Param("sourcetime") String sourcetime);
    void insertData1(@Param("modeid") int modeid, @Param("sourcename") String sourcename,@Param("sourcepath") String sourcepath,@Param("sourcetime") String sourcetime);
    void insertData2(@Param("modeid") int modeid, @Param("sourcename") String sourcename,@Param("sourcepath") String sourcepath,@Param("sourcetime") String sourcetime);
    MysqlData mysqlData(@Param("sourceid")   Integer sourceid);
}
