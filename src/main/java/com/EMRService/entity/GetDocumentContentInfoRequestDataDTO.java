package com.EMRService.entity;

import lombok.Data;

@Data
public class GetDocumentContentInfoRequestDataDTO {
    /**
     * 患者唯一标识1
     * CIS EMRXH
     */
    private String patientId;
    /**
     * 患者标识类别
     * 默认或0为CIS EMRXH，1:HIS住院流水号syxh
     */
    private String patientIdKind;
    /**
     * 婴儿序号
     * PatientIdKind为1时有效，非婴儿则传0
     */
    private String babyId;
    /**
     * 病历记录序号
     */
    private String documentId;
    /**
     * 病历记录序号标记
     * 0:病历记录序号 1:病程记录序号
     */
    private String documentIdFlag;

    private String operatorId;

    private String operatorName;
}
