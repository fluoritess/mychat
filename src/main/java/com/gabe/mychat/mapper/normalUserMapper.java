package com.gabe.mychat.mapper;

import com.gabe.mychat.pojo.normalUser;
import com.gabe.mychat.pojo.normalUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface normalUserMapper {
    int countByExample(normalUserExample example);

    int deleteByExample(normalUserExample example);

    int insert(normalUser record);

    int insertSelective(normalUser record);

    List<normalUser> selectByExample(normalUserExample example);

    int updateByExampleSelective(@Param("record") normalUser record, @Param("example") normalUserExample example);

    int updateByExample(@Param("record") normalUser record, @Param("example") normalUserExample example);
}