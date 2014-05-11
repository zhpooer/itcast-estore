package io.zhpooer.estore.web.filter;

import io.zhpooer.estore.service.OrderService;
import io.zhpooer.estore.service.impl.OrderServiceImpl;
import io.zhpooer.estore.utils.ServiceFactory;

import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class OrderCleanListener implements ServletContextListener {

    Timer timer = null;
    OrderService os = (OrderService) ServiceFactory.generate(OrderServiceImpl.class);
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                os.cleanUnpayOrders();
            }
        }, 100*1000, 1000l*60*30);
    }

}
