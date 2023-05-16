package org.evolboot.ws.core.autoconfigure;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.ws.core.WsMessageHandle;
import org.evolboot.ws.core.WsOnMessage;
import org.evolboot.ws.core.WsService;
import org.evolboot.ws.core.convert.WsAttributeConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author evol
 */
@Configuration
@Slf4j
public class WsCoreConfiguration {


    @Bean
    public WsMessageHandle getWsMessageHandle(ApplicationContext context) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        log.info("初始化Websocket处理器");
        Map<String, WsMessageHandle.MethodAndParamClass> wsHandles = Maps.newHashMap();
        for (Object bean : context.getBeansWithAnnotation(WsService.class).values()) {
            Class<?> clazz = bean.getClass();
            for (Method method : clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent(WsOnMessage.class)) {
                    WsOnMessage wsOnMessage = method.getAnnotation(WsOnMessage.class);
                    String action = wsOnMessage.value();
                    Class<?>[] paramTypes = method.getParameterTypes();
                    Class<?> convertClass = wsOnMessage.convert();
                    if (paramTypes.length == 0) {
                        log.error("存在没有参数的Websocket处理器:action:{},class:{},method:{}", action, clazz, method.getName());
                        throw new RuntimeException("存在没有参数的Websocket处理器:" + action + ":" + clazz + ":" + method.getName());
                    } else if (paramTypes.length > 1) {
                        log.error("存在重复的Websocket处理器:action:{},class:{},method:{}", action, clazz, method.getName());
                        throw new RuntimeException("存在重复的Websocket处理器:" + action + ":" + clazz + ":" + method.getName());
                    } else if (wsHandles.containsKey(action)) {
                        log.error("存在多个相同的Websocket处理器:action:{},class:{},method:{}", action, clazz, method.getName());
                        throw new RuntimeException("存在多个相同的Websocket处理器:" + action + ":" + clazz + ":" + method.getName());
                    } else if (convertClass.equals(Object.class) &&
                            (paramTypes[0].getSimpleName().equals("List")
                                    || paramTypes[0].getSimpleName().equals("Map")
                                    || paramTypes[0].getSimpleName().equals("Set")
                                    || paramTypes[0].getSimpleName().indexOf("[]") > 0)) {
                        log.error("List,Map,Set,Array 等结构需要自定义转换器:实现 WsAttributeConverter 接口, action:{},class:{},method:{}", action, clazz, method.getName());
                        throw new RuntimeException("List,Map,Set,Array 等结构需要自定义转换器:实现 WsAttributeConverter 接口:" + action + ":" + clazz + ":" + method.getName());

                    } else {
                        Class<?> paramClass = paramTypes[0];
                        WsAttributeConverter<String, Object> valueOf = null;
                        String simpleName = paramClass.getSimpleName();
                        if (!wsOnMessage.convert().equals(Object.class)) {
                            valueOf = (WsAttributeConverter<String, Object>) wsOnMessage.convert().getDeclaredConstructor().newInstance();
                        } else if (paramClass.isPrimitive() && paramClass.getName().equals("char")) {
                            valueOf = s -> s;
                        } else if (paramClass.isPrimitive() && paramClass.getName().equals("int") || simpleName.equals("Integer")) {
                            valueOf = Integer::valueOf;
                        } else if (simpleName.equalsIgnoreCase("Byte")) {
                            valueOf = Byte::valueOf;
                        } else if (simpleName.equalsIgnoreCase("Short")) {
                            valueOf = Short::valueOf;
                        } else if (simpleName.equalsIgnoreCase("Long")) {
                            valueOf = Long::valueOf;
                        } else if (simpleName.equalsIgnoreCase("Float")) {
                            valueOf = Float::valueOf;
                        } else if (simpleName.equalsIgnoreCase("Double")) {
                            valueOf = Double::valueOf;
                        } else if (simpleName.equalsIgnoreCase("Boolean")) {
                            valueOf = Boolean::valueOf;
                        } else if (simpleName.equalsIgnoreCase("String")) {
                            valueOf = s -> s;
                        }
                        wsHandles.put(action, new WsMessageHandle.MethodAndParamClass(paramClass, method, bean, valueOf));
                    }
                }
            }
        }
        log.info("初始化Websocket处理器,共有:{}个", wsHandles.size());
        return new WsMessageHandle(wsHandles);
    }

}
