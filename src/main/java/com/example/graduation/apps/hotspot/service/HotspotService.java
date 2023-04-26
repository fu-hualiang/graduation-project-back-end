package com.example.graduation.apps.hotspot.service;

import com.example.graduation.apps.hotspot.object.Band;
import com.example.graduation.apps.hotspot.object.Gov;
import com.example.graduation.exception.MyException;

import java.util.List;

public interface HotspotService {
    List<Band> findBands() throws MyException;

    List<Band> findSearches() throws MyException;

    List<Gov> findGovs() throws MyException;
}
