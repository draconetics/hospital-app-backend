package com.demo.hospital.bootstrap;

import com.demo.hospital.model.HospitalEntity;
import com.demo.hospital.model.PatientEntity;
import com.demo.hospital.repository.HospitalRepository;
import com.demo.hospital.repository.PatientRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private PatientRepository patientRepository;
    private HospitalRepository hospitalRepository;

    DevBootstrap(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData(){

/*        HospitalEntity hospital01 = new HospitalEntity();
        hospital01.setName("hospital san juan de Dios");
        hospital01.setAddress("Av. Juan de la rosa");
        hospital01.setCreatedAt(new Date(100,5, 12));
        hospitalRepository.save(hospital01);*/

        PatientEntity patient01 = new PatientEntity();
        patient01.setName("pedro");
        patient01.setLastName("Poveda");
        patient01.setDirection("temporal");
        patient01.setBornDate(new Date(2000,12,20));
        System.out.println("--------------");
        System.out.println(patient01.toString());
        System.out.println(String.valueOf(patient01));
        System.out.println(String.valueOf(patient01));
        patientRepository.save(patient01);
    }

}
