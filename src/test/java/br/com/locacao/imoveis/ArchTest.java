package br.com.locacao.imoveis;

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
            .importPackages("br.com.locacao.imoveis");

        noClasses()
            .that()
            .resideInAnyPackage("br.com.locacao.imoveis.service..")
            .or()
            .resideInAnyPackage("br.com.locacao.imoveis.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..br.com.locacao.imoveis.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
