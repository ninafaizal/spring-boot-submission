package com.bank.controller;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShutdownController implements ApplicationContextAware {

    private ApplicationContext context;

    @PostMapping("/shutdownContext")
    public String shutdownContext() {
        Thread shutdownThread = new Thread(() -> {
            try {
                Thread.sleep(1000); // Wait for 1 second to allow response to return
                ((ConfigurableApplicationContext) context).close();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        shutdownThread.setDaemon(false);
        shutdownThread.start();
        return "Shutting down gracefully...";
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
