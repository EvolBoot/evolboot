package org.evolboot.ws.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.ws.core.convert.WsAttributeConverter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author evol
 */
@Slf4j
public final class WsMessageHandle {

    private Map<String, MethodAndParamClass> wsHandles;


    public WsMessageHandle(Map<String, MethodAndParamClass> wsHandles) {
        this.wsHandles = wsHandles;
    }


    public String handleMessage(String action, String message) {
        MethodAndParamClass methodAndParamClass = wsHandles.get(action);
        if (methodAndParamClass == null) {
            log.info("没有对应的Websocket处理器:{}", action);
            return null;
        }
        try {
            Method method = methodAndParamClass.getMethod();
            Object invoke;
            if (methodAndParamClass.getValueOf() != null) {
                Object apply = methodAndParamClass.valueOf.convert(message);
                invoke = method.invoke(methodAndParamClass.getBean(), apply);
            } else {
                invoke = method.invoke(methodAndParamClass.getBean(), JsonUtil.parse(message, methodAndParamClass.getClazz()));
            }
            return JsonUtil.stringify(invoke);
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error("Websocket处理器报错", e);
        }
        return null;
    }


    @Getter
    @Setter
    @AllArgsConstructor
    public static class MethodAndParamClass {
        private Class<?> clazz;
        private Method method;
        private Object bean;
        public WsAttributeConverter<String, Object> valueOf;

    }


}
