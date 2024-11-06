package com.example.demo.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.entity.cliente;

public interface IClienteDao extends CrudRepository<cliente,Long> {

}
