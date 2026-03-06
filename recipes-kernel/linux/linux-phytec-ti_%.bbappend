FILESEXTRAPATHS:prepend := "${THISDIR}/linux-phytec-ti:"

SRC_URI:append = " \
	file://handheld_fragments.scc \
	file://0001-am62p5-fpsc-support.patch \
	file://0002-phyhandheld-support.patch \
	file://0003-add-dss.patch \
	file://0004-add-lcd197-support.patch \
	file://0005-add-mspm0.patch \
	file://0006-WIP-Nathan-s-phyhandheld-updates.patch \
	file://0001-WIP-update-button-mapping.patch \
	file://0001-WIP-backlight.patch \
	file://0001-WIP-updates-from-John.patch \
	file://0001-WIP-swap-joysticks.patch \
	file://0001-WIP-update-home-button-invert-sticks.patch \
	file://0001-WIP-buffer-update.patch \
	file://0001-WIP-MSPM0-nReset-GPIO.patch \
	file://0001-WIP-joystick-scaling.patch \
	file://0001-drivers-input-joystick-phyhandheld.c-use-HATS-for-d-.patch \
	file://0001-drivers-input-joystick-phyhandheld-makes-stick-butto.patch \
	file://0001-WIP-swap-buttons-1-and-0.patch \
	file://0001-WIP-centering.patch \
	file://0001-WIP-deadzone.patch \
"



COMPATIBLE_MACHINE  = "^("
COMPATIBLE_MACHINE .=  "phyboard-lyra"
COMPATIBLE_MACHINE .= "|phyboard-lynx"
COMPATIBLE_MACHINE .= ")$"
