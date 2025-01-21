package com.codebox.submission_service.mapper;

public interface Mapper <T, U> {

    U mapTo(T t);

    T mapFrom(U u);

}
