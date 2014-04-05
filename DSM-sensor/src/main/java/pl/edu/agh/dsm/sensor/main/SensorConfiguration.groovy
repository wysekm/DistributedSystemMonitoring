package pl.edu.agh.dsm.sensor.main

import pl.edu.agh.dsm.sensor.iface.MonitoredResource
import groovy.util.logging.Slf4j

@Slf4j
@Singleton
class SensorConfiguration {
    public static SensorConfiguration getInstance() {
        return instance
    }

    def root
    def monitoredObjects
    int threadCount
    String sensorId
    String monitorAddress

    public void readConfigurationFile(String name) {
        //[ dragons be here
        root = new XmlParser().parseText(new File(name).text)
        sensorId = root.@id
        monitorAddress = root.configuration.monitor_address.text()
        threadCount = root.configuration.thread_count.text()
        monitoredObjects = []
        root.monitored_objects.group.each { group ->
            int interval = group.@interval.toInteger()
            group.monitored.each { monitored ->
                String id = monitored.@id
                String fullClassName;
                if (monitored.@class.contains('.')) {
                    fullClassName = monitored.@class
                } else {
                    fullClassName = "pl.edu.agh.dsm.sensor.impl.${monitored.@class}"
                }
                log.debug("Creating new SensorThread for resource class $fullClassName()")
                def rm = this.class.classLoader.loadClass(fullClassName).newInstance()
                def mo = new SensorThread(id, interval, (MonitoredResource)rm)
                monitoredObjects.add(mo)
                monitored?.param?.each { param ->
                    log.trace("Setting parameter for MonitoredResource")
                    def value;
                    if (param.@value.isInteger()) value = param.@value.toInteger()
                    else if (param.@value.isDouble()) value = param.@value.toDouble()
                    else if (param.@value.equalsIgnoreCase("true")) value = true
                    else if (param.@value.equalsIgnoreCase("false")) value = false
                    else value = param.@value
                    rm."${param.@name}" = value
                }
            }
        }
        //]
    }

    public String getSensorId() {
        return sensorId
    }

    public String getMonitorIp() {
        return monitorAddress.substring(0, monitorAddress.indexOf(':'))
    }

    public int getMonitorPort() {
        return monitorAddress.substring(monitorAddress.indexOf(':') + 1).toInteger()
    }

    public List<SensorThread> getMonitoredObjects() {
        return monitoredObjects
    }

    public int getThreadCount() {
        return threadCount;
    }
}
