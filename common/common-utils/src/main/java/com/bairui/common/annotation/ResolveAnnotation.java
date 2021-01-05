package com.bairui.common.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ResolveAnnotation {
    public String  serviceCode()  default "serviceCode";
    public String  serviceScene()  default "serviceScene";
}
