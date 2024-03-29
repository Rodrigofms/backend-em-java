package me.dio.sdw2024;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import me.dio.sdw2024.application.AskChampionsUseCase;
import me.dio.sdw2024.application.ListChampionsUseCase;
import me.dio.sdw2024.domain.ports.ChampionsRepository;
import me.dio.sdw2024.domain.ports.GenerativeAiApi;


@EnableFeignClients
@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public ListChampionsUseCase provideListChampionsUseCase(ChampionsRepository repository) {
    return new ListChampionsUseCase(repository);
  }

  @Bean
  public AskChampionsUseCase provideAskChampionUseCase(ChampionsRepository repository, GenerativeAiApi genAiApi) {
    return new AskChampionsUseCase(repository, genAiApi);
  }
}
