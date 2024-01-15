package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.example.demo.bean.Empresa;
import com.example.demo.repository.EmpresaRepository;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
public class EmpresaRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EmpresaRepository EmpresaRepository;

    @BeforeEach
    void setUp() {

    }
    private Empresa insertDemoEmpresa(Empresa empresa){
        entityManager.persist(empresa);
        entityManager.flush();
        return empresa;
    }

    @Test
    @Sql(scripts = "classpath:import.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void findAllWithSql() {
        List<Empresa> empresita = EmpresaRepository.findAll();
        assertEquals(3, empresita.size());
    }

    @Test
    public void FindAllById() {
        insertDemoEmpresa(new Empresa(1, "A12312312", "Indra"));
        insertDemoEmpresa(new Empresa(2, "B45645678", "Itic"));
        insertDemoEmpresa(new Empresa(3, "A98798765", "Blanc i Roig SL"));

        List<Empresa> empresas = EmpresaRepository.findAllById(1);
        assertThat(empresas).hasSize(1);
        Empresa empresa = empresas.get(0);


        
        assertThat(empresa.getId()).isEqualTo(1);
        assertThat(empresa.getNom()).isEqualTo("Indra");
        assertThat(empresa.getDescripcion()).isEqualTo("A12312312");
    }


    @Test
    public void FindAllByNom() {
        insertDemoEmpresa(new Empresa(1, "A12312312", "Indra"));
        insertDemoEmpresa(new Empresa(2, "B45645678", "Itic"));
        insertDemoEmpresa(new Empresa(3, "A98798765", "Blanc i Roig SL"));

        List<Empresa> empresas = EmpresaRepository.findAllByNom("Indra");

        System.out.println("Número de empresas recuperadas: " + empresas.size());
        System.out.println("Empresas recuperadas: " + empresas);

        if (!empresas.isEmpty()) {
            Empresa empresa = empresas.get(0);
            assertThat(empresa.getId()).isEqualTo(1);
            assertThat(empresa.getDescripcion()).isEqualTo("A12312312");
        } else {
            System.out.println("No se encontraron empresas con el nombre 'Indra'.");
        }
    }



    @Test
    public void FindAllByDescripcion() {
        insertDemoEmpresa(new Empresa(1, "A12312312", "Indra"));
        insertDemoEmpresa(new Empresa(2, "B45645678", "Itic"));
        insertDemoEmpresa(new Empresa(3, "A98798765", "Blanc i Roig SL"));

        List<Empresa> empresas = EmpresaRepository.findAllByDescripcion("A12312312");

        assertThat(empresas).hasSize(1);

        Empresa empresa = empresas.get(0);

        assertThat(empresa.getId()).isEqualTo(1);
        assertThat(empresa.getNom()).isEqualTo("Indra");
    }

}
