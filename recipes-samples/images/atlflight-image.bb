SUMMARY = "Basic ATLFLight console image"

IMAGE_FEATURES += "splash ssh-server-dropbear hwcodecs"

LICENSE = "MIT"

inherit core-image distro_features_check extrausers

CORE_IMAGE_BASE_INSTALL += " \
    coreutils gptfdisk kernel-modules 96boards-tools \
"

EXTRA_USERS_PARAMS = "\
useradd -p '' linaro; \
"
