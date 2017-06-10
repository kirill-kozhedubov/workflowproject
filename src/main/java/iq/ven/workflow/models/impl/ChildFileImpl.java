package iq.ven.workflow.models.impl;

import iq.ven.workflow.models.ChildFile;

import java.math.BigInteger;

public class ChildFileImpl implements ChildFile {

    private BigInteger fileId;
    private byte[] file;
    private String fileName;
    private BigInteger childId;

    public ChildFileImpl() {
    }

    private ChildFileImpl(ChildFileBuilder builder) {
        this.fileId = builder.fileId;
        this.file = builder.file;
        this.fileName = builder.fileName;
        this.childId = builder.childId;
    }

    @Override
    public BigInteger getFileId() {
        return fileId;
    }

    @Override
    public byte[] getFile() {
        return file;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    @Override
    public BigInteger getChildId() {
        return childId;
    }

    public void setFileId(BigInteger fileId) {
        this.fileId = fileId;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setChildId(BigInteger childId) {
        this.childId = childId;
    }

    public static class ChildFileBuilder {
        private BigInteger fileId;
        private byte[] file;
        private String fileName;
        private BigInteger childId;

        public ChildFileBuilder(BigInteger fileId, byte[] file, String fileName, BigInteger childId) {
            this.fileId = fileId;
            this.fileName = fileName;
            this.childId = childId;
        }

        public ChildFileBuilder addFile(byte[] file) {
            this.file = file;
            return this;
        }

        public ChildFileImpl buildChildFile() {
            return new ChildFileImpl(this);
        }

    }

    @Override
    public String toString() {
        return "ChildFileImpl{" +
                "fileId=" + fileId +
                ", fileName='" + fileName + '\'' +
                ", childId=" + childId +
                '}';
    }
}
