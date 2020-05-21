package com.guoguo.fengyulou.controller;

import com.power.common.util.IpUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试控制器
 */
@RestController
public class TestController {
    @RequestMapping("/pc")
    public Map<String, Object> pc(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();

        String ipAddr = IpUtil.getIpAddr(request);
        System.out.println(ipAddr);

        return map;
    }
}
