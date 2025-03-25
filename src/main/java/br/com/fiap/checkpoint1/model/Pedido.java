package br.com.fiap.checkpoint1.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String clienteNome;
    
    @Column(nullable = false)
    private LocalDate dataPedido;

    private double valorTotal;
    	
    @PrePersist
    public void prePersist() {
        this.dataPedido = LocalDate.now();
    }
    
    public void setValorTotal(double valorTotal) {
        if (valorTotal < 0) {
            throw new IllegalArgumentException("O valor total não pode ser negativo");
        }
        this.valorTotal = valorTotal;
    }

    public void setClienteNome(String clienteNome) {
        if (clienteNome == null || clienteNome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do cliente é obrigatório");
        }
        this.clienteNome = clienteNome;
    }
}
