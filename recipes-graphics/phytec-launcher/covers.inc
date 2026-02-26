# covers.inc — Cover art deployment for phytec-launcher
#
# Installs PNG cover art from the recipe's files/covers/ directory.
# To add a new cover:
#   1. Drop a PNG into files/covers/
#   2. Add a matching file://covers/<name>.png to LAUNCHER_COVERS
#   3. Add a matching icon= line to launcher.conf

LAUNCHER_COVERS = "\
    file://covers/supertuxkart.png \
    file://covers/neverball.png \
    file://covers/neverputt.png \
    file://covers/3d-som-viewer.png \
    file://covers/glmark2.png \
    file://covers/retroarch.png \
    file://covers/freedoom1.png \
    file://covers/freedoom2.png \
"

SRC_URI:append = " ${LAUNCHER_COVERS}"

do_install:append() {
    install -d ${D}/usr/share/phytec-launcher/covers
    for f in ${WORKDIR}/covers/*.png; do
        [ -f "$f" ] && install -m 0644 "$f" \
                        ${D}/usr/share/phytec-launcher/covers/
    done
}
