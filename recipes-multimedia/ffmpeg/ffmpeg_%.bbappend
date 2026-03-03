# Fix FFmpeg for MP4 video playback.
#
# meta-ampliphy uses --disable-everything to enforce a patent-free codec
# policy, but this also disables demuxers, parsers, protocols, and filters
# — breaking all media file I/O.  It then only re-enables decoders.
#
# We re-enable the remaining component categories (all patent-safe — patent
# concerns only apply to codecs, not container formats or I/O) and undo the
# H.264/AAC decoder disables since our handheld product ships a demo video
# (how-we-built-this.mp4) that requires them.  local.conf already sets
# LICENSE_FLAGS_ACCEPTED += "commercial".

# Re-enable component categories that --disable-everything killed.
# Demuxers, parsers, protocols, and filters are not patent-encumbered.
EXTRA_OECONF:append = " \
    --enable-demuxers \
    --enable-parsers \
    --enable-protocols \
    --enable-filters \
"

# Re-enable H.264 and AAC decoders.
EXTRA_OECONF:remove = " \
    --disable-decoder=h264 \
    --disable-decoder=aac \
"
