package com.codingchallengePriya.myOwnDiffTool.service;

public class LcsResult {
    private final int length;
    private final String lcsString;

    public LcsResult(int length, String lcsString) {
        this.length = length;
        this.lcsString = lcsString;
    }

    public int getLength() {
        return length;
    }

    public String getLcsString() {
        return lcsString;
    }
}

