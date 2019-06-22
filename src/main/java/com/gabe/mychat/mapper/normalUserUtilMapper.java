package com.gabe.mychat.mapper;

import com.gabe.mychat.pojo.normalUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface normalUserUtilMapper {
    normalUser selectUserById(String userId);
}
