package ru.arkhipov.MySecondTestAppSpringBoot.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import ru.arkhipov.MySecondTestAppSpringBoot.model.Positions;

import java.time.Year;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnnualBonusServiceImplTest {

    private final AnnualBonusServiceImpl service = new AnnualBonusServiceImpl();

    // --- Годовая премия ---

    @Test
    void calculate() {
        // given
        Positions position = Positions.HR;
        double bonus = 2.0;
        int workDays = 243;
        double salary = 100000.00;

        // when
        double result = service.calculate(position, salary, bonus, workDays, 2025);

        // then (2025 - обычный год, 365 дней)
        double expected = 360493.8271604938;
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void calculateInLeapYear() {
        // when (2024 - високосный год, 366 дней)
        double result = service.calculate(Positions.HR, 100000.00, 2.0, 243, 2024);

        // then
        double expected = 361481.48148148146;
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void leapYearGivesBiggerBonusThanCommonYear() {
        double leap = service.calculate(Positions.DEV, 100000.00, 2.0, 243, 2024);
        double common = service.calculate(Positions.DEV, 100000.00, 2.0, 243, 2025);

        assertThat(leap).isGreaterThan(common);
    }

    @Test
    void calculateWithoutYearUsesCurrentYear() {
        double expected = service.calculate(Positions.HR, 100000.00, 2.0, 243, Year.now().getValue());

        double result = service.calculate(Positions.HR, 100000.00, 2.0, 243);

        assertThat(result).isEqualTo(expected);
    }

    // --- Квартальная премия ---

    @Test
    void calculateQuarterlyBonusForManager() {
        // given
        Positions position = Positions.TL;

        // when
        double result = service.calculateQuarterlyBonus(position, 100000.00, 2.0, 243, 2025);

        // then (четверть годовой премии 781069.9588477366)
        double expected = 195267.48971193415;
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void quarterlyBonusIsQuarterOfAnnualBonus() {
        double annual = service.calculate(Positions.CTO, 150000.00, 1.5, 240, 2024);

        double quarterly = service.calculateQuarterlyBonus(Positions.CTO, 150000.00, 1.5, 240, 2024);

        assertThat(quarterly).isEqualTo(annual / 4);
    }

    @Test
    void calculateQuarterlyBonusForNotManagerThrowsException() {
        assertThatThrownBy(() -> service.calculateQuarterlyBonus(Positions.DEV, 100000.00, 2.0, 243, 2025))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("DEV");

        assertThatThrownBy(() -> service.calculateQuarterlyBonus(Positions.HR, 100000.00, 2.0, 243, 2025))
                .isInstanceOf(IllegalArgumentException.class);
    }

    // --- Признак управленца ---

    @Test
    void managerFlagIsSetOnlyForManagers() {
        assertThat(Positions.TL.isManager()).isTrue();
        assertThat(Positions.PO.isManager()).isTrue();
        assertThat(Positions.TPM.isManager()).isTrue();
        assertThat(Positions.CTO.isManager()).isTrue();

        assertThat(Positions.DEV.isManager()).isFalse();
        assertThat(Positions.HR.isManager()).isFalse();
    }
}