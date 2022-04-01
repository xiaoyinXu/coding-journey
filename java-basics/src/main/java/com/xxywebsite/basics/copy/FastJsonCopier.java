package com.xxywebsite.basics.copy;

import com.alibaba.fastjson.JSONObject;

/**
 * @author xuxiaoyin
 * @since 2022/4/1
 **/
public class FastJsonCopier implements Copier {
    @Override
    public <T> T copy(Object source, Class<T> targetClazz) {
        return JSONObject.parseObject(JSONObject.toJSONString(source), targetClazz);
    }
}
