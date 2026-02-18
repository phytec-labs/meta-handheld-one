SUMMARY = "Freedoom: Free content for Doom-engine games"
DESCRIPTION = "The Freedoom project aims at collaboratively creating Free IWAD files."
HOMEPAGE = "https://freedoom.github.io/"
SECTION = "games"
LICENSE = "BSD-3-Clause"
# Note: License changed from GPL to BSD-3-Clause in newer versions
LIC_FILES_CHKSUM = "file://COPYING.txt;md5=8fc7d0c4df94d13fa3802cf4bc9da382"

# Use native unzip to extract the release archive
do_unpack[depends] += "unzip-native:do_populate_sysroot"

PV = "0.13.0"

SRC_URI = "https://github.com/freedoom/freedoom/releases/download/v${PV}/freedoom-${PV}.zip"
SRC_URI[sha256sum] = "3f9b264f3e3ce503b4fb7f6bdcb1f419d93c7b546f4df3e874dd878db9688f59"

# Since these are just data files, we don't need a compiler
inherit allarch

# Modern Yocto syntax (using colon instead of underscore)
FILES:${PN} = "${datadir}/games/doom/*"
FILES:${PN}-doc = "${datadir}/doc/freedoom/*"

do_install() {
    install -d ${D}${datadir}/games/doom
    install -d ${D}${datadir}/doc/freedoom

    # Install the main game files (IWADs)
    # Freedoom 0.13.0 uses these names:
    install -m 0644 ${WORKDIR}/freedoom-${PV}/freedoom1.wad ${D}${datadir}/games/doom/
    install -m 0644 ${WORKDIR}/freedoom-${PV}/freedoom2.wad ${D}${datadir}/games/doom/

    # Install documentation
    install -m 0644 ${WORKDIR}/freedoom-${PV}/README.html ${D}${datadir}/doc/freedoom/
}
