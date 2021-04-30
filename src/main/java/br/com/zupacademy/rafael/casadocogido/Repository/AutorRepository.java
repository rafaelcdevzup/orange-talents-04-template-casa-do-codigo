package br.com.zupacademy.rafael.casadocogido.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zupacademy.rafael.casadocogido.Entity.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

}
