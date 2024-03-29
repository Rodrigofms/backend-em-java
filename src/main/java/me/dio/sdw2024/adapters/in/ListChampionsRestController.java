package me.dio.sdw2024.adapters.in;

import io.swagger.v3.oas.annotations.tags.Tag;
import me.dio.sdw2024.application.ListChampionsUseCase;
import me.dio.sdw2024.domain.model.Champions;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Campeões", description = "Endpoints do dominio de Campeões do Lol")
@RestController
@RequestMapping("/champions")
public record ListChampionsRestController(ListChampionsUseCase useCase) {

  @GetMapping
  public List<Champions> findAllChampions() {
    return useCase.findAll();
  }
}
