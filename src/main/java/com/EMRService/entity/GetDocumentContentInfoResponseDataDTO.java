package com.EMRService.entity;

import lombok.Data;

@Data
public class GetDocumentContentInfoResponseDataDTO extends ResponseDataDTO  {
    /**
     * 病程记录内容
     */
    private String courseRecordContent;
}
