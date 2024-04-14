package com.EMRService.service;

import com.EMRService.entity.PatientInfoDTO;

public interface PatientInfoService {
    PatientInfoDTO getPatientInfo(Long emrxh);
}
