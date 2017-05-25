package iq.ven.workflow.models.impl;

import iq.ven.workflow.models.UserFile;

import java.math.BigInteger;
import java.util.List;

public class UserFileImpl implements UserFile {

    private BigInteger fileId;
    private byte[] file;
    private String fileName;
    private BigInteger fileAuthorId;
    private List<BigInteger> listOfUsersFileAccessibleTo;

    public UserFileImpl(UserFileBuilder builder) {
        this.fileId = builder.fileId;
        this.file = builder.file;
        this.fileName = builder.fileName;
        this.fileAuthorId = builder.fileAuthorId;
        this.listOfUsersFileAccessibleTo = builder.listOfUsersFileAccessibleTo;
    }

    public BigInteger getFileId() {
        return fileId;
    }

    public byte[] getFile() {
        return file;
    }

    public String getFileName() {
        return fileName;
    }

    public BigInteger getFileAuthor() {
        return fileAuthorId;
    }

    public List<BigInteger> getListOfUsersFileAccessibleTo() {
        return listOfUsersFileAccessibleTo;
    }


    public static class UserFileBuilder {
        private BigInteger fileId;
        private byte[] file;
        private String fileName;
        private BigInteger fileAuthorId;
        private List<BigInteger> listOfUsersFileAccessibleTo;

        public UserFileBuilder(BigInteger fileId, String fileName, BigInteger fileAuthorId) {
            this.fileId = fileId;
            this.fileName = fileName;
            this.fileAuthorId = fileAuthorId;
        }

        public UserFileBuilder addFile(byte[] file) {
            this.file = file;
            return this;
        }

        public UserFileBuilder addAccessList(List<BigInteger> listOfUsersFileAccessibleTo) {
            this.listOfUsersFileAccessibleTo = listOfUsersFileAccessibleTo;
            return this;
        }

        public UserFileImpl buildUserFile() {
            return new UserFileImpl(this);
        }
    }

}
