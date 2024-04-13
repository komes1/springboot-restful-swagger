package com.EMRService.dao;

import java.math.BigDecimal;
import java.util.List;

import com.EMRService.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DocumentManagerDao {
    @Select("select\t\n"+
            "id,\t\n"+
            "name,\t\n"+
            "age\t\n"+
            "from\t\n"+
            "User1\t\n"
    )
    List<User> selectInfo();

    @Select("select TOP 1 VALUE FROM EMR_CPOE_BLSJ_S  WHERE EMRXH=#{emrxh} and BLXH=#{documentId} and ISBC=#{isbcl} "
    )
    String getDocumentContentInfo(@Param("emrxh") BigDecimal emrxh, @Param("documentId") BigDecimal documentId, @Param("isbcl") String isbcl);
}
