package com.bairui.common.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface BussRouteAnnotation {
    public  String  routeBussCode()  default  "routeBussCode";
}
