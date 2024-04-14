package com.EMRService.entity;

import lombok.Data;

@Data
public class GetDocumentContentInfoResponseDataDTO extends ResponseDataDTO {
    /**
     * 文本内容
     */
    private String wbnr;
}
