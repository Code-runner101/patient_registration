package com.example.ThymeleafProject.admin;

import com.example.ThymeleafProject.Appointment.Appointment;
import com.example.ThymeleafProject.Appointment.AppointmentRepository;
import com.example.ThymeleafProject.patient.Patient;
import com.example.ThymeleafProject.patient.PatientRepository;
import com.example.ThymeleafProject.user.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final PatientRepository repository;
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AdminController(PatientRepository repository, AppointmentRepository appointmentRepository) {
        this.repository = repository;
        this.appointmentRepository = appointmentRepository;
    }

    @GetMapping
    public String getAdminMainPg(HttpSession session){
        session.removeAttribute("patient");
        return "admin";
    }

    @GetMapping("/searchSinglePatInf")
    public String getSearchPatInf(){
        return "searchSinglePatInf";
    }

    @GetMapping("/searchPat")
    public String getSearchPat(){
        return "searchPat";
    }

    @GetMapping("/addPatInf")
    public String getAddPatInfPg(HttpSession session, Model model){
        // Проверяем, был ли уже найден пациент и сохранен в сессии
        Patient patient = (Patient) session.getAttribute("patient");
        if(patient != null){
            // Если пациент найден, добавляем его в модель
            model.addAttribute("patient", patient);
            return "addPatInf";
        }
        // Если пациент не найден, переходим на страницу поиска
        return "redirect:/admin/searchPat";
    }

    @PostMapping("/addPatInf")
    public String addPatientInfo(@RequestBody String body, @ModelAttribute Patient patient, BindingResult result, HttpSession session) {
        PDFGenerator pdfGenerator = new PDFGenerator();

        if (result.hasErrors()) {
            return "addPatInf";
        }
        // Получаем текущего пациента из сессии
        Patient currentPatient = (Patient) session.getAttribute("patient");
        if (currentPatient == null) {
            return "searchPat";
        }
        // Устанавливаем гражданство в зависимости от выбранной radiobuttn
        String citizenship = "РФ"; // Значение по умолчанию для гражданина РФ
        if (patient.getPassportType().equals("foreign")) {
            citizenship = patient.getCitizenship(); // Значение из формы для иностранного гражданина
        }

        // Обновляем данные пациента
        if(citizenship.equals("РФ")){
            currentPatient.setPassportSer(patient.getPassportSer());
        }
        else {
            currentPatient.setPassportSer(null);
        }
        currentPatient.setPassportNum(patient.getPassportNum());
        currentPatient.setGender(patient.getGender());
        currentPatient.setBirthdate(patient.getBirthdate());
        currentPatient.setCitizenship(citizenship);
        currentPatient.setPassportType(patient.getPassportType());
        // Сохраняем обновленные данные в базу данных
        repository.save(currentPatient);
        pdfGenerator.fillPdfTemplate(currentPatient);
        session.removeAttribute("patient");
        return "redirect:/admin";
    }


    @PostMapping("/searchPat")
    public String getPatient(@RequestParam("regNum") String regNum, Model model, HttpSession session) {
        Patient patient = repository.findByRegNum(regNum);
        if (patient == null) {
            throw new IllegalArgumentException("Invalid pat reg-num:" + regNum);
        }
        // Пациент найден, добавляем информацию в модель и устанавливаем атрибут сессии
        model.addAttribute("patient", patient);
        session.setAttribute("patient", patient);
        return "redirect:/admin/addPatInf";
    }

    @GetMapping("/patients")
    @ResponseBody
    public List<Patient> getAllPatients() {
        return repository.findAll();
    }

    @GetMapping("/patients/{id}")
    @ResponseBody
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        return repository.findById(id)
                .map(patient -> ResponseEntity.ok().body(patient))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/patients/search")
    @ResponseBody
    public List<Patient> searchPatients(@RequestParam("query") String query) {
        return repository.findByRegNumOrPhoneNum(query, query);
    }

    @GetMapping("/appointments")
    @ResponseBody
    public List<Appointment> getAppointments() {
        return appointmentRepository.findAll();
    }
}
