package com.EMRService.service;

import com.EMRService.entity.PatientDiagnosisInfosDTO;

public interface DiagnoseService {
    boolean insertPatientDiagnosis(PatientDiagnosisInfosDTO info);
}
