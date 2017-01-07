package com.excilys.formation.computerdatabase.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.excilys.formation.computerdatabase.dto.ComputerDto;
import com.excilys.formation.computerdatabase.dto.ComputerDto.ComputerDtoBuilder;
import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.model.Computer.Builder;

public final class ComputerDtoMapper {

    private static final String LABEL_DATEFORMATTER = "label.dateFormatter";
    
    private ComputerDtoMapper() {
    }

    /**
     * @param pListDto
     * @return
     */
    public static List<Computer> computerDtoListToComputerList(List<ComputerDto> listComputerDto, Locale locale) {
        List<Computer> computers = new ArrayList<>();
        if (listComputerDto != null) {
            listComputerDto.forEach(computerDto-> computers.add(computerDtoToComputer(computerDto,locale)));
        }
        return computers;
    }

    /**
     * @param companyDto
     * @return
     */
    public static Computer computerDtoToComputer(ComputerDto computerDto, Locale locale) {
        if (computerDto != null) {
            ResourceBundle messages = ResourceBundle.getBundle("cdb", locale);
            Builder computer = new Computer.Builder(computerDto.getName()).id(computerDto.getId());
            if (computerDto.getIntroduced() != null && !computerDto.getIntroduced().isEmpty()) {
                computer.introduced(LocalDate.parse(computerDto.getIntroduced(), DateTimeFormatter.ofPattern(messages.getString(LABEL_DATEFORMATTER))));
            }
            if (computerDto.getDiscontinued() != null && !computerDto.getDiscontinued().isEmpty()) {
                computer.discontinued(LocalDate.parse(computerDto.getDiscontinued(), DateTimeFormatter.ofPattern(messages.getString(LABEL_DATEFORMATTER))));
            }
            computer.company(new Company.Builder(computerDto.getCompanyName()).id(computerDto.getCompanyId()).build());
            return computer.build();
        }
        return null;
    }

    /**
     * @param pList
     * @return
     */
    public static List<ComputerDto> computerListToComputerDtoList(List<Computer> listComputer, Locale locale) {
        List<ComputerDto> computersDto = new ArrayList<>();
        if (listComputer != null) {
            listComputer.forEach(computer-> computersDto.add(computerToComputerDto(computer, locale)));
        }
        return computersDto;
    }

    /**
     * @param company
     * @return
     */
    public static ComputerDto computerToComputerDto(Computer computer, Locale locale) {
        if (computer != null) {
            ResourceBundle messages = ResourceBundle.getBundle("cdb", locale);
            ComputerDtoBuilder computerDto = new ComputerDto.ComputerDtoBuilder(computer.getName()).id(computer.getId());
            if (computer.getIntroduced() != null) {
                computerDto.introduced(computer.getIntroduced().format(DateTimeFormatter.ofPattern(messages.getString(LABEL_DATEFORMATTER))));
            }
            if (computer.getDiscontinued() != null) {
                computerDto.discontinued(computer.getDiscontinued().format(DateTimeFormatter.ofPattern(messages.getString(LABEL_DATEFORMATTER))));
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
