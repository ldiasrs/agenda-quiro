package br.com.agendaquiro.config;


public final class PathMappings {
    public static final String BASE_PATH_MAPPING="/api/pedidoentrega";

    public static final String AUTH_MAPPING="/auth";
    public static final String CREATE_MAPPING="/create";
    public static final String ID_MAPPING="/{id}";
    public static final String INFO_MAPPING="/info";
    public static final String ADMIN_MAPPING="/admin";

    public static final String getFullPath(String path) {
        return BASE_PATH_MAPPING+path;
    }

}
