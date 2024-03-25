package com.student.information.management.data.student.dao.impl;

import com.student.information.management.app.model.student.Student;
import com.student.information.management.data.connection.ConnectionHelper;
import com.student.information.management.data.student.dao.StudentDao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static com.student.information.management.data.utils.QueryConstant.*;
/**
 * An implementation class of the Student Data Access Object.
 *
 * */
public class StudentDaoImpl implements StudentDao {
    Connection con = ConnectionHelper.getConnection();
    @Override
    public List<Student> getAllStudents() {
        try {
            PreparedStatement stmt = con.prepareStatement(GET_ALL_STUDENTS_STATEMENT);
            ResultSet rs = stmt.executeQuery();
            List<Student> students = new ArrayList<>();

            while(rs.next()) {
                students.add(setStudent(rs));
            }
            return students;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Student getStudentById(String id){
        List<Student> students = new ArrayList<>();
        try {
            PreparedStatement stmt = con.prepareStatement(GET_STUDENT_BY_STUDENT_ID_STATEMENT);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                return setStudent(rs);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    @Override
    public boolean addStudent(Student student) {
        try {
            PreparedStatement statement = con.prepareStatement(ADD_STUDENT_STATEMENT);
            statement.setString(1, student.getStudentId());
            statement.setString(2, student.getLastName());
            statement.setString(3, student.getFirstName());
            statement.setString(4, student.getMiddleName());
            statement.setString(5, student.getSex());
            statement.setTimestamp(6, student.getBirthday());
            statement.setString(7, student.getReligion());
            statement.setString(8, student.getEmail());
            statement.setString(9, student.getAddress());
            statement.setString(10, student.getContactNumber());
            int result = statement.executeUpdate();
            return result == 1? true: false;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<Student> addStudents(ResultSet rs) {
        return addStudents(rs);
    }
    public Student setStudent(ResultSet rs) {
        try{
            Student student = new Student();
            student.setStudentId(rs.getString("student_id"));
            student.setLastName(rs.getString("last_name"));
            student.setFirstName(rs.getString("first_name"));
            student.setMiddleName(rs.getString("middle_name"));
            student.setSex(rs.getString("sex"));
            student.setBirthday(Timestamp.valueOf(rs.getString("birthday")));
            student.setReligion(rs.getString("religion"));
            student.setEmail(rs.getString("email"));
            student.setAddress(rs.getString("address"));
            student.setContactNumber(rs.getString("contact_number"));
            return student;
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    @Override
    public boolean updateStudent(Student student) {
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_STATEMENT);
            statement.setString(1, student.getLastName());
            statement.setString(2, student.getFirstName());
            statement.setString(3, student.getMiddleName());
            statement.setString(4, student.getSex());
            statement.setTimestamp(5, student.getBirthday());
            statement.setString(6, student.getReligion());
            statement.setString(7, student.getEmail());
            statement.setString(8, student.getAddress());
            statement.setString(9, student.getContactNumber());
            statement.setString(10, student.getStudentId());
            int result = statement.executeUpdate();

            return result == 1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
