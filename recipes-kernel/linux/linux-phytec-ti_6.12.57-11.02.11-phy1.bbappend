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
"

COMPATIBLE_MACHINE  = "^("
COMPATIBLE_MACHINE .=  "phyboard-lyra"
COMPATIBLE_MACHINE .= "|phyboard-lynx"
COMPATIBLE_MACHINE .= ")$"
