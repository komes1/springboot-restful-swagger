package com.EMRService.service;

import com.EMRService.dao.PatientInfoDao;
import com.EMRService.dao.UserInfoDao;
import com.EMRService.entity.PatientInfoDTO;
import com.EMRService.entity.UserInfoDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    private final UserInfoDao userInfoDao;

    public UserInfoServiceImpl(UserInfoDao userInfoDao) {
        this.userInfoDao = userInfoDao;
    }

    @Override
    public List<UserInfoDTO> getAllUserInfos() {
        return userInfoDao.getAllUserInfos();
    }
}