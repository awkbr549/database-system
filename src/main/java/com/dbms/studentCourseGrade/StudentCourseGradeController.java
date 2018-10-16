package com.dbms.studentCourseGrade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dbms.student.Student;
import com.dbms.student.StudentRepository;
import com.dbms.course.Course;
import com.dbms.course.CourseRepository;

@Controller
public class StudentCourseGradeController {
    @Autowired // Spring and Hibernate will auto-generate this object
    private StudentCourseGradeRepository scgRepo;

    @Autowired
    private StudentRepository studentRepo;
    
    @Autowired
    private CourseRepository courseRepo;
    
    @GetMapping("/registration") // localhost:8080/registration
    public String scgForm(Model model) {
	model.addAttribute("scgQ", new StudentCourseGrade());
	//model.addAttribute("scgList", scgRepo.findAll());
	model.addAttribute("scgList", scgRepo.findAllByOrderByScgIdAsc());
	return "registration";
    } //scgForm

    @PostMapping("/registration") // localhost:8080/registration
    public String scgSubmit(@ModelAttribute StudentCourseGrade scg, Model model) {
	if (studentRepo.findOne(scg.getScgId().getStudent().getSid()) != null &&
	    courseRepo.findOne(scg.getScgId().getCourse().getCid()) != null &&
	    scgRepo.findOne(scg.getScgId()) == null
	    ) {
	    scgRepo.save(scg);
	} //if

	return "registration";
    } //scgSubmit

    @GetMapping("/registration/info") // localhost:8080/registration/info/
    public String getRegistrationInfo(@RequestParam(value = "cid",
						    required = true) String cid,
				      @RequestParam(value = "sid",
						    required = true) String sid,
				      Model model) {	
	if (studentRepo.findOne(new Long(sid)) == null ||
	    courseRepo.findOne(new Long(cid)) == null) {
	    return scgForm(model);
	} //if

	Student tempS = studentRepo.getOne(new Long(sid));
	Course tempC = courseRepo.getOne(new Long(cid));
	
	StudentCourseGrade scg = new StudentCourseGrade();
	scg.setScgId(sid, cid);
	scg.getScgId().setStudent(tempS);
	scg.getScgId().setCourse(tempC);
	scg.setGrade(scgRepo.getOne(scg.getScgId()).getGrade());
	model.addAttribute("scg", scg);
        
	return "registrationInfo";
    } //getRegistrationInfo
    
    @GetMapping("/registration/update")
    public String updateForm(@RequestParam(value = "cid", required = true) String cid,
			     @RequestParam(value = "sid", required = true) String sid,
			     Model model) {
	if (studentRepo.findOne(new Long(sid)) == null ||
	    courseRepo.findOne(new Long(cid)) == null) {
	    return scgForm(model);
	} //if
	
	StudentCourseGrade scg = new StudentCourseGrade();
	scg.setScgId(sid, cid);
	scg.getScgId().setStudent(studentRepo.getOne(new Long(sid)));
	scg.getScgId().setCourse(courseRepo.getOne(new Long(cid)));
	scg.setGrade(scgRepo.getOne(scg.getScgId()).getGrade());

	StudentCourseGrade scgQ = new StudentCourseGrade();
	
	model.addAttribute("scg", scg);	
	model.addAttribute("scgQ", scgQ);
	
	return "registrationUpdate";
    } //updateForm

    @PostMapping("/registration/update")
    public String updateConfirm(@ModelAttribute StudentCourseGrade scgQ,
				@ModelAttribute StudentCourseGrade scg,
				@ModelAttribute String grade,
				Model model) {
	//StudentCourseGrade tempScg = scgRepo.findOne(scg.getScgId());
	scg.setGrade(scgQ.getGrade());
	//scg.setGrade(grade);
	scgRepo.save(scg);
	return scgForm(model);
    } //updateConfirm

    @GetMapping("/registration/delete") // localhost:8080/registration/delete/
    public String delete(@RequestParam(value = "sid", required = true) String sid,
			 @RequestParam(value = "cid", required = true) String cid,
			 Model model) {
	StudentCourseGrade temp = new StudentCourseGrade();
	temp.setScgId(sid, cid);
	if (scgRepo.findOne(temp.getScgId()) != null) {
	    scgRepo.delete(getScgByScgId(scgRepo.findAll(), temp.getScgId()));
	} //if

	return scgForm(model);		  
    } //delete
	
    public static StudentCourseGrade getScgByScgId(Iterable<StudentCourseGrade> scgList, StudentCourseGradeId scgId) {
	StudentCourseGrade rtrnScg = null;
	for (StudentCourseGrade scg : scgList) {
	    if (scg.getScgId().equals(scgId)) {
		rtrnScg = scg;
		break;
	    } //if
	} //for-each
	return rtrnScg;
    } //getScgByScgId    
} //StudentCourseGradeController
