package com.evhorus.java_spring_course.common.mediator;

public interface RequestHandler<T extends Request<R>, R> {

    R handle(T request);

    Class<T> getRequestType();
}
