package com.avit.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.avit.entity.EligibilityDetails;
import com.avit.repsitories.EligibilityRepo;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AppRunner implements ApplicationRunner {

	private final EligibilityRepo eligibilityRepo;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		EligibilityDetails eligibilityDetails1 = EligibilityDetails.builder().planStatus("Y").id(1).planName("ASAP").name("Abhi").email("Abhi@123")
				.gender('M').ssn(123234l).mobile(03470703l).build();
		eligibilityRepo.save(eligibilityDetails1);
		
		EligibilityDetails eligibilityDetails2 = EligibilityDetails.builder().planName("SNAP").planStatus("Y").id(2).name("Abhishek").email("Abhishek@123")
				.gender('M').ssn(12323234l).mobile(99370703l).build();
		eligibilityRepo.save(eligibilityDetails2);
		
		EligibilityDetails eligibilityDetails3 = EligibilityDetails.builder().planName("ADDAP").id(3).planStatus("N").name("Abhishekgec").email("Abhishekgec@123")
				.gender('M').ssn(12323234l).mobile(99370703l).build();
		eligibilityRepo.save(eligibilityDetails3);

	}

}
