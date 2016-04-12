package com.github.cstroe.sqs.hibernate;

import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.config.ConfigurableComponent;
import net.sourceforge.stripes.config.Configuration;
import net.sourceforge.stripes.controller.ExecutionContext;
import net.sourceforge.stripes.controller.Interceptor;
import net.sourceforge.stripes.controller.Intercepts;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;

@Intercepts(value = {LifecycleStage.HandlerResolution, LifecycleStage.ResolutionExecution, LifecycleStage.RequestComplete})
public class HibernateStripesInterceptor implements Interceptor, ConfigurableComponent {
    private static final Logger LOG = LoggerFactory.getLogger(HibernateStripesInterceptor.class);

    public void init(Configuration configuration) throws Exception {
        LOG.debug("initialized");
    }

    @SuppressWarnings("unchecked")
    public Resolution intercept(ExecutionContext ctx) throws Exception {
        Resolution next;
        try {
            if (ctx.getLifecycleStage().equals(LifecycleStage.HandlerResolution)) {
                LOG.debug("beginning hibernate transaction");
                HibernateSessionUtil.beginTransaction();
            }
            next = ctx.proceed();
            if (ctx.getLifecycleStage().equals(LifecycleStage.ResolutionExecution)) {
                LOG.debug("committing and closing transaction");
                HibernateSessionUtil.commitTransaction();
            }

            if(ctx.getLifecycleStage().equals(LifecycleStage.RequestComplete)) {
                HibernateSessionUtil.closeSession();
            }
       } catch (Throwable ex) {
            HibernateSessionUtil.rollbackOnly();
            if (ex instanceof ServletException) {
                throw (ServletException) ex;
            } else if (ex instanceof HibernateException) {
                throw (HibernateException) ex;
            } else {
                throw new ServletException(ex);
            }
        }
        return next;
    }
}
