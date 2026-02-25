SUMMARY = "LVGL 9.1 PHYTEC Handheld Launcher with SDL2"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://github.com/phytec-labs/phytec-handheld-launcher.git;protocol=https;branch=scarthgap \
           file://launcher.conf \
           file://weston.ini \
           "

SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

DEPENDS = "virtual/libsdl2 lvgl libdrm"
RDEPENDS:${PN} = "libsdl2 lvgl libdrm"

do_compile() {
    ${CXX} ${CXXFLAGS} ${S}/src/main.cpp \
        -I${STAGING_INCDIR} \
        -o phytec-handheld-launcher \
        -lSDL2 -llvgl -ldrm -lm \
        ${LDFLAGS}
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 phytec-handheld-launcher ${D}${bindir}

    install -d ${D}${sysconfdir}/phytec-launcher
    install -m 0644 ${WORKDIR}/launcher.conf \
                    ${D}${sysconfdir}/phytec-launcher/launcher.conf

    install -d ${D}/usr/share/phytec-launcher
    install -m 0644 ${S}/assets/loading.png \
                    ${D}/usr/share/phytec-launcher/loading.png

    install -d ${D}${sysconfdir}/xdg/weston
    install -m 0644 ${WORKDIR}/weston.ini \
                    ${D}${sysconfdir}/xdg/weston/weston.ini
}
