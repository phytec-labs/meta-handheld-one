#!/bin/sh

# Wait for the Weston socket to actually exist before trying to connect.
# weston.service may be "active" before the socket file is ready.
WAYLAND_SOCKET="/run/wayland-0"
TIMEOUT=30
ELAPSED=0

echo "Waiting for Weston socket at ${WAYLAND_SOCKET}..."
while [ ! -S "${WAYLAND_SOCKET}" ] && [ $ELAPSED -lt $TIMEOUT ]; do
    sleep 1
    ELAPSED=$((ELAPSED + 1))
done

if [ ! -S "${WAYLAND_SOCKET}" ]; then
    echo "Weston socket not found after ${TIMEOUT}s, aborting"
    exit 1
fi

echo "Weston socket found, starting launcher"

export WAYLAND_DISPLAY=/run/wayland-0
export XDG_RUNTIME_DIR=/run/user/0

# SDL2 GameController mapping for the PHYTEC Handheld One's MSPM0 gamepad.
# Discover GUID and verify button indices with: phytec-handheld-launcher --input-debug
# This env var is inherited by all child processes (RetroArch, games, etc.)
#
# Current mapping (pre-VID/PID kernel — name-based GUID, D-pad as buttons):
#export SDL_GAMECONTROLLERCONFIG="1800b0c45048595445432048616e6400,PHYTEC Handheld One Gamepad,a:b0,b:b1,y:b2,x:b3,leftshoulder:b4,rightshoulder:b5,back:b6,start:b7,guide:b8,dpup:b9,dpdown:b10,dpleft:b11,dpright:b12,leftx:a0,lefty:a1,rightx:a2,righty:a3,platform:Linux,"
#
# After flashing updated kernel (VID/PID set, D-pad as hat axes, ABS_Z/RZ registered):
# GUID will change — rediscover with --input-debug, then use hat notation for D-pad:
export SDL_GAMECONTROLLERCONFIG="1800b0c45048595445432048616e6400,PHYTEC Handheld One Gamepad,a:b0,b:b1,y:b2,x:b3,leftshoulder:b4,rightshoulder:b5,back:b6,start:b7,guide:b8,dpup:h0.1,dpdown:h0.4,dpleft:h0.8,dpright:h0.2,leftx:a0,lefty:a1,lefttrigger:a2,rightx:a3,righty:a4,righttrigger:a5,platform:Linux,"

exec /usr/bin/phytec-handheld-launcher
