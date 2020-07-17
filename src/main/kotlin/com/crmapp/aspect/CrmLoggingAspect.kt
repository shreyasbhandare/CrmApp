package com.crmapp.aspect

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Aspect
@Component
class CrmLoggingAspect {
    private val logger = LoggerFactory.getLogger(CrmLoggingAspect::javaClass.name)

    // for controller
    @Pointcut("execution(* com.crmapp.controller.*.*(..))")
    fun forControllerPackage() {}

    // for service
    @Pointcut("execution(* com.crmapp.service.*.*(..))")
    fun forServicePackage() {}

    // for DAO
    @Pointcut("execution(* com.crmapp.dao.*.*(..))")
    fun forDAOPackage() {}

    @Pointcut("forControllerPackage() || forServicePackage() || forDAOPackage()")
    fun forAppFlow() {}

    @Before("forAppFlow()")
    fun before(theJoinPoint: JoinPoint) {
        // show method signature
        val theMethod = theJoinPoint.signature.toShortString()
        logger.info("===> in @Before calling method: $theMethod")

        // display param
        val args = theJoinPoint.args
        args.forEachIndexed { index, element ->
            logger.info("===> Arg $index = $element")
        }
    }

    @AfterReturning(pointcut = "forAppFlow()", returning = "theResult")
    fun afterReturning(theJoinPoint: JoinPoint, theResult : Any?) {
        // show method signature
        val theMethod = theJoinPoint.signature.toShortString()
        logger.info("===> in @AfterReturning calling method: $theMethod")

        logger.info("===> method result: $theResult")
    }
}