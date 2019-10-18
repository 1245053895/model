package com.xd.zt.mapper.model;

import com.xd.zt.domain.model.Programme;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ModelMapper {
    List<Programme> selectAllModel();
    void insertProgram(Programme programme);
}
