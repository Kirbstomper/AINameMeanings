package kirbstomper.architecture.pipeline;

import kirbstomper.architecture.pipeline.filters.BabyNameInformationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Optional;

@RestController
public class BabyNameController {

    private final BabyNameInformationService informationService;

    public BabyNameController(BabyNameInformationService informationService) {
        this.informationService = informationService;
    }

    @GetMapping("/nameInformation")
    public BabyNameInformation getInformationForName(@RequestBody String name){
        System.out.println(name);
        return informationService.getNameInformation(name);
    }
}
