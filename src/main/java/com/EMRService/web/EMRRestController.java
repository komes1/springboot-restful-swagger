package com.EMRService.web;

import com.EMRService.entity.GetDocumentContentInfoRequestDataDTO;
import com.EMRService.entity.GetDocumentContentInfoResponseDataDTO;
import com.EMRService.entity.ResponseDataDTO;
import com.EMRService.service.DocumentManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EMRService.entity.User;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.math.BigDecimal;
import java.util.List;


/**
 * 
* @Title: UserRestController
* @Description: 
* 用户控制层
* 在浏览器输入 http://localhost:8183/swagger-ui.html 即可查看
* @Version:1.0.0  
* @author pancm
* @date 2018年3月19日
 */
@RestController
@RequestMapping(value = "/api")
public class EMRRestController {
	@Autowired
	private DocumentManagerService documentManagerService;
	private  final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ApiOperation(value="删除病历", notes="获取病历")
    @PostMapping("/DeleteDocument")
    public ResponseDataDTO DeleteDocument(@RequestBody GetDocumentContentInfoRequestDataDTO requestDataDTO) {
        logger.info("开始删除病历，请求参数:{}",requestDataDTO);
        ResponseDataDTO result=new ResponseDataDTO();
        String sEmrxh=requestDataDTO.getPatientId();
        String sDocumentId=requestDataDTO.getDocumentId();
        String isbcl=requestDataDTO.getDocumentIdFlag();

        if(requestDataDTO==null)
        {
            result.setCode("-1");
            result.setMessage("入参格式错误!");
            return result;
        }

        BigDecimal emrxh = null;
        try {
            emrxh = new BigDecimal(sEmrxh);
        } catch (NumberFormatException e) {
            result.setCode("-1");
            result.setMessage("[PatientId]入参格式错误!");
            return result;
        }

        BigDecimal documentId = null;
        try {
            documentId = new BigDecimal(sDocumentId);
        } catch (NumberFormatException e) {
            result.setCode("-1");
            result.setMessage("[DocumentId]入参格式错误!");
            return result;
        }


        String content= documentManagerService.getDocumentContentInfo(emrxh,documentId,isbcl);
        if(content==null || content.trim().isEmpty())
        {
            result.setCode("-1");
            result.setMessage("根据病历记录序号" + documentId + "和标记" + isbcl + "未找到相应的病历记录内容!");
            return result;
        }
        result.setCode("200");
        result.setMessage("成功");
        return result;
    }
	
	/**
	 * 注：@GetMapping("/user")是spring 4.3的新注解等价于：
	 * @RequestMapping(value = "/user", method = RequestMethod.GET)
	 * @param user
	 * @return
	 */
	@ApiOperation(value="获取用户列表", notes="根据User对象查询用户信息")
	@ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @PostMapping("/findByUser")
    public List<User> findByUser(User user) {
    	logger.info("开始查询用户列表，请求参数:{}",user);
    	/*User user2 =new User();
    	user2.setId(1L);
    	user2.setAge(18);
    	user2.setName("xuwujing");
        return user2;*/
		return documentManagerService.selectInfoResult();
    }

	@ApiOperation(value="获取病历内容信息", notes="获取病历内容信息")
	@PostMapping("/GetDocumentContentInfo")
	public GetDocumentContentInfoResponseDataDTO GetDocumentContentInfo(@RequestBody GetDocumentContentInfoRequestDataDTO requestDataDTO) {
		logger.info("开始获取病历内容信息，请求参数:{}",requestDataDTO);
        GetDocumentContentInfoResponseDataDTO result=new GetDocumentContentInfoResponseDataDTO();
		String sEmrxh=requestDataDTO.getPatientId();
		String sDocumentId=requestDataDTO.getDocumentId();
		String isbcl=requestDataDTO.getDocumentIdFlag();

        if(requestDataDTO==null)
        {
            result.setCode("-1");
            result.setMessage("入参格式错误!");
            return result;
        }

        BigDecimal emrxh = null;
        try {
            emrxh = new BigDecimal(sEmrxh);
        } catch (NumberFormatException e) {
            result.setCode("-1");
            result.setMessage("[PatientId]入参格式错误!");
            return result;
        }

        BigDecimal documentId = null;
        try {
            documentId = new BigDecimal(sDocumentId);
        } catch (NumberFormatException e) {
            result.setCode("-1");
            result.setMessage("[DocumentId]入参格式错误!");
            return result;
        }


		String content= documentManagerService.getDocumentContentInfo(emrxh,documentId,isbcl);
		if(content==null || content.trim().isEmpty())
        {
            result.setCode("-1");
            result.setMessage("根据病历记录序号" + documentId + "和标记" + isbcl + "未找到相应的病历记录内容!");
            return result;
        }
		result.setCourseRecordContent(content);
		result.setCode("200");
		result.setMessage("成功");
		return result;
	}
}
