package cn.weizhis.cms.service.impl.system;

import cn.weizhis.cms.common.InvokeResult;
import cn.weizhis.cms.dao.system.SysUserTokenDao;
import cn.weizhis.cms.entity.system.SysUserTokenEntity;
import cn.weizhis.cms.service.system.SysUserTokenService;
import cn.weizhis.cms.shiro.TokenGenerator;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Auther: minliang
 * @Date: 2018/10/9 15:40
 * @Description:
 */
@Service("sysUserTokenService")
public class SysUserTokenServiceImpl implements SysUserTokenService {
    private final static int EXPIRE = 3600 * 12;

    @Autowired
    private SysUserTokenDao sysUserTokenDao;

    @Override
    public InvokeResult createToken(long userId) {
        //生成一个token
        String token = TokenGenerator.generateValue();

        //当前时间
        Date now = new Date();
        //过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

        //判断是否生成过token
        SysUserTokenEntity tokenEntity = sysUserTokenDao.queryObject(userId);
        if(tokenEntity == null){
            tokenEntity = new SysUserTokenEntity();
            tokenEntity.setUserId(userId);
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);

            //保存token
            sysUserTokenDao.save(tokenEntity);
        }else{
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);

            //更新token
            sysUserTokenDao.update(tokenEntity);
        }
        InvokeResult result = new InvokeResult();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", token);
        jsonObject.put("expire", EXPIRE);
        result.setData(jsonObject);

        return result;
    }

    @Override
    public InvokeResult queryToken(long userId) {
        //判断是否生成过token
        SysUserTokenEntity tokenEntity = sysUserTokenDao.queryObject(userId);
        InvokeResult result = new InvokeResult();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", tokenEntity.getToken());
        jsonObject.put("expire", tokenEntity.getExpireTime());
        result.setData(jsonObject);
        return result;
    }

    @Override
    public SysUserTokenEntity queryByToken(String accessToken) {
        //判断是否生成过token
        SysUserTokenEntity tokenEntity = sysUserTokenDao.queryByToken(accessToken);
        return tokenEntity;
    }

    @Override
    public void logout(long userId) {
        //生成一个token
        String token = TokenGenerator.generateValue();

        //修改token
        SysUserTokenEntity tokenEntity = new SysUserTokenEntity();
        tokenEntity.setUserId(userId);
        tokenEntity.setToken(token);
        sysUserTokenDao.update(tokenEntity);
    }
}
