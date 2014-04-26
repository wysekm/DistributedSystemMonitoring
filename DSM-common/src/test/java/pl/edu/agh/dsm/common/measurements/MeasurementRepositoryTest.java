package pl.edu.agh.dsm.common.measurements;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.dsm.common.dto.MeasurementDto;
import pl.edu.agh.dsm.common.measurement.MeasurementRepository;
import pl.edu.agh.dsm.common.measurement.impl.MeasurementRepositoryImpl;

import java.io.IOException;
import java.util.List;
import java.util.UUID;


public class MeasurementRepositoryTest {

    MeasurementRepository measurementRepository;
    MeasurementDto measurementDto;

    @Before
    public void setUp() {
        measurementRepository = new MeasurementRepositoryImpl();
        measurementDto = addDto();
    }

    @Test
    public void testAdd() throws IOException {
        measurementRepository.save(measurementDto);
        Assert.assertEquals(measurementDto, measurementRepository.find(measurementDto.getId()));
    }

    @Test
    public void testDelete() throws IOException {
        Predicate<MeasurementDto> precondition = Predicates.alwaysTrue();
        List<MeasurementDto> listOfMeasurements = measurementRepository.findAll(precondition);
        Assert.assertTrue(listOfMeasurements.contains(measurementDto));

        measurementRepository.remove(measurementDto.getId());
        listOfMeasurements = measurementRepository.findAll(precondition);
        Assert.assertFalse(listOfMeasurements.contains(measurementDto));
    }

    @Test
    public void testGetList() throws IOException {
        Predicate<MeasurementDto> precondition = Predicates.alwaysTrue();

        MeasurementDto measurementDto1 = addDto();
        MeasurementDto measurementDto2 = addDto();
        MeasurementDto measurementDto3 = addDto();

        List<MeasurementDto> listOfMeasurements = measurementRepository.findAll(precondition);

        Assert.assertTrue(listOfMeasurements.contains(measurementDto1));
        Assert.assertTrue(listOfMeasurements.contains(measurementDto2));
        Assert.assertTrue(listOfMeasurements.contains(measurementDto3));

    }

    public MeasurementDto addDto() {
        MeasurementDto mDto = new MeasurementDto(UUID.randomUUID(), "Host", "memUsage", "mb", "http://localhost:8085");
        measurementRepository.save(mDto);
        return mDto;
    }


}
