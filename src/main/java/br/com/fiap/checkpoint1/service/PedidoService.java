package br.com.fiap.checkpoint1.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.checkpoint1.model.Pedido;
import br.com.fiap.checkpoint1.repository.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> buscarPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    public Pedido salvar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }
    
    public Pedido update(Long id, Pedido pedido) {
    	Pedido entity = pedidoRepository.getReferenceById(id);
    	updateData(entity, pedido);
    	return pedidoRepository.save(entity);
    }

    public void deletar(Long id) {
        pedidoRepository.deleteById(id);
    }
    
    private void updateData(Pedido entity, Pedido pedido) {
		entity.setClienteNome(pedido.getClienteNome());
		entity.setValorTotal(pedido.getValorTotal());
		entity.setDataPedido(LocalDate.now());
	}
}
