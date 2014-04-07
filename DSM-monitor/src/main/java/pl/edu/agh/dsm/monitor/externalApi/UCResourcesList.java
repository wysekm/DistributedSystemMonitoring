package pl.edu.agh.dsm.monitor.externalApi;

import pl.edu.agh.dsm.monitor.dto.SystemResourceDto;

import java.util.List;

public interface UCResourcesList {
    public List<SystemResourceDto> list();

}
