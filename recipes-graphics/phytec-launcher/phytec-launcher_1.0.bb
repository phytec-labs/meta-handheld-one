SUMMARY = "LVGL 9.1 PHYTEC Handheld Launcher with SDL2"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://github.com/phytec-labs/phytec-handheld-launcher.git;protocol=https;branch=scarthgap \
           file://launcher.conf \
           file://phytec-launcher.service \
           file://phytec-launcher-start.sh \
           file://retroarch.cfg \
           https://fileshare.phytec.com/index.php/s/dKCqzTGdNkBTCN5/download/how-we-built-this.mp4;name=video \
           file://neverballrc \
           file://stk/config.xml \
           file://stk/highscore.xml \
           file://stk/input.xml \
           file://stk/players.xml \
           file://stk/server_config.xml \
           "

SRC_URI[video.sha256sum] = "345592702357fcb4243fa84e5f74607103c61185e24a711c1d96c2e1105e2d41"

FILES:${PN} += "${ROOT_HOME}/how-we-built-this.mp4 \
                ${ROOT_HOME}/.neverball-dev/neverballrc \
                ${ROOT_HOME}/.config/supertuxkart/config-0.10/config.xml \
                ${ROOT_HOME}/.config/supertuxkart/config-0.10/highscore.xml \
                ${ROOT_HOME}/.config/supertuxkart/config-0.10/input.xml \
                ${ROOT_HOME}/.config/supertuxkart/config-0.10/players.xml \
                ${ROOT_HOME}/.config/supertuxkart/config-0.10/server_config.xml \
                "

SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

DEPENDS = "virtual/libsdl2 lvgl libdrm libpng systemd"
RDEPENDS:${PN} = "libsdl2 lvgl libdrm systemd retroarch mpv"

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
        ${S}/src/settings.cpp \
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

    install -d ${D}${ROOT_HOME}
    install -m 0644 ${WORKDIR}/how-we-built-this.mp4 \
                ${D}${ROOT_HOME}/how-we-built-this.mp4

    install -d ${D}${ROOT_HOME}/.neverball-dev
    install -m 0644 ${WORKDIR}/neverballrc \
                ${D}${ROOT_HOME}/.neverball-dev/neverballrc

    install -d ${D}${ROOT_HOME}/.config/supertuxkart/config-0.10/
    install -m 0644 ${WORKDIR}/stk/config.xml \
                ${D}${ROOT_HOME}/.config/supertuxkart/config-0.10/config.xml
    install -m 0644 ${WORKDIR}/stk/highscore.xml \
                ${D}${ROOT_HOME}/.config/supertuxkart/config-0.10/highscore.xml
    install -m 0644 ${WORKDIR}/stk/input.xml \
                ${D}${ROOT_HOME}/.config/supertuxkart/config-0.10/input.xml
    install -m 0644 ${WORKDIR}/stk/players.xml \
                ${D}${ROOT_HOME}/.config/supertuxkart/config-0.10/players.xml
    install -m 0644 ${WORKDIR}/stk/server_config.xml \
                ${D}${ROOT_HOME}/.config/supertuxkart/config-0.10/server_config.xml
}
