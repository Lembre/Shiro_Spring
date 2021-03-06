package shiro.handles;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Lembre on 2018.10.23
 */
@Controller
@RequestMapping("/shiro")
public class ShiroHandler {
    @RequestMapping("/login")//获取表单用户名和密码
    public String login(@RequestParam("username") String username, @RequestParam("password") String password){
        Subject currentUser = SecurityUtils.getSubject();

        if (!currentUser.isAuthenticated()) {
            // 把用户名和密码封装为 UsernamePasswordToken 对象
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            token.setRememberMe(true);
            try {
                System.out.println("1:"+token.hashCode());
               //执行登录
                currentUser.login(token);//这个token实际上传到了Realm
            }

            catch (AuthenticationException ae) {
                //unexpected condition?  error?
                System.out.println("2.login fail:"+ae.getMessage());
            }
        }
        return "redirect:/list.jsp";
    }
}
