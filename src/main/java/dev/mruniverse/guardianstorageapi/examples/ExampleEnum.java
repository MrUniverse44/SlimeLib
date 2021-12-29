package dev.mruniverse.guardianstorageapi.examples;

import dev.mruniverse.guardianstorageapi.interfaces.GuardianFiles;

public enum ExampleEnum implements GuardianFiles {
    FILE1 {
        @Override
        public String getFileName() {
            return "Tests.yml";
        }
        @Override
        public String getFolderName() {
            return "";
        }
        @Override
        public String getResourceFileName() {
            return "Tests.yml";
        }
        @Override
        public boolean isInDifferentFolder() {
            return false;
        }
    },
    FILE2 {
        @Override
        public String getFileName() {
            return "Tests.yml";
        }
        @Override
        public String getFolderName() {
            return "Tests";
        }
        @Override
        public String getResourceFileName() {
            return "Tests.yml";
        }
        @Override
        public boolean isInDifferentFolder() {
            return true;
        }
    }
}
