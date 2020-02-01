package bsi.trabalho.p2.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Campo Nome não pode estar vazio.")
    @Column(nullable = false)
    private String nome;

    @NotNull(message = "Campo Preço é obrigatório.")
    @Positive(message = "Preço deve ser um valor positivo e diferente de 0.")
    @Column(nullable = false)
    private double preco;

    private String unidadeMedida;

    @NotNull(message = "Data de validade é obrigatória.")
    @Future(message = "Data de Validade deve ter um valor futuro.")
    @Column(nullable = false)
    @Temporal(value = TemporalType.DATE)
    private Calendar dtValidade;

    @Transient
    private String dataString;

    private String categoria;

    private String foto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public Calendar getDtValidade() {
        return dtValidade;
    }

    public void setDtValidade(Calendar dtValidade) {
        this.dtValidade = dtValidade;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDataString() {
        if (this.dtValidade != null) {
            DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            this.dataString = sdf.format(dtValidade.getTime());
            return dataString;
        } else {
            return "Data inválida ou nula";
        }
    }
    

    public void setDataString(String dataString) {
        this.dataString = dataString;
    }

}
