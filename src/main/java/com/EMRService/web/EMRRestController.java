package com.EMRService.web;

import com.EMRService.entity.*;
import com.EMRService.service.DiagnoseService;
import com.EMRService.service.DocumentManagerService;
import com.EMRService.service.PatientInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;


/**
 * @author x.yh
 * 在浏览器输入 http://localhost:8183/swagger-ui.html 即可查看
 * @Version:1.0.0
 * @date 2024年4月14日
 */
@RestController
@RequestMapping(value = "/api")
public class EMRRestController {
    @Autowired
    private DocumentManagerService documentManagerService;
    @Autowired
    private PatientInfoService patientInfoService;
    @Autowired
    private DiagnoseService diagnoseService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ApiOperation(value = "新增患者诊断", notes = "新增患者诊断")
    @PostMapping("/AddPatientDiagnosisInfo")
    public ResponseDataDTO AddPatientDiagnosisInfo(@RequestBody PatientDiagnosisInfosDTO requestDataDTO) {
        logger.info("开始新增患者诊断，请求参数:{}", requestDataDTO);
        ResponseDataDTO result = new ResponseDataDTO();
        Long emrxh = requestDataDTO.getEmrxh();
        if (requestDataDTO == null) {
            result.setCode("-1");
            result.setMessage("入参格式错误!");
            return result;
        }
        if (emrxh == 0) {
            result.setCode("-1");
            result.setMessage("患者标识emrxh不能为空!");
            return result;
        }

        PatientInfoDTO patientInfoDTO = patientInfoService.getPatientInfo(emrxh);
        if (patientInfoDTO == null) {
            result.setCode("-1");
            result.setMessage("根据PatientId:" + emrxh + "未找到相应的患者信息!");
            return result;
        }
        Boolean insertResult = diagnoseService.insertPatientDiagnosis(requestDataDTO);
        if (insertResult) {
            result.setCode("200");
            result.setMessage("新增成功");
            return result;
        } else {
            result.setCode("-1");
            result.setMessage("新增失败！");
            return result;
        }

    }


    @ApiOperation(value = "更新病历元素（S表）节点数据", notes = "更新病历元素（S表）表节点数据")
    @PostMapping("/UpdateDocumentElementValue")
    public ResponseDataDTO UpdateDocumentElementValue(@RequestBody DocumentContentInfoRequestDataDTO requestDataDTO) {
        logger.info("开始更新病历元素（S表）节点数据，请求参数:{}", requestDataDTO);
        ResponseDataDTO result = new ResponseDataDTO();
        String sEmrxh = requestDataDTO.getPatientId();
        String sDocumentId = requestDataDTO.getDocumentId();
        String isbcl = requestDataDTO.getDocumentIdFlag();
        String elementValue = requestDataDTO.getElementValue();
        String elementKey = requestDataDTO.getElementKey();

        if (requestDataDTO == null) {
            result.setCode("-1");
            result.setMessage("入参格式错误!");
            return result;
        }

        Long emrxh = null;
        try {
            emrxh = new Long(sEmrxh);
        } catch (NumberFormatException e) {
            result.setCode("-1");
            result.setMessage("[PatientId]入参格式错误!");
            return result;
        }

        Long documentId = null;
        try {
            documentId = new Long(sDocumentId);
        } catch (NumberFormatException e) {
            result.setCode("-1");
            result.setMessage("[DocumentId]入参格式错误!");
            return result;
        }
        if (elementKey.isEmpty()) {
            result.setCode("-1");
            result.setMessage("[elementKey]不能为空!");
            return result;
        }
        if (elementValue.isEmpty()) {
            result.setCode("-1");
            result.setMessage("[elementValue]不能为空!");
            return result;
        }

        PatientInfoDTO patientInfoDTO = patientInfoService.getPatientInfo(emrxh);
        if (patientInfoDTO == null) {
            result.setCode("-1");
            result.setMessage("根据PatientId:" + emrxh + "未找到相应的患者信息!");
            return result;
        }
        Boolean updateResult = documentManagerService.updateDocumentContentInfo(emrxh, documentId, isbcl, elementKey, elementValue);
        if (updateResult) {
            result.setCode("200");
            result.setMessage("更新成功");
            return result;
        } else {
            result.setCode("-1");
            result.setMessage("更新失败！");
            return result;
        }
    }


    @ApiOperation(value = "删除病历", notes = "逻辑删除病历")
    @PostMapping("/DeleteDocument")
    public ResponseDataDTO DeleteDocument(@RequestBody DocumentContentInfoRequestDataDTO requestDataDTO) {
        logger.info("开始删除病历，请求参数:{}", requestDataDTO);
        ResponseDataDTO result = new ResponseDataDTO();
        String sEmrxh = requestDataDTO.getPatientId();
        String sDocumentId = requestDataDTO.getDocumentId();
        String isbcl = requestDataDTO.getDocumentIdFlag();
        if (requestDataDTO == null) {
            result.setCode("-1");
            result.setMessage("入参格式错误!");
            return result;
        }

        Long emrxh = null;
        try {
            emrxh = new Long(sEmrxh);
        } catch (NumberFormatException e) {
            result.setCode("-1");
            result.setMessage("[PatientId]入参格式错误!");
            return result;
        }

        Long documentId = null;
        try {
            documentId = new Long(sDocumentId);
        } catch (NumberFormatException e) {
            result.setCode("-1");
            result.setMessage("[DocumentId]入参格式错误!");
            return result;
        }
        PatientInfoDTO patientInfoDTO = patientInfoService.getPatientInfo(emrxh);
        if (patientInfoDTO == null) {
            result.setCode("-1");
            result.setMessage("根据PatientId:" + emrxh + "未找到相应的患者信息!");
            return result;
        }
        Boolean delResult = documentManagerService.delDocument(documentId, isbcl);
        if (delResult) {
            result.setCode("200");
            result.setMessage("删除成功");
            return result;
        } else {
            result.setCode("-1");
            result.setMessage("删除失败！");
            return result;
        }
    }

    @ApiOperation(value = "获取病历文本内容信息", notes = "获取病历文本内容信息")
    @PostMapping("/GetDocumentContentInfo")
    public GetDocumentContentInfoResponseDataDTO GetDocumentContentInfo(@RequestBody DocumentContentInfoRequestDataDTO requestDataDTO) {
        logger.info("开始获取病历内容信息，请求参数:{}", requestDataDTO);
        GetDocumentContentInfoResponseDataDTO result = new GetDocumentContentInfoResponseDataDTO();
        String sEmrxh = requestDataDTO.getPatientId();
        String sDocumentId = requestDataDTO.getDocumentId();
        String isbcl = requestDataDTO.getDocumentIdFlag();

        if (requestDataDTO == null) {
            result.setCode("-1");
            result.setMessage("入参格式错误!");
            return result;
        }

        Long emrxh = null;
        try {
            emrxh = new Long(sEmrxh);
        } catch (NumberFormatException e) {
            result.setCode("-1");
            result.setMessage("[PatientId]入参格式错误!");
            return result;
        }

        Long documentId = null;
        try {
            documentId = new Long(sDocumentId);
        } catch (NumberFormatException e) {
            result.setCode("-1");
            result.setMessage("[DocumentId]入参格式错误!");
            return result;
        }

        String content = documentManagerService.getDocumentContentInfo(emrxh, documentId, isbcl);
        if (content == null || content.trim().isEmpty()) {
            result.setCode("-1");
            result.setMessage("根据病历记录序号" + documentId + "和标记" + isbcl + "未找到相应的病历记录内容!");
            return result;
        }
        result.setWbnr(content);
        result.setCode("200");
        result.setMessage("成功");
        return result;
    }


}
