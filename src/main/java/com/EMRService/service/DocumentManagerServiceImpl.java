package com.EMRService.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EMRService.dao.DocumentManagerDao;
import com.EMRService.entity.User;

@Service
public class DocumentManagerServiceImpl implements DocumentManagerService {
    @Autowired
    private DocumentManagerDao documentManagerDao;

    @Override
    public List<User> selectInfoResult() {
        return documentManagerDao.selectInfo();
    }

    @Override
    public String getDocumentContentInfo(BigDecimal emrxh, BigDecimal documentId, String isbcl) {
        return documentManagerDao.getDocumentContentInfo(emrxh,documentId,isbcl);
    }
}
