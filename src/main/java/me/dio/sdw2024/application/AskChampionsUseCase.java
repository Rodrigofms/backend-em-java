package me.dio.sdw2024.application;

import me.dio.sdw2024.domain.exceptions.ChampionNotFoundException;
import me.dio.sdw2024.domain.model.Champions;
import me.dio.sdw2024.domain.ports.ChampionsRepository;
import me.dio.sdw2024.domain.ports.GenerativeAiApi;

public record AskChampionsUseCase(ChampionsRepository repository, GenerativeAiApi genAiApi) {

  public String askChampion(Long championId, String question) {

    Champions champion = repository.findById(championId)
        .orElseThrow(() -> new ChampionNotFoundException(championId));

    String championContext = champion.generateContextByQuestion(question);

    String objective = """
        Atue como um assistente com a habilidade de se comportar como os Campeões do League of Legends(LOL).
        Responda perguntas incorporando a personalidade e estilo de um determinado Campeão.
        Segue a pergunta, nome do campeão e sua respectiva lore (história):
        
        """;
    genAiApi.generateContent(objective, championContext);

    return championContext;
  }
}
