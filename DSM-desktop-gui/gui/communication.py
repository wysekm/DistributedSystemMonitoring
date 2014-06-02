# imports
from functools import partial
import json
from twisted.internet.defer import Deferred
from twisted.internet.protocol import Protocol
from twisted.internet import reactor, task
from twisted.web.client import Agent

from utils import print_msg_with_class_name, show_error_window, gen_



# consts
RESOURCE_LABEL = "resource"
METRIC_LABEL = "metric"
UNIT_LABEL = "unit"
LINK_LABEL = "rest_link"
TIMESTAMP_LABEL = "timestamp"
VALUE_LABEL = "data"
COMPLEX_DETAILS_LABEL = "complexDetails"
COMPLEX_LABEL = "complex"
MESSAGE_LABEL = "message"


############################# Requesters #############################

class ObjectWithRequestAgent(object):
    """Parent class for objects with request agent"""
    def __init__(self, container):
        self.agent = Agent(reactor)
        self.container = container
        self.loop_stoper = None

    def cb_process_error(self, error):
        print_msg_with_class_name(self, "Error while processing a REST request: {}".format(error))
        print_msg_with_class_name(self, error.getTraceback())
        self.loop_stoper()
        show_error_window(None, "Connecting to monitor failed, more information in sysout.")

    def cb_process_request(self, response):
        raise NotImplementedError


class MonitorLister(ObjectWithRequestAgent):
    """Class for listing monitor's resources"""
    def __init__(self, container, measurement_lister):
        super(MonitorLister, self).__init__(container)
        self.__measurement_lister = measurement_lister

    def create_request_address(self, ip, port):
        print_msg_with_class_name(self, "Creating request address")
        addr = "http://{}:{}/measurements".format(ip, port)
        print_msg_with_class_name(self, "Request address created: {}".format(addr))
        return addr

    def send_list_request(self, ip, port):
        print_msg_with_class_name(self, "Sending REST request for listing resources")
        req_result = self.agent.request('GET', self.create_request_address(ip, port))
        req_result.addCallbacks(self.cb_process_request, self.cb_process_error)

    def cb_process_request(self, response):
        print_msg_with_class_name(self, "Request is being processed")
        finished = Deferred()
        response.deliverBody(JsonResourcesExtractor(self.container, finished))
        finished.addCallback(self.__measurement_lister.cb_get_measurement_values)
        print_msg_with_class_name(self, "Request has been succesfully processed")


class MeasurementLister(ObjectWithRequestAgent):
    """Class for listing individual measurements from monitor"""
    def __init__(self, container):
        super(MeasurementLister, self).__init__(container)

    def cb_get_measurement_values(self, result):
        for resource_data in result.itervalues():
            for metric_data in resource_data.itervalues():
                modified_callback_with_measurement_dict = partial(self.cb_update_with_received_values, metric_data)
                finished_single_measurement = Deferred()
                finished_single_measurement.addCallback(modified_callback_with_measurement_dict)
                self.send_measurement_request(metric_data, finished_single_measurement)

    def cb_update_with_received_values(self, dict_to_update, result):
        dict_to_update.update(result)

    def send_measurement_request(self, measurement, deffered_measurement):
        print_msg_with_class_name(self, "Sending REST request for getting a measurement value: {}".format(
                                                                                            measurement[LINK_LABEL]))
        req_result = self.agent.request('GET', str(measurement[LINK_LABEL]))
        modified_callback_with_measurement_dict = partial(self.process_request, deffered_measurement)
        req_result.addCallbacks(modified_callback_with_measurement_dict, self.cb_process_error)

    def process_request(self, deffered_measurement, response):
        print_msg_with_class_name(self, "Request is being processed")
        response.deliverBody(JsonMeasurementExtractor(self.container, deffered_measurement))
        print_msg_with_class_name(self, "Request has been succesfully processed")


    ############################# JSON utilities #############################

class JsonExtractor(Protocol):

    """Parent class for json extractors"""
    def __init__(self, container, finished):
        self.container = container
        self.finished = finished


class JsonResourcesExtractor(JsonExtractor):
    """Class for extracting list of resources from json"""
    def __init__(self, container, finished):
        JsonExtractor.__init__(self, container, finished)
        # self.__gen = self.__reset_gen()

    def dataReceived(self, data):
        # print_msg_with_class_name(self, "Received JSON data for extraction")
        converted_data = self.__convert_json_to_dict(data)
        analysed_data = self.__analyse_data(converted_data)
        # print_msg_with_class_name(self, "JSON data successfully extracted")
        self.finished.callback(analysed_data)
        self.container.append(analysed_data)
        # print_msg_with_class_name(self, "Received data has been successfully added to the container")

    def __convert_json_to_dict(self, data):
        # print_msg_with_class_name(self, "Converting from JSON to dict")
        json_data = json.loads(data)
        return json_data["_embedded"]["measurementDtoList"]

    def __analyse_data(self, data):
        # print_msg_with_class_name(self, "Extracting useful data from dict")
        measurements = [self.__measurement_extractor(measurement) for measurement in data]
        # print_msg_with_class_name(self, "Extracting rest links")
        measurements = map(self.__link_extractor, measurements)
        return self.__merge_data(measurements)

    def __link_extractor(self, measurement):
        measurement[LINK_LABEL] += "/data?limit=last"  # some ugly shortcut
        return measurement

    def __measurement_extractor(self, measurement):
        # ans_dict = {RESOURCE_LABEL: measurement[RESOURCE_LABEL],
        return {RESOURCE_LABEL: measurement[RESOURCE_LABEL],
                METRIC_LABEL: measurement[METRIC_LABEL],
                UNIT_LABEL: measurement[UNIT_LABEL],
                LINK_LABEL: measurement["_links"]["details"]["href"],
                # COMPLEX_LABEL:  True if COMPLEX_DETAILS_LABEL in measurement["_links"] else False
                }

        # if ans_dict[COMPLEX_LABEL]:
        # ans_dict[METRIC_LABEL] += " Complex{}".format(self.__get_next_ind())

        # return ans_dict

    # @staticmethod
    # def __reset_gen():
    #     return gen_()
    #
    # def __get_next_ind(self):
    #     return self.__gen.next()

    def __merge_data(self, measurements):
        # print_msg_with_class_name(self, "Merging extracted data")
        final_data = {}

        for measurement in measurements:
            current_resource = measurement[RESOURCE_LABEL]
            if current_resource not in final_data:
                final_data[current_resource] = {}
            elif measurement[METRIC_LABEL] in final_data[current_resource]:
                # measurement[METRIC_LABEL] += str(self.__get_next_ind())
                # measurement[METRIC_LABEL] = self.__create_indexed_name(measurement, current_resource, final_data)
                current_resource = self.__create_indexed_name(current_resource, measurement[METRIC_LABEL], final_data)
                final_data[current_resource] = {}
            final_data[current_resource].update({measurement[METRIC_LABEL]:
                                                     {UNIT_LABEL: measurement[UNIT_LABEL],
                                                      LINK_LABEL: measurement[LINK_LABEL]}})

        return final_data

    # @staticmethod
    # def __check_if_data_in_final_dict(measurement, final_dict):
    #     if

    @staticmethod
    def __create_indexed_name(cur_resource, cur_metric, final_data):
        index = 1
        temp_name = ""
        while True:
            temp_name = cur_resource + "({})".format(index)
            if temp_name not in final_data or (temp_name in final_data and cur_metric not in final_data[temp_name]):
                break
            else:
                index += 1

        return temp_name


class JsonMeasurementExtractor(JsonExtractor):
    """Class for extracting measurements from json"""
    def __init__(self, container, finished):
        JsonExtractor.__init__(self, container, finished)

    def dataReceived(self, data):
        self.finished.callback(self.__convert_json_to_dict(data))

    @staticmethod
    def __convert_json_to_dict(data):
        return json.loads(data)[0]


############################# Managing class #############################

class MeasurementDataManager(object):
    """Class for managing entire rest functionality"""
    def __init__(self, listing_delay):
        self.current_measurements = []
        self.__measurement_lister = MeasurementLister(self.current_measurements)
        self.__resource_lister = MonitorLister(self.current_measurements, self.__measurement_lister)
        self.list_call = None
        self.listing_delay = listing_delay

    def start(self, monitor_ip, monitor_port):
        self.list_call = task.LoopingCall(self.run, (monitor_ip, monitor_port))
        self.add_loop_stoper(self.__resource_lister, self.list_call.stop)
        self.list_call.start(self.listing_delay, True)

    def stop(self):
        if self.list_call:
            try:
                self.list_call.stop()
            except AssertionError:
                pass  # if the task exists but is not running
            self.list_call = None
            self.clean_measurements()

    def run(self, monitor_addr):
        self.__resource_lister.send_list_request(monitor_addr[0], monitor_addr[1])

    def listing_running(self):
        return bool(self.list_call)

    def print_results(self):
        if self.current_measurements:
            print self.current_measurements.pop()
            self.clean_measurements()

    def get_results(self):
        if self.current_measurements:
            measurements = self.current_measurements.pop()
            self.clean_measurements()
            return measurements

    def clean_measurements(self):
        self.current_measurements[:] = []

    @staticmethod
    def add_loop_stoper(obj_to_stop, loop_stoper):
        obj_to_stop.loop_stoper = loop_stoper


class ListCallbackException(Exception):
    pass

############################# MAIN - for testing #############################

if __name__ == "__main__":
    data_manager = MeasurementDataManager(2)
    data_manager.start("127.0.0.1", "8080")
    reactor.callLater(20, reactor.stop)
    reactor.run()