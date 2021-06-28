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
public class SortStudentByName implements Comparator<Student> {
    @Override
    public int compare(Student student1, Student student2) {
        String s1=student1.getHoten();
        String s2=student2.getHoten();
        return s1.substring(s1.lastIndexOf(" ")+1).compareTo(s2.substring(s2.lastIndexOf(" ")+1));
        }
}