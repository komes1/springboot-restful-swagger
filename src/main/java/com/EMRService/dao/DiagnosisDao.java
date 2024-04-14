package com.EMRService.dao;

import com.EMRService.entity.PatientDiagnosisInfosDTO;
import com.EMRService.entity.PatientInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Repository
public class DiagnosisDao {
    @Autowired
    @Qualifier("jdbcTemplate_CISDB")
    private JdbcTemplate jdbcTemplate_CISDB;

    @Resource(name = "jdbcTemplate_CISDB_DATA")
    private JdbcTemplate jdbcTemplate_CISDB_DATA;

    public boolean insertPatientDiagnosis(PatientDiagnosisInfosDTO info) {
        // 创建参数映射
        String pattern = "yyyy-MM-dd HH:mm:ss";
        // 创建 DateTimeFormatter 对象
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

        String sql = "insert into EMR_CPOE_BRZDK(EMRXH,ZDLB,ZDLX,ZDDM,ZDMC,ZDRQ,ZDYSDM,KSDM) VALUES (?,?,?,?,?,?,?,?)";

        Object[] params = new Object[]{
                info.getEmrxh(),
                info.getZdlb(),
                info.getZdlx(),
                info.getZddm(),
                info.getZdmc(),
                LocalDateTime.parse(info.getZdrq(), formatter),
                info.getZdysdm(),
                info.getKsdm()
        };
        return jdbcTemplate_CISDB.update(sql, params) > 0;

        /*//String sql = "insert into EMR_CPOE_BRZDK(EMRXH,ZDLB,ZDLX,ZDDM,ZDMC,ZDRQ,ZDYSDM,KSDM) VALUES (:emrxh,:zdlb,:zdlx,:zddm,:zdmc,:zdrq,:zdysdm,:ksdm)";
        Map<String, Object> params = new HashMap<>();
        params.put("emrxh", info.getEmrxh());
        params.put("zdlb", info.getZdlb());
        params.put("zdlx", info.getZdlx());
        params.put("zddm", info.getZddm());
        params.put("zdmc", info.getZdmc());
        params.put("zdrq", LocalDateTime.parse(info.getZdrq(), formatter));
        params.put("zdysdm", info.getZdysdm());
        params.put("ksdm", info.getKsdm());
        return jdbcTemplate_CISDB.update(sql,params)>0;*/
    }

    class PatientInfoRowMapper implements RowMapper<PatientInfoDTO> {
        @Override
        public PatientInfoDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            final PatientInfoDTO patientInfoDTOEntity = new PatientInfoDTO();
            patientInfoDTOEntity.setNSyxh(rs.getLong("SYXH"));
            return patientInfoDTOEntity;
        }
    }
}
