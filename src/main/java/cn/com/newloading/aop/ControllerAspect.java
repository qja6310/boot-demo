package cn.com.newloading.aop;

import cn.com.newloading.bean.WebLog;
import cn.com.newloading.service.LogService;
import cn.com.newloading.utils.TimeUtil;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Enumeration;

@Aspect
@Component
public class ControllerAspect {

    @Autowired
    private LogService logService;

    @Around("execution(* cn.com.newloading.controller.*.*(..))")
    public Object process(ProceedingJoinPoint point) throws Throwable {
        WebLog log = new WebLog();//日志对象
        Date inTime = new Date();
        log.setInTime(TimeUtil.dateToString(inTime));
        Object[] args = point.getArgs();
        Object obj = point.proceed(args);//目标方法，返回的值
        String url = point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName();
        //过滤不需要记录的日志
        if("不需要记录的url".equals(url)){
            return obj;
        }
        log.setUrl(url);
        HttpServletRequest request = (HttpServletRequest)args[0];//入参是HttpServletRequest对象
        StringBuffer inParam = new StringBuffer();//入参
        Enumeration enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()){
            String paramName = (String) enumeration.nextElement();
            String value = request.getParameter(paramName);
            inParam.append(paramName + ":" + value + "&&&");
        }
        log.setInParam(inParam.toString().substring(0,inParam.toString().length() - 3));
        log.setOutParam(obj.toString());//回参
        Date outTime = new Date();
        log.setOutTime(TimeUtil.dateToString(outTime));
        log.setRedate(String.valueOf(outTime.getTime() - inTime.getTime()));
        logService.addLog(log);
        return obj;
    }
}
