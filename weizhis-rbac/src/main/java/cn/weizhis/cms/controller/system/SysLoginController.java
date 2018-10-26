package cn.weizhis.cms.controller.system;

import cn.weizhis.cms.common.InvokeResult;
import cn.weizhis.cms.shiro.ShiroUtils;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

/**
 * @Auther: minliang
 * @Date: 2018/10/8 11:05
 * @Description:
 */
@RestController
public class SysLoginController extends AbstractController {
    @Autowired
    private Producer producer;

    @GetMapping("captcha.jpg")
    public void captcha(HttpServletResponse response)throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //保存到shiro session
        ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
    }

    /**
     * 登录
     */
    @ResponseBody
    @PostMapping(value = "/login")
    public InvokeResult login(HttpServletRequest request, @RequestBody Map<String, String> param) {
        InvokeResult result = new InvokeResult();
        System.out.println(param);
        String username = param.get("userName");
        String password = param.get("password");
        String captcha = param.get("captcha");
//        String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
//        if(!captcha.equalsIgnoreCase(kaptcha)){
//            return result.failure("验证码不正确");
//        }
        try{
            Subject subject = ShiroUtils.getSubject();
            //sha256加密
            System.out.println(password);
            password = new Sha256Hash(password).toHex();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            subject.login(token);
            result.setData(ShiroUtils.getSession().getId());
        }catch (UnknownAccountException e) {
            return result.failure(e.getMessage());
        }catch (IncorrectCredentialsException e) {
            return result.failure(e.getMessage());
        }catch (LockedAccountException e) {
            return result.failure(e.getMessage());
        }catch (AuthenticationException e) {
            return result.failure("账户验证失败");
        }
        return result.sucess();
    }

    /**
     * 登录
     */
//    @ResponseBody
//    @RequestMapping(value = "/sys/login", method = RequestMethod.POST)
//    public InvokeResult login(HttpServletRequest request, @RequestBody Map<String, String> param)throws IOException {
//        InvokeResult result = new InvokeResult();
//        String username = param.get("username");
//        String password = param.get("password");
//        String captcha = param.get("captcha");
//        String uuid = param.get("uuid");
//
////        boolean flag = sysCaptchaService.validate(uuid, captcha);
////        if(!flag){
////            return result.failure("验证码不正确");
////        }
//
//        //用户信息
//        SysUserEntity user = sysUserService.queryUserByUserName(username);
//        //账号不存在、密码错误
//        if(user == null || !user.getPassword().equals(new Sha256Hash(password).toHex())) {
//            return result.failure("账号或密码不正确");
//        }
//        //账号锁定
//        if(user.getStatus() == 0){
//            return result.failure("账号已被锁定,请联系管理员");
//        }
//        result = sysUserTokenService.createToken(user.getUserId());
//        return result.sucess();
//    }

    /**
     * 退出
     */
    @GetMapping(value = "logout")
    @ResponseBody
    public InvokeResult logout() {
        InvokeResult result = new InvokeResult();
        ShiroUtils.logout();
        return result;
    }

    @GetMapping(value = "/unauthorized")
    @ResponseBody
    public InvokeResult unauthorized() {
        return new InvokeResult().failure("请登录");
    }

}
