package org.pension.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.pension.model.Employee;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeePlanService {

    private static final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .enable(SerializationFeature.INDENT_OUTPUT)
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    public static void printAllEmployee(List<Employee> employees) throws Exception {
        List<Employee> sorted = employees.stream()
                .sorted(Comparator.comparing(Employee::getYearlySalary).reversed()
                        .thenComparing(Employee::getLastName))
                .collect(Collectors.toList());

        System.out.println(mapper.writeValueAsString(sorted));
    }

    public static void printNewEnrolment(List<Employee> employees) throws Exception {
        LocalDate now = LocalDate.now();
        LocalDate nextQuarterStart = now.withDayOfMonth(1).plusMonths(3 - (now.getMonthValue() - 1) % 3);
        LocalDate nextQuarterEnd = nextQuarterStart.plusMonths(2).withDayOfMonth(nextQuarterStart.plusMonths(2).lengthOfMonth());

        List<Employee> enrollees = employees.stream()
                .filter(e -> e.getPensionPlan() == null)
                .filter(e -> {
                    LocalDate employmentDate = e.getEmploymentDate();
                    LocalDate threeYearsLater = employmentDate.plusYears(3);
                    return (!threeYearsLater.isBefore(nextQuarterStart) && !threeYearsLater.isAfter(nextQuarterEnd));
                })
                .sorted(Comparator.comparing(Employee::getEmploymentDate).reversed())
                .collect(Collectors.toList());

        System.out.println(mapper.writeValueAsString(enrollees));
    }
}
