package model;

import java.math.BigDecimal;

public class ContaMagica {
    private String nome;
    private BigDecimal saldo;
    private Categoria categoria = Categoria.SILVER;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public ContaMagica(String nome, BigDecimal saldo) {
        this.nome = nome;
        this.saldo = saldo;
    }

    public Categoria getStatus() {
        if (saldo.compareTo(new BigDecimal(50000)) < 0) {
            categoria = Categoria.SILVER;
        }
        else if (saldo.compareTo(new BigDecimal(50000)) == 0 || (saldo.compareTo(new BigDecimal(50000)) > 0 && saldo.compareTo(new BigDecimal(200000)) < 0)) {
            categoria = Categoria.GOLD;
        }
        else{
            categoria = Categoria.PLATINUM;
        }
        return categoria;
    }

    public void deposito(BigDecimal valor) {
        if (valor.compareTo(new BigDecimal(0)) == 0 || (valor.compareTo(new BigDecimal(0))) < 0){
            System.out.println("Não é possível depositar valores negativos ou nulos!");
        }
        else {
            if (categoria == Categoria.SILVER) {
                saldo = saldo.add(valor);
            }
            else if (categoria == Categoria.GOLD) {
                BigDecimal valor2 = valor;
                valor = valor.multiply(new BigDecimal(0.01));
                valor = valor.add(valor2);
                saldo = saldo.add(valor);
            }
            else if (categoria == Categoria.PLATINUM) {
                BigDecimal valor2 = valor;
                valor = valor.multiply(new BigDecimal(0.025));
                valor = valor.add(valor2);
                saldo = saldo.add(valor);
            }
        }
    }

    public void retirada(BigDecimal valor) {
        if (valor.compareTo(getSaldo()) > 0){
            System.out.println("Saldo insuficiente! Selecione um novo valor.");
        }
        else {
            saldo = saldo.subtract(valor);

            if (categoria == Categoria.PLATINUM){
                if (saldo.compareTo(new BigDecimal(100000)) < 0) {
                    categoria = Categoria.GOLD;
                    System.out.println("Você passou da categoria PLATINUM para GOLD.");
                }
            }
            else if (categoria == Categoria.GOLD){
                if (saldo.compareTo(new BigDecimal(25000)) < 0) {
                    categoria = Categoria.SILVER;
                    System.out.println("Você passou da categoria GOLD para SILVER.");
                }
            }
        }
    }
}
