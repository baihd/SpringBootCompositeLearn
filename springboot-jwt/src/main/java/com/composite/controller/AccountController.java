package com.composite.controller;

import com.alibaba.fastjson.JSON;
import com.composite.entity.Account;
import com.composite.entity.JsonResult;
import com.composite.entity.JwtProperty;
import com.composite.entity.ResultStatusCode;
import com.composite.utils.jwt.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AccountController {

    @Autowired
    private JwtProperty jwtProperty;

    // @ApiOperation(value = "使用JWT进行token验证，传入登录参数")
    @RequestMapping(value = "/oauth/token", method = RequestMethod.POST)
    public String getAccessToken(String accountName, String accountPwd,
                                 String clientId, String mobileType, String deviceToken,
                                 @RequestParam(value = "logonMode", required = false, defaultValue = "0") int logonMode) {

        JsonResult result = new JsonResult();

        try {
            if (clientId == null || (clientId.compareTo(jwtProperty.getClientId()) != 0)) {

                result.setStatus(ResultStatusCode.INVALID_CLIENTID.getErrorCode());
                result.setMessage(ResultStatusCode.INVALID_CLIENTID.getErrorMsg());

                return JSON.toJSONString(result);
            }

            Account user = new Account();
            /*try {
                user = userService.login(accountName, accountPwd, logonMode);
                accountService.updateLogin(accountName, mobileType, deviceToken);
            } catch (Exception e) {

                return new JsonResult(null, e.getMessage()).failure().toString();
            }*/

            //Department dept = deptService.getDeptByUserId(user.getId());

            // 拼装accessToken
            String accessToken = JwtHelper.createJWT(accountName, user.getId(), "userRole", jwtProperty.getClientId(),
                    jwtProperty.getName(), jwtProperty.getExpiresSecond() * 1000, jwtProperty.getBase64Secret());

            // 返回accessToken
            //  AccessToken accessTokenEntity = new AccessToken();
            //  accessTokenEntity.setAccessToken("bearer " + accessToken);

            Map map = new HashMap<>();
            map.put("accessToken", "bearer " + accessToken);
            map.put("userId", user.getId());
            map.put("realName", user.getRealName());
            map.put("phoneNo", user.getPhoneNumber());
            map.put("accountName", user.getAccountName());
            map.put("avatar", user.getAvatar());
            map.put("deptId", "dept"+user.getId());
            map.put("deptName", "dept"+user.getRealName());

            // 如果是后台管理登录的话，则继续查询一下这个人的所有的权限，所有的操作授权数据
            /*if (logonMode == 1) {
                List<Authorization> lst = authorizationService.getAuthorizationsByUserId(user.getId());
                List perms = new ArrayList<>();

                for (Authorization object : lst) {
                    perms.add(object.getPageInnerName() + ":" + object.getElementInnerName());
                }
                map.put("perms", perms);
            }*/

            result.setStatus(ResultStatusCode.OK.getErrorCode());
            result.setMessage(ResultStatusCode.OK.getErrorMsg());
            result.setData(map);

            return JSON.toJSONString(result);
        } catch (Exception ex) {
            result.setStatus(ResultStatusCode.SYSTEM_ERR.getErrorCode());
            result.setMessage(ResultStatusCode.SYSTEM_ERR.getErrorMsg());

            return JSON.toJSONString(result);
        }
    }
}
