package com.wine.to.up.test.service.components;

import com.wine.to.up.test.service.annotations.InjectEventLogger;
import com.wine.to.up.test.service.logging.EventLogger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

/**
 * Post processing injection of EventLogger
 * in class' fields marked by {@code InjectEventLogger} annotation
 */
@Component
public class EventLoggerBeanPostProcessor implements BeanPostProcessor {
    @Autowired
    AppMetrics metrics;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        process(bean);

        // We must return bean after completion of processing
        return bean;
    }

    private void process(Object bean) {
        var beanClass = bean.getClass();
        var fields = beanClass.getDeclaredFields();

        // Search every field for annotation presence
        for (var field : fields) {
            var annotation = AnnotationUtils.getAnnotation(field, InjectEventLogger.class);

            // Skip irrelevant fields
            if (annotation == null) {
                continue;
            }

            // Allow access to private fields
            field.setAccessible(true);

            var logger = LoggerFactory.getLogger(beanClass);

            // Inject our logger
            ReflectionUtils.setField(field, bean, new EventLogger(logger, metrics)); // todo sukhoa move to common lib
        }
    }
}
