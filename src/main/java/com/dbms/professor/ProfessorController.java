package com.dbms.professor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dbms.course.Course;
import com.dbms.course.CourseRepository;
import com.dbms.department.Department;
import com.dbms.department.DepartmentRepository;

//import com.dbms.professorCourse.*;

@Controller
public class ProfessorController {
	
    @Autowired
    private ProfessorRepository profRepo;
	
    @Autowired
    private DepartmentRepository deptRepo;
	
    @Autowired
    private CourseRepository courseRepo;

    //@Autowired
    //private ProfessorCourseRepository pcRepo;
	
    @GetMapping("/professors")
    public String professorForm(Model model) {
	model.addAttribute("prof", new ProfessorBean());
	List<ProfessorBean> profList=getProfessorList(profRepo.findAll());
	model.addAttribute("profList",profList);
	return "professors";
    }
	
    @PostMapping("/professors")
    public String professorSubmit(@Valid @ModelAttribute("prof") ProfessorBean pBean, BindingResult bindingResult,
				  Model model) {
	if (bindingResult.hasErrors()) {
	    List<ProfessorBean> profList = getProfessorList(profRepo.findAll());
	    model.addAttribute("profList", profList);
	    return "professors";
	}
	Long deptId=pBean.getDeptId();
	if(deptId!=null) {
	    Department dept = deptRepo.findOne(deptId);
	    if(dept!=null) {
		try {
		    Professor professor = populateProfEntity(pBean, dept);
		    profRepo.save(professor);
		    model.addAttribute("insertmessage", "Successful!");
		}catch(Exception e) {
		    model.addAttribute("insertmessage", e.getMessage());
		    List<ProfessorBean> profList = getProfessorList(profRepo.findAll());
		    model.addAttribute("profList", profList);
		    return "professors";
		}
	    }else {
		model.addAttribute("insertmessage", "Failed to add professor.Invalid Department!");
		List<ProfessorBean> profList = getProfessorList(profRepo.findAll());
		model.addAttribute("profList", profList);
		return "professors";
	    }
	}else {
	    model.addAttribute("insertmessage", "Department id cannot be null!");
	    List<ProfessorBean> profList = getProfessorList(profRepo.findAll());
	    model.addAttribute("profList", profList);
	    return "professors";
	}
		 
	return professorForm(model);
    }
	
    @GetMapping("/professors/delete")
    public String delete(@RequestParam(value = "pid", required = true) String pid, Model model) {
	profRepo.delete(profRepo.findOne(new Long(pid)));
	return professorForm(model);
    }
	
    @GetMapping("/professors/update")
    public String updateForm(@RequestParam(value = "pid", required = true) String pid, Model model) {	
	model.addAttribute("profQ", new ProfessorBean());
	Professor p=profRepo.findOne(new Long(pid));
	ProfessorBean currProf=populateProfBean(p);
	model.addAttribute("currentProf",currProf);
	return "professorUpdate";
    }
	
    @PostMapping("/professors/update")
    public String updateConfirm(@Valid @ModelAttribute("profQ") ProfessorBean pBean,BindingResult bindingResult,Model model) {	
	Professor old=profRepo.findOne(pBean.getPid());
	Department dept=deptRepo.findOne(pBean.getDeptId());
	if(dept!=null) {
	    old=updateProfEntity(old, pBean, dept);
	    profRepo.save(old);
	    model.addAttribute("modmessage","Successfully Updated Professor Id:"+pBean.getPid());
	}else {
	    model.addAttribute("modmessage","Failed to update Professor Id:"+pBean.getPid()+".Invalid Department!");
	}
	return professorForm(model);
    }
	
    @GetMapping("/professors/info")
    public String getProfessorInfo(@RequestParam(value = "pid", required = true)
				   String pid,
				   Model model) {
	if (profRepo.findOne(new Long(pid)) == null) {
	    return professorForm(model);
	} //if

	/*Professor professor = professorRepo.getOne(new Long(pid));

	//Get the department for this professor
	Department deptartment = deptRepo.getOne(new Long(professor.getDid()));
	
	//Get all courses taught by this professor
	List<ProfessorCourse> pcList = pcRepo.findAll();
	List<ProfessorCourse> pcListResult =
	    new ArrayList<ProfessorCourse>();
	for (ProfessorCourse pc : pcList) {
	    if (pc.getPcId().getProfessor().getPid().equals(professor.getPid())) {
		pfListResult.add(pc);
	    } //if
	} //for

	model.addAttribute("prof", professor);
	model.addAttribute("dept", department);
	model.addAttribute("pcList", pcListResult);
	return "professorInfo";*/
	

	
	Professor p = profRepo.findOne(new Long(pid));
	model.addAttribute("deptInfo", p.getDepartment());
	model.addAttribute("prof", p);
	List<Course> courseList = courseRepo.findCourses(new Long(pid));
	model.addAttribute("courseList", courseList);
	return "professorInfo";
    }
	
    private Professor populateProfEntity(ProfessorBean pBean,Department dept) {
	Professor professor=new Professor();
	professor.setPid(pBean.getPid());
		
	try {
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    if(!pBean.getDob().isEmpty()) {
		java.util.Date parsed = format.parse(pBean.getDob());
		java.sql.Date sqlDate = new java.sql.Date(parsed.getTime());
		professor.setDob(sqlDate);
	    }
	}catch(ParseException pe) {
	    System.out.println(pe.getMessage());
	}
        
	professor.setFname(pBean.getFname());
	professor.setLname(pBean.getLname());
	professor.setGender(pBean.getGender());
	professor.setEmail(pBean.getEmail());
	professor.setCity(pBean.getCity());
	professor.setState(pBean.getState());
	professor.setStreet(pBean.getStreet());
	professor.setZip(pBean.getZip());
	professor.setDepartment(dept);
	return professor;
    }
	
    private List<ProfessorBean> getProfessorList(Iterable<Professor> profIter){
	List<ProfessorBean> profBeanList=new ArrayList<>();
	Iterator<Professor> iter=profIter.iterator();
	while(iter.hasNext()) {
	    Professor p=iter.next();
	    ProfessorBean bean=populateProfBean(p);
	    profBeanList.add(bean);
	}
	return profBeanList;
    }
	
    private ProfessorBean populateProfBean(Professor p) {
	ProfessorBean bean=new ProfessorBean();
	bean.setPid(p.getPid());
		
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	if(p.getDob()!=null) {
	    String text = sdf.format(p.getDob());
	    bean.setDob(text);
	}
		
	//bean.setDob(p.getDob());
	bean.setFname(p.getFname());
	bean.setLname(p.getLname());
	bean.setGender(p.getGender());
	bean.setEmail(p.getEmail());
	bean.setCity(p.getCity());
	bean.setState(p.getState());
	bean.setStreet(p.getStreet());
	bean.setZip(p.getZip());
	bean.setDeptId(p.getDepartment().getDid());
	return bean;
    }
	
    private Professor updateProfEntity(Professor professor,ProfessorBean pBean,Department dept) {
	professor.setPid(pBean.getPid());
		
	try {
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    if(!pBean.getDob().isEmpty()) {
		java.util.Date parsed = format.parse(pBean.getDob());
		java.sql.Date sqlDate = new java.sql.Date(parsed.getTime());
		professor.setDob(sqlDate);
	    }else {
		professor.setDob(null);
	    }
	}catch(ParseException pe) {
	    System.out.println(pe.getMessage());
	}
		
	//professor.setDob(pBean.getDob());
	professor.setFname(pBean.getFname());
	professor.setLname(pBean.getLname());
	professor.setGender(pBean.getGender());
	professor.setEmail(pBean.getEmail());
	professor.setCity(pBean.getCity());
	professor.setState(pBean.getState());
	professor.setStreet(pBean.getStreet());
	professor.setZip(pBean.getZip());
	professor.setDepartment(dept);
	return professor;
    }
}
