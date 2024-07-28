package com.EMRService.dao;

import com.EMRService.entity.PatientInfoDTO;
import com.EMRService.entity.UserInfoDTO;
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
public class UserInfoDao {
    @Autowired
    @Qualifier("jdbcTemplate_CISDB")
    private JdbcTemplate jdbcTemplate_CISDB;

    public List<UserInfoDTO> getAllUserInfos() {
        String sql = "SELECT TOP 100 ID id,NAME name,KS_MC ksmc FROM SYS_ZGDMK ";
        RowMapper<UserInfoDTO> rowMapper = new UserInfoRowMapper(); // 自定义的 RowMapper
        List<UserInfoDTO> result = jdbcTemplate_CISDB.query(sql, new BeanPropertyRowMapper<>(UserInfoDTO.class));
        // 如果查询结果为空，则返回 null
        if (result.isEmpty()) {
            return null;
        }
        // 否则，返回第一个用户对象
        return result;
    }

    class UserInfoRowMapper implements RowMapper<UserInfoDTO> {
        @Override
        public UserInfoDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            final UserInfoDTO userInfoDTOEntity = new UserInfoDTO();
            userInfoDTOEntity.setId(rs.getString("ID"));
            userInfoDTOEntity.setName(rs.getString("NAME"));
            userInfoDTOEntity.setKsmc(rs.getString("KS_MC"));
            return userInfoDTOEntity;
        }
    }
}
