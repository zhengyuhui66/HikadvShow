package com.hik.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public final class SpringBeanUtil implements ApplicationContextAware {

    private static ApplicationContext ctx;

    /**
     * ͨ��spring�����ļ������õ�bean idȡ��bean����
     * @param id spring bean IDֵ
     * @return spring bean����
     */
    public static Object getBean(String id) {
        if (ctx == null) {
            throw new NullPointerException("ApplicationContext is null");
        }
        return ctx.getBean(id);
    }

  @Override
  public void setApplicationContext(ApplicationContext applicationcontext)
      throws BeansException {
    ctx = applicationcontext;
  }

}