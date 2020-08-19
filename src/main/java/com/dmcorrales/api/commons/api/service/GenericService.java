package com.dmcorrales.api.commons.api.service;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public abstract class GenericService<HK, HV> {

    public abstract String save(HV entity);
    public abstract List<HV> findAll();



}
