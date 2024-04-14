package com.EMRService.service;

public interface DocumentManagerService {
    String getDocumentContentInfo(Long emrxh, Long documentId, String isbcl);

    Boolean delDocument(Long documentId, String isbcl);

    Boolean updateDocumentContentInfo(Long emrxh, Long documentId, String isbcl, String ysjdm, String content);
}
