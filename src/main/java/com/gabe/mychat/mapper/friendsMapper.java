package com.gabe.mychat.mapper;

import com.gabe.mychat.pojo.friends;
import com.gabe.mychat.pojo.friendsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface friendsMapper {
    int countByExample(friendsExample example);

    int deleteByExample(friendsExample example);

    int insert(friends record);

    int insertSelective(friends record);

    List<friends> selectByExample(friendsExample example);

    int updateByExampleSelective(@Param("record") friends record, @Param("example") friendsExample example);

    int updateByExample(@Param("record") friends record, @Param("example") friendsExample example);
}