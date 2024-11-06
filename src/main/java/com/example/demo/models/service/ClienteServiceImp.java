package com.example.demo.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.dao.IClienteDao;
import com.example.demo.models.entity.cliente;

@Service
public class ClienteServiceImp implements IClienteService {
	@Autowired
	IClienteDao iClienteDao;

	@Override
	public void save(cliente client) {
		iClienteDao.save(client);
	}

	@Override
	public cliente findById(long id) {
		return iClienteDao.findById(id).orElse(null);
	}

	@Override
	public List<cliente> findAll() {
		return (List<cliente>)iClienteDao.findAll();
	}
}
