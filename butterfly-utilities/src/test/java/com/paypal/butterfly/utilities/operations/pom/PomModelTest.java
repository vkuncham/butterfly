package com.paypal.butterfly.utilities.operations.pom;

import com.paypal.butterfly.utilities.TransformationUtilityTestHelper;
import org.apache.maven.model.Model;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class PomModelTest extends TransformationUtilityTestHelper {

    @Test
    public void fetchModelFromRemoteTest() throws Exception {
        String groupId = "junit";
        String artifactId = "junit";
        String version = "4.12";

        PomModel pomModel = new PomModel(groupId, artifactId, version, "pom", "https://repo1.maven.org/maven2/");
        Model model = pomModel.fetchModelFromRemote();

        assertEquals(groupId, model.getGroupId());
        assertEquals(artifactId, model.getArtifactId());
        assertEquals(version, model.getVersion());
    }

}
