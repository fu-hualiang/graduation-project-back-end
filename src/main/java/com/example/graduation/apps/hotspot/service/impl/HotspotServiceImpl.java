package com.example.graduation.apps.hotspot.service.impl;

import com.example.graduation.apps.hotspot.object.Band;
import com.example.graduation.apps.hotspot.object.Gov;
import com.example.graduation.apps.hotspot.service.HotspotService;
import com.example.graduation.exception.MyException;
import com.example.graduation.utils.HttpUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("hotspotService")
public class HotspotServiceImpl implements HotspotService {
    @Resource
    ObjectMapper objectMapper;
    @Override
    public List<Band> findBands() throws MyException {
        String url = "https://weibo.com/ajax/statuses/hot_band";
        String res = HttpUtils.get(url, null, null);
        try{
            JsonNode bandListJsonNode = objectMapper.readTree(res).get("data").get("band_list");
            List<Band> bandList = new ArrayList<>();
            int count = bandListJsonNode.size();
            for (int i=0;i<count;++i){
                JsonNode bandJsonNode = bandListJsonNode.get(i);
                if (!bandJsonNode.has("realpos")) continue;
                Band band = new Band();
                band.setRealPos(bandJsonNode.get("realpos").asInt());
                band.setLabelName(bandJsonNode.get("label_name").asText());
                band.setWord(bandJsonNode.get("word").asText());
                band.setOnboardTime(bandJsonNode.get("onboard_time").asLong()*1000);
                band.setSubjectLabel(bandJsonNode.get("subject_label").asText());
                band.setChannelType(bandJsonNode.get("channel_type").asText());

                List<String> starName = new ArrayList<>();
                int starNameCount = bandJsonNode.get("star_name").size();
                for (int j=0;j<starNameCount;++j){
                    starName.add(bandJsonNode.get("star_name").get(j).asText());
                }
                band.setStarName(starName);

                band.setRawHot(bandJsonNode.get("raw_hot").asLong());
                band.setCategory(bandJsonNode.get("category").asText());
                band.setIsHot(bandJsonNode.has("is_hot"));
                band.setSubjectQuery(bandJsonNode.get("subject_querys").asText());

                bandList.add(band);
            }
            return bandList;
        }catch (Exception e){
            e.printStackTrace();
            throw new MyException(40000,"未知错误");
        }
    }

    @Override
    public List<Band> findSearches() throws MyException {
        String url = "https://weibo.com/ajax/side/hotSearch";
        String res = HttpUtils.get(url, null, null);
        try{
            JsonNode bandListJsonNode = objectMapper.readTree(res).get("data").get("realtime");
            List<Band> bandList = new ArrayList<>();
            int count = bandListJsonNode.size();
            for (int i=0;i<count;++i){
                JsonNode bandJsonNode = bandListJsonNode.get(i);
                if (!bandJsonNode.has("realpos")) continue;
                Band band = new Band();
                band.setRealPos(bandJsonNode.get("realpos").asInt());
                band.setLabelName(bandJsonNode.get("label_name").asText());
                band.setWord(bandJsonNode.get("word").asText());
                band.setOnboardTime(bandJsonNode.get("onboard_time").asLong());
                band.setSubjectLabel(bandJsonNode.get("subject_label").asText());
                band.setChannelType(bandJsonNode.get("channel_type").asText());

                List<String> starName = new ArrayList<>();
                int starNameCount = bandJsonNode.get("star_name").size();
                for (int j=0;j<starNameCount;++j){
                    starName.add(bandJsonNode.get("star_name").get(j).asText());
                }
                band.setStarName(starName);

                band.setRawHot(bandJsonNode.get("raw_hot").asLong());
                band.setCategory(bandJsonNode.get("category").asText());
                band.setIsHot(bandJsonNode.has("is_hot"));
                band.setSubjectQuery(bandJsonNode.get("subject_querys").asText());

                bandList.add(band);
            }
            return bandList;
        }catch (Exception e){
            e.printStackTrace();
            throw new MyException(40000,"未知错误");
        }
    }

    @Override
    public List<Gov> findGovs() throws MyException {
        Set<String> midSet = new HashSet<>();
        String url = "https://weibo.com/ajax/statuses/hot_band";
        List<Gov> govList = new ArrayList<>();
        try {
            String res = HttpUtils.get(url, null, null);
            JsonNode hotgovJsonNode = objectMapper.readTree(res).get("data").get("hotgov");
            while (!midSet.contains(hotgovJsonNode.get("mid").asText())){
                midSet.add(hotgovJsonNode.get("mid").asText());
                Gov gov = new Gov();
                gov.setName(hotgovJsonNode.get("name").asText());
                String[] arr = hotgovJsonNode.get("url").asText().split("/");
                gov.setUrl(arr[arr.length-1]);
                govList.add(gov);
                res = HttpUtils.get(url, null, null);
                hotgovJsonNode = objectMapper.readTree(res).get("data").get("hotgov");
            }
            return govList;
        }catch (Exception e){
            e.printStackTrace();
            throw new MyException(40000,"未知错误");
        }
    }
}
