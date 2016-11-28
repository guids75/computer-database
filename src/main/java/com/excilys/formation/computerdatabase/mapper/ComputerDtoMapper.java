package com.excilys.formation.computerdatabase.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.computerdatabase.dto.ComputerDto;
import com.excilys.formation.computerdatabase.dto.ComputerDto.ComputerDtoBuilder;
import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.model.Computer.ComputerBuilder;

public final class ComputerDtoMapper {

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    private ComputerDtoMapper() {
    }

    /**
     * @param pListDto
     * @return
     */
    public static List<Computer> computerDtoListToComputerList(List<ComputerDto> listComputerDto) {
        List<Computer> computers = new ArrayList<>();
        if (listComputerDto != null) {
            for (ComputerDto computerDto : listComputerDto) {
                computers.add(computerDtoToComputer(computerDto));
            }
        }
        return computers;
    }

    /**
     * @param companyDto
     * @return
     */
    public static Computer computerDtoToComputer(ComputerDto computerDto) {
        if (computerDto != null) {
            ComputerBuilder computer = new Computer.ComputerBuilder(computerDto.getName()).id(computerDto.getId());
            if (computerDto.getIntroduced() != null) {
                computer.introduced(LocalDate.parse(computerDto.getIntroduced(), dateTimeFormatter));
            }
            if (computerDto.getDiscontinued() != null) {
                computer.discontinued(LocalDate.parse(computerDto.getDiscontinued(), dateTimeFormatter));
            }
            computer.company(new Company.CompanyBuilder(computerDto.getCompanyName()).id(computerDto.getCompanyId()).build());
            return computer.build();
        }
        return null;
    }

    /**
     * @param pList
     * @return
     */
    public static List<ComputerDto> computerListToComputerDtoList(List<Computer> listComputer) {
        List<ComputerDto> computersDto = new ArrayList<>();
        if (listComputer != null) {
            for (Computer computer : listComputer) {
                computersDto.add(computerToComputerDto(computer));
            }
        }
        return computersDto;
    }

    /**
     * @param company
     * @return
     */
    public static ComputerDto computerToComputerDto(Computer computer) {
        if (computer != null) {
            ComputerDtoBuilder computerDto = new ComputerDto.ComputerDtoBuilder(computer.getName()).id(computer.getId());
            if (computer.getIntroduced() != null) {
                computerDto.introduced(computer.getIntroduced().toString());
            }
            if (computer.getDiscontinued() != null) {
                computerDto.discontinued(computer.getDiscontinued().toString());
            }
            if (computer.getCompany() != null) {
                computerDto.companyId(computer.getCompany().getId());
                computerDto.companyName(computer.getCompany().getName());
            }
            return computerDto.build();
        }
        return null;
    }

}
