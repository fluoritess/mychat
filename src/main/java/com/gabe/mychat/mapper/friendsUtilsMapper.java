package com.gabe.mychat.mapper;

import com.gabe.mychat.pojo.friends;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface friendsUtilsMapper {
    List<friends> selectByUserId(String userId);

}
