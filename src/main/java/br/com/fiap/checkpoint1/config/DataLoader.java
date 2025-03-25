package br.com.fiap.checkpoint1.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.checkpoint1.model.Pedido;
import br.com.fiap.checkpoint1.repository.PedidoRepository;

@Configuration
public class DataLoader {
	
	@Bean
    CommandLineRunner carregarBanco(PedidoRepository pedidoRepository) {
        return args -> {
        	pedidoRepository.deleteAll();

            List<Pedido> pedidos = List.of(
                new Pedido(null, "Natan Santos", null, 500.0),
                new Pedido(null, "Kayky Paschoal", null, 220.5),
                new Pedido(null, "João Pedro", null, 150.0)
    		);

            pedidoRepository.saveAll(pedidos);

            // Confirmação no console
            long total = pedidoRepository.count();
            System.out.println("Total de produtos no banco: " + total);
        };
    }
}
