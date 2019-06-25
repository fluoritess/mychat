package com.gabe.mychat.mapper;

import com.gabe.mychat.pojo.message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface messageUtilMapper {
    List<message> selectByTime(@Param("senderId") String senderId,@Param("receiverId")  String receiverId);
}
