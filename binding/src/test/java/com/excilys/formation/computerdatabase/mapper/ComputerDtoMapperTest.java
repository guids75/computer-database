package com.excilys.formation.computerdatabase.mapper;

import com.excilys.formation.computerdatabase.dto.ComputerDto;
import com.excilys.formation.computerdatabase.dto.ComputerDto;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.model.Computer;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.*;

/**
 * Created by excilys on 06/01/17.
 */
public class ComputerDtoMapperTest {

    private Computer computer;
    private Computer computer2;
    private ComputerDto computerDto;
    private ComputerDto computerDto2;
    private List<Computer> listComputer;
    private List<ComputerDto> listComputerDto;
    private Locale locale;

    @Before
    public void setUp() throws Exception {
        computer = new Computer.Builder("comp1").id(1L).build();
        computer2 = new Computer.Builder("comp2").id(2L).build();
        computerDto = new ComputerDto.ComputerDtoBuilder("compdto1").id(3L).build();
        computerDto2 = new ComputerDto.ComputerDtoBuilder("compdto2").id(4L).build();
        listComputer = new ArrayList<>();
        listComputer.add(computer);
        listComputer.add(computer2);
        listComputerDto = new ArrayList<>();
        listComputerDto.add(computerDto);
        listComputerDto.add(computerDto2);
        locale = LocaleContextHolder.getLocale();
    }


    @Test
    public void computerDtoListToComputerList() throws Exception {
        List<Computer> listTest = ComputerDtoMapper.computerDtoListToComputerList(listComputerDto, locale);
        assertNotNull("computerDtoList to computerList : not null", listTest);
        assertTrue("computerDtoList to computerList : companies in", listTest.size() == 2);
    }

    @Test
    public void computerDtoToComputer() throws Exception {
        Computer computerTest = ComputerDtoMapper.computerDtoToComputer(computerDto, locale);
        assertNotNull("computerDto to computer : not null", computerTest);
        assertTrue("computerDto to computer : computer in", computerTest.getId() == computerDto.getId());
    }

    @Test
    public void computerListToComputerDtoList() throws Exception {
        List<ComputerDto> listTest = ComputerDtoMapper.computerListToComputerDtoList(listComputer, locale);
        assertNotNull("computerList to computerDtoList : not null", listTest);
        assertTrue("computerList to computerDtoList : companies in", listTest.size() == 2);
    }

    @Test
    public void computerToComputerDto() throws Exception {
        ComputerDto computerDtoTest = ComputerDtoMapper.computerToComputerDto(computer, locale);
        assertNotNull("computer to computerDto : not null", computerDtoTest);
        assertTrue("computer to computerDto : computer in", computerDtoTest.getId() == computer.getId());
    }

}