package kirbstomper.architecture.namegenerator.names;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Table
public record BabyNameInformation(@Id Integer id , String name, String origin, String meaning, List<String> similarNames) {

}
