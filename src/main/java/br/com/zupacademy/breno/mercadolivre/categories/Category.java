package br.com.zupacademy.breno.mercadolivre.categories;

import javax.persistence.*;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private Category superCategory;

    @Deprecated
    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public void setSuperCategory(Category superCategory) {
        this.superCategory = superCategory;
    }
}
