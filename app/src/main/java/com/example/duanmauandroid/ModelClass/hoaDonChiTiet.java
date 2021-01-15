package com.example.duanmauandroid.ModelClass;

public class hoaDonChiTiet {

    private int maHDCT;
    private hoaDon hoaDon;
    private sach sach;
    private int soLuongMua;

    public hoaDonChiTiet() {
    }

    public hoaDonChiTiet(int maHDCT, hoaDon hoaDon, sach sach, int soLuongMua) {
        this.maHDCT = maHDCT;
        this.hoaDon = hoaDon;
        this.sach = sach;
        this.soLuongMua = soLuongMua;
    }

    public int getMaHDCT() {
        return maHDCT;
    }

    public void setMaHDCT(int maHDCT) {
        this.maHDCT = maHDCT;
    }

    public hoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(hoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public sach getSach() {
        return sach;
    }

    public void setSach(sach sach) {
        this.sach = sach;
    }

    public int getSoLuongMua() {
        return soLuongMua;
    }

    public void setSoLuongMua(int soLuongMua) {
        this.soLuongMua = soLuongMua;
    }

    @Override
    public String toString() {
        return "HoaDonChiTiet{" +
                "maHDCT=" + maHDCT +
                ", hoaDon=" + hoaDon.toString() +
                ", sach=" + sach.toString() +
                ", soLuongMua=" + soLuongMua +
                '}';
    }
}
