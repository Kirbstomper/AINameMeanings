package kirbstomper.architecture.pipeline.filters;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kirbstomper.architecture.pipeline.BabyNameInformation;
import kirbstomper.architecture.pipeline.BabyNameRepository;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class BabyNameInformationService {

    private final OpenAiChatClient openAiChatClient;
    private final BabyNameRepository repository;
    private final ObjectMapper objectMapper = new ObjectMapper();
    public BabyNameInformationService(OpenAiChatClient openAiChatClient, BabyNameRepository repository) {
        this.openAiChatClient = openAiChatClient;
        this.repository = repository;
    }

    public BabyNameInformation getNameInformation(String name) {
        var possible = repository.getBabyNameInformationByName(name);
        return possible.orElseGet(() -> {
            var prompt = new Prompt("Get me information about the baby name " + name + """
                     in the format:
                    {\s
                    "name": String,
                    "origin": String,
                    "meaning": String,
                    "similarNames": List<String>
                    }
                    """ /*,OpenAiChatOptions.builder().withFunction("getNameInformation").build()*/);


            var chatResp = openAiChatClient.call(prompt);
            System.out.println(chatResp.getResult().getOutput().getContent());
            try {
                var informationToReturn = objectMapper.readValue(chatResp.getResult().getOutput().getContent(), BabyNameInformation.class);
                repository.save(informationToReturn);
                return informationToReturn;
            } catch (JsonProcessingException e) {
                return new BabyNameInformation(0, name, "Not found, please check back later!", "Please check back later!", Collections.emptyList());
            }
        });

    }
}
