require recipes-images/images/phytec-headless-image.bb
require recipes-core/images/retro-image-minimal.bb

SUMMARY = "This image is designed to show the hardware and graphics capabilities \
           of our handheld on wayland."

IMAGE_FEATURES += "\
    ssh-server-openssh \
    hwcodecs \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'weston', '', d)} \
"

LICENSE = "MIT"

IMAGE_INSTALL:append = " \
	freedoom \
	chocolate-doom \
	supertuxkart \
	neverball \
	neverball-data \
	phytec-launcher \
	\
	packagegroup-base \
	\
	packagegroup-gstreamer \
	gstreamer1.0-libav \
	\
	${@bb.utils.contains("DISTRO_FEATURES", "virtualization", "packagegroup-virtualization", "", d)} \
	${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'qtwayland qtwayland-plugins weston weston-init', '', d)} \
	${@bb.utils.contains('DISTRO_FEATURES', 'x11 wayland', 'weston-xwayland', '', d)} \
"

#deploy meta-example lvgl demo based on machine
IMAGE_INSTALL:append:phyboard-lynx = " sdl2-am62p-ew-3d-demo"
IMAGE_INSTALL:append:phyboard-lyra = " sdl2-am62p-ew-3d-demo"

# Disable Vulkan drivers if Vulkan is not supported by the hardware
IMAGE_INSTALL:remove = "${@bb.utils.contains('DISTRO_FEATURES', 'vulkan', '', 'mesa-vulkan-drivers', d)}"
