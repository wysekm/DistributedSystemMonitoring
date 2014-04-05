package pl.edu.agh.dsm.sensor.impl

import org.junit.runner.RunWith
import org.spockframework.runtime.Sputnik
import spock.lang.Specification

import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@RunWith(Sputnik)
class CpuUsageTest extends Specification {


    def "CPU usage should be percent represented as double value between 0.0 and 1.0"() {
        expect:
        result >= 0 && result <= 1.0

        where:
        result << runNCPUChecks(200)
    }

    private def runNCPUChecks(int n) {
        ExecutorService es = Executors.newFixedThreadPool(50)
        def futures = []
        def ret = []
        for (int i = 0; i < n; ++i) {
            futures.add(es.submit(new Callable<Double>() {
                @Override
                Double call() throws Exception {
                    return new CpuUsage().checkResource()
                }
            }))
        }
        futures.collect { future ->
            future.get()
        }
    }

}
