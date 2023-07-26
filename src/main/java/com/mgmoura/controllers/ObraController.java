package com.mgmoura.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mgmoura.dtos.ObraPostRequestDto;
import com.mgmoura.dtos.ObraResponseDto;
import com.mgmoura.entities.Autor;
import com.mgmoura.entities.Obra;
import com.mgmoura.repositories.AutorRepository;
import com.mgmoura.repositories.ObraRepository;

@RestController
@RequestMapping("/obra")
public class ObraController {
	
	@Autowired
	private ObraRepository obraRepository;
	
	@Autowired
	private AutorRepository autorRepository;
	
	@PostMapping
	public ResponseEntity<ObraResponseDto> post(@RequestBody ObraPostRequestDto dto) {
		
		ObraResponseDto response = new ObraResponseDto();
		
		try {
			Optional<Autor> autor = autorRepository.findById(dto.getIdAutor());
			
			if(autor.isEmpty()) {
				
				response.setStatus(HttpStatus.BAD_REQUEST); // 400
				response.setMensagem("Autor não encontrado.");
				
			}else {
				Obra obra = new Obra();
				
				obra.setAutor(autor.get());
				obra.setDescricao(dto.getDescricao());
				obra.setNome(dto.getNome());
				// obra.setDataPublicacao(new SimpleDateFormat("yyyy-MM-dd").parse(dto.getDataPublicacao()));
				// obra.setDataExposicao(new SimpleDateFormat("yyyy-MM-dd").parse(dto.getDataExposicao()));
				
				obra.setDataPublicacao(new SimpleDateFormat("yyyy-MM-dd").parse(dto.getDataPublicacao()));
				obra.setDataExposicao(new SimpleDateFormat("yyyy-MM-dd").parse(dto.getDataExposicao()));
								
				obraRepository.save(obra);
				
				response.setStatus(HttpStatus.CREATED); // 201
				response.setMensagem("Obra cadastrada com sucesso.");
				
				
			}
			
		}catch (Exception e) {
				
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setMensagem(e.getMessage());
		}
		
		return ResponseEntity.status(response.getStatus().value()).body(response);

	}
	
	@GetMapping("{dataInicio}/{dataFim}")
	public ResponseEntity<List<Obra>> getAll(
			@PathVariable("dataInicio") String dataInicio, 
			@PathVariable("dataFim") String dataFim) {
		
		try {
			Date dataMin = new SimpleDateFormat("yyyy-DD-mm").parse(dataInicio);
			Date dataMax = new SimpleDateFormat("yyyy-MM-dd").parse(dataFim);
			
			List<Obra> obras = obraRepository.findByDatas(dataMin, dataMax);
			
			return ResponseEntity.status(200).body(obras);
			
		}catch (Exception e) {
			return ResponseEntity.status(500).body(null);
		}
		
	}
	
	@DeleteMapping("{idObra}")
	public ResponseEntity<ObraResponseDto> delete(@PathVariable("idObra") Integer idObra) {
		
		ObraResponseDto response = new ObraResponseDto();
		
		try {
			Optional<Obra> obra = obraRepository.findById(idObra);
			
			if(obra.isEmpty()) {
				
				response.setStatus(HttpStatus.BAD_REQUEST); // 400
				response.setMensagem("Obra não localizada.");
				
				
			}else {
				obraRepository.delete(obra.get());
				
				response.setStatus(HttpStatus.OK); // 200
				response.setMensagem("Obra excluída com sucesso.");
				response.setObra(obra.get());
				
			}
			
		}catch (Exception e) {
			
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR); // 500
			response.setMensagem(e.getMessage());
		}
		
		return ResponseEntity.status(response.getStatus().value()).body(response);
	}

}
