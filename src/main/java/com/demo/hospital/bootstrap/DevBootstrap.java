package com.demo.hospital.bootstrap;

import com.demo.hospital.model.DoctorEntity;
import com.demo.hospital.model.HospitalEntity;
import com.demo.hospital.model.PatientEntity;
import com.demo.hospital.repository.DoctorRepository;
import com.demo.hospital.repository.HospitalRepository;
import com.demo.hospital.repository.PatientRepository;
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

    DevBootstrap(PatientRepository patientRepository,
                 HospitalRepository hospitalRepository,
                 DoctorRepository doctorRepository){

        this.patientRepository = patientRepository;
        this.hospitalRepository = hospitalRepository;
        this.doctorRepository = doctorRepository;
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

        /*PATIENTS*/

        PatientEntity patient01 = new PatientEntity();
        patient01.setName("pedro");
        patient01.setLastName("Poveda");
        patient01.setDirection("temporal");
        patient01.setBornDate(new GregorianCalendar(1993, 2, 21).getTime());
        patientRepository.save(patient01);
    }

}
