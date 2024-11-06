package com.example.demo.models.service;

import java.util.List;

import com.example.demo.models.entity.cliente;

public interface IClienteService {
	public void save(cliente client);
	public cliente findById(long id);
	public List<cliente> findAll();
}
