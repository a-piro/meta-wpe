LICENSE = "CLOSED"

DEPENDS += "wpewebkit glib-2.0 libxkbcommon wayland mesa-gl"

SRCREV = "345f993d7c50f57d856663ca1bf6175883871cb0"

SRC_URI = "git://github.com/WebPlatformForEmbedded/WPEBackend-mesa.git;protocol=http;branch=master"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

PACKAGECONFIG ?= "gbm dma-buf"

PACKAGECONFIG[gbm] = "-DWPE_MESA_GBM=ON,-DWPE_MESA_GBM=OFF,"
PACKAGECONFIG[dma-buf] = "-DWPE_MESA_EXPORTABLE_DMA_BUF=ON,-DWPE_MESA_EXPORTABLE_DMA_BUF=OFF,"
PACKAGECONFIG[experimental-wayland] = "-DWPE_MESA_EXPERIMENTAL_WAYLAND_EGL=ON,-DWPE_MESA_EXPERIMENTAL_WAYLAND_EGL=OFF,"
PACKAGECONFIG[tegra] = "-DWPE_MESA_DRM_TEGRA_SUPPORT=ON,-DWPE_MESA_DRM_TEGRA_SUPPORT=OFF,"

do_install() {

	install -d ${D}${libdir}
	install -m 0755 ${B}/libWPEBackend-*.so ${D}${libdir}/

}

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/libWPEBackend-default.so ${libdir}/libWPEBackend-mesa.so"
INSANE_SKIP ="dev-so"