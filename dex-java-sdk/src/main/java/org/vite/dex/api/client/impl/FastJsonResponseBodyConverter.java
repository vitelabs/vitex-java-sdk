package org.vite.dex.api.client.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.ResponseBody;
import okio.BufferedSource;
import okio.Okio;
import org.vite.dex.api.client.exception.DexApiException;
import retrofit2.Converter;

import java.io.IOException;
import java.lang.reflect.Type;

@Slf4j
public class FastJsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Type type;

    public FastJsonResponseBodyConverter(Type type) {
        this.type = type;
    }

    /*
     * 转换方法
     */
    @Override
    public T convert(ResponseBody value) throws IOException {
        BufferedSource bufferedSource = Okio.buffer(value.source());
        String tempStr = bufferedSource.readUtf8();
        bufferedSource.close();

        JSONObject json = JSONObject.parseObject(tempStr);
        Integer code = json.getInteger("code");
        String msg = json.getString("msg");
        // 当code不为0时，设置data为{}，这样转化就不会出错了
        if (code == 0) {
            return json.getObject("data",type);
        }

        log.error("code:{},msg:{}",code,msg);
        //异常处理
        switch (code){
            case 1001:
                throw new DexApiException(DexApiException.REQUESTS_LIMIT_ERROR ,msg);
            case 1002:
                throw new DexApiException(DexApiException.INPUT_ERROR ,msg);
            case 1003:
                throw new DexApiException(DexApiException.GENERATE_BLOCK_ERROR ,msg);
            case 1004:
                throw new DexApiException(DexApiException.EXEC_ERROR ,msg);
            default:
                throw new DexApiException(DexApiException.SERVER_ERROR,msg);
        }
    }
}
