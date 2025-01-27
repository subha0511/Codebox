package com.codebox.contest_service.mapper;

public interface Mapper<T, U> {
    U mapTo(T t);

    T mapFrom(U u);
}
