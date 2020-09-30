package al.aurel.microservicesaga;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("al.aurel.microservicesaga");

        noClasses()
            .that()
            .resideInAnyPackage("al.aurel.microservicesaga.service..")
            .or()
            .resideInAnyPackage("al.aurel.microservicesaga.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..al.aurel.microservicesaga.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
