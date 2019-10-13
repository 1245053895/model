package com.xd.zt.service.model;

import com.xd.zt.domain.model.Programme;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public interface ModelService {
    List<Programme> selectAllModel();
}
