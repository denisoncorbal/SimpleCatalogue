package br.com.dgc.simplecatalogue.model.entity;

import javax.persistence.*;

@Entity
public class Copy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCopy;

    @ManyToOne
    @JoinColumn(name = "idWork", nullable = false)
    private Work work;
}
