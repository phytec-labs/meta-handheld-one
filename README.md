KSP-5046
============

* based on: BSP-Yoctoi-Ampliphy-AM62.PD25.1.0
* author: Garrett Giordano <ggiordano@phytec.com>
* machine: handheld-one
* yocto: scarthgap 5.0

Layer Dependencies
==================

- meta-games <git@github.com:cazfi/meta-games.git>
- meta-doom <git@github.com:geoffrey-vl/meta-doom.git>

Bluetooth Gamepad & Audio Support
==================================

- Enables Joycon support for a good chunk of bluetooth joypads including Nintendo Pro controllers.
- Brings in pipewire with alsa support for bluetooth audio output.
