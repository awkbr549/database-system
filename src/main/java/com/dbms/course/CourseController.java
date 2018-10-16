package com.dbms.course;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dbms.department.Department;
import com.dbms.department.DepartmentRepository;
import com.dbms.professor.Professor;
import com.dbms.professor.ProfessorRepository;

@Controller
public class CourseController {
	
	@Autowired
	private ProfessorRepository profRepo;
	
	@Autowired
	private DepartmentRepository deptRepo;
	
	@Autowired
	private CourseRepository courseRepo;
	
	@GetMapping("/courses")
	public String courseForm(Model model) {
		model.addAttribute("courseA", new CourseBean());
		List<CourseBean> courseList=getCourseList(courseRepo.findAll());
		model.addAttribute("courseList",courseList);
		return "courses";
	}
	
	@PostMapping("/courses")
	public String courseSubmit(@Valid @ModelAttribute("courseA") CourseBean cBean, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			List<CourseBean> courseList = getCourseList(courseRepo.findAll());
			model.addAttribute("courseList", courseList);
			return "courses";
		}
		Department dept = deptRepo.findOne(cBean.getDid());
		Professor prof = profRepo.findOne(cBean.getPid());
		if (dept != null && prof != null) {
			try {
				Course c = populateCourseEntity(cBean, dept, prof);
				courseRepo.save(c);
			}catch(Exception e) {
				model.addAttribute("insertmessage", e.getMessage());
				List<CourseBean> courseList = getCourseList(courseRepo.findAll());
				model.addAttribute("courseList", courseList);
				return "courses";
			}
		} else {
			model.addAttribute("insertmessage", "Failed to add course .Invalid Department or Professor!");
			List<CourseBean> courseList = getCourseList(courseRepo.findAll());
			model.addAttribute("courseList", courseList);
			return "courses";
		}
		return courseForm(model);
	}
	
	@GetMapping("/courses/delete")
	public String delete(@RequestParam(value = "cid", required = true) String cid, Model model) {
		courseRepo.delete(courseRepo.findOne(new Long(cid)));
		return courseForm(model);
	}
	
	@GetMapping("/courses/update")
	public String updateForm(@RequestParam(value = "cid", required = true) String cid, Model model) {	
		model.addAttribute("courseQ", new CourseBean());
		Course c = courseRepo.findOne(new Long(cid));
		CourseBean currCourse =populateCourseBean(c);
		model.addAttribute("currentCourse",currCourse);
		return "courseUpdate";
	}
	
	@PostMapping("/courses/update")
	public String updateConfirm(@Valid @ModelAttribute("courseQ") CourseBean cBean,BindingResult bindingResult,Model model) {	
		Course old=courseRepo.findOne(cBean.getCid());
		old=updateCourseEntity(old, cBean);
		courseRepo.save(old);
		return courseForm(model);
	}
	
	@GetMapping("/courses/info")
	public String getCourseInfo(@RequestParam(value = "cid", required = true) String cid, Model model) {
		Course c=courseRepo.findOne(new Long(cid));
		model.addAttribute("course",c);
		model.addAttribute("dInfo",c.getDepartment());
		if(c.getProfessor()!=null) {
			model.addAttribute("pInfo",c.getProfessor());
		}
		return "courseInfo";
	}
	
	private Course updateCourseEntity(Course course,CourseBean cBean) {
		course.setCid(cBean.getCid());
		course.setCid(cBean.getCid()); 
		course.setCname(cBean.getCname());
		course.setCnumber(cBean.getCnumber());
		course.setDescription(cBean.getDescription());
		course.setCredits(cBean.getCredits());
		course.setSemester(cBean.getSemester());
		return course;
	}
	
	private Course populateCourseEntity(CourseBean cBean,Department dept, Professor prof) {
		Course course = new Course();
		course.setCid(cBean.getCid()); 
		course.setCname(cBean.getCname());
		course.setCnumber(cBean.getCnumber());
		course.setDescription(cBean.getDescription());
		course.setCredits(cBean.getCredits());
		course.setSemester(cBean.getSemester());
		course.setProfessor(prof);
		course.setDepartment(dept);
		return course;
	}
	
	private List<CourseBean> getCourseList(Iterable<Course> courseIter){
		List<CourseBean> courseBeanList=new ArrayList<>();
		Iterator<Course> iter=courseIter.iterator();
		while(iter.hasNext()) {
			Course p=iter.next();
			CourseBean bean=populateCourseBean(p);
			courseBeanList.add(bean);
		}
		return courseBeanList;
	}
	
	private CourseBean populateCourseBean(Course cBean) {
		CourseBean bean=new CourseBean();
		bean.setCid(cBean.getCid());
		bean.setCname(cBean.getCname());
		bean.setCnumber(cBean.getCnumber());
		bean.setDescription(cBean.getDescription());
		bean.setCredits(cBean.getCredits());
		bean.setSemester(cBean.getSemester());
		bean.setDid(cBean.getDepartment().getDid());
		if(cBean.getProfessor()!=null)
		 bean.setPid(cBean.getProfessor().getPid());
		return bean;
	}
	
}