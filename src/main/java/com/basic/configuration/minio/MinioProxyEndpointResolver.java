package com.basic.configuration.minio;

import com.basic.property.StorageProperty;
import com.basic.util.ServletUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * 根据当前请求域名解析 MinIO 对外访问地址。
 */
@Component
@RequiredArgsConstructor
public class MinioProxyEndpointResolver {

    private final StorageProperty storageProperty;

    public String resolve() {
        HttpServletRequest request = ServletUtils.getRequest();
        if (request == null || CollectionUtils.isEmpty(storageProperty.getProxyEndpoints())) {
            return storageProperty.getProxyEndpoint();
        }

        Set<String> requestHosts = getRequestHosts(request);
        for (Map.Entry<String, String> entry : storageProperty.getProxyEndpoints().entrySet()) {
            String configuredHost = normalizeHost(entry.getKey());
            if (StringUtils.isNotBlank(configuredHost) && requestHosts.contains(configuredHost)) {
                return entry.getValue();
            }
        }
        return storageProperty.getProxyEndpoint();
    }

    private Set<String> getRequestHosts(HttpServletRequest request) {
        Set<String> hosts = new LinkedHashSet<>();
        addForwardedHosts(hosts, request.getHeader("X-Forwarded-Host"));
        addHost(hosts, request.getHeader("Host"));
        addHost(hosts, request.getServerName());
        addHost(hosts, request.getHeader("Origin"));
        addHost(hosts, request.getHeader("Referer"));
        return hosts;
    }

    private void addForwardedHosts(Set<String> hosts, String headerValue) {
        if (StringUtils.isBlank(headerValue)) {
            return;
        }
        for (String host : headerValue.split(",")) {
            addHost(hosts, host);
        }
    }

    private void addHost(Set<String> hosts, String value) {
        String host = normalizeHost(value);
        if (StringUtils.isNotBlank(host)) {
            hosts.add(host);
        }
    }

    private String normalizeHost(String value) {
        if (StringUtils.isBlank(value)) {
            return "";
        }

        String host = value.trim();
        if (host.contains("://")) {
            try {
                URI uri = new URI(host);
                host = uri.getHost();
            } catch (URISyntaxException ignored) {
                host = host.substring(host.indexOf("://") + 3);
            }
        }

        int pathIndex = host.indexOf("/");
        if (pathIndex >= 0) {
            host = host.substring(0, pathIndex);
        }

        int portIndex = host.lastIndexOf(":");
        if (portIndex > 0 && host.indexOf(":") == portIndex) {
            host = host.substring(0, portIndex);
        }

        return host.toLowerCase(Locale.ROOT);
    }
}
