package me.dio.sdw2024.adapters.in;

import io.swagger.v3.oas.annotations.tags.Tag;
import me.dio.sdw2024.application.AskChampionsUseCase;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Campeões", description = "Endpoints do dominio de Campeões do Lol")
@RestController
@RequestMapping("/champions")
public record AskChampionsRestController(AskChampionsUseCase useCase) {

  @PostMapping("/{championId}/ask")
  public AskChampionResponse askChampion(@PathVariable("championId") Long championId, @RequestBody AskChampionRequest request) {
    String answer = useCase.askChampion(championId, request.question());
    return new AskChampionResponse(answer);
  }

  public record AskChampionRequest(String question) {
  }

  public record AskChampionResponse(String answer) {
  }
}
