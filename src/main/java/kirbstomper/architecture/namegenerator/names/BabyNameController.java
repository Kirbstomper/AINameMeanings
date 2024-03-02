package kirbstomper.architecture.namegenerator.names;

import kirbstomper.architecture.namegenerator.names.BabyNameInformationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BabyNameController {

    private final BabyNameInformationService informationService;

    public BabyNameController(BabyNameInformationService informationService) {
        this.informationService = informationService;
    }


    @GetMapping("/name")
    public String name(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", informationService.getNameInformation(name));
        return "name";
    }
}
