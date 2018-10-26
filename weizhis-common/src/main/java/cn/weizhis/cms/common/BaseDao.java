package cn.weizhis.cms.common;

import java.util.List;
import java.util.Map;

/**
 * @Auther: minliang
 * @Date: 2018/10/8 14:06
 * @Description:
 */
public interface BaseDao<T> {
    void save(T t);

    void save(Map<String,Object> map);

    void saveBatch(List<T> list);

    int update(T t);

    int update(Map<String,Object> map);

    int delete(Long id);

    int delete(T t);

    int delete(Map<String,Object> map);

    int deleteBatch(Object[] ids);

    T queryObject(Object id);

    List<T> queryList(Map<String,Object> map);

    int queryTotal(Map<String,Object> map);

    int queryTotal();
}
