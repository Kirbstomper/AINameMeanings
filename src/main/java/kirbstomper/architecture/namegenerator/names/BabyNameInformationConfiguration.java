package kirbstomper.architecture.namegenerator.names;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.util.Optional;
import java.util.function.Function;

@Configuration
public class BabyNameInformationConfiguration {

    @Bean
    @Description("Get baby name information from database")
    public Function<Request, Optional<BabyNameInformation>> getNameInformation(BabyNameRepository repository){
        return s-> repository.getBabyNameInformationByName(s.name);
    }

    private record Request(String name){};
}
