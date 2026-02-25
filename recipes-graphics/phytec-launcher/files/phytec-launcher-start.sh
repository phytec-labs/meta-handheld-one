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

exec /usr/bin/phytec-handheld-launcher
