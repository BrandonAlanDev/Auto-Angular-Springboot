package com.example.demo.controllers;

import java.util.Map;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.example.demo.models.entity.cliente;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("listar")
public class FacturaPdfView extends AbstractPdfView{
	
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<cliente> lista = (List<cliente>) model.get("lista");
		PdfPTable table = new PdfPTable(1);
		table.setSpacingAfter(20);
		for (cliente c : lista) {
			table.addCell(c.getNombre());
			table.addCell(c.getApellido());
			table.addCell(c.getEmail());
			table.addCell(String.valueOf(c.getCreateAt()));
		}
		document.add(table);
		
	}

}
