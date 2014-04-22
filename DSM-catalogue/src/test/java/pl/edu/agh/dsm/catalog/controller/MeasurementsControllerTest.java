package pl.edu.agh.dsm.catalog.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

//so that testDelete is after testAdd
@FixMethodOrder(MethodSorters.NAME_ASCENDING)	
public class MeasurementsControllerTest {
	
	@Test
	public void testGetList() throws IOException {
		checkMockServer();
		HttpResponseResult result = makeGetRequest("http://localhost:8080/measurements");
		Assert.assertEquals(HttpStatus.SC_OK, result.statusLine.getStatusCode());
		Assert.assertFalse(result.resultContent.isEmpty());
	}

	@Test
	public void testAdd() throws IOException {
		checkMockServer();
		
		String data = "{\n"
				+ "\"id\":\"a8bfb961-c123-4aac-a686-48a33de8cb11\",\n"
				+ "\"resource\": \"Host\",\n" 
				+ "\"metric\": \"memUsage\",\n"
				+ "\"unit\": \"mb\",\n"
				+ "\"monitor\": \"http://localhost:8085\"\n" 
				+ "}";
		HttpEntity dataEntity = new StringEntity(data,ContentType.APPLICATION_JSON);
		Credentials userCredentials = new UsernamePasswordCredentials("monitor", "monitor");
		HttpResponseResult result = makePostRequest("http://localhost:8080/measurements",
				dataEntity, userCredentials);
		Assert.assertEquals(HttpStatus.SC_CREATED, result.statusLine.getStatusCode());
		
		result = makeGetRequest("http://localhost:8080/measurements");
		Assume.assumeTrue(result.statusLine.getStatusCode() == HttpStatus.SC_OK);
		Assert.assertTrue(result.resultContent.contains("{\"resource\":\"Host\",\"metric\":\"memUsage\",\"unit\":\"mb\",\"_links\":{\"details\":{\"href\":\"http://localhost:8085/measurements/a8bfb961-c123-4aac-a686-48a33de8cb11\"}}}"));
	}
	
	@Test
	public void testDelete() throws IOException {
		checkMockServer();
		
		HttpResponseResult result = makeGetRequest("http://localhost:8080/measurements");
		Assume.assumeTrue(result.statusLine.getStatusCode() == HttpStatus.SC_OK);
		Assume.assumeTrue(result.resultContent.contains("a8bfb961-c123-4aac-a686-48a33de8cb11"));
		
		Credentials userCredentials = new UsernamePasswordCredentials("monitor", "monitor");
		result = makeDeleteRequest("http://localhost:8080/measurements/a8bfb961-c123-4aac-a686-48a33de8cb11",
				userCredentials);
		Assert.assertEquals(HttpStatus.SC_OK, result.statusLine.getStatusCode());
		
		result = makeGetRequest("http://localhost:8080/measurements");
		Assume.assumeTrue(result.statusLine.getStatusCode() == HttpStatus.SC_OK);
		Assert.assertFalse(result.resultContent.contains("a8bfb961-c123-4aac-a686-48a33de8cb11"));
	}
	
	@Test
	public void testDeleteNotAuthenticated() throws IOException {
		checkMockServer();

		String data = "{}";
		HttpEntity dataEntity = new StringEntity(data,ContentType.APPLICATION_JSON);
		Credentials userCredentials = new UsernamePasswordCredentials("badUser", "badPassword");
		HttpResponseResult result = makePostRequest("http://localhost:8080/measurements",
				dataEntity, userCredentials);
		Assert.assertEquals(HttpStatus.SC_UNAUTHORIZED, result.statusLine.getStatusCode());
	}
	
	@Test
	public void testDeleteNotAuthenticated2() throws IOException {
		checkMockServer();

		HttpResponseResult result = makeDeleteRequest("http://localhost:8080/measurements/a8bfb961-c123-4aac-a686-48a33de8cb11",
				null);
		Assert.assertEquals(HttpStatus.SC_UNAUTHORIZED, result.statusLine.getStatusCode());
	}
	
	@Test
	public void testDeleteNotAuthorized() throws IOException {
		checkMockServer();

		Credentials userCredentials = new UsernamePasswordCredentials("user", "user");
		HttpResponseResult result = makeDeleteRequest("http://localhost:8080/measurements/a8bfb961-c123-4aac-a686-48a33de8cb11",
				userCredentials);
		Assert.assertEquals(HttpStatus.SC_FORBIDDEN, result.statusLine.getStatusCode());
	}
	
	@Test
	public void testAddNotAuthenticated() throws IOException {
		checkMockServer();

		Credentials userCredentials = new UsernamePasswordCredentials("badUser", "badPassword");
		HttpResponseResult result = makeDeleteRequest("http://localhost:8080/measurements/a8bfb961-c123-4aac-a686-48a33de8cb11",
				userCredentials);
		Assert.assertEquals(HttpStatus.SC_UNAUTHORIZED, result.statusLine.getStatusCode());
	}
	
	@Test
	public void testAddNotAuthenticated2() throws IOException {
		checkMockServer();

		String data = "{}";
		HttpEntity dataEntity = new StringEntity(data,ContentType.APPLICATION_JSON);
		HttpResponseResult result = makePostRequest("http://localhost:8080/measurements",
				dataEntity, null);
		Assert.assertEquals(HttpStatus.SC_UNAUTHORIZED, result.statusLine.getStatusCode());
	}
	
	@Test
	public void testAddNotAuthorized() throws IOException {
		checkMockServer();

		String data = "{}";
		HttpEntity dataEntity = new StringEntity(data,ContentType.APPLICATION_JSON);
		Credentials userCredentials = new UsernamePasswordCredentials("user", "user");
		HttpResponseResult result = makePostRequest("http://localhost:8080/measurements",
				dataEntity, userCredentials);
		Assert.assertEquals(HttpStatus.SC_FORBIDDEN, result.statusLine.getStatusCode());
	}

	public void checkMockServer() throws IOException {
		try {
			new URL("http://localhost:8080/measurements").openConnection().connect();
		} catch (IOException e) {
			Assume.assumeNoException("Server is not up", e);
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	//TODO should be moved to some helper class
	
	public static class HttpResponseResult {
		public String resultContent;
		public StatusLine statusLine;
		
		public HttpResponseResult() {}
		public HttpResponseResult(StatusLine statusLine) {
			super();
			this.statusLine = statusLine;
		}
	}
	
	public HttpResponseResult makePostRequest(String uri, HttpEntity data, Credentials userCredential) throws IOException {
		CredentialsProvider credsProvider = prepareCredenitalsProvider(uri, userCredential);
		HttpPost httppost = new HttpPost(uri);
		httppost.setEntity(data);
		return makeRequest(httppost, credsProvider);
	}
	
	public HttpResponseResult makeGetRequest(String uri) throws IOException {
		HttpGet httpget = new HttpGet(uri);
		return makeRequest(httpget, null);
	}
	
	public HttpResponseResult makeDeleteRequest(String uri, Credentials userCredential) throws IOException {
		CredentialsProvider credsProvider = prepareCredenitalsProvider(uri, userCredential);
		HttpDelete httpdelete = new HttpDelete(uri);
		return makeRequest(httpdelete, credsProvider);
	}
	
	private CredentialsProvider prepareCredenitalsProvider(String uri, Credentials credentials) throws IOException {
		if(credentials == null) {
			return null;
		}
		URI requestUri = null;
		try {
			requestUri = new URI(uri);
		} catch (URISyntaxException e) {
			throw new IOException(e);
		}
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(new AuthScope(requestUri.getHost(), requestUri.getPort()),
				credentials);
		return credsProvider;
	}
	
	private HttpResponseResult makeRequest(HttpUriRequest request, CredentialsProvider credsProvider) throws IOException {
		HttpClientBuilder clientBuilder = HttpClients.custom();
		if(credsProvider != null) {
			clientBuilder.setDefaultCredentialsProvider(credsProvider);
		}	
		CloseableHttpClient httpclient = clientBuilder.build();
		HttpResponseResult result = null;
		try {
			System.out.println("Executing request " + request.getRequestLine());
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
