package com.paypal.butterfly.utilities.operations.pom;

import com.paypal.butterfly.extensions.api.TUExecutionResult;
import com.paypal.butterfly.extensions.api.TransformationContext;
import com.paypal.butterfly.extensions.api.TransformationUtility;
import org.apache.commons.io.IOUtils;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;

public class PomModel extends TransformationUtility {

    private String groupId, artifactId, version, type, repoURI;
    private static final String DESCRIPTION = "Retrieve the parent pom and load it in to Model Object";

    public PomModel(String groupId, String artifactId, String version, String type, String repoURI) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
        this.type = type;
        this.repoURI = repoURI.replaceAll("/$","");
    }


    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    protected TUExecutionResult execution(File transformedAppFolder, TransformationContext transformationContext) {
        Model model = fetchModelFromRemote();
        return TUExecutionResult.value(this, model);
    }

    public Model fetchModelFromRemote(){
        MavenXpp3Reader reader = new MavenXpp3Reader();
        Model model = null;

        try (InputStream inputStream = new URL(repoURI + "/" + groupId + "/" + artifactId + "/" + version + "/" + artifactId + "-" + version + "." + type).openStream()) {
            File parentPom = Files.createTempFile(groupId + ":" + artifactId + ":" + version, type).toFile();
            IOUtils.copy(inputStream, new FileOutputStream(parentPom));

            FileInputStream fileInputStream = new FileInputStream(parentPom);
            model = reader.read(fileInputStream);
            parentPom.deleteOnExit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return model;
    }
}

