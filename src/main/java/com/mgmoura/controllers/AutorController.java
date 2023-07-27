package com.mgmoura.controllers;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mgmoura.dtos.AutorPostRequestDto;
import com.mgmoura.dtos.AutorPutRequestDto;
import com.mgmoura.dtos.AutorResponseDto;
import com.mgmoura.entities.Autor;
import com.mgmoura.repositories.AutorRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/autor")
public class AutorController {
	
	@Autowired
	private AutorRepository autorRepository;
	
	@PostMapping
	public ResponseEntity<AutorResponseDto> post(@RequestBody AutorPostRequestDto dto) {
		
		AutorResponseDto response = new AutorResponseDto();
		
		try {
			Autor autor = new Autor();
			autor.setNome(dto.getNome());
			autor.setDataNascimento(new SimpleDateFormat("yyyy-MM-dd").parse(dto.getDataNascimento()));
			autor.setSexo(dto.getSexo());
			autor.setEmail(dto.getEmail());
			autor.setPaisOrigem(dto.getPaisOrigem());
			autor.setCpf(dto.getCpf());
			
			autorRepository.save(autor);
						
			response.setStatus(HttpStatus.CREATED); // 201
			response.setMensagem("Autor cadastrado com sucesso.");
			response.setAutor(autor);
			
			
		}catch (Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setMensagem(e.getMessage());
		}
		
		return ResponseEntity.status(response.getStatus().value()).body(response);
	}

	@PutMapping
	public ResponseEntity<AutorResponseDto> put(@RequestBody AutorPutRequestDto dto) {
		
		AutorResponseDto response = new AutorResponseDto();
		
		try {
			Optional<Autor> autor = autorRepository.findById(dto.getIdAutor());
			
			if(autor.isEmpty()) {
				
				response.setStatus(HttpStatus.BAD_REQUEST); // 400
				response.setMensagem("Autor não encontrado.");
				
			}else {
				
				Autor item = autor.get();
				
				item.setNome(dto.getNome());
				item.setSexo(dto.getSexo());
				item.setDataNascimento(new SimpleDateFormat("yyyy-MM-dd").parse(dto.getDataNascimento()));
//				item.setDataNascimento(dto.getDataNascimento());
				item.setEmail(dto.getEmail());
				item.setPaisOrigem(dto.getPaisOrigem());
				item.setCpf(dto.getCpf());
				
				autorRepository.save(item);
				
				response.setStatus(HttpStatus.OK); // 200
				response.setMensagem("Autor atualizado com sucesso.");
				response.setAutor(item);
			}
			
		}catch (Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR); // 500
			response.setMensagem(e.getMessage());
		}
		
		return ResponseEntity.status(response.getStatus().value()).body(response);
 
	}
	

	@DeleteMapping("{idAutor}")
	public ResponseEntity<AutorResponseDto> delete(@PathVariable("idAutor") Integer idAutor) {
		
		AutorResponseDto response = new AutorResponseDto();
		
		try {
			Optional<Autor> autor = autorRepository.findById(idAutor);
			
			if(autor.isEmpty()) {
				response.setStatus(HttpStatus.BAD_REQUEST); // 400
				response.setMensagem("Autor não localizado");
	
			}else {
				if(autorRepository.countByObra(idAutor) > 0) {
					response.setStatus(HttpStatus.BAD_REQUEST);
					response.setMensagem("Não é possível excluir, o Autor possui obra(s) vinculadas");
				}
			
			else {
				
				autorRepository.delete(autor.get());
				
				response.setStatus(HttpStatus.OK); // 200
				response.setMensagem("Autor excluído com sucesso.");
				response.setAutor(autor.get());
				}
			}
		}catch (Exception e) {
			
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR); // 500
			response.setMensagem(e.getMessage());
		}
		
		return ResponseEntity.status(response.getStatus().value()).body(response);
	}

	@GetMapping
	public ResponseEntity<List<Autor>> getAll(){
		
		try {
			
			List<Autor> autores = autorRepository.findAll();
			
			return ResponseEntity.status(200).body(autores);
			
		}catch (Exception e) {
			return ResponseEntity.status(500).body(null);
		}
	}
	
		
	
	@GetMapping("{idAutor}")
	public ResponseEntity<Autor> getById(@PathVariable("idAutor") Integer idAutor) {
		
		try {
			Optional<Autor> autor = autorRepository.findById(idAutor);
			
			if(autor.isPresent()) {
				return ResponseEntity.status(200).body(autor.get());
				
			}else {
				return ResponseEntity.status(204).body(null);
			}
			
		}catch (Exception e) {
			return ResponseEntity.status(500).body(null);
		}
	}
}
