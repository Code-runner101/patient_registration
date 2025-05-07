package com.example.ThymeleafProject.patient;

import com.example.ThymeleafProject.Appointment.Appointment;
import com.example.ThymeleafProject.Appointment.AppointmentRepository;
import com.example.ThymeleafProject.RegNumGenerator;
import com.example.ThymeleafProject.employee.Doctor;
import com.example.ThymeleafProject.employee.DoctorRepository;
import com.example.ThymeleafProject.schedule.Schedule;
import com.example.ThymeleafProject.schedule.ScheduleRepository;
import com.example.ThymeleafProject.user.User;
import com.example.ThymeleafProject.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;

    @GetMapping
    public String getPatPg() {
        return "patient";
    }

    @GetMapping("/bookAppointment")
    public String getBookPg(Principal principal) {
        User currentUser = userRepository.findByUsername(principal.getName());
        Patient patient = currentUser.getPatient();

        // Проверить, заполнены ли необходимые поля
        if (patient == null) {
            return "redirect:/patient";
        }
        return "bookAppointment";
    }

    @PostMapping("/bookAppointment")
    public String bookAppointment(@RequestParam Long doctorId, @RequestParam Long scheduleId, Principal principal,Model model) {
        // Найти текущего пользователя и связать его с данными пациента
        User currentUser = userRepository.findByUsername(principal.getName());
        Patient patient = currentUser.getPatient();

        Schedule schedule = null;
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(scheduleId);
        if (optionalSchedule.isPresent()) {
            schedule = optionalSchedule.get();
        } else {
            return "Schedule not found.";
        }

        Doctor doctor = null;
        Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorId);
        if (optionalDoctor.isPresent()) {
            doctor = optionalDoctor.get();
        } else {
            return "Doctor not found.";
        }

        // Создать новую запись на прием
        Appointment appointment = new Appointment(patient, doctor, schedule);

        // Сохранить запись на прием в базе данных
        appointmentRepository.save(appointment);

        // Обновить статус расписания на "booked"
        schedule.setStatus("booked");
        scheduleRepository.save(schedule);

        return "redirect:/";
    }

    @PostMapping("/add")
    public String addPatient(Patient patient, Principal principal) {
        // Найти текущего пользователя и связать его с данными пациента
        User currentUser = userRepository.findByUsername(principal.getName());
        Patient patientCheck = currentUser.getPatient();
        patient.setUser(currentUser);
        currentUser.setPatient(patient);

        if (patientCheck != null) {
            return "redirect:/patient/bookAppointment";
        }

        String regNum = RegNumGenerator.generateRegNum();
        patient.setPhoneNum("+7"+patient.getPhoneNum());
        patient.setRegNum(regNum);

        patientRepository.save(patient);

        return "redirect:/patient/bookAppointment";
    }

    @GetMapping("/appointments")
    @ResponseBody
    public List<Appointment> getAppointments(Principal principal) {
        User currentUser = userRepository.findByUsername(principal.getName());
        Patient patient = currentUser.getPatient();
        return appointmentRepository.findByPatient(patient);
    }

    @GetMapping("/find/{regNum}")
    public Patient findPatient(@PathVariable String regNum) {
        return patientRepository.findByRegNum(regNum);
    }
}
