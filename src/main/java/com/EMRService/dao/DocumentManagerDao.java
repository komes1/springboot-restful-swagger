package com.EMRService.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class DocumentManagerDao {

    @Autowired
    @Qualifier("jdbcTemplate_CISDB")
    private JdbcTemplate jdbcTemplate_CISDB;

    @Resource(name = "jdbcTemplate_CISDB_DATA")
    private JdbcTemplate jdbcTemplate_CISDB_DATA;

    public String getDocumentContentInfo(Long emrxh, Long documentId, String isbcl) {
        String result = "";
        String sql = "select TOP 1 WBNR FROM EMR_CPOE_BLWJJGNRK  WHERE EMRXH=? and BLXH=? and ISBC=? and WJJG_INDEX=0";
        result = this.jdbcTemplate_CISDB_DATA.queryForObject(sql, String.class, emrxh, documentId, isbcl);
        return result;
    }


    public Boolean updateDocumentContentInfo(Long emrxh, Long documentId, String isbcl, String ysjdm, String content) {
        String sql = "update EMR_CPOE_BLSJ_S set VALUE=?  WHERE EMRXH=? and BLXH=? and ISBC=? and YSJDM=?";
        return this.jdbcTemplate_CISDB_DATA.update(sql, content, emrxh, documentId, isbcl, ysjdm) > 0;
    }

    public Boolean delDocument(Long documentId, String isbcl) {
        String sql = "";
        if (isbcl == "1") {
            sql = "update EMR_CPOE_BLBCJLK set STATE=4604 WHERE XH=?";
        } else {
            sql = "update EMR_CPOE_BLJLK set STATE=4604 WHERE XH=?";
        }
        return this.jdbcTemplate_CISDB.update(sql, documentId) > 0;
    }
}
