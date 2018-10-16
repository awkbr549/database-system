package com.dbms.department;

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

import com.dbms.course.Course;
import com.dbms.course.CourseRepository;
import com.dbms.professor.Professor;
import com.dbms.professor.ProfessorRepository;

@Controller
public class DepartmentController {
	
	@Autowired
	private DepartmentRepository deptRepo;
	
	@Autowired
	private ProfessorRepository profRepo;
	
	@GetMapping("/departments")
	public String departmentForm(Model model) {
		model.addAttribute("dept", new Department());
		model.addAttribute("deptList",deptRepo.findAll());
		return "departments";
	}
	
	@PostMapping("/departments")
	public String professorSubmit(@Valid @ModelAttribute("dept") Department dept, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("deptList", deptRepo.findAll());
			return "departments";
		}
		try {
			deptRepo.save(dept);
			model.addAttribute("insertmessage", "Successful!");
		} catch (Exception e) {
			model.addAttribute("insertmessage", e.getMessage());
			model.addAttribute("deptList", deptRepo.findAll());
			return "departments";
		}
		return departmentForm(model);
	}
	
	/*@GetMapping("/departments/delete")
	public String delete(@RequestParam(value = "did", required = true) String did, Model model) {
		deptRepo.delete(deptRepo.findOne(new Long(did)));
		return departmentForm(model);
	}*/
	
	@GetMapping("/departments/update")
	public String updateForm(@RequestParam(value = "did", required = true) String did, Model model) {	
		model.addAttribute("deptQ", new Department());
		Department d=deptRepo.findOne(new Long(did));
		model.addAttribute("currentDept",d);
		return "deptUpdate";
	}
	
	@PostMapping("/departments/update")
	public String updateConfirm(@ModelAttribute("deptQ") Department newDept,Model model) {	
		Department old=deptRepo.findOne(newDept.getDid());
		old=updateDeptEntity(old, newDept);
		deptRepo.save(old);
		model.addAttribute("modmessage","Successfully Updated Department Id:"+old.getDid());
		return departmentForm(model);
	}
	
	private Department updateDeptEntity(Department oldDept,Department newDept) {
		oldDept.setDeptName(newDept.getDeptName());
		oldDept.setDeptDesc(newDept.getDeptDesc());
		return oldDept;
	}
	
	@GetMapping("/departments/info")
	public String getDepartmentInfo(@RequestParam(value = "did", required = true) String did, Model model) {
		Department d=deptRepo.findOne(new Long(did));
		model.addAttribute("dept",d);
		List<Professor> profList=profRepo.findProfInDept(new Long(did));
		model.addAttribute("profList",profList);
		return "departmentInfo";
	}
	
	
}
