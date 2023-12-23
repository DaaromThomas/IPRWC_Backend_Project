package com.iprwc.iprwc_backend_project.service;

import com.iprwc.iprwc_backend_project.Dao.OrderRepository;
import com.iprwc.iprwc_backend_project.model.BackendOrder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService
{
    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public BackendOrder save(BackendOrder order) {
        return orderRepository.save(order);
    }

    @Override
    public List<BackendOrder> findAll() {
        return (List<BackendOrder>) orderRepository.findAll();
    }

    @Override
    public Optional<BackendOrder> findById(String id) {
        return orderRepository.findById(id);
    }

    @Override
    public Optional<BackendOrder> findByName(String name) {
        return orderRepository.findById(name);
    }

    @Override
    public void delete(BackendOrder order) {
        orderRepository.delete(order);
    }
}
