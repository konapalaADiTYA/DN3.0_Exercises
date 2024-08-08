package com.library.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.library.service.BookService.addBook(..))")
    public void logBefore() {
        System.out.println("LoggingAspect.logBefore() : Method addBook() is about to be called");
    }

    @After("execution(* com.library.service.BookService.addBook(..))")
    public void logAfter() {
        System.out.println("LoggingAspect.logAfter() : Method addBook() has been called");
    }
}
