package com.EMRService.dao;

import com.EMRService.entity.PatientInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PatientInfoDao {
    @Autowired
    @Qualifier("jdbcTemplate_CISDB")
    private JdbcTemplate jdbcTemplate_CISDB;

    @Resource(name = "jdbcTemplate_CISDB_DATA")
    private JdbcTemplate jdbcTemplate_CISDB_DATA;

    public PatientInfoDTO getPatientInfo(Long emrxh) {
        String sql = "SELECT SYXH nSyxh FROM INP_BRSYK WHERE CISXH = ?";
        RowMapper<PatientInfoDTO> rowMapper = new PatientInfoRowMapper(); // 自定义的 RowMapper
        List<PatientInfoDTO> result = jdbcTemplate_CISDB.query(sql, new Object[]{emrxh}, new BeanPropertyRowMapper<>(PatientInfoDTO.class));
        // 如果查询结果为空，则返回 null
        if (result.isEmpty()) {
            return null;
        }
        // 否则，返回第一个用户对象
        return result.get(0);
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
