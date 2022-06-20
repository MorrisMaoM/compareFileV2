package com.mao.comparefilev2.Model;

public class ExcelLogModel {
    private String leftFileName;
    private String LeftFilePath;
    private String rightFileName;
    private String rightFilePath;
    private String compareStatus;
    private String statusDescription;

    @Override
    public String toString() {
        return "ExcelLogModel{" +
                "leftFileName='" + leftFileName + '\'' +
                ", LeftFilePath='" + LeftFilePath + '\'' +
                ", rightFileName='" + rightFileName + '\'' +
                ", rightFilePath='" + rightFilePath + '\'' +
                ", compareStatus='" + compareStatus + '\'' +
                ", statusDescription='" + statusDescription + '\'' +
                '}';
    }

    public String getLeftFileName() {
        return leftFileName;
    }

    public void setLeftFileName(String leftFileName) {
        this.leftFileName = leftFileName;
    }

    public String getLeftFilePath() {
        return LeftFilePath;
    }

    public void setLeftFilePath(String leftFilePath) {
        LeftFilePath = leftFilePath;
    }

    public String getRightFileName() {
        return rightFileName;
    }

    public void setRightFileName(String rightFileName) {
        this.rightFileName = rightFileName;
    }

    public String getRightFilePath() {
        return rightFilePath;
    }

    public void setRightFilePath(String rightFilePath) {
        this.rightFilePath = rightFilePath;
    }

    public String getCompareStatus() {
        return compareStatus;
    }

    public void setCompareStatus(String compareStatus) {
        this.compareStatus = compareStatus;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public ExcelLogModel() {
    }

    public ExcelLogModel(String leftFileName, String leftFilePath, String rightFileName, String rightFilePath, String compareStatus, String statusDescription) {
        this.leftFileName = leftFileName;
        LeftFilePath = leftFilePath;
        this.rightFileName = rightFileName;
        this.rightFilePath = rightFilePath;
        this.compareStatus = compareStatus;
        this.statusDescription = statusDescription;
    }



}
