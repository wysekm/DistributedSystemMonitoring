package pl.edu.agh.dsm.monitor.externalApi.impl;

import org.springframework.beans.factory.annotation.Autowired;

import pl.edu.agh.dsm.common.annotations.UseCase;
import pl.edu.agh.dsm.common.dto.SystemResourceDto;
import pl.edu.agh.dsm.common.measurement.SystemResourceRepository;
import pl.edu.agh.dsm.monitor.externalApi.UCResourcesList;

import java.util.List;

@UseCase("UC_PF_MT4E")
public class UCResourcesListImpl implements UCResourcesList {


    @Autowired
    SystemResourceRepository systemResourceRepository;

    @Override
    public List<SystemResourceDto> list() {
        return systemResourceRepository.findAll();
    }
}
