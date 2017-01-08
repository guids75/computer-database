package com.excilys.formation.computerdatabase.ui.service;

import com.excilys.formation.computerdatabase.constraints.Constraints;
import com.excilys.formation.computerdatabase.dto.CompanyDto;
import com.excilys.formation.computerdatabase.dto.ComputerDto;
import com.excilys.formation.computerdatabase.mapper.ComputerDtoMapper;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.persistence.company.CompanyDao;
import com.excilys.formation.computerdatabase.persistence.computer.ComputerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Locale;

@Service
public class DatabaseService {

    private static Client client = ClientBuilder.newClient();
    private static final String BASE_URL = "http://localhost:8080/";

    @Autowired
    private CompanyDao companyDao; // dao for Computer to manage the computers
    @Autowired
    private ComputerDao computerDao; // dao for Computer to manage the computers

    public ComputerDto insert(Computer computer) {
        Locale locale = LocaleContextHolder.getLocale();
        WebTarget target = client.target(BASE_URL).path("computers/add/");
        return target.request().post(Entity.entity(ComputerDtoMapper.computerToComputerDto(computer, locale), MediaType.APPLICATION_JSON)).readEntity(new GenericType<ComputerDto>(){});
    }

    public ComputerDto update(Computer computer) {
        Locale locale = LocaleContextHolder.getLocale();
        WebTarget target = client.target(BASE_URL).path("computers/edit/");
        return target.request().put(Entity.entity(ComputerDtoMapper.computerToComputerDto(computer, locale), MediaType.APPLICATION_JSON)).readEntity(new GenericType<ComputerDto>(){});
    }

    public String deleteComputer(Constraints constraints) {
        WebTarget target = client.target(BASE_URL).path("computers/delete/" + constraints.getIdList().toString().substring(1,constraints.getIdList().toString().length()-1));
        return target.request(MediaType.APPLICATION_JSON_TYPE).delete().readEntity(String.class);
    }

    public List<ComputerDto> listComputers(Constraints constraints) {
        Locale locale = LocaleContextHolder.getLocale();
        WebTarget target = client.target(BASE_URL).path("computers/" + + constraints.getPage().getActualPage() + "/" + constraints.getPage().getNbElementsByPage());
        return target.request(MediaType.APPLICATION_JSON_TYPE).get().readEntity(new GenericType<List<ComputerDto>>(){});
    }

    public ComputerDto showComputerDetails(Long computerId) {
        Locale locale = LocaleContextHolder.getLocale();
        System.out.println(computerId);
        WebTarget target = client.target(BASE_URL).path("computers/show/" + computerId);
        return target.request(MediaType.APPLICATION_JSON_TYPE).get().readEntity(ComputerDto.class);
    }

    public int count(Constraints constraints) {
        String path = "computers/number/";
        if (constraints.getSearch() != null && !constraints.getSearch().isEmpty()) {
            path += constraints.getSearch();
        }
        WebTarget target = client.target(BASE_URL).path(path);
        return target.request(MediaType.APPLICATION_JSON_TYPE).get().readEntity(Integer.class);
    }

    public String deleteCompany(Constraints constraints) {
        WebTarget target = client.target(BASE_URL).path("companies/delete/" + constraints.getIdCompany());
               return target.request(MediaType.APPLICATION_JSON_TYPE).delete().readEntity(String.class);

    }

    public List<CompanyDto> listCompanies(Constraints constraints) {
        WebTarget target = clientt.target(BASE_URL).path("companies/" + constraints.getPage().getActualPage() + "/" + constraints.getPage().getNbElementsByPage());
        return target.request(MediaType.APPLICATION_JSON_TYPE).get().readEntity(new GenericType<List<CompanyDto>>(){});
    }


}
