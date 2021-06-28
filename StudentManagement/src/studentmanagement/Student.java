/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanagement;

/**
 *
 * @author DELL
 */
public class Student implements Comparable<Student>{
    private String mssv;
    private String hoten;
    private float seminar;
    private float giuaKy;
    private float cuoiKy;

    public Student(String mssv, String hoten) {
        this.mssv = mssv;
        this.hoten = hoten;
        this.giuaKy=0;
        this.cuoiKy=0;
        this.seminar=0;
    }

    public Student() {
    }

    public Student(String mssv, String hoten, float diemSeminar, float diemGiuaKy, float diemCuoiKy) {
        this.mssv = mssv;
        this.hoten = hoten;
        this.seminar = diemSeminar;
        this.giuaKy = diemGiuaKy;
        this.cuoiKy = diemCuoiKy;
    }

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public float getDiemSeminar() {
        return seminar;
    }

    public void setDiemSeminar(float diemSeminar) {
        this.seminar = diemSeminar;
    }

    public float getDiemGiuaKy() {
        return giuaKy;
    }

    public void setDiemGiuaKy(float diemGiuaKy) {
        this.giuaKy = diemGiuaKy;
    }

    public float getDiemCuoiKy() {
        return cuoiKy;
    }

    public void setDiemCuoiKy(float diemCuoiKy) {
        this.cuoiKy = diemCuoiKy;
    }
    
    public float getDiemTongKet(){
        return (float) (seminar*0.2+giuaKy*0.3+cuoiKy*0.5);
    }

    @Override
    public String toString() {
        return "Student:" + "mssv=" + mssv + "\t hoten=" + hoten + "\t diemSeminar=" + seminar + "\t diemGiuaKy=" + giuaKy + "\t diemCuoiKy=" + cuoiKy + "\t diemTongKet=" + this.getDiemTongKet() ;
    }

    @Override
    public int compareTo(Student o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
