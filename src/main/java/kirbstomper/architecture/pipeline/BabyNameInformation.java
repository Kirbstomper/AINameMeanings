package kirbstomper.architecture.pipeline;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Collections;
import java.util.List;

@Table
public record BabyNameInformation(@Id Integer id , String name, String origin, String meaning, List<String> similarNames) {

}
