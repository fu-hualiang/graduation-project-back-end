package com.example.graduation.apps.hotspot.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Band {
    Integer realPos;
    String labelName;
    String word;
    Long onboardTime;
    // "剧集","综艺","电影"...
    String subjectLabel;
    // "Entertainment"或者""
    String channelType;
    List<String> starName;
    Long rawHot;
    String category;
    // 可能为空
    Boolean isHot;
    // subjectLabel的拓展
    String subjectQuery;
}
