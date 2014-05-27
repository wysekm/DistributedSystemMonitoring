#!/usr/bin/env python

import wx
from twisted.internet import wxreactor
wxreactor.install()

import argparse
from twisted.internet import reactor
from utils import check_uint
from main_window_ui import MainDialog


if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="GUI for the Distributed System Monitoring project.")
    parser.add_argument("-l", "--listingdelay", type=check_uint, default=5,
                    help="The number of second between sending REST requests in uint")
    parser.add_argument("-r", "--refreshingdelay", type=check_uint, default=5,
                    help="The number of second between refreshing GUI in uint")
    args = parser.parse_args()
    app = wx.PySimpleApp()
    main_dialog = MainDialog(args.listingdelay, args.refreshingdelay)
    main_dialog.Show(True)
    reactor.registerWxApp(app)
    reactor.run()