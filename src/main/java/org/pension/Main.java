package org.pension;

import org.pension.model.Employee;
import org.pension.model.PensionPlan;
import org.pension.service.EmployeePlanService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        List<Employee> employees = loadSampleData();

        System.out.println("All Employees:");
        EmployeePlanService.printAllEmployee(employees);

        System.out.println("\nQuarterly Upcoming Enrollees:");
        EmployeePlanService.printNewEnrolment(employees);
    }

    private static List<Employee> loadSampleData() {
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(
                1L,
                "Daniel",
                "Agar",
                LocalDate.of(2018, 1, 17),
                105_945.50,
                new PensionPlan("EX1089", LocalDate.of(2023, 1, 17), 100.00)
        ));

        employees.add(new Employee(
                2L,
                "Benard",
                "Shaw",
                LocalDate.of(2018, 10, 3),
                197_750.00,
                null
        ));

        employees.add(new Employee(
                3L,
                "Carly",
                "Agar",
                LocalDate.of(2014, 5, 16),
                842_000.75,
                new PensionPlan("SM2307", LocalDate.of(2019, 11, 4), 1555.50)
        ));

        employees.add(new Employee(
                4L,
                "Wesley",
                "Schneider",
                LocalDate.of(2018, 11, 2),
                74_500.00,
                null
        ));

        return employees;
    }
}