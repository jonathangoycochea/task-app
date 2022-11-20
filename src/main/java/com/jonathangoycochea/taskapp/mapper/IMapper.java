package com.jonathangoycochea.taskapp.mapper;

public interface IMapper<I, O> {
    public O map(I input);
}
