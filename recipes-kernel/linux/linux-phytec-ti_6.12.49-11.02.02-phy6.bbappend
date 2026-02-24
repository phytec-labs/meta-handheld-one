FILESEXTRAPATHS:prepend := "${THISDIR}/linux-phytec-ti:"

SRC_URI:append = " \
	file://handheld_fragments.scc \
"

COMPATIBLE_MACHINE  = "^("
COMPATIBLE_MACHINE .=  "phyboard-lyra"
COMPATIBLE_MACHINE .= "|phyboard-lynx"
COMPATIBLE_MACHINE .= ")$"
