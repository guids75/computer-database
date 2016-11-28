package com.excilys.formation.computerdatabase.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.computerdatabase.dto.ComputerDto;
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
        ComputerBuilder computer = new Computer.ComputerBuilder(computerDto.getName()).id(computerDto.getId()).company(
                new Company.CompanyBuilder(computerDto.getCompanyName()).id(computerDto.getCompanyId()).build());
        if (computerDto.getIntroduced() != null) {
            computer.introduced(LocalDate.parse(computerDto.getIntroduced(), dateTimeFormatter));
        }
        if (computerDto.getDiscontinued() != null) {
            computer.discontinued(LocalDate.parse(computerDto.getDiscontinued(), dateTimeFormatter));
        }
        return computer.build();
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
        ComputerDto computerDto = new ComputerDto();
        computerDto.setName(computer.getName());
        computerDto.setId(computer.getId());
        if (computer.getIntroduced() != null) {
            computerDto.setIntroduced(computer.getIntroduced().toString());
        }
        if (computer.getDiscontinued() != null) {
            computerDto.setDiscontinued(computer.getDiscontinued().toString());
        }
        computerDto.setCompanyId(computer.getCompany().getId());
        computerDto.setCompanyName(computer.getCompany().getName());
        return computerDto;
    }

}
