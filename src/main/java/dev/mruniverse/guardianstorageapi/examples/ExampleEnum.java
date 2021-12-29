package dev.mruniverse.guardianstorageapi.examples;

import dev.mruniverse.guardianstorageapi.interfaces.GuardianFiles;

public enum ExampleEnum implements GuardianFiles {
    FILE1("Tests.yml"),
    FILE2("Tests2.yml","Tests","Tests.yml"),
    FILE3("Tests3.yml","Tests3.yml",true);

    private final boolean differentFolder;

    private final String file;

    private final String folder;

    private final String resource;

    ExampleEnum(String file) {
        this.file = file;
        this.resource = file;
        this.differentFolder = false;
        this.folder = "";
    }

    ExampleEnum(String file,String folder) {
        this.file = file;
        this.resource = file;
        this.differentFolder = true;
        this.folder = folder;
    }

    ExampleEnum(String file,String folder,String resource) {
        this.file = file;
        this.resource = resource;
        this.differentFolder = true;
        this.folder = folder;
    }

    ExampleEnum(String file,String folderOrResource,boolean isResource) {
        this.file = file;
        if(isResource) {
            this.resource = folderOrResource;
            this.folder = "";
            this.differentFolder = false;
        } else {
            this.resource = file;
            this.folder = folderOrResource;
            this.differentFolder = true;
        }
    }

    @Override
    public String getFileName() {
        return this.file;
    }
    @Override
    public String getFolderName() {
        return this.folder;
    }
    @Override
    public String getResourceFileName() {
        return this.resource;
    }
    @Override
    public boolean isInDifferentFolder() {
        return this.differentFolder;
    }
}
