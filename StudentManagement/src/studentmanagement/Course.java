/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Nhom 4A
 */
public class Course {
    
    public static Scanner scanner = new Scanner(System.in);
    private String tenKhoaHoc;
    private int soLuongSinhVien;
    private List<Student> list;
    
    public Course() {
        this.list = new ArrayList<>();
    }

    public List<Student> getList() {
        return list;
    }

    public void setList(List<Student> list) {
        this.list = list;
    }
    
    public void addStudent(Student student){
        this.list.add(student);
    }

    public int getSoLuongSinhVien() {
        return soLuongSinhVien;
    }

    public void setSoLuongSinhVien(int soLuongSinhVien) {
        this.soLuongSinhVien = soLuongSinhVien;
    }

    public String getTenKhoaHoc() {
        return tenKhoaHoc;
    }

    public void setTenKhoaHoc(String tenKhoaHoc) {
        this.tenKhoaHoc = tenKhoaHoc;
    }

    public List<Student> searchStudentByName(String name){
        return this.list.stream().filter(o->o.getHoten().toLowerCase().contains(name.trim().toLowerCase())).collect(Collectors.toList());
    }
    
    public List<Student> searchStudentByMssv(String mssv){
        return this.list.stream().filter(o->o.getMssv().contains(mssv.trim())).collect(Collectors.toList());
    }
    
    public String inputHoten() {
        System.out.println("Nhap ho va ten: ");
        String name = scanner.nextLine().trim();
        return name;
    }
    
    public float inputDiem() {
        System.out.print("Nhap diem: ");
        while (true) {
            try {
                float seminar = Float.parseFloat((scanner.nextLine()));
                if (seminar < 0.0 || seminar > 10.0) {
                    throw new NumberFormatException();
                }
                return seminar;
            } catch (NumberFormatException ex) {
                System.out.print("Da xay ra loi! Vui long nhap lai:");
            }
        }
    }
    
    public String inputMssv() {
        scanner.nextLine();
        System.out.println("Nhap mssv: ");
        String mssv= scanner.nextLine().trim();
        return mssv;
    }
    
    public void xoaSinhVienTheoMssv() {
        Student student = null;
        scanner.nextLine();
        System.out.println("Nhap mssv muon xoa:");
        String mssv=scanner.nextLine().trim();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (list.get(i).getMssv().equals(mssv)) {
                student = list.get(i);
                break;
            }
        }
        if (student != null) {
            list.remove(student);
            System.out.println("Xoa thanh cong");
        } else {
            System.out.println("Khong ton tai sinh vien co mssv="+ mssv);
        }
    }
    
    public void themStudent(){
        System.out.println("LUU Y:SINH VIEN VUA TAO NEN TAT CA CAC HE SO DIEM BANG 0");
        System.out.println("NHAP THONG TIN SINH VIEN");
        String mssv=inputMssv().trim();
        String hoten=inputHoten().trim();
        boolean check=true;
        while(check){
            for(Student iStudent:list){
                if(iStudent.getMssv() == null ? mssv == null : iStudent.getMssv().equals(mssv)){
                    System.out.println("Trung mssv. Vui long nhap lai");
                    mssv=scanner.nextLine().trim();
                    check=true;
                    break;
                }
                check=false;
            }
        }
        Student student=new Student(mssv,hoten);
        this.list.add(student);
    }
    
    public void show(){
        System.out.println("-----------"+"KHOA HOC "+this.tenKhoaHoc.toUpperCase()+"------------");
        System.out.format("%5s | ", "STT");
        System.out.format("%10s | ", "MSSV");
        System.out.format("%20s | ", "HO VA TEN");
        System.out.format("%15s | ", "DIEM SEMINAR");
        System.out.format("%15s | ", "DIEM GK");
        System.out.format("%15s | ", "DIEM CK");
        System.out.format("%15s ", "DIEM TONG KET");
        System.out.println("");
        list.stream().map((student) -> {
            System.out.format("%5d | ",list.indexOf(student)+1);
            return student;
        }).map((student) -> {
            System.out.format("%10s | ", student.getMssv());
            return student;
        }).map((student) -> {
            System.out.format("%20s | ", student.getHoten());
            return student;
        }).map((student) -> {
            System.out.format("%15.1f | ", student.getDiemSeminar());
            return student;
        }).map((student) -> {
            System.out.format("%15.1f | ", student.getDiemGiuaKy());
            return student;
        }).map((student) -> {
            System.out.format("%15.1f | ", student.getDiemCuoiKy());
            return student;
        }).forEachOrdered((student) -> {
            System.out.format("%15.1f\n", student.getDiemTongKet());
        });
    }
    
    public static List<Student> readStudentFromFile(String fileName){
        List<Student> students=new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line=br.readLine();
            while(line!=null){
                String[] attributes = line.trim().split(",");
                Student sv=createStudent(attributes);
                students.add(sv);
                line=br.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return students;
    }
    
    private static Student createStudent(String[] data){
        String mssv=data[0];
        String hoten=data[1];
        float seminar=Float.parseFloat(data[2]);
        float gk=Float.parseFloat(data[3]);
        float ck=Float.parseFloat(data[4]);
        return new Student(mssv,hoten,seminar,gk,ck);
    }
    
    public int searchPosition(List<Student> student){
        if(student!=null)
            return list.indexOf(student.get(0));
        else
            return -1;
    }
    
    public void nhapDiemSeminar(){
        scanner.nextLine();
        System.out.println("Nhap MSSV:");
        String mssv=scanner.nextLine().trim();
        List<Student> student=new ArrayList<>();
        list.stream().filter((i) -> (i.getMssv() == null ? mssv == null : i.getMssv().equals(mssv))).forEachOrdered((i) -> {
            student.add(i);
        });
        if(student.size()==1){
            float diem=inputDiem();
            list.get(list.indexOf(student.get(0))).setDiemSeminar(diem);
        }
        else if(student.isEmpty())
            System.out.println("Khong tim thay sinh vien co mssv yeu cau");
        else
            System.out.println("mssv vua nhap chua chinh xac");
        
    }
    
    /**
     *
     */
    public void nhapDiemGiuaKy(){
        scanner.nextLine();
        System.out.println("Nhap MSSV:");
        String mssv=scanner.nextLine().trim();
        List<Student> student=new ArrayList<>();
        list.stream().filter((i) -> (i.getMssv() == null ? mssv == null : i.getMssv().equals(mssv))).forEachOrdered((i) -> {
            student.add(i);
        });
        if(student.size()==1){
            float diem=inputDiem();
            list.get(list.indexOf(student.get(0))).setDiemGiuaKy(diem);
        }
        else if(student.isEmpty())
            System.out.println("Khong tim thay sinh vien co mssv yeu cau");
        else
            System.out.println("mssv vua nhap chua chinh xac");
    }
    
    /**
     *
     */
    public void nhapDiemCuoiKy(){
        scanner.nextLine();
        System.out.println("Nhap MSSV:");
        String mssv=scanner.nextLine().trim();
        List<Student> student=new ArrayList<>();
        list.stream().filter((i) -> (i.getMssv() == null ? mssv == null : i.getMssv().equals(mssv))).forEachOrdered((i) -> {
            student.add(i);
        });
        if(student.size()==1){
            float diem=inputDiem();
            list.get(list.indexOf(student.get(0))).setDiemCuoiKy(diem);
        }
        else if(student.isEmpty())
            System.out.println("Khong tim thay sinh vien co mssv yeu cau");
        else
            System.out.println("mssv vua nhap chua chinh xac");
    }
    
    public void writeToFile() throws IOException{
        File file = new File("src/DanhSachSv.txt");
        file.createNewFile();
        FileWriter myWriter = new FileWriter("src/DanhSachSv.txt",false);
        myWriter.write(String.format("%5s | ", "STT"));
        myWriter.write(String.format("%10s | ", "MSSV"));
        myWriter.write(String.format("%20s | ", "HO VA TEN"));
        myWriter.write(String.format("%15s | ", "DIEM SEMINAR"));
        myWriter.write(String.format("%15s | ", "DIEM GK"));
        myWriter.write(String.format("%15s | ", "DIEM CK"));
        myWriter.write(String.format("%15s ", "DIEM TONG KET"));
        myWriter.write("\n");
        list.stream().map((student) -> {
            try {
                myWriter.write(String.format("%5d | ",list.indexOf(student)+1));
            } catch (IOException ex) {
                Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
            }
            return student;
        }).map((student) -> {
            try {
                myWriter.write(String.format("%10s | ", student.getMssv()));
            } catch (IOException ex) {
                Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
            }
            return student;
        }).map((student) -> {
            try {
                myWriter.write(String.format("%20s | ", student.getHoten()));
            } catch (IOException ex) {
                Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
            }
            return student;
        }).map((student) -> {
            try {
                myWriter.write(String.format("%15.1f | ", student.getDiemSeminar()));
            } catch (IOException ex) {
                Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
            }
            return student;
        }).map((student) -> {
            try {
                myWriter.write(String.format("%15.1f | ", student.getDiemGiuaKy()));
            } catch (IOException ex) {
                Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
            }
            return student;
        }).map((student) -> {
            try {
                myWriter.write(String.format("%15.1f | ", student.getDiemCuoiKy()));
            } catch (IOException ex) {
                Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
            }
            return student;
        }).forEachOrdered((student) -> {
            try {
                myWriter.write(String.format("%15.1f\n", student.getDiemTongKet()));
            } catch (IOException ex) {
                Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        myWriter.close();
        System.out.println("Xuat file thanh cong.");
    }
    
    public static void main(String[] args) throws IOException {
        
        int luachon;
        boolean exit=false;
        Course course=new Course();
        System.out.println("NHAP THONG TIN KHOA HOC");
        System.out.print("Nhap ten:");
        String tenKhoahoc=scanner.nextLine();
        course.setTenKhoaHoc(tenKhoahoc);
        System.out.print("Nhap so luong sinh vien:");
        int soLuongsv=scanner.nextInt();
        course.setSoLuongSinhVien(soLuongsv);
        
        course.setList(Course.readStudentFromFile("src/course.txt"));
        
        while(true){
        System.out.println("-----------MENU------------");
        System.out.println("1. Them student.");
        System.out.println("2. Xoa student theo mssv.");
        System.out.println("3. Nhap diem seminar.");
        System.out.println("4. Nhap diem giua ki.");
        System.out.println("5. Nhap diem cuoi ki.");
        System.out.println("6. Sap xep theo ten.");
        System.out.println("7. Sap xep theo diem tong ket.");
        System.out.println("8. Xem danh sach student.");
        System.out.println("9. Tim kiem sinh vien theo mssv.");
        System.out.println("10. Tim kiem sinh vien theo ten.");
        System.out.println("11. Xuat file danh sach sinh vien.");
        System.out.println("0. Thoat.");
        System.out.println("---------------------------");
        System.out.print("Nhap lua chon cua ban: ");
        luachon=scanner.nextInt();
        switch(luachon){
            case 1:
                if(course.list.size()>=course.getSoLuongSinhVien()){
                    System.out.println("Khoa hoc da du sinh vien");
                }
                else
                    course.themStudent();
                break;
            case 2:
                course.xoaSinhVienTheoMssv();
                break;
            case 3:
                course.nhapDiemSeminar();
                break;
            case 4:
                course.nhapDiemGiuaKy();
                break;
            case 5:
                course.nhapDiemCuoiKy();
                break;
            case 6:
                Collections.sort(course.list,new SortStudentByName());
                course.show();
                break;
            case 7:
                Collections.sort(course.list,new SortStudentByDiemTongKet());
                course.show();
                break;
            case 8:
                course.show();
                break;
            case 9:
                scanner.nextLine();
                System.out.println("Nhap mssv sinh vien can tim kiem:");
                String mssv=scanner.nextLine();
                List<Student> list1=course.searchStudentByMssv(mssv);
                list1.forEach((i) -> {
                    System.out.println(i.toString());
                });
                break;
            case 10:
                scanner.nextLine();
                System.out.println("Nhap ten sinh vien can tim kiem:");
                String name=scanner.nextLine();
                List<Student> list2=course.searchStudentByName(name);
                list2.forEach((i) -> {
                    System.out.println(i.toString());
                });
                break;
            case 11:
                course.writeToFile();
                break;
            default:
                exit=true;
                break;
        }
        if(exit){
            break;
        }
        }
    }

    
}
