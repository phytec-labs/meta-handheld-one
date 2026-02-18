require recipes-images/images/phytec-headless-image.bb
require recipes-core/images/retro-image-minimal.bb

SUMMARY = "This image is designed to show the hardware and graphics capabilities \
           of our handheld on wayland."

IMAGE_FEATURES += "\
    splash \
    ssh-server-openssh \
    hwcodecs \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'weston', '', d)} \
"

LICENSE = "MIT"

IMAGE_INSTALL:append = " \
	freedoom \
	supertuxkart \
	neverball \
	neverball-data \
	\
	packagegroup-base \
	\
	packagegroup-gstreamer \
	\
	${@bb.utils.contains("DISTRO_FEATURES", "virtualization", "packagegroup-virtualization", "", d)} \
	${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'qtwayland qtwayland-plugins weston weston-init', '', d)} \
	${@bb.utils.contains('DISTRO_FEATURES', 'x11 wayland', 'weston-xwayland', '', d)} \
"
