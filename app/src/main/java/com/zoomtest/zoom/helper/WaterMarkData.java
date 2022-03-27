package com.zoomtest.zoom.helper;

public class WaterMarkData {

    private final int width;

    private final int height;

    private final byte[] yuv;

    public WaterMarkData(int width, int height, byte[] yuv) {
        this.width = width;
        this.height = height;
        this.yuv = yuv;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public byte[] getYuv() {
        return yuv;
    }
}
