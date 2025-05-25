package com.learn.bigevent.validation;

import com.learn.bigevent.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StateValidation implements ConstraintValidator<State,String> {
    /**
     *
     * @param s 将来要校验的数据
     * @param constraintValidatorContext 验证上下文对象，用于自定义校验消息
     * @return 如果返回false则校验不通过；true则校验通过
     */
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) {
            return false;
        }
        return s.equals("已发布") || s.equals("草稿") || s.equals("Published") || s.equals("Draft");
    }
}
