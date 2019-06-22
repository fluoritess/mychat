package com.gabe.mychat.mapper;

import com.gabe.mychat.pojo.message;
import com.gabe.mychat.pojo.messageExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface messageMapper {
    int countByExample(messageExample example);

    int deleteByExample(messageExample example);

    int deleteByPrimaryKey(String messageId);

    int insert(message record);

    int insertSelective(message record);

    List<message> selectByExample(messageExample example);

    message selectByPrimaryKey(String messageId);

    int updateByExampleSelective(@Param("record") message record, @Param("example") messageExample example);

    int updateByExample(@Param("record") message record, @Param("example") messageExample example);

    int updateByPrimaryKeySelective(message record);

    int updateByPrimaryKey(message record);
}