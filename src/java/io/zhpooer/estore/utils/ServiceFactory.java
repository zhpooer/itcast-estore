package io.zhpooer.estore.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ServiceFactory {
    public static Object generate(Class<?> clazz){
        final Object instance;
        try {
            instance = clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } 
       
        Object proxy = Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args)
                    throws Throwable {
                TransactionManager.startTransaction();
                Object result = null;
                try {
                    result = method.invoke(instance, args);
                    TransactionManager.commit();
                } catch (Exception e) {
                    TransactionManager.rollback();
                    throw new RuntimeException(e);
//                    e.printStackTrace();
                } finally {
                    TransactionManager.release();
                }
                return result;
            }
        });
        return proxy; 
    }
}
