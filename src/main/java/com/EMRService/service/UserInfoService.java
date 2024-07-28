package com.EMRService.service;

import com.EMRService.entity.PatientInfoDTO;
import com.EMRService.entity.UserInfoDTO;

import java.util.List;

public interface UserInfoService {
    List<UserInfoDTO> getAllUserInfos();
}