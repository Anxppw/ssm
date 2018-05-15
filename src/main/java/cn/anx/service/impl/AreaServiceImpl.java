package cn.anx.service.impl;

import cn.anx.mapper.AreaMapper;
import cn.anx.pojo.Area;
import cn.anx.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaMapper areaMapper;

    @Override
    public List<Area> queryArea() {
        return areaMapper.queryArea();
    }

}
