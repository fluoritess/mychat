package com.gabe.mychat.controller;

import com.gabe.mychat.service.AdminService;
import com.gabe.mychat.util.ArchivesLog;
import com.gabe.mychat.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * description:
 *
 * @author haifeng
 * @version 1.0
 * @date 2019/6/26 0026 上午 10:47
 * @since jdk
 */
@Controller
public class AdminController {

    @Autowired
    AdminService adminService;

    @ResponseBody
    @ArchivesLog(operationName = "查询全部用户",operationType = "查询操作")
    @RequestMapping("/selectAllUser")
    public R selectAllUser(){
        return R.ok().put("data", adminService.findAllUser());
    }

    @ResponseBody
    @ArchivesLog(operationName = "查询用户相关信息",operationType = "查询操作")
    @RequestMapping("/getUserInformation")
    public R getUserInformation(){
        Map<String, Object> map = new HashMap<>();
        map.put("userNumber", adminService.getUserNumber());
        map.put("userAddress", adminService.getUserAddress());
        map.put("userGender", adminService.getUserGender());
        return R.ok().put("data", map);
    }

    @ResponseBody
    @ArchivesLog(operationName = "删除用户",operationType = "删除操作")
    @RequestMapping("/deleteUser")
    public R deleteUser(){
        Map<String, Object> map = new HashMap<>();
        map.put("userNumber", adminService.getUserNumber());
        map.put("userAddress", adminService.getUserAddress());
        map.put("userGender", adminService.getUserGender());
        return R.ok();
    }
}
