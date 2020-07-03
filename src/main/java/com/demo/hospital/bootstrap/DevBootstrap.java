package com.demo.hospital.bootstrap;

import com.demo.hospital.model.*;
import com.demo.hospital.repository.*;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private PatientRepository patientRepository;
    private HospitalRepository hospitalRepository;
    private DoctorRepository doctorRepository;
    private SpecialityRepository specialityRepository;
    private NoteRepository noteRepository;

    DevBootstrap(PatientRepository patientRepository,
                 HospitalRepository hospitalRepository,
                 DoctorRepository doctorRepository,
                 SpecialityRepository specialityRepository,
                 NoteRepository noteRepository){

        this.patientRepository = patientRepository;
        this.hospitalRepository = hospitalRepository;
        this.doctorRepository = doctorRepository;
        this.specialityRepository = specialityRepository;
        this.noteRepository = noteRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try {
            initData();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void initData() throws ParseException {

        /*HOSPITAL*/

        HospitalEntity hospital01 = new HospitalEntity();
        hospital01.setName("hospital san juan de Dios");
        hospital01.setAddress("Av. Juan de la rosa");
        hospital01.setCreatedAt(new SimpleDateFormat("MM/dd/yyyy").parse("20/2/2002"));
        hospitalRepository.save(hospital01);

        HospitalEntity hospital02 = new HospitalEntity();
        hospital02.setName("hospital Viedma");
        hospital02.setAddress("Av. Victor Ustrariz");
        hospital02.setCreatedAt(new GregorianCalendar(2014, 2, 25).getTime());
        hospitalRepository.save(hospital02);

        HospitalEntity hospital03 = new HospitalEntity();
        hospital03.setName("hospital Los Olivos");
        hospital03.setAddress("Av. Heroinas");
        hospital03.setCreatedAt(new GregorianCalendar(2014, 2, 15).getTime());
        hospitalRepository.save(hospital03);

        /*DOCTORS*/

        DoctorEntity doctor01 = new DoctorEntity();
        doctor01.setName("homero");
        doctor01.setLastName("simpson");
        doctor01.setAddress("esteban arce");
        doctor01.setBornDate(new GregorianCalendar(1975, 6, 25).getTime());
        doctor01.setHospital(hospital01);
        doctorRepository.save(doctor01);

        DoctorEntity doctor02 = new DoctorEntity();
        doctor02.setName("lisa");
        doctor02.setLastName("simpson");
        doctor02.setAddress("Brazil");
        doctor02.setBornDate(new GregorianCalendar(1980, 4, 13).getTime());
        doctor02.setHospital(hospital01);
        doctorRepository.save(doctor02);

        DoctorEntity doctor03 = new DoctorEntity();
        doctor03.setName("homero");
        doctor03.setLastName("simpson");
        doctor03.setAddress("esteban arce");
        doctor03.setBornDate(new GregorianCalendar(1984, 4, 15).getTime());
        doctor03.setHospital(hospital01);
        doctorRepository.save(doctor03);

        /* SPECIALIZATION */

        SpecialityEntity spec01 = new SpecialityEntity();
        spec01.setName("Dermatologists");
        spec01.setDescription("Have problems with your skin, hair, nails?");
        spec01.setDoctor(doctor01);
        specialityRepository.save(spec01);

        SpecialityEntity spec02 = new SpecialityEntity();
        spec02.setName("Critical Care Medicine Specialists");
        spec02.setDescription("They care for people who are critically ill or injured");
        spec02.setDoctor(doctor01);
        specialityRepository.save(spec02);

        SpecialityEntity spec03 = new SpecialityEntity();
        spec03.setName("Endocrinologists");
        spec03.setDescription("These are experts on hormones and metabolism");
        spec03.setDoctor(doctor02);
        specialityRepository.save(spec03);

        /*PATIENTS*/

        PatientEntity patient01 = new PatientEntity();
        patient01.setName("Juan carlos");
        patient01.setLastName("villagran");
        patient01.setAddress("temporal");
        patient01.setBornDate(new GregorianCalendar(1993, 2, 21).getTime());
        patient01.setDoctor(doctor01);
        patientRepository.save(patient01);

        PatientEntity patient02 = new PatientEntity();
        patient02.setName("Mariana");
        patient02.setLastName("dupleich");
        patient02.setAddress("Av. Panamericana");
        patient02.setBornDate(new GregorianCalendar(1985, 1, 22).getTime());
        patient02.setDoctor(doctor01);
        patientRepository.save(patient02);

        PatientEntity patient03 = new PatientEntity();
        patient03.setName("Matilda");
        patient03.setLastName("Leon");
        patient03.setAddress("Av 6 de Agosto");
        patient03.setBornDate(new GregorianCalendar(1980, 8, 28).getTime());
        patient03.setDoctor(doctor02);
        patientRepository.save(patient03);

        /* NOTES */
        NoteEntity note01 = new NoteEntity();
        note01.setDescription("this is a description for patient 01");
        note01.setDateOfEmition(new GregorianCalendar(2015, 8, 28).getTime());
        note01.setPatient(patient01);
        noteRepository.save(note01);

        NoteEntity note02 = new NoteEntity();
        note02.setDescription("this is a description for patient 02");
        note02.setDateOfEmition(new GregorianCalendar(2019, 9, 29).getTime());
        note02.setPatient(patient01);
        noteRepository.save(note02);

        NoteEntity note03 = new NoteEntity();
        note03.setDescription("this is a description for patient 03");
        note03.setDateOfEmition(new GregorianCalendar(2020, 12, 22).getTime());
        note03.setPatient(patient02);
        noteRepository.save(note03);
    }

}
