package kirbstomper.architecture.namegenerator.names;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
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

    /**
     * Attempts to get information about a baby name from the database
     * if not in the database, then we ask our chat client for that information
     * @param name the babies name
     * @return Information about the baby name
     */
    public BabyNameInformation getNameInformation(String name) {
        var possible = repository.getBabyNameInformationByName(StringUtils.capitalize(name.toLowerCase()));
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
