package com.gabe.mychat.mapper;

import com.gabe.mychat.pojo.role;
import com.gabe.mychat.pojo.roleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface roleMapper {
    int countByExample(roleExample example);

    int deleteByExample(roleExample example);

    int deleteByPrimaryKey(Integer roleId);

    int insert(role record);

    int insertSelective(role record);

    List<role> selectByExample(roleExample example);

    role selectByPrimaryKey(Integer roleId);

    int updateByExampleSelective(@Param("record") role record, @Param("example") roleExample example);

    int updateByExample(@Param("record") role record, @Param("example") roleExample example);

    int updateByPrimaryKeySelective(role record);

    int updateByPrimaryKey(role record);
}