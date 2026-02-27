SUMMARY = "LVGL 9.1 PHYTEC Handheld Launcher with SDL2"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://github.com/phytec-labs/phytec-handheld-launcher.git;protocol=https;branch=claude/pedantic-hofstadter \
           file://launcher.conf \
           file://phytec-launcher.service \
           file://phytec-launcher-start.sh \
           file://retroarch.cfg \
           "

SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

DEPENDS = "virtual/libsdl2 lvgl libdrm libpng systemd"
RDEPENDS:${PN} = "libsdl2 lvgl libdrm systemd retroarch"

inherit systemd

SYSTEMD_SERVICE:${PN} = "phytec-launcher.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

require covers.inc

do_compile() {
    ${CXX} ${CXXFLAGS} \
        ${S}/src/main.cpp \
        ${S}/src/config.cpp \
        ${S}/src/launcher.cpp \
        ${S}/src/input.cpp \
        ${S}/src/ui.cpp \
        -I${STAGING_INCDIR} \
        -I${STAGING_INCDIR}/lvgl \
        -I${S}/src \
        -o phytec-handheld-launcher \
        -lSDL2 -llvgl -lpng -ldrm -lm \
        ${LDFLAGS}
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 phytec-handheld-launcher ${D}${bindir}
    install -m 0755 ${WORKDIR}/phytec-launcher-start.sh ${D}${bindir}

    install -d ${D}${sysconfdir}/phytec-launcher
    install -m 0644 ${WORKDIR}/launcher.conf \
                    ${D}${sysconfdir}/phytec-launcher/launcher.conf

    install -d ${D}/usr/share/phytec-launcher
    install -m 0644 ${S}/assets/loading.png \
                    ${D}/usr/share/phytec-launcher/loading.png

    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/phytec-launcher.service \
                    ${D}${systemd_system_unitdir}/phytec-launcher.service

    install -d ${D}${sysconfdir}/retroarch
    install -m 0644 ${WORKDIR}/retroarch.cfg \
                ${D}${sysconfdir}/retroarch/retroarch.cfg
}
