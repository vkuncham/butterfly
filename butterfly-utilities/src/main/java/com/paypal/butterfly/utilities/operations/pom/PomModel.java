package com.paypal.butterfly.utilities.operations.pom;

import com.paypal.butterfly.extensions.api.TOExecutionResult;
import org.apache.maven.model.Model;

public class PomModel extends AbstractPomOperation<PomChangePackaging> {

    private String groupId, artifactId, version;

    public PomModel(String groupId, String artifactId, String version) {

        this.groupId=groupId;
        this.artifactId=artifactId;
        this.version=version;
    }

    @Override
    protected TOExecutionResult pomExecution(String relativePomFile, Model model) {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }
}

