package com.gabe.mychat.mapper;

import com.gabe.mychat.pojo.broadcast;
import com.gabe.mychat.pojo.broadcastExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface broadcastMapper {
    int countByExample(broadcastExample example);

    int deleteByExample(broadcastExample example);

    int deleteByPrimaryKey(Integer broadcastId);

    int insert(broadcast record);

    int insertSelective(broadcast record);

    List<broadcast> selectByExample(broadcastExample example);

    broadcast selectByPrimaryKey(Integer broadcastId);

    int updateByExampleSelective(@Param("record") broadcast record, @Param("example") broadcastExample example);

    int updateByExample(@Param("record") broadcast record, @Param("example") broadcastExample example);

    int updateByPrimaryKeySelective(broadcast record);

    int updateByPrimaryKey(broadcast record);
}