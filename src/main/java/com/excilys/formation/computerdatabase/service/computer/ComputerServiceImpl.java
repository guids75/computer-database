package com.excilys.formation.computerdatabase.service.computer;

import com.excilys.formation.computerdatabase.dto.CompanyDto;
import com.excilys.formation.computerdatabase.exception.ConnectionException;
import com.excilys.formation.computerdatabase.mapper.ComputerDtoMapper;
import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.dto.ComputerDto;
import com.excilys.formation.computerdatabase.model.Computer.ComputerBuilder;
import com.excilys.formation.computerdatabase.persistence.computer.computerImpl.ComputerDaoImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author GUIDS
 *
 */
public enum ComputerServiceImpl implements ComputerService {

  INSTANCE;
  private static final ComputerDaoImpl computerDao = 
      ComputerDaoImpl.INSTANCE; // dao for Computer to manage the computers
  private static final Logger slf4jLogger = LoggerFactory.getLogger(ComputerServiceImpl.class);

  @Override
  public ComputerDto insert(ComputerDto computerDto) {
    try {
      return ComputerDtoMapper
          .computerToComputerDto(computerDao.insert(ComputerDtoMapper.computerDtoToComputer(computerDto)));
    } catch (ConnectionException exception) {
      slf4jLogger.error("Error in ComputerService in inserty");
      slf4jLogger.error(exception.getMessage());
    }
    return null;
  }

  @Override
  public ComputerDto update(ComputerDto computerDto) {
    try {
      return ComputerDtoMapper
          .computerToComputerDto(computerDao.update(ComputerDtoMapper.computerDtoToComputer(computerDto)));
    } catch (ConnectionException exception) {
      slf4jLogger.error("Error in ComputerService in update");
      slf4jLogger.error(exception.getMessage());
    }
    return null;
  }

  @Override
  public void delete(long computerId) {
    try {
      computerDao.delete(computerId);
    } catch (ConnectionException exception) {
      slf4jLogger.error("Error in ComputerService in delete");
      slf4jLogger.error(exception.getMessage());
    }
  }

  @Override
  public List<ComputerDto> list(int nbElements, int offset) {
    try {
      return ComputerDtoMapper.computerListToComputerDtoList(computerDao.list(nbElements, offset));
    } catch (ConnectionException exception) {
      slf4jLogger.error("Error in ComputerService in list");
      slf4jLogger.error(exception.getMessage());
    }
    return null;
  }

  @Override
  public void showComputerDetails(long computerId) {
    try {
      computerDao.showComputerDetails(computerId);
    } catch (ConnectionException exception) {
      slf4jLogger.error("Error in ComputerService in showComputerDetails");
      slf4jLogger.error(exception.getMessage());
    }
  }

  @Override
  public int count(String search) {
    try {
      return computerDao.count(search);
    } catch (ConnectionException exception) {
      slf4jLogger.error("Error in ComputerService in count");
      slf4jLogger.error(exception.getMessage());
    }
    return -1;
  }

  @Override
  public List<ComputerDto> search(String search, int nbElements, int offset) {
    try {
      return ComputerDtoMapper.computerListToComputerDtoList(computerDao.search(search, nbElements, offset));
    } catch (ConnectionException exception) {
      slf4jLogger.error("Error in ComputerService in search");
      slf4jLogger.error(exception.getMessage());
    }
    return null;
  }

}
