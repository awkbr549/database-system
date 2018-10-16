package com.dbms.studentDepartmentMajor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dbms.student.Student;
import com.dbms.student.StudentRepository;
import com.dbms.department.Department;
import com.dbms.department.DepartmentRepository;

@Controller
public class StudentDepartmentMajorController {
    @Autowired
    private StudentDepartmentMajorRepository sdmRepo;

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private DepartmentRepository deptRepo;

    @GetMapping("/majors")
    public String sdmForm(Model model) {
	model.addAttribute("sdmQ", new StudentDepartmentMajor());
	model.addAttribute("sdmList", sdmRepo.findAllByOrderBySdmIdAsc());
	return "majors";
    } //sdmForm

    @PostMapping("/majors")
    public String sdmSubmit(@ModelAttribute StudentDepartmentMajor sdm,
			    Model model) {
	if (studentRepo.findOne(sdm.getSdmId().getStudent().getSid()) != null &&
	    deptRepo.findOne(sdm.getSdmId().getDepartment().getDid()) != null &&
	    sdmRepo.findOne(sdm.getSdmId()) == null
	    ) {
	    sdmRepo.save(sdm);
	} //if

	return sdmForm(model);
    } //sdmSubmit

    @GetMapping("/majors/info")
    public String getMajorsInfo(@RequestParam(value = "did",
					      required = true) String did,
				@RequestParam(value = "sid",
					      required = true) String sid,
				Model model) {
	if (studentRepo.findOne(new Long(sid)) == null ||
	    deptRepo.findOne(new Long(did)) == null) {
	    return sdmForm(model);
	} //if

	Student tempS = studentRepo.getOne(new Long(sid));
	Department tempD = deptRepo.getOne(new Long(did));

	StudentDepartmentMajor sdm = new StudentDepartmentMajor();
	sdm.setSdmId(sid, did);
	sdm.getSdmId().setStudent(tempS);
	sdm.getSdmId().setDepartment(tempD);
	sdm.setMajor(sdmRepo.getOne(sdm.getSdmId()).getMajor());
	model.addAttribute("sdm", sdm);

	return "majorsInfo";
    } //getMajorsInfo

    @GetMapping("/majors/delete")
    public String delete(@RequestParam(value = "sid",
				       required = true) String sid,
			 @RequestParam(value = "did",
				       required = true) String did,
			 Model model) {
	StudentDepartmentMajor temp = new StudentDepartmentMajor();
	temp.setSdmId(sid, did);
	if (sdmRepo.findOne(temp.getSdmId()) != null) {
	    sdmRepo.delete(getSdmBySdmId(sdmRepo.findAll(), temp.getSdmId()));
	} //if

	return sdmForm(model);
    } //delete

    public static StudentDepartmentMajor getSdmBySdmId(Iterable<StudentDepartmentMajor> sdmList, StudentDepartmentMajorId sdmId) {
	StudentDepartmentMajor rtrnSdm = null;
	for (StudentDepartmentMajor sdm : sdmList) {
	    if (sdm.getSdmId().equals(sdmId)) {
		rtrnSdm = sdm;
		break;
	    } //if
	} //for-each

	return rtrnSdm;
    } //getSdmBySdmId
} //StudentDepartmentMajorController
