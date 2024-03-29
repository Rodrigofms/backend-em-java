package me.dio.sdw2024.domain.ports;

import java.util.List;
import java.util.Optional;

import me.dio.sdw2024.domain.model.Champions;

public interface ChampionsRepository {

  List<Champions> findAll();
  Optional<Champions> findById(Long id);
}
