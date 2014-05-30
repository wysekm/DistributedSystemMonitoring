package pl.edu.agh.dsm.monitor.core.model.measurement.complex.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import pl.edu.agh.dsm.monitor.core.model.measurement.complex.ComplexMeasurement;
import pl.edu.agh.dsm.monitor.core.model.measurement.complex.ComplexType;
import pl.edu.agh.dsm.monitor.core.model.measurement.complex.task.annotation.Task;
import pl.edu.agh.dsm.monitor.core.model.measurement.complex.task.annotation.TaskAnnotationParser;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Created by Tom on 2014-05-29.
 */
@Component
public class TaskExecutor {

	static final Logger log = LoggerFactory.getLogger(TaskExecutor.class);

	@Autowired
	private ApplicationContext appCtx;
	private List<ComplexMeasurementTask> timeBasedTasks = new ArrayList<>();
	private List<ComplexMeasurementTask> dataBasedTasks = new ArrayList<>();

	private List<ComplexType> availableComplexTypes = new ArrayList<>();
	private Map<String, String> typeCodeBeanNameMap = new HashMap<>();

	@Scheduled(fixedDelay = 5000)
	public void updateTimeBasedTasks() {
		for(ComplexMeasurementTask task : timeBasedTasks) {
			task.update();
		}
	}

	public void updateDataBasedTasks(UUID baseMeasurementId) {
		for(ComplexMeasurementTask task : dataBasedTasks) {
			UUID baseId = task.getComplexMeasurement().getBaseMeasurementId();
			if(baseId.equals(baseMeasurementId)) {
				task.update();
			}
		}
	}

	public void createTask(ComplexMeasurement measurement) throws IllegalArgumentException {
		String beanName = typeCodeBeanNameMap.get(measurement.getType());
		Assert.notNull(beanName, "no such complex type : " + measurement.getType());
		ComplexMeasurementTask task = (ComplexMeasurementTask) appCtx.getBean(beanName, measurement);
		if(task.getUpdateType() == UpdateType.DATA_BASED) {
			dataBasedTasks.add(task);
		} else {
			timeBasedTasks.add(task);
		}
		log.debug("created a new task with id {}", measurement.getId());
	}

	public void deleteTask(UUID uuid) {
		for(ComplexMeasurementTask task : timeBasedTasks) {
			UUID id = task.getComplexMeasurement().getId();
			if(id.equals(uuid)) {
				timeBasedTasks.remove(task);
				log.debug("deleted task with id {}", id);
				return;
			}
		}
		for(ComplexMeasurementTask task : dataBasedTasks) {
			UUID id = task.getComplexMeasurement().getId();
			if(id.equals(uuid)) {
				dataBasedTasks.remove(task);
				log.debug("deleted task with id {}", id);
				return;
			}
		}
		log.warn("task ({}) was not deleted", uuid);
	}

	public List<ComplexType> getAvailableComplexTypes() {
		return availableComplexTypes;
	}

	@PostConstruct
	public void initAvailableTaskTypes() {
		String[] beanNames = appCtx.getBeanNamesForType(ComplexMeasurementTask.class, true, false);
		for (String beanName : beanNames) {
			Task task = appCtx.findAnnotationOnBean(beanName, Task.class);
			ComplexType complexType = TaskAnnotationParser.getComplexType(task);
			checkComplexType(complexType, beanName);
			typeCodeBeanNameMap.put(complexType.getTypeCode(), beanName);
			availableComplexTypes.add(complexType);
			log.info("registered complex task type: {}", complexType.getTypeCode());
		}
	}

	private void checkComplexType(ComplexType complexType, String beanName) {
		if (complexType.getTypeCode().isEmpty()) {
			throw new FatalBeanException("Task with empty taskCode : " + beanName);
		}
		if (typeCodeBeanNameMap.containsKey(complexType.getTypeCode())) {
			throw new FatalBeanException("Multiple tasks with the same code : " + complexType.getTypeCode());
		}
	}
}
