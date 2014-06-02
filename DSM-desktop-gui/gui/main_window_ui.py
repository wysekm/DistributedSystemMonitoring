# -*- coding: utf-8 -*- 

import wx

from wx.lib.mixins.listctrl import ListCtrlAutoWidthMixin
from twisted.internet import reactor, task
from communication import MeasurementDataManager, VALUE_LABEL, UNIT_LABEL
from utils import show_error_window

###########################################################################
## Class MyDialog1
###########################################################################


class AutoWidthListCtrl(wx.ListCtrl, ListCtrlAutoWidthMixin):
    """Mixin class for the listrctrl with auto-sized last column"""
    def __init__(self, parent):
        wx.ListCtrl.__init__(self, parent, wx.ID_ANY, wx.DefaultPosition, wx.DefaultSize,
                             wx.LC_REPORT | wx.LC_SINGLE_SEL | wx.EXPAND)
        ListCtrlAutoWidthMixin.__init__(self)


class MainDialog(wx.Frame):
    """Class with application's main dialog"""
    def __init__(self, refreshing_delay, listing_delay):

        wx.Frame.__init__(self, None, id=wx.ID_ANY, title=u"DistributedSystemMonitoring GUI", pos=wx.DefaultPosition,
                          size=wx.DefaultSize, style=wx.DEFAULT_DIALOG_STYLE)

        self.refreshing_delay = refreshing_delay
        self.data_manager = MeasurementDataManager(listing_delay)
        self.gui_refresh_task = None
        self.current_moni_addr = None
        self.receiving_data = False
        self.set_moni_label = False

        self.SetSizeHintsSz(wx.Size(-1, -1), wx.DefaultSize)
        fgSizer1 = wx.FlexGridSizer(2, 1, 0, 0)
        fgSizer1.SetFlexibleDirection(wx.BOTH)
        fgSizer1.SetNonFlexibleGrowMode(wx.FLEX_GROWMODE_SPECIFIED)
        fgSizer2 = wx.FlexGridSizer(1, 2, 0, 0)
        fgSizer2.SetFlexibleDirection(wx.BOTH)
        fgSizer2.SetNonFlexibleGrowMode(wx.FLEX_GROWMODE_SPECIFIED)
        fgSizer2.SetMinSize(wx.Size(400, -1))
        self.m_getMoniAdrButton = wx.Button(self, wx.ID_ANY, u"Connect", wx.DefaultPosition, wx.DefaultSize, 0)
        fgSizer2.Add(self.m_getMoniAdrButton, 0, wx.ALL, 5)
        self.m_moniAddrLabel = wx.StaticText(self, wx.ID_ANY, u"No active connection", wx.DefaultPosition,
                                       wx.DefaultSize, 0)
        self.m_moniAddrLabel.Wrap(-1)
        fgSizer2.Add(self.m_moniAddrLabel, 0, wx.ALIGN_CENTER_VERTICAL | wx.ALL, 5)
        fgSizer1.Add(fgSizer2, 1, 0, 5)

        self.m_dataList = AutoWidthListCtrl(self)
        self.m_dataList.SetMinSize(wx.Size(380, 300))
        self.m_dataList.InsertColumn(0, u"Metric Name")
        self.m_dataList.SetColumnWidth(0, 140)
        self.m_dataList.InsertColumn(1, u"Resource Name")
        self.m_dataList.SetColumnWidth(1, 130)
        self.m_dataList.InsertColumn(2, u"Value")

        fgSizer1.Add(self.m_dataList, 0, wx.ALL | wx.EXPAND, 5)

        self.SetSizer(fgSizer1)
        self.Layout()
        fgSizer1.Fit(self)

        self.m_getMoniAdrButton.Bind(wx.EVT_BUTTON, self.get_monit_adr)

    def ask_for_interruption(self):
        dlg = wx.MessageDialog(self, u"Active connection has been found. In order to create next connection" + \
                                     u"the current one needs to be closed. \n\nDo you want to continue?",
                                u"Disconnect?", wx.YES_NO | wx.ICON_QUESTION)

        return dlg.ShowModal()

    def stop_listing_data(self):
        self.clear_moni_label()
        self.data_manager.stop()
        self.m_dataList.DeleteAllItems()
        if self.gui_refresh_task:
            try:
                self.gui_refresh_task.stop()
            except AssertionError:
                pass  # if the task exists but is not running
            self.gui_refresh_task = None


    def get_monit_adr(self, e):
        quit_flag = False
        entered_addr = ""

        if self.data_manager.listing_running() and self.receiving_data:
            if self.ask_for_interruption() == wx.ID_NO:
                return

        self.stop_listing_data()
        dlg = wx.TextEntryDialog(self, u"Enter catalog address: (format adr_ip:port)",
                                 u"Connection configuration:",
                                 style=wx.OK | wx.CANCEL)
        while not quit_flag:
            dlg.SetValue(entered_addr)
            if dlg.ShowModal() == wx.ID_OK:
                entered_addr = dlg.GetValue()
                if self.validate_ip4_address(entered_addr):
                    quit_flag = True
                    self.data_manager.start(*entered_addr.split(":"))
                    self.current_moni_addr = entered_addr
                    if not self.gui_refresh_task:
                        self.gui_refresh_task = task.LoopingCall(self.refesh_if_results)
                        self.gui_refresh_task.start(self.refreshing_delay, True)
                else:
                    show_error_window(self, u"Entered address is not a valid IPv4 address.")
            else:
                quit_flag = True

    def set_moni_addr_in_gui(self):
        if self.set_moni_label or not self.receiving_data:
            return

        succesful_conn_msg = u"Connected to catalog: {}"
        self.m_moniAddrLabel.SetLabel(succesful_conn_msg.format(self.current_moni_addr))
        self.set_moni_label = True

        self.refresh_gui()

    def clear_moni_label(self):
        self.set_moni_label = False
        self.receiving_data = False
        self.m_moniAddrLabel.SetLabel(u"No active connection")
        self.refresh_gui()

    def on_close(self, e):
        reactor.stop()
        self.Destroy()

    def refesh_if_results(self):
        measurement_data = self.data_manager.get_results()
        if measurement_data:
            self.substitute_gui_data(measurement_data)
            self.set_moni_addr_in_gui()
            self.refresh_gui()

    def substitute_gui_data(self, measurement_data):
        self.m_dataList.DeleteAllItems()
        gui_data_dict = self.create_gui_update_dict(measurement_data)

        if not gui_data_dict:
            return

        self.sort_data(gui_data_dict)
        if not self.receiving_data:
            self.receiving_data = True

        index = 0
        for metric_name, metric_data in gui_data_dict.iteritems():
            self.m_dataList.InsertStringItem(index, metric_name)
            index += 1
            for measurement_data in metric_data:
                pos = self.m_dataList.InsertStringItem(index, "")
                self.m_dataList.SetStringItem(pos, 1, measurement_data[0])
                self.m_dataList.SetStringItem(pos, 2, "{}{}".format(measurement_data[1], measurement_data[2]))
                index += 1

    def create_gui_update_dict(self, measurement_data):
        gui_dict = {}

        for resource_name, resource_data in measurement_data.iteritems():
            for metric_name, metric_data in resource_data.iteritems():
                if metric_name not in gui_dict:
                    gui_dict[metric_name] = []
                # try:
                if VALUE_LABEL in metric_data:
                    gui_dict[metric_name].append(
                        (resource_name, self.round_value(metric_data[VALUE_LABEL]), metric_data[UNIT_LABEL]))
                else:
                    gui_dict[metric_name].append((resource_name, "NO DATA ", ""))
                #     gui_dict = None

        return gui_dict

    @staticmethod
    def validate_ip4_address(addr):
        import re

        re1 = "((?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?))(?![\\d])"  # IPv4 IP Address 1
        re2 = "(:)"
        re3 = "(\\d+)"  # Port Number

        mon_addr_reg = re.compile(re1 + re2 + re3, re.IGNORECASE | re.DOTALL)
        return bool(mon_addr_reg.search(addr))

    @staticmethod
    def refresh_gui():
        wx.Yield()

    @staticmethod
    def round_value(value):
        return round(value, 2)

    @staticmethod
    def sort_data(data_to_sort):
        sorting_key = lambda y: y[1]
        for metrics in data_to_sort.itervalues():
            metrics.sort(key=sorting_key, reverse=True)