package com.gabe.mychat.mapper;

import com.gabe.mychat.pojo.user;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface userUtilMapper {
    user selectUserByNickName(String userId);
}
