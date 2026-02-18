SUMMARY = "Chocolate Doom - A functional, vanilla-compatible Doom engine"
DESCRIPTION = "Chocolate Doom is a Doom source port that is minimalist and aims to be as faithful as possible to the original DOS version of Doom."
HOMEPAGE = "https://www.chocolate-doom.org/"
SECTION = "games"
LICENSE = "GPL-2.0-only"

LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

# Depend on SDL2 versions of libraries
DEPENDS = "virtual/libsdl2 libsdl2-mixer libsdl2-net libsamplerate0"

PV = "3.0.1"

SRC_URI = "https://www.chocolate-doom.org/downloads/${PV}/chocolate-doom-${PV}.tar.gz"
SRC_URI[sha256sum] = "d435d6177423491d60be706da9f07d3ab4fabf3e077ec2a3fc216e394fcfc8c7"

inherit autotools pkgconfig

TARGET_CFLAGS += "-fcommon"

# Chocolate Doom installs binaries to /usr/games by default,
# you might want to move them or add /usr/games to your PATH
# Include binaries, icons, and desktop/metadata files
FILES:${PN} += " \
    ${prefix}/games/* \
    ${datadir}/icons \
    ${datadir}/appdata \
    ${datadir}/applications \
"
