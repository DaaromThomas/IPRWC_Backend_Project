package com.iprwc.iprwc_backend_project.service;

import com.iprwc.iprwc_backend_project.model.BackendOrder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OrderService
{
    BackendOrder save(BackendOrder order);

    List<BackendOrder> findAll();

    Optional<BackendOrder> findById(String id);

    Optional<BackendOrder> findByName(String name);

    void delete(BackendOrder order);
}
