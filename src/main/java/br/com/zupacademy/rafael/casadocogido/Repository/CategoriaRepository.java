package br.com.zupacademy.rafael.casadocogido.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zupacademy.rafael.casadocogido.Entity.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	boolean existsByNome(String nome);
}
