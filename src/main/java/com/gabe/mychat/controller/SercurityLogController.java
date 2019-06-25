package com.gabe.mychat.controller;

import com.gabe.mychat.mapper.sercurityLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author wsw
 * @Package com.gabe.mychat.controller
 * @Description:
 * @date 2019年6月25日 18:45:28
 */
@Controller
public class SercurityLogController {
    @Autowired
    sercurityLogMapper sercurityLogMapper;

}
