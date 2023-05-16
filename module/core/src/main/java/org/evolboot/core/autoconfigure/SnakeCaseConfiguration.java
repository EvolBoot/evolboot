package org.evolboot.core.autoconfigure;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 针对使用@RequestParam 注解参数，转为 SNAKE_CASE 类型
 * 用不上
 *
 * @author evol
 */
@Deprecated
@Configuration
public class SnakeCaseConfiguration implements WebMvcConfigurer {

//
//    @Bean
//    @Primary
//    public ParameterNameDiscoverer parameterNameDiscoverer() {
//        return new SnakeCaseParameterNameDiscoverer();
//    }

    public static class SnakeCaseParameterNameDiscoverer implements ParameterNameDiscoverer {

        private ParameterNameDiscoverer defaultParameterNameDiscoverer = new DefaultParameterNameDiscoverer();

        @Override
        public String[] getParameterNames(Method method) {
            String[] defaultParameterNames = defaultParameterNameDiscoverer.getParameterNames(method);
            Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            Class<?>[] parameterTypes = method.getParameterTypes();
            List<String> parameterNames = new ArrayList<>();

            for (int i = 0; i < defaultParameterNames.length; i++) {
                String defaultParameterName = defaultParameterNames[i];
                String snakeCaseParameterName = StringUtils.hasText(defaultParameterName)
                        ? StringUtils.uncapitalize(StringUtils
                        .arrayToDelimitedString(StringUtils.tokenizeToStringArray(defaultParameterName, "_"), "_"))
                        : "";

                for (Annotation annotation : parameterAnnotations[i]) {
                    if (annotation.annotationType().equals(RequestParam.class)) {
                        RequestParam requestParam = AnnotationUtils.getAnnotation(annotation, RequestParam.class);
                        String requestParamValue = requestParam.value();

                        if (StringUtils.hasText(requestParamValue)) {
                            snakeCaseParameterName = requestParamValue;
                        }

                        break;
                    }
                }

                parameterNames.add(snakeCaseParameterName);
            }

            return parameterNames.toArray(new String[0]);
        }

        @Override
        public String[] getParameterNames(Constructor<?> constructor) {
            return defaultParameterNameDiscoverer.getParameterNames(constructor);
        }
    }
}
