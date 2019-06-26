package com.gabe.mychat.mapper;

import com.gabe.mychat.pojo.user;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface userUtilMapper {
    List<user> selectUserByNickName(String userId);
}
