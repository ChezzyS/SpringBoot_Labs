package ru.arkhipov.MySecondTestAppSpringBoot.service;

import ru.arkhipov.MySecondTestAppSpringBoot.model.Positions;

import java.time.Year;

public interface AnnualBonusService {


    double calculate(Positions positions, double salary, double bonus, int workDays, int year);


    default double calculate(Positions positions, double salary, double bonus, int workDays) {
        return calculate(positions, salary, bonus, workDays, Year.now().getValue());
    }


    double calculateQuarterlyBonus(Positions positions, double salary, double bonus, int workDays, int year);


    default double calculateQuarterlyBonus(Positions positions, double salary, double bonus, int workDays) {
        return calculateQuarterlyBonus(positions, salary, bonus, workDays, Year.now().getValue());
    }
}
