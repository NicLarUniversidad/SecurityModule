package com.cl.evaluation.register.services.database;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public abstract class AbmService<R extends CrudRepository<M, String>, M> {

    @Autowired
    protected R repository;

    public M save(M model) {
        return repository.save(model);
    }

    public M save(M model, String id) {
        M savedModel = this.findById(id);
        if (savedModel != null)
            return savedModel;
        return repository.save(model);
    }

    public List<M> save(List<M> models) {
        List<M> savedModels = new ArrayList<>();
        models.forEach((M p) -> savedModels.add(this.save(p)));
        return savedModels;
    }
    public M update(M model) {
        return repository.save(model);
    }

    public Iterable<M> findAll() {
        return repository.findAll();
    }

    public M findById(String id) {
        if (isNull(id))
            return null;
        else
            return repository.findById(id).orElse(null);
    }

    public void delete(M model) {
        repository.delete(model);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}