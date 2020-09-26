package com.truongtuananh.truongtuananh;

public class MonHoc {
    private int IdMH;
    private  String TenMH;
    private String TenSV;
    private  String MaSoSV;
    private String SoTC;
    private String LopHocSV;

    public MonHoc(int idMH, String tenMH, String tenSV, String maSoSV, String soTC, String lopHocSV) {
        IdMH = idMH;
        TenMH = tenMH;
        TenSV = tenSV;
        MaSoSV = maSoSV;
        SoTC = soTC;
        LopHocSV = lopHocSV;
    }

    public int getIdMH() {
        return IdMH;
    }

    public void setIdMH(int idMH) {
        IdMH = idMH;
    }

    public String getTenMH() {
        return TenMH;
    }

    public void setTenMH(String tenMH) {
        TenMH = tenMH;
    }

    public String getTenSV() {
        return TenSV;
    }

    public void setTenSV(String tenSV) {
        TenSV = tenSV;
    }

    public String getMaSoSV() {
        return MaSoSV;
    }

    public void setMaSoSV(String maSoSV) {
        MaSoSV = maSoSV;
    }

    public String getSoTC() {
        return SoTC;
    }

    public void setSoTC(String soTC) {
        SoTC = soTC;
    }

    public String getLopHocSV() {
        return LopHocSV;
    }

    public void setLopHocSV(String lopHocSV) {
        LopHocSV = lopHocSV;
    }
}
