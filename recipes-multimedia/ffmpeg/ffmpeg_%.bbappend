# Re-enable H.264 and AAC decoders for MP4 video playback.
#
# meta-ampliphy enforces a patent-free-only codec policy by disabling these
# in its own ffmpeg bbappend.  Our handheld product ships a demo video
# (how-we-built-this.mp4) that requires H.264+AAC, and local.conf already
# sets LICENSE_FLAGS_ACCEPTED += "commercial".
#
# We only undo the specific decoder disables we need — the rest of
# meta-ampliphy's patent-free policy remains intact.

EXTRA_OECONF:remove = " \
    --disable-decoder=h264 \
    --disable-decoder=aac \
"
