package com.dbms.student;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//import com.dbms.studDept.StudentDeptMajor;

import com.dbms.studentCourseGrade.*;
import com.dbms.course.Course;
//import com.dbms.studentDepartmentMajor.*;

@Controller
public class StudentController {

    @Autowired // Spring and Hibernate will auto-generate this object
    private StudentRepository studentRepo;

    @Autowired
    private StudentCourseGradeRepository scgRepo;

    //@Autowired
    //private StudentDepartment sdmRepo;
    
    @GetMapping("/students") // localhost:8080/students/
    public String studentForm(Model model) {
	model.addAttribute("studentQ", new Student());
	model.addAttribute("studentList", studentRepo.findAll());
	return "students";
    } // studentForm

    @PostMapping("/students") // localhost:8080/students/
    public String studentSubmit(@ModelAttribute Student s, Model model) {
	studentRepo.save(s);
	return studentForm(model);
    } // studentSubmit

    @GetMapping("/students/delete") // localhost:8080/students/delete
    public String delete(@RequestParam(value = "sid", required = true) String sid, Model model) {
	studentRepo.delete(getStudentById(studentRepo.findAll(), new Long(sid)));
	return studentForm(model);
    } // delete

    public static Student getStudentById(Iterable<Student> studentList, Long sid) {
	Student rtrnStudent = null;
	for (Student s : studentList) {
	    if (s.getSid().equals(sid)) {
		rtrnStudent = s;
		break;
	    } // if
	} // for-each
	return rtrnStudent;
    } // getStudentById

    @GetMapping("/students/update")
    public String updateForm(@RequestParam(value = "sid", required = true) String sid, Model model) {	
	model.addAttribute("student", new Student());
	Student currentStudent=studentRepo.findOne(new Long(sid));
	model.addAttribute("currentStudent",currentStudent);
	return "studentUpdate";
    }
	
    @PostMapping("/students/update")
    public String updateConfirm(@ModelAttribute Student student,Model model) {	
	Student old=studentRepo.findOne(student.getSid());
	old=updateStudEntity(old, student);
	studentRepo.save(old);
	model.addAttribute("modmessage","Successfully Updated Student Id:"+student.getSid());
	return studentForm(model);
    }
	
    @GetMapping("/students/info")
    public String getStudentInfo(@RequestParam(value = "sid", required = true)
				 String sid,
				 Model model) {
	if (studentRepo.findOne(new Long(sid)) == null) {
	    return studentForm(model);
	} //if

        Student student = studentRepo.getOne(new Long(sid));

	//Get all courses and grades of a student
	List<StudentCourseGrade> scgList = scgRepo.findAll();
	List<StudentCourseGrade> scgListResult =
	    new ArrayList<StudentCourseGrade>();
	for (StudentCourseGrade scg : scgList) {
	    if (scg.getScgId().getStudent().getSid().equals(student.getSid())) {
		scgListResult.add(scg);
	    } //if
	} //for

	//Get all departments and majors of a student
	/*List<StudentDepartmentMajor> sdmList = sdmRepo.findAll();
	List<StudentDepartmentMajor> sdmListResult =
	    new ArrayList<StudentDepartmentMajor>();
	for (StudentDepartmentMajor sdm : sdmList) {
	    if (sdm.getSdmId().getStudent().getSid().equals(student.getSid())) {
		sdmListResult.add(sdm);
	    } //if
	} //if*/

	model.addAttribute("student", student);
	/**/ model.addAttribute("sdmList", null);
	model.addAttribute("scgList", scgListResult);
	return "studentInfo";
    }
	
    private Student updateStudEntity(Student old,Student newStudent) {
	old.setSid(newStudent.getSid());
	old.setDob(newStudent.getDob());
	old.setFname(newStudent.getFname());
	old.setLname(newStudent.getLname());
	old.setGender(newStudent.getGender());
	old.setEmail(newStudent.getEmail());
	old.setCity(newStudent.getCity());
	old.setState(newStudent.getState());
	old.setStreet(newStudent.getStreet());
	old.setZip(newStudent.getZip());
	old.setResidency(newStudent.getResidency());
	return old;
    }
} // StudentController

/* Put the following inside the class to allow insertions via GET requests */
/*
 * @GetMapping(path="/add/") // Map ONLY GET Requests public @ResponseBody
 * String addNewStudent(@RequestParam String dob,
 * 
 * @RequestParam String fname,
 * 
 * @RequestParam String lname,
 * 
 * @RequestParam String gender,
 * 
 * @RequestParam String email,
 * 
 * @RequestParam String street,
 * 
 * @RequestParam String city,
 * 
 * @RequestParam String state,
 * 
 * @RequestParam String zip,
 * 
 * @RequestParam String residency ) { // @ResponseBody means the returned String
 * is the response, not a view name // @RequestParam means it is a parameter
 * from the GET or POST request
 * 
 * Student s = new Student(); s.setDob(dob); s.setFname(fname);
 * s.setLname(lname); s.setGender(gender); s.setEmail(email);
 * s.setStreet(street); s.setCity(city); s.setState(state); s.setZip(zip);
 * s.setResidency(residency); studentRepo.save(s); return "Saved"; }
 */

/* Put the following in the class to show in JSON format */
/*
 * @GetMapping(path="/all/") // localhost:8080/students/all/
 * public @ResponseBody Iterable<Student> getAllStudents() { // This returns a
 * JSON or XML with the students return studentRepo.findAll(); }
 */
