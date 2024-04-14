package com.EMRService.service;

import org.springframework.stereotype.Service;

import com.EMRService.dao.DocumentManagerDao;

@Service
public class DocumentManagerServiceImpl implements DocumentManagerService {
    /*private DocumentManagerDao documentManagerDao=new DocumentManagerDao();*/

    private final DocumentManagerDao documentManagerDao;

    public DocumentManagerServiceImpl(DocumentManagerDao documentManagerDao) {
        this.documentManagerDao = documentManagerDao;
    }

    @Override
    public String getDocumentContentInfo(Long emrxh, Long documentId, String isbcl) {
        return documentManagerDao.getDocumentContentInfo(emrxh, documentId, isbcl);
    }

    @Override
    public Boolean delDocument(Long documentId, String isbcl) {
        return documentManagerDao.delDocument(documentId, isbcl);
    }

    @Override
    public Boolean updateDocumentContentInfo(Long emrxh, Long documentId, String isbcl, String ysjdm, String content) {
        return documentManagerDao.updateDocumentContentInfo(emrxh, documentId, isbcl, ysjdm, content);
    }
}
