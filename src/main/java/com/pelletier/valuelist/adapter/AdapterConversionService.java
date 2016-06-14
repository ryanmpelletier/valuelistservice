package com.pelletier.valuelist.adapter;

public interface AdapterConversionService {
    public <T> T convertIfNeeded(String paramKey, Object paramValue);
}
