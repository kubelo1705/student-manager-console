/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanagement;

import java.util.Comparator;

/**
 *
 * @author DELL
 */
public class SortStudentByDiemTongKet implements Comparator<Student> {
    @Override
    public int compare(Student student1, Student student2) {
        if (student1.getDiemTongKet() < student2.getDiemTongKet()) {
            return 1;
        }
        return -1;
    }
}
