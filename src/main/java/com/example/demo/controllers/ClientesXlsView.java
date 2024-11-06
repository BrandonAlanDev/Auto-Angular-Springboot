package com.example.demo.controllers;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.example.demo.models.entity.cliente;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("listar.xlsx")
public class ClientesXlsView extends AbstractXlsView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setHeader("Content-Disposition","attachment;filename=\"cliente.xlsx\"");
		List<cliente>lista = (List<cliente>)model.get("clientes");
		Sheet sheet = workbook.createSheet("Clientes");
		Row row=sheet.createRow(0);
		Cell cell = row.createCell(0);
		cell.setCellValue("Listado de Clientes");
		Row row2=sheet.createRow(1);
		cell = row2.createCell(0);
		cell.setCellValue("Nombre");
		cell = row2.createCell(1);
		cell.setCellValue("Apellido");
		cell = row2.createCell(2);
		cell.setCellValue("email");
		cell = row2.createCell(3);
		cell.setCellValue("Fecha de creacion");
		
		Row row3;
		int i=2;
		for (cliente c:lista) {
			row3=sheet.createRow(i);
			i++;
			cell=row3.createCell(0);
			cell.setCellValue(c.getNombre());
			cell = row3.createCell(1);
			cell.setCellValue(c.getApellido());
			cell = row3.createCell(2);
			cell.setCellValue(c.getEmail());
			cell = row3.createCell(3);
			cell.setCellValue(c.getCreateAt());
		}
	}

}
