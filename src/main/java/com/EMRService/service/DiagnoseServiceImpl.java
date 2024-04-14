package com.EMRService.service;

import com.EMRService.dao.DiagnosisDao;
import com.EMRService.entity.PatientDiagnosisInfosDTO;
import org.springframework.stereotype.Service;

@Service
public class DiagnoseServiceImpl implements DiagnoseService {
    private final DiagnosisDao diagnosisDao;

    public DiagnoseServiceImpl(DiagnosisDao diagnosisDao) {
        this.diagnosisDao = diagnosisDao;
    }

    @Override
    public boolean insertPatientDiagnosis(PatientDiagnosisInfosDTO info) {
        return diagnosisDao.insertPatientDiagnosis(info);
    }
}
