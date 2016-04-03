SUMMARY = "PX4 Firmware build"
HOMEPAGE = "http://px4.io/"
SECTION = "console/network"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=868b4eea242622c0f210eb9a2c13f6d5"

DEPENDS = "cmake-native"

PR = "r1"

FILES_${PN} = "/home/linaro"
FILES_${PN}-adsp = "/usr/share/data/adsp"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRCREV = "2f057939ad1b0247b9191957a0d948bcae243db5"
SRC_URI = "git://github.com/PX4/Firmware.git \
	  "

S = "${WORKDIR}/git"

INSANE_SKIP_${PN}-adsp = "arch"
INSANE_SKIP_${PN}-adsp = "ldflags"
INSANE_SKIP_${PN}-adsp = "libdir"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"

PACKAGES = "${PN} ${PN}-adsp"

python () {
	error_qa = d.getVar('ERROR_QA', True)
	if 'arch' in error_qa:
		d.setVar('ERROR_QA', error_qa.replace(' arch', ''))
}

do_configure() {
}

do_compile() {
	# Set Hexagon SDK location to default install location
	export HEXAGON_TOOLS_ROOT=${HOME}/Qualcomm/HEXAGON_Tools/7.2.10/Tools
	export HEXAGON_SDK_ROOT=${HOME}/Qualcomm/Hexagon_SDK/2.0
	export HEXAGON_ARM_SYSROOT=${HOME}/Qualcomm/Hexagon_SDK/2.0/sysroot

	# build apps proc binary
	make posix_eagle_default

	# hexagon-linker doesn't handle -O1 or --hash-style=gnu
	unset LDFLAGS
	unset CFLAGS
	unset CPPFLAGS

	# build adsp libs
	make qurt_eagle_default
}

do_install() {
	# apps_proc binaries
	install -d ${D}/home/linaro 
	install -m 0755 ${S}/build_posix_eagle_default/src/firmware/posix/mainapp ${D}/home/linaro

	# adsp_proc libs
	install -d ${D}/usr/share/data/adsp
	install -m 0755 ${S}/build_qurt_eagle_default/src/firmware/qurt/libmainapp.so ${D}/usr/share/data/adsp
	install -m 0755 ${S}/build_qurt_eagle_default/src/firmware/qurt/libpx4muorb_skel.so ${D}/usr/share/data/adsp
}

