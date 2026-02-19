FILESEXTRAPATHS:prepend := "${THISDIR}/linux-phytec-ti:"

SRC_URI:append = " \
	file://bluetooth_controller.scc \
"

COMPATIBLE_MACHINE  = "^("
COMPATIBLE_MACHINE .=  "phyboard-lynx"
COMPATIBLE_MACHINE .= ")$"
