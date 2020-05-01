package com.kxingyi.blog.aspect;

import com.alibaba.fastjson.JSON;
import com.kxingyi.blog.enums.ResultEnum;
import com.kxingyi.blog.enums.StateEnum;
import com.kxingyi.blog.exception.ParamsValidationException;
import com.kxingyi.blog.pojo.Log;
import com.kxingyi.blog.service.LogService;
import com.kxingyi.blog.utils.validate.BeanValidator;
import com.kxingyi.blog.utils.StringUtils;
import com.kxingyi.blog.utils.ThreadLocalContext;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 切面输出基本信息
 * 以及记录日志
 *
 * @author: 杨德石
 * @date: 2019/8/5 13:22
 * @Version 1.0
 */
@Aspect
@Component
@Slf4j
public class RequestAspect {

@Autowired
private LogService logService;
    /**
     * 两个..代表所有子目录，最后括号里的两个..代表所有参数
     */
    @Pointcut("execution( * com.kxingyi.*.controller..*(..))")
    public void logPointCut() {
    }
    @Pointcut("execution( * com.kxingyi.*.interceptor..*(..))")
    public void authorizationCut() {
    }

    /**
     * 方法执行之前调用
     */
    @Before("logPointCut() || authorizationCut()")
    public void doBefore(JoinPoint joinPoint) throws Exception {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        String uri = request.getRequestURI();
        // 记录下请求内容
        printRequestLog(joinPoint, request, uri);
        Object[] args = joinPoint.getArgs();
        String errMsg = checkParams(args);
        if(!StringUtils.isNullOrEmpty(errMsg)){
            throw new ParamsValidationException(ResultEnum.ERROR.getCode(), errMsg);
        }
    }

    @Around("logPointCut() || authorizationCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object ob = pjp.proceed();
        long time = System.currentTimeMillis() - startTime;
        log.debug("耗时 : {}", time);
        Log logger = ThreadLocalContext.get().getLogger();
        logger.setLogTime(time);
        return ob;
    }
    /**
     *@QualifiedClassName:com.kxingyi.blog.aspect.RequestAspect
     *@ClassName:checkParams
     *@Description:TODO
     *@Author:byliu
     *@Date:2020/4/22 14:12
     *@Paramter:[args]
     *@Return:java.lang.String
     * 检查请求所带的参数
    */
    public String checkParams(Object[] args){
        StringBuilder errMsg = new StringBuilder();
        for (Object param : args) {
            String msg = BeanValidator.validator(param);
            if(StringUtils.isNullOrEmpty(msg)){
                continue;
            }
            errMsg.append(BeanValidator.validator(param));
        }
        return errMsg.toString();
    }
    /**
     * 后置通知
     *
     * @param ret
     */
    @AfterReturning(returning = "ret", pointcut = "logPointCut() || authorizationCut()")
    public void doAfterReturning(Object ret) {
        String result = JSON.toJSONString(ret);
        log.debug("返回值：{}", JSON.toJSONString(ret));
        Log logger = ThreadLocalContext.get().getLogger();
        logger.setLogResult(result);
        logService.save(logger);
    }

    /**
     * 异常通知
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "logPointCut() || authorizationCut()", throwing = "e")
    public void saveExceptionLog(JoinPoint joinPoint, Throwable e) {
        Log logger = ThreadLocalContext.get().getLogger();
        logger.setLogStatus(StateEnum.REQUEST_ERROR.getCode());
        String exception = StringUtils.getPackageException(e, "com.kxingyi");
        logger.setLogMessage(exception);
        logger.setLogTime(0L);
        logService.save(logger);
    }

    /**
     * 打印请求日志
     *
     * @param joinPoint
     * @param request
     * @param uri
     */
    private void printRequestLog(JoinPoint joinPoint, HttpServletRequest request, String uri) {
        // 拿到切面方法
        log.debug("请求地址 : {}", uri);
        log.debug("请求方式 : {}", request.getMethod());
        // 获取真实的ip地址
        String ip = StringUtils.getRemoteIp(request);
        log.debug("IP : {}", ip);
        String controllerName = joinPoint.getSignature().getDeclaringTypeName();
        log.debug("方法 : {}.{}", controllerName, joinPoint.getSignature().getName());
        String params = Arrays.toString(joinPoint.getArgs());
        log.debug("请求参数：{}", params);
        // 获取日志实体
        Log logger = ThreadLocalContext.get().getLogger();
        logger.setLogUrl(uri);
        logger.setLogParams(params);
        logger.setLogStatus(StateEnum.REQUEST_SUCCESS.getCode());
        logger.setLogMethod(request.getMethod());
        logger.setLogIp(ip);
    }

}
