package com.gabe.mychat.mapper;

import com.gabe.mychat.pojo.sercurityLog;
import com.gabe.mychat.pojo.sercurityLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface sercurityLogMapper {
    int countByExample(sercurityLogExample example);

    int deleteByExample(sercurityLogExample example);

    int deleteByPrimaryKey(Integer logId);

    int insert(sercurityLog record);

    int insertSelective(sercurityLog record);

    List<sercurityLog> selectByExample(sercurityLogExample example);

    sercurityLog selectByPrimaryKey(Integer logId);

    int updateByExampleSelective(@Param("record") sercurityLog record, @Param("example") sercurityLogExample example);

    int updateByExample(@Param("record") sercurityLog record, @Param("example") sercurityLogExample example);

    int updateByPrimaryKeySelective(sercurityLog record);

    int updateByPrimaryKey(sercurityLog record);
}