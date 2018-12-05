package com.paypal.butterfly.utilities.operations.pom;

import com.paypal.butterfly.extensions.api.TOExecutionResult;
import org.apache.maven.model.Model;

public class PomGetPackaging  extends AbstractPomOperation<PomChangePackaging> {

    private String artifactId;

    public PomGetPackaging(String artifactId) {
        this.artifactId = artifactId;
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
