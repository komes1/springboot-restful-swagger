package com.EMRService.service;

import com.EMRService.entity.User;

import java.math.BigDecimal;
import java.util.List;

public interface DocumentManagerService {
    List<User> selectInfoResult();
    String getDocumentContentInfo(BigDecimal emrxh, BigDecimal documentId, String isbcl );
}
