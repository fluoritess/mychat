package com.gabe.mychat.mapper;

import com.gabe.mychat.pojo.admin;
import com.gabe.mychat.pojo.adminExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface adminMapper {
    int countByExample(adminExample example);

    int deleteByExample(adminExample example);

    int insert(admin record);

    int insertSelective(admin record);

    List<admin> selectByExample(adminExample example);

    int updateByExampleSelective(@Param("record") admin record, @Param("example") adminExample example);

    int updateByExample(@Param("record") admin record, @Param("example") adminExample example);
}