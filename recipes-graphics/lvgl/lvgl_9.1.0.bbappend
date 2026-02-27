DEPENDS += "libpng"

# Enable POSIX filesystem driver, libpng decoder, and system malloc in
# lv_conf.h.  These sed rules run after the base lv-conf.inc pass so they
# always take effect regardless of PACKAGECONFIG selection.
#
# LV_USE_STDLIB_MALLOC  LV_STDLIB_CLIB  — use system malloc/free instead of
#   the built-in 64 KB pool (which cannot hold decoded PNG pixel data).
do_configure:append() {
    sed -r \
        -e "s|^([[:space:]]*#define LV_USE_STDLIB_MALLOC[[:space:]]).*|\1 LV_STDLIB_CLIB|" \
        -e "s|^([[:space:]]*#define LV_USE_FS_POSIX[[:space:]]).*|\1 1|" \
        -e "s|^([[:space:]]*#define LV_FS_POSIX_LETTER[[:space:]]).*|\1 'A'|" \
        -e "s|^([[:space:]]*#define LV_FS_POSIX_PATH[[:space:]]).*|\1 \"/\"|" \
        -e "s|^([[:space:]]*#define LV_USE_LIBPNG[[:space:]]).*|\1 1|" \
        -e "s|^([[:space:]]*#define LV_FONT_MONTSERRAT_20[[:space:]]).*|\1 1|" \
        -i "${S}/lv_conf.h"
}
