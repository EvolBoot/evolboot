package org.evolboot.ws.core;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.ws.core.convert.WsAttributeConverter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
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


    /**
     * 不知类型时，先转为 string，然后再转
     *
     * @param action
     * @param message
     * @return
     */
    public Object handleMessage(String action, String message) {
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
            return invoke;
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error("Websocket处理器报错", e);
        }
        return null;
    }

    /**
     * message 已知类型
     *
     * @param action
     * @param message
     * @return
     */
    public Object handleMessage(String action, Object message) {
        MethodAndParamClass methodAndParamClass = wsHandles.get(action);
        if (methodAndParamClass == null) {
            log.info("没有对应的Websocket处理器:{}", action);
            return null;
        }
        try {
            Method method = methodAndParamClass.getMethod();
            return method.invoke(methodAndParamClass.getBean(), message);
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

    public MethodAndParamClass getByAction(String action) {
        return wsHandles.get(action);
    }

    public Collection<String> getAllAction() {
        return wsHandles.keySet();
    }

}
