package cn.weizhis.cms.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Auther: minliang
 * @Date: 2018/10/9 16:28
 * @Description:
 */
public class OAuth2Token implements AuthenticationToken {
    private String token;

    public OAuth2Token(String token){
        this.token = token;
    }

    @Override
    public String getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

}
