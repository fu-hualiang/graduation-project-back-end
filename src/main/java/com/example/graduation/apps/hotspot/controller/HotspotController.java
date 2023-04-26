package com.example.graduation.apps.hotspot.controller;

import com.example.graduation.apps.hotspot.object.Band;
import com.example.graduation.apps.hotspot.object.Gov;
import com.example.graduation.apps.hotspot.service.HotspotService;
import com.example.graduation.exception.MyException;
import com.example.graduation.utils.resultUtils.Result;
import com.example.graduation.utils.resultUtils.ResultUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("hotspots")
public class HotspotController {
    @Resource
    HotspotService hotspotService;

    @GetMapping("bands")
    public Result<List<Band>> findBands() throws MyException {
        return ResultUtil.success(hotspotService.findBands());
    }

    @GetMapping("searches")
    public Result<List<Band>> findSearches() throws MyException {
        return ResultUtil.success(hotspotService.findSearches());
    }

    @GetMapping("govs")
    public Result<List<Gov>> findGovs() throws MyException {
        return ResultUtil.success(hotspotService.findGovs());
    }
}
