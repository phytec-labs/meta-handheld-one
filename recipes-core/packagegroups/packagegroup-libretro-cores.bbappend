# Remove problematic cores from the list
LIBRETRO_CORE_PACKAGES:remove = " \
	ppsspp-libretro \
	vecx-libretro \
	dolphin-libretro \
	pcsx2-libretro \
	fbneo-libretro \
"

# Disable heavy platform groups for AM62x
LIBRETRO_TARGET_PLATFORMS:remove = " \
	ps2 \
	ngc \
	wii \
"
