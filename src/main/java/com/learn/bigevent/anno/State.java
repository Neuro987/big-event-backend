package com.learn.bigevent.anno;

import com.learn.bigevent.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented // Meta annotation
@Target({ElementType.FIELD}) // Meta annotation, indicates the annotation can be applied to fields
@Retention(RetentionPolicy.RUNTIME) // Meta annotation, indicates the annotation is retained at runtime

@Constraint(
        validatedBy = {StateValidation.class} // Specifies the class that implements the validation logic
)


public @interface State {

    // The error message to be returned when validation fails
    String message() default "state的值只能是“已发布”或“草稿”";
    // Defines the groups the annotation belongs to
    Class<?>[] groups() default {};
    // Defines the payload for additional data
    Class<? extends Payload>[] payload() default {};
}
