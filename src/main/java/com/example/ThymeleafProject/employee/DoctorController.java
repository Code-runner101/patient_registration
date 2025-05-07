package com.example.ThymeleafProject.employee;

import com.example.ThymeleafProject.schedule.Schedule;
import com.example.ThymeleafProject.schedule.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;

    @GetMapping
    public List<Map<String, Object>> getAllDoctors() {
        return doctorRepository.findAll().stream().map(doctor -> {
            Map<String, Object> doctorMap = new HashMap<>();
            doctorMap.put("id", doctor.getId());
            doctorMap.put("surname", doctor.getSurname());
            doctorMap.put("firstName", doctor.getFirstName());
            doctorMap.put("position", doctor.position);
            return doctorMap;
        }).collect(Collectors.toList());
    }

    @GetMapping("/schedule/{doctorId}")
    public List<Schedule> getAvailableSchedule(@PathVariable Long doctorId) {
        return scheduleRepository.findAvailableByDoctorId(doctorId);
    }
}
