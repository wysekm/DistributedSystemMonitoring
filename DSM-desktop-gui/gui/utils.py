import argparse
import wx


def print_msg_with_class_name(obj, msg):
    print "{} - {}".format(obj.__class__.__name__, msg)


def show_error_window(parent, msg):
    dlg = wx.MessageDialog(parent, msg, u"Error", wx.OK | wx.ICON_ERROR)
    dlg.ShowModal()
    dlg.Destroy()


def check_uint(value):
    ivalue = int(value)
    if ivalue < 0:
        raise argparse.ArgumentTypeError("{} is an invalid positive int value".format(value))
    return ivalue


def gen_():
    index = 0
    while True:
        index += 1
        yield index