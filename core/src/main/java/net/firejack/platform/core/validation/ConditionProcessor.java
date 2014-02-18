/*
 * Firejack Open Flame - Copyright (c) 2012 Firejack Technologies
 *
 * This source code is the product of the Firejack Technologies
 * Core Technologies Team (Benjamin A. Miller, Oleg Marshalenko, and Timur
 * Asanov) and licensed only under valid, executed license agreements
 * between Firejack Technologies and its customers. Modification and / or
 * re-distribution of this source code is allowed only within the terms
 * of an executed license agreement.
 *
 * Any modification of this code voids any and all warranties and indemnifications
 * for the component in question and may interfere with upgrade path. Firejack Technologies
 * encourages you to extend the core framework and / or request modifications. You may
 * also submit and assign contributions to Firejack Technologies for consideration
 * as improvements or inclusions to the platform to restore modification
 * warranties and indemnifications upon official re-distributed in patch or release form.
 */

package net.firejack.platform.core.validation;

import net.firejack.platform.core.utils.OpenFlameSpringContext;
import net.firejack.platform.core.validation.annotation.Condition;
import net.firejack.platform.core.validation.annotation.ValidationMode;
import net.firejack.platform.core.validation.condition.AbstractCondition;
import net.firejack.platform.core.validation.constraint.vo.Constraint;
import net.firejack.platform.core.validation.exception.RuleValidationException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Component
public class ConditionProcessor implements IMessageRuleProcessor {

    private static final Logger logger = Logger.getLogger(ConditionProcessor.class);

    @Override
    public List<ValidationMessage> validate(Method readMethod, String property, Object value, ValidationMode mode)
            throws RuleValidationException {
        List<ValidationMessage> validationMessages = new ArrayList<ValidationMessage>();
        Annotation annotation = readMethod.getAnnotation(Condition.class);
        if (annotation != null) {
            Condition condition = (Condition) annotation;
            try {
                AbstractCondition voCondition = (AbstractCondition) OpenFlameSpringContext.getBean(condition.value());
                List<ValidationMessage> vMessages = voCondition.validate(value);
                if (vMessages != null) {
                    validationMessages = vMessages;
                }
            } catch (NoSuchBeanDefinitionException e) {
                logger.warn(e.getMessage());
            }
        }
        return validationMessages;
    }

    @Override
    public List<Constraint> generate(Method readMethod, String property, Map<String, String> params) {
        List<Constraint> constraintVOs = null;
        Annotation annotation = readMethod.getAnnotation(Condition.class);
        if (annotation != null) {
            Condition condition = (Condition) annotation;
            try {
                AbstractCondition voCondition = (AbstractCondition) OpenFlameSpringContext.getBean(condition.value());
                constraintVOs = voCondition.generateConstraints(params);
            } catch (NoSuchBeanDefinitionException e) {
                logger.warn(e.getMessage());
            }
        }
        return constraintVOs;
    }

}