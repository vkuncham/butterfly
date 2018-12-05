package com.paypal.butterfly.utilities.operations.pom;

import com.paypal.butterfly.extensions.api.*;
import org.apache.commons.io.IOUtils;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

public class PomModel extends TransformationUtility {

    private String groupId, artifactId, version;
    private static final String DESCRIPTION = "Retrieve the parent pom and load it in to Model Object";


    public PomModel() {

    }

    public PomModel(String groupId, String artifactId, String version) {

        this.groupId=groupId;
        this.artifactId=artifactId;
        this.version=version;
    }



    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    protected TUExecutionResult execution(File transformedAppFolder, TransformationContext transformationContext) {


        MavenXpp3Reader reader = new MavenXpp3Reader();
        Model model = null;

        try (InputStream inputStream = new URL("https://repo1.maven.org/maven2/"+groupId+"/"+artifactId +"/" +version + "/" + artifactId+"-" +version +".pom").openStream();
             FileOutputStream fileOS = new FileOutputStream("/Users/vkuncham/Documents/root.pom")){

            int i = IOUtils.copy(inputStream, fileOS);

            File parentPom = new File("/Users/vkuncham/Documents/root.pom");
            FileInputStream fileInputStream = new FileInputStream(parentPom);
            model = reader.read(fileInputStream);

        }catch(Exception ex) {
            ex.printStackTrace();
        }


        return TUExecutionResult.value(this, model);
    }
}

