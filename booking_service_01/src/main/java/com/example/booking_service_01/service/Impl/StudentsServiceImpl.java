package com.example.booking_service_01.service.Impl;

import com.example.booking_service_01.dto.StudentsDTO;
import com.example.booking_service_01.entity.Students;
import com.example.booking_service_01.mapper.BookingMapper;
import com.example.booking_service_01.repository.StudentsRepository;
import com.example.booking_service_01.service.StudentsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentsServiceImpl implements StudentsService{
    @Autowired
    StudentsRepository studentsRepository;

    @Override
    public StudentsDTO findBySid(Integer sid) {
        Students students = studentsRepository.findBySid(sid);
        StudentsDTO studentsDTO = BookingMapper.INSTANCE.students_To_DTO(students);
        return studentsDTO;
    }

    @Override
    public boolean checkSid(Integer sid) {
        Students students = studentsRepository.findBySid(sid);
        if(students==null) {
            return false;
        }
        return true;
    }

    @Override
    public Integer insertStudentsDTO(StudentsDTO studentsDTO) {
        Students students = BookingMapper.INSTANCE.studentsDTO_To_Entity(studentsDTO);
        studentsRepository.save(students);
        return students.getSid();
    }

    @Override
    public void delete(StudentsDTO studentsDTO) {
        Students students = BookingMapper.INSTANCE.studentsDTO_To_Entity(studentsDTO);
        studentsRepository.delete(students);
    }

    @Override
    public boolean students_login(Integer sid ,String pw) {
        Students students = studentsRepository.findBySid(sid);
        if(students.getPw().equals(pw)) {
            return true;
        }
        else {
            return false;
        }
    }
    @Override
    public Integer update(StudentsDTO studentsDTO) {
        Students students = BookingMapper.INSTANCE.studentsDTO_To_Entity(studentsDTO);
        studentsRepository.save(students);
        return students.getSid();
    }
    
}