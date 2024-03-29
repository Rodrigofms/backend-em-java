package me.dio.sdw2024.application;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import me.dio.sdw2024.domain.model.Champions;

@SpringBootTest
public class ListChampionsUseCaseIntegrationTest {

  @Autowired
  private ListChampionsUseCase listChampionsUseCase;

  @Test
  public void testListChampions() {
      List<Champions> champions = listChampionsUseCase.findAll();

      Assertions.assertEquals(12, champions.size());
  }
}

//Teste inicial para ver se funcionava antes do Swagger ser instalado(desconsiderar o erro ao testar codigo)