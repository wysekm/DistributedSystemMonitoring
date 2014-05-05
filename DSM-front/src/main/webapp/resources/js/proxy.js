function proxy_create(url, method) {
    return "/proxy/?method=" + method + "&url=" + url;
}