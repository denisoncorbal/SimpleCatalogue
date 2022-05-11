package br.com.dgc.simplecatalogue.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Work implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idWork;
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy="work")
    private Set<Copy> copies;

    public Work() {
    }

    public Long getIdWork() {
        return idWork;
    }

    public String getName() {
        return name;
    }

    public void setIdWork(Long idWork) {
        this.idWork = idWork;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Copy> getCopies() {
        return copies;
    }

    public void setCopies(Set<Copy> copies) {
        this.copies = copies;
    }
}
