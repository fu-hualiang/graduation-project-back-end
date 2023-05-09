package com.example.graduation.apps.data.service;

import com.example.graduation.apps.data.dto.AccountDTO;
import com.example.graduation.apps.data.dto.UserDTO;

import java.util.List;

public interface DataService {
    List<AccountDTO> findAccountData(Long weiboId);

    List<UserDTO> findUserData(Long weiboId);
}
