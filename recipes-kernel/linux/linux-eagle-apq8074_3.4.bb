# Copyright (C) 2014 Linaro
# Released under the MIT license (see COPYING.MIT for the terms)

require recipes-kernel/linux/linux-caf.inc

SRC_URI += "file://defconfig"

COMPATIBLE_MACHINE = "eagle"
SRCBRANCH = "v4.2.6"
SRCREV = "AU_LINUX_ANDROID_KK_2.7_RB1.04.04.02.007.041"
