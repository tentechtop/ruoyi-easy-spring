package com.ruoyi.framework.aspectj;


import com.ruoyi.framework.redis.RedisCache;
import com.ruoyi.project.system.service.RedisService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

import static com.ruoyi.common.constant.CacheConstants.USER_BALANCE;
import static com.ruoyi.common.constant.RedisPrefixConst.BALANCE_EXPIRE_TIME;


@Aspect
@Component
public class BalanceCacheAspect {

    @Autowired
    private RedisService redisService;

    @Resource
    private RedisTemplate redisTemplate;




    /**
     * 设置缓存切入点 在注解的位置切入代码
     */
    @Pointcut("@annotation(com.ruoyi.framework.aspectj.lang.annotation.BalanceCache)")
    public void balanceCachePointCut() {}



    @Around(value = "balanceCachePointCut()")
    @SuppressWarnings("unchecked")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {

        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();

        Object[] args = pjp.getArgs();


        for (Object arg : args) {
            System.out.println("Method parameter: " + arg);
        }

        //判断用户是否存在余额
        Integer userBalance = (Integer)redisService.get(USER_BALANCE+"123");
        if (userBalance==null){
            System.out.println("redis无数据");
            redisService.set(USER_BALANCE+"123", 5, BALANCE_EXPIRE_TIME);
            System.out.println("目标方法继续执行");
            return pjp.proceed();
        }else {
            return  userBalance==0? null:pjp.proceed();
        }
    }


    @AfterReturning(value = "balanceCachePointCut()", returning = "keys")
    @SuppressWarnings("unchecked")
    public Object afterMethod(JoinPoint joinPoint, Object keys) {
        System.out.println("方法执行后");

        Integer userBalance = (Integer)redisService.get(USER_BALANCE+"123");
        System.out.println("额度"+userBalance);

        Integer newBalance = userBalance - 1;
        System.out.println("新的额度"+newBalance);
        redisService.set(USER_BALANCE+"123", newBalance, BALANCE_EXPIRE_TIME);


        return null;
    }


}
