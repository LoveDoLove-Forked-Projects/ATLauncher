/*
 * ATLauncher - https://github.com/ATLauncher/ATLauncher
 * Copyright (C) 2013-2022 ATLauncher
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.atlauncher.constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import com.atlauncher.App;
import com.atlauncher.data.LauncherVersion;
import com.atlauncher.data.ScreenResolution;
import com.atlauncher.utils.OS;

@SuppressWarnings("MutablePublicArray")
public class Constants {
    static {
        String versionFromFile;
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(App.class.getResourceAsStream("/version"), StandardCharsets.UTF_8))) {
            versionFromFile = reader.lines().collect(Collectors.joining("")).trim();
        } catch (IOException e) {
            versionFromFile = "3.0.0.0";
        }
        String[] versionParts = versionFromFile.split("\\.", 4);

        String stream = "Release";

        if (versionParts[3].endsWith(".Beta")) {
            versionParts[3] = versionParts[3].replace(".Beta", "");
            stream = "Beta";
        }

        VERSION = new LauncherVersion(Integer.parseInt(versionParts[0]), Integer.parseInt(versionParts[1]),
                Integer.parseInt(versionParts[2]), Integer.parseInt(versionParts[3]), stream,
                OS.getRunningProgramHashCode());
    }

    // Launcher config
    public static final LauncherVersion VERSION;
    public static final String LAUNCHER_NAME = "ATLauncher";
    public static final String LAUNCHER_WEBSITE = "https://atlauncher.com";
    public static final String DEFAULT_THEME_CLASS = "com.atlauncher.themes.Dark";
    public static final String GA_TRACKING_ID = "UA-88820616-7";
    public static final String CROWDIN_URL = "https://crowdin.com/project/atlauncher";
    public static final String SENTRY_DSN = "https://499c3bbc55cb434dad42a3ac670e2c91@sentry.io/1498519";

    // Launcher domains, endpoints, etc
    private static final String DEFAULT_BASE_LAUNCHER_PROTOCOL = "https://";
    private static final String DEFAULT_BASE_LAUNCHER_DOMAIN = "atlauncher.com";

    public static String BASE_LAUNCHER_PROTOCOL = DEFAULT_BASE_LAUNCHER_PROTOCOL;
    public static String BASE_LAUNCHER_DOMAIN = DEFAULT_BASE_LAUNCHER_DOMAIN;
    public static String API_BASE_URL = DEFAULT_BASE_LAUNCHER_PROTOCOL + "api." + DEFAULT_BASE_LAUNCHER_DOMAIN
            + "/v1/launcher/";
    public static String GRAPHQL_ENDPOINT = DEFAULT_BASE_LAUNCHER_PROTOCOL + "api." + DEFAULT_BASE_LAUNCHER_DOMAIN
            + "/v2/graphql";
    public static String API_HOST = "api." + DEFAULT_BASE_LAUNCHER_DOMAIN;
    public static String ANALYTICS_BASE_URL = DEFAULT_BASE_LAUNCHER_PROTOCOL + "analytics."
            + DEFAULT_BASE_LAUNCHER_DOMAIN;
    public static String PASTE_CHECK_URL = DEFAULT_BASE_LAUNCHER_PROTOCOL + "paste." + DEFAULT_BASE_LAUNCHER_DOMAIN;
    public static String PASTE_HOST = "paste." + DEFAULT_BASE_LAUNCHER_DOMAIN;
    public static String SERVERS_LIST_PACK = DEFAULT_BASE_LAUNCHER_PROTOCOL + DEFAULT_BASE_LAUNCHER_DOMAIN
            + "/servers/list/pack";
    public static String PASTE_API_URL = DEFAULT_BASE_LAUNCHER_PROTOCOL + "paste." + DEFAULT_BASE_LAUNCHER_DOMAIN
            + "/api/create-v2";

    // CDN domains, endpoints, etc
    private static final String DEFAULT_BASE_CDN_PROTOCOL = "https://";
    private static final String DEFAULT_BASE_CDN_DOMAIN = "download.nodecdn.net";
    public static final String BASE_CDN_PATH = "/containers/atl";

    public static String BASE_CDN_PROTOCOL = DEFAULT_BASE_CDN_PROTOCOL;
    public static String BASE_CDN_DOMAIN = DEFAULT_BASE_CDN_DOMAIN;
    public static String DOWNLOAD_SERVER = DEFAULT_BASE_CDN_PROTOCOL + DEFAULT_BASE_CDN_DOMAIN + BASE_CDN_PATH;
    public static String DOWNLOAD_HOST = DEFAULT_BASE_CDN_DOMAIN;

    // Mixpanel analytics
    // if you fork or modify this launcher, you must not use this token and get your
    // own
    public static final String MIXPANEL_PROJECT_TOKEN = "3cb6581cb284c1a1b6e189fef3410495";
    public static final String MIXPANEL_BASE_URL = "https://api.mixpanel.com";

    // CurseForge domains, endpoints, config, etc
    public static final String CURSEFORGE_CORE_API_URL = "https://api.curseforge.com/v1";
    // if you fork or modify this launcher, you must not use this API key and apply
    // for your own
    public static final String CURSEFORGE_CORE_API_KEY = "$2a$10$.7CSxLm/lnj5lCBSM5jGQ.3SICSX4j9r661AgoB1Rc4Nw8jCMKcv2";
    public static final String CURSEFORGE_CORE_API_HOST = "api.curseforge.com";
    public static final int CURSEFORGE_FORGE_MODLOADER_ID = 1;
    public static final int CURSEFORGE_FABRIC_MODLOADER_ID = 4;
    public static final int CURSEFORGE_QUILT_MODLOADER_ID = 5;
    public static final int CURSEFORGE_NEOFORGE_MODLOADER_ID = 6;
    public static final int CURSEFORGE_PAGINATION_SIZE = 20;
    public static final int CURSEFORGE_FABRIC_MOD_ID = 306612;
    public static final int CURSEFORGE_LEGACY_FABRIC_MOD_ID = 400281;
    public static final int CURSEFORGE_JUMPLOADER_MOD_ID = 361988;
    public static final int CURSEFORGE_SINYTRA_CONNECTOR_MOD_ID = 890127;
    public static final int CURSEFORGE_FORGIFIED_FABRIC_API_MOD_ID = 889079;
    public static final int CURSEFORGE_PLUGINS_SECTION_ID = 5;
    public static final int CURSEFORGE_MODS_SECTION_ID = 6;
    public static final int CURSEFORGE_MODPACKS_SECTION_ID = 4471;
    public static final int CURSEFORGE_RESOURCE_PACKS_SECTION_ID = 12;
    public static final int CURSEFORGE_WORLDS_SECTION_ID = 17;
    public static final int CURSEFORGE_SHADER_PACKS_SECTION_ID = 6552;

    // Modrinth domains, endpoints, config, etc
    public static final String MODRINTH_API_URL = "https://api.modrinth.com/v2";
    public static final String MODRINTH_HOST = "api.modrinth.com";
    public static final String MODRINTH_FABRIC_MOD_ID = "P7dR8mSH";
    public static final String MODRINTH_LEGACY_FABRIC_MOD_ID = "9CJED7xi";
    public static final String MODRINTH_QSL_MOD_ID = "qvIfYCYJ";
    public static final String MODRINTH_SINYTRA_CONNECTOR_MOD_ID = "u58R1TMW";
    public static final String MODRINTH_FORGIFIED_FABRIC_API_MOD_ID = "Aqlf1Shp";
    public static final int MODRINTH_PAGINATION_SIZE = 20;

    // FTB domains, endpoints, config, etc
    public static final String FTB_API_URL = "https://api.feed-the-beast.com/v1/modpacks/public";
    public static final String FTB_HOST = "api.feed-the-beast.com";
    public static final int FTB_PAGINATION_SIZE = 20;

    // Technic domains, endpoints, config, etc
    public static final String TECHNIC_API_URL = "https://api.technicpack.net";
    public static final String TECHNIC_HOST = "api.technicpack.net";
    public static final int TECHNIC_PAGINATION_SIZE = 20;

    // Forge domains, endpoints, etc
    public static final String FORGE_MAVEN = "https://maven.minecraftforge.net/net/minecraftforge/forge";
    public static final String FORGE_PROMOTIONS_FILE = "https://files.minecraftforge.net/net/minecraftforge/forge/promotions_slim.json";
    public static final String FORGE_MAVEN_BASE = "https://maven.minecraftforge.net/";
    public static final String FORGE_HOST = "maven.minecraftforge.net";
    public static final String FORGE_OLD_MAVEN_BASE = "https://files.minecraftforge.net/maven/";

    // Fabric domains, endpoints, etc
    public static final String FABRIC_MAVEN = "https://maven.fabricmc.net/";
    public static final String FABRIC_HOST = "maven.fabricmc.net";

    // Legacy Fabric domains, endpoints, etc
    public static final String LEGACY_FABRIC_MAVEN = "https://maven.legacyfabric.net/";
    public static final String LEGACY_FABRIC_HOST = "maven.legacyfabric.net";

    // NeoForge domains, endpoints, etc
    public static final String NEOFORGE_MAVEN = "https://maven.neoforged.net/";
    public static final String NEOFORGE_HOST = "maven.neoforged.net";

    // Quilt domains, endpoints, etc
    public static final String QUILT_MAVEN = "https://maven.quiltmc.org/repository/release/";
    public static final String QUILT_HOST = "maven.quiltmc.org";

    // Minecraft domains, endpoints, etc
    public static final String LAUNCHER_META_MINECRAFT = "https://launchermeta.mojang.com";
    public static final String MINECRAFT_LIBRARIES = "https://libraries.minecraft.net/";
    public static final String MINECRAFT_RESOURCES = "https://resources.download.minecraft.net";
    public static final String MINECRAFT_VERSION_MANIFEST_URL = LAUNCHER_META_MINECRAFT
            + "/mc/game/version_manifest.json";
    public static final String MINECRAFT_JAVA_RUNTIME_URL = LAUNCHER_META_MINECRAFT
            + "/v1/products/java-runtime/2ec0cc96c44e5a76b9c8b7c39df7210883d12871/all.json";
    public static final int MINECRAFT_DEFAULT_SERVER_PORT = 25565;

    // Misc
    public static final String LEGACY_JAVA_FIXER_URL = "https://cdn.atlcdn.net/legacyjavafixer-1.0.jar";
    public static final String LEGACY_JAVA_FIXER_MD5 = "12c337cb2445b56b097e7c25a5642710";
    public static final String[] DATE_FORMATS = { "dd/MM/yyyy", "MM/dd/yyyy", "yyyy/MM/dd", "dd MMMM yyyy",
            "dd-MM-yyyy", "MM-dd-yyyy", "yyyy-MM-dd" };
    // instance name, pack name, pack version, minecraft version
    public static final String[] INSTANCE_TITLE_FORMATS = { "%1$s (%2$s %3$s)", "%1$s", "%1$s (%4$s)", "%1$s (%3$s)" };

    public static final ScreenResolution[] SCREEN_RESOLUTIONS = {
            new ScreenResolution(854, 480),
            new ScreenResolution(1280, 720),
            new ScreenResolution(1366, 768),
            new ScreenResolution(1600, 900),
            new ScreenResolution(1920, 1080),
            new ScreenResolution(2560, 1440),
            new ScreenResolution(3440, 1440),
            new ScreenResolution(3840, 2160)
    };

    public static final String DEFAULT_JAVA_PARAMETERS = "-XX:+UnlockExperimentalVMOptions -XX:+UseG1GC -XX:G1NewSizePercent=20 -XX:G1ReservePercent=20 -XX:MaxGCPauseMillis=50 -XX:G1HeapRegionSize=32M";

    // Custom for ATLauncher Microsoft login constants
    // if you fork or modify this launcher, you must not use this Client ID
    public static final String MICROSOFT_LOGIN_CLIENT_ID = "90890812-00d1-48a8-8d3f-38465ef43b58";
    public static final int MICROSOFT_LOGIN_REDIRECT_PORT = 28562;
    public static final String MICROSOFT_LOGIN_REDIRECT_URL = "http://127.0.0.1:" + MICROSOFT_LOGIN_REDIRECT_PORT;
    public static final String MICROSOFT_LOGIN_REDIRECT_URL_ENCODED = "http%3A%2F%2F127.0.0.1%3A"
            + MICROSOFT_LOGIN_REDIRECT_PORT;
    public static final String[] MICROSOFT_LOGIN_SCOPES = { "XboxLive.signin", "XboxLive.offline_access" };

    // General Microsoft login constants
    public static final String MICROSOFT_LOGIN_URL = "https://login.live.com/oauth20_authorize.srf" + "?client_id="
            + MICROSOFT_LOGIN_CLIENT_ID
            + "&prompt=select_account&cobrandid=8058f65d-ce06-4c30-9559-473c9275a65d&response_type=code" + "&scope="
            + String.join("%20", MICROSOFT_LOGIN_SCOPES) + "&redirect_uri=" + MICROSOFT_LOGIN_REDIRECT_URL_ENCODED;
    public static final String MICROSOFT_AUTH_TOKEN_URL = "https://login.live.com/oauth20_token.srf";
    public static final String MICROSOFT_XBL_AUTH_TOKEN_URL = "https://user.auth.xboxlive.com/user/authenticate";
    public static final String MICROSOFT_XSTS_AUTH_TOKEN_URL = "https://xsts.auth.xboxlive.com/xsts/authorize";
    public static final String MICROSOFT_MINECRAFT_LOGIN_URL = "https://api.minecraftservices.com/launcher/login";
    public static final String MICROSOFT_MINECRAFT_PROFILE_URL = "https://api.minecraftservices.com/minecraft/profile";
    public static final String MICROSOFT_MINECRAFT_ENTITLEMENTS_URL = "https://api.minecraftservices.com/entitlements/license";

    public static void setBaseLauncherDomain(String baseLauncherDomain) {
        String host = baseLauncherDomain.replace("https://", "").replace("http://", "");

        BASE_LAUNCHER_PROTOCOL = baseLauncherDomain.startsWith("https://") ? "https://" : "http://";
        BASE_LAUNCHER_DOMAIN = host;
        API_BASE_URL = BASE_LAUNCHER_PROTOCOL + "api." + host + "/v1/launcher/";
        API_HOST = "api." + host;
        PASTE_CHECK_URL = BASE_LAUNCHER_PROTOCOL + "paste." + host;
        PASTE_HOST = "paste." + host;
        SERVERS_LIST_PACK = BASE_LAUNCHER_PROTOCOL + host + "/servers/list/pack";
        PASTE_API_URL = BASE_LAUNCHER_PROTOCOL + "paste." + host + "/api/create-v2";
    }

    public static void setBaseCdnDomain(String baseCdnDomain) {
        String host = baseCdnDomain.replace("https://", "").replace("http://", "");

        BASE_CDN_PROTOCOL = baseCdnDomain.startsWith("https://") ? "https://" : "http://";
        BASE_CDN_DOMAIN = host;
        DOWNLOAD_SERVER = baseCdnDomain + BASE_CDN_PATH;
        DOWNLOAD_HOST = host;
    }

    public static void setBaseCdnPath(String baseCdnPath) {
        DOWNLOAD_SERVER = BASE_CDN_PROTOCOL + BASE_CDN_DOMAIN + baseCdnPath;
    }
}
