package br.com.fiap.checkpoint1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.checkpoint1.model.Pedido;
import br.com.fiap.checkpoint1.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired
    private PedidoService pedidoService;
	
	@GetMapping
    public List<Pedido> listarTodos() {
        return pedidoService.listarTodos();
    }
	
	@GetMapping("/{id}")
    public Optional<Pedido> buscarPorId(@PathVariable Long id) {
        return pedidoService.buscarPorId(id);
    }
	
	@PostMapping
    public Pedido criar(@RequestBody Pedido pedido) {
        return pedidoService.salvar(pedido);
    }
	
	@DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
		pedidoService.deletar(id);
    }
	
	@PutMapping("/{id}")
	public ResponseEntity<Pedido> update(@PathVariable Long id, @RequestBody Pedido pedidoAtualizado){
		pedidoAtualizado = pedidoService.update(id, pedidoAtualizado);
		return ResponseEntity.ok().body(pedidoAtualizado);
	}
	
}
