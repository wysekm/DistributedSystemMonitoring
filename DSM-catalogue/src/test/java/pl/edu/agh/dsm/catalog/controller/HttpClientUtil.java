package pl.edu.agh.dsm.catalog.controller;

import java.io.IOException;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientUtil {

	static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

	public static class HttpResponseResult {
		public String resultContent;
		public StatusLine statusLine;

		public HttpResponseResult() {
		}

		public HttpResponseResult(StatusLine statusLine) {
			super();
			this.statusLine = statusLine;
		}
	}

	public static HttpResponseResult makePostRequest(String uri,
			HttpEntity data, Credentials userCredential) throws IOException {
		HttpPost httppost = new HttpPost(uri);
		httppost.setEntity(data);
		return makeRequest(httppost, userCredential);
	}

	public static HttpResponseResult makeGetRequest(String uri,
			Credentials userCredential) throws IOException {
		HttpGet httpget = new HttpGet(uri);
		return makeRequest(httpget, userCredential);
	}

	public static HttpResponseResult makeDeleteRequest(String uri,
			Credentials userCredential) throws IOException {
		HttpDelete httpdelete = new HttpDelete(uri);
		return makeRequest(httpdelete, userCredential);
	}

	private static CredentialsProvider prepareCredenitalsProvider(URI uri,
			Credentials credentials) throws IOException {
		if (credentials == null) {
			return null;
		}
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(
				new AuthScope(uri.getHost(), uri.getPort()), credentials);
		return credsProvider;
	}

	private static HttpResponseResult makeRequest(HttpUriRequest request,
			Credentials credentials) throws IOException {
		HttpClientBuilder clientBuilder = HttpClients.custom();
		if (credentials != null) {
			CredentialsProvider credsProvider = prepareCredenitalsProvider(
					request.getURI(), credentials);
			clientBuilder.setDefaultCredentialsProvider(credsProvider);
		}
		CloseableHttpClient httpclient = clientBuilder.build();
		HttpResponseResult result = null;
		try {
			logger.debug("Executing request {}", request.getRequestLine());
			CloseableHttpResponse response = httpclient.execute(request);
			try {
				StatusLine statusLine = response.getStatusLine();
				result = new HttpResponseResult(statusLine);
				HttpEntity entity = response.getEntity();
				result.resultContent = EntityUtils.toString(entity);
				EntityUtils.consume(entity);
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}
		return result;
	}
}
