package com.excilys.formation.computerdatabase.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Computer;

public final class ComputerJdbcMapper implements RowMapper<Computer> {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd"); // to convert localdate to date

    public Computer mapRow(ResultSet results, int rowNum) throws SQLException {
        Computer.Builder computer = new Computer.Builder(results.getString("computerName"))
                .id(results.getLong("computerId"));
        if (results.getDate("computer.introduced") != null) {
            computer.introduced(LocalDate.parse(simpleDateFormat.format(results.getDate("computer.introduced"))));
        }
        if (results.getDate("computer.discontinued") != null) {
            computer.discontinued(
                    LocalDate.parse(simpleDateFormat.format(results.getDate("computer.discontinued"))));
        }
        if (results.getLong("companyId") != 0 && results.getString("companyName") != null) {
            computer.company(new Company.Builder(results.getString("companyName"))
                    .id(results.getLong("companyId")).build());
        }
        return computer.build();
    }
}