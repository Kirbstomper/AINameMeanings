package kirbstomper.architecture.namegenerator.names;

import kirbstomper.architecture.namegenerator.names.BabyNameInformation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BabyNameRepository extends CrudRepository<BabyNameInformation, Integer> {

    Optional<BabyNameInformation> getBabyNameInformationByName(String name);
}


