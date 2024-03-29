package me.dio.sdw2024.adapters.out;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;

import feign.RequestInterceptor;
import me.dio.sdw2024.domain.ports.GenerativeAiApi;

@FeignClient(name = "OpenAiApi", url = "${openai.base-url}", configuration = OpenAiApi.Config.class)
public interface OpenAiApi extends GenerativeAiApi {

  @PostMapping("/v1/chat/completions")
  OpenAiChatResponse chatCompletion(OpenAiChatRequest req);

  @Override
  default String generateContent(String objective, String context) {
    String model = "gpt-3.5-turbo";
    List<Message> messages = List.of(
        new Message("system", objective),
        new Message("user", context));
    OpenAiChatRequest req = new OpenAiChatRequest(model, messages);

    OpenAiChatResponse resp = chatCompletion(req);

    return resp.choices().getFirst().message().content;
  }

  record OpenAiChatRequest(String model, List<Message> messages) {
  }

  record Message(String role, String content) {
  }

  record OpenAiChatResponse(List<Choice> choices) {
  }

  record Choice(Message message) {
  }

  class Config {
    @Bean
    public RequestInterceptor apikeyRequestInterceptor(@Value("${openai.api-key}") String apiKey) {
      return requestTemplate -> requestTemplate.header(HttpHeaders.AUTHORIZATION, "Bearer %s".formatted(apiKey));
    }
  }
}
