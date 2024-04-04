package com.lifters.higorsouza.electionapi.utils;

import lombok.extern.log4j.Log4j2;

import java.util.Random;

@Log4j2
public class VoterNumberGenerator {

    public static String generateVoterNumber(){
        log.info("Gerando número de identificação do eleitor.");

        Random random = new Random();
        int generatedNumber = random.nextInt(900000) + 100000;
        String generatedVoterNumber = String.valueOf(generatedNumber);

        log.info("Número gerado: {}", generatedVoterNumber);
        return generatedVoterNumber;
    }
}
