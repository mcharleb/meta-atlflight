SUMMARY = "PX4 Firmware build"
HOMEPAGE = "http://px4.io/"
SECTION = "console/network"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=868b4eea242622c0f210eb9a2c13f6d5"

#DEPENDS = "zlib"

FILES_${PN} = "/home/linaro"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRCREV = "2f057939ad1b0247b9191957a0d948bcae243db5"
SRC_URI = "git://github.com/PX4/Firmware.git \
	  "

S = "${WORKDIR}/git"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"
PACKAGES = "${PN}"

INSANE_SKIP_${PN} = "ldflags"

do_configure() {
}

do_compile() {
	export HEXAGON_TOOLS_ROOT=${HOME}/Qualcomm/HEXAGON_Tools/7.2.10/Tools
	export HEXAGON_SDK_ROOT=${HOME}/Qualcomm/Hexagon_SDK/2.0
	export HEXAGON_ARM_SYSROOT=${HOME}/Qualcomm/Hexagon_SDK/2.0/sysroot
	make posix_eagle_default
}

do_install() {
	install -d ${D}/home/linaro 
	install -m 0755 ${S}/build_posix_eagle_default/src/firmware/posix/mainapp ${D}/home/linaro
}

