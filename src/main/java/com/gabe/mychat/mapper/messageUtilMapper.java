package com.gabe.mychat.mapper;

import com.gabe.mychat.pojo.message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface messageUtilMapper {
    List<message> selectByTime(String senderId,String receiverId);
}
