package cn.weizhis.cms.redis;

/**
 * @Auther: minliang
 * @Date: 2018/10/9 10:20
 * @Description:
 */
public class RedisKeys {
    public static String getSysConfigKey(String key){
        return "sys:config:" + key;
    }

    public static String getShiroSessionKey(String key){
        return "sessionid:" + key;
    }

}
