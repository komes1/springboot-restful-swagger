package com.EMRService.service;

import com.EMRService.dao.PatientInfoDao;
import com.EMRService.entity.PatientInfoDTO;
import org.springframework.stereotype.Service;

@Service
public class PatientInfoServiceImpl implements PatientInfoService {
    private final PatientInfoDao patientInfoDao;

    public PatientInfoServiceImpl(PatientInfoDao patientInfoDao) {
        this.patientInfoDao = patientInfoDao;
    }

    @Override
    public PatientInfoDTO getPatientInfo(Long emrxh) {
        return patientInfoDao.getPatientInfo(emrxh);
    }
}
