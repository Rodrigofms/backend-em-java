package me.dio.sdw2024.application;

import java.util.List;

import me.dio.sdw2024.domain.model.Champions;
import me.dio.sdw2024.domain.ports.ChampionsRepository;

public record ListChampionsUseCase(ChampionsRepository repository) {
  public List<Champions> findAll() {
    return repository.findAll();
  }

}
