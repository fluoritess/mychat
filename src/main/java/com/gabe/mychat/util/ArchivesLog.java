package com.gabe.mychat.util;

import java.lang.annotation.*;

/**
 * @author wsw
 * @Package com.gabe.mychat
 * @Description:
 * @date 2019年6月21日 11:14:47
 */

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ArchivesLog {
/** 要执行的操作类型比如：add操作 **/

    String operationType() default "";

/** 要执行的具体操作比如：添加用户 **/

    String operationName() default "";
}
