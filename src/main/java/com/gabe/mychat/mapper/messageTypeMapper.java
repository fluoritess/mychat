package com.gabe.mychat.mapper;

import com.gabe.mychat.pojo.messageType;
import com.gabe.mychat.pojo.messageTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface messageTypeMapper {
    int countByExample(messageTypeExample example);

    int deleteByExample(messageTypeExample example);

    int deleteByPrimaryKey(Integer messagaeTypeId);

    int insert(messageType record);

    int insertSelective(messageType record);

    List<messageType> selectByExample(messageTypeExample example);

    messageType selectByPrimaryKey(Integer messagaeTypeId);

    int updateByExampleSelective(@Param("record") messageType record, @Param("example") messageTypeExample example);

    int updateByExample(@Param("record") messageType record, @Param("example") messageTypeExample example);

    int updateByPrimaryKeySelective(messageType record);

    int updateByPrimaryKey(messageType record);
}