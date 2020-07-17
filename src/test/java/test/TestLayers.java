package test;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import org.junit.Test;
import com.tngtech.archunit.core.importer.ClassFileImporter;

public class TestLayers {

 @Test
 public void testLayers() {
  layeredArchitecture()
    .layer("Web").definedBy("..web..")
    .layer("BusinessLogic").definedBy("..model..")
    .layer("Persistence").definedBy("..persistence..")
    .whereLayer("Web").mayNotBeAccessedByAnyLayer()
    .whereLayer("BusinessLogic").mayOnlyBeAccessedByLayers("Web", "Persistence")
    .whereLayer("Persistence").mayNotBeAccessedByAnyLayer()
    .check(new ClassFileImporter().importPackages("competition"));
 }

}
