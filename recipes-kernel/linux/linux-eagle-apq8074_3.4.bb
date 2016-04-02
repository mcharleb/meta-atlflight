# Copyright (C) 2014 Linaro
# Released under the MIT license (see COPYING.MIT for the terms)

require recipes-kernel/linux/linux-caf.inc

SRC_URI += "file://defconfig"

COMPATIBLE_MACHINE = "eagle"
SRCBRANCH = "kk_2.7"
SRCREV = "dd82ae6173b15a340cfa9beedfa8e75ee0ecd58e"
