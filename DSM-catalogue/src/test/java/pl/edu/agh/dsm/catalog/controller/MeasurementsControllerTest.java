package pl.edu.agh.dsm.catalog.controller;

import static pl.edu.agh.dsm.common.utils.HttpClientUtil.makeDeleteRequest;
import static pl.edu.agh.dsm.common.utils.HttpClientUtil.makeGetRequest;
import static pl.edu.agh.dsm.common.utils.HttpClientUtil.makePostRequest;

import java.io.IOException;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import pl.edu.agh.dsm.common.utils.HttpClientUtil.HttpResponseResult;

// TODO use spring mockmvc 

//so that testDelete is after testAdd
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MeasurementsControllerTest {

	@Test
	public void testGetList() throws IOException {
		checkMockServer();
		HttpResponseResult result = makeGetRequest(
				"http://localhost:8080/measurements", null);
		Assert.assertEquals(HttpStatus.SC_OK, result.statusLine.getStatusCode());
		Assert.assertFalse(result.resultContent.isEmpty());
	}

	@Test
	public void testAdd() throws IOException {
		checkMockServer();

		String data = "{\n"
				+ "\"id\":\"a8bfb961-c123-4aac-a686-48a33de8cb11\",\n"
				+ "\"resource\": \"Host\",\n" + "\"metric\": \"memUsage\",\n"
				+ "\"unit\": \"mb\",\n"
				+ "\"monitor\": \"http://localhost:8085\"\n" + "}";
		HttpEntity dataEntity = new StringEntity(data,
				ContentType.APPLICATION_JSON);
		Credentials userCredentials = new UsernamePasswordCredentials(
				"monitor", "monitor");
		HttpResponseResult result = makePostRequest(
				"http://localhost:8080/measurements", dataEntity,
				userCredentials);
		Assert.assertEquals(HttpStatus.SC_CREATED,
				result.statusLine.getStatusCode());

		result = makeGetRequest("http://localhost:8080/measurements", null);
		Assume.assumeTrue(result.statusLine.getStatusCode() == HttpStatus.SC_OK);
		Assert.assertTrue(result.resultContent
				.contains("{\"resource\":\"Host\",\"metric\":\"memUsage\",\"unit\":\"mb\",\"_links\":{\"details\":{\"href\":\"http://localhost:8085/measurements/a8bfb961-c123-4aac-a686-48a33de8cb11\"}}}"));
	}

	@Test
	public void testDelete() throws IOException {
		checkMockServer();

		HttpResponseResult result = makeGetRequest(
				"http://localhost:8080/measurements", null);
		Assume.assumeTrue(result.statusLine.getStatusCode() == HttpStatus.SC_OK);
		Assume.assumeTrue(result.resultContent
				.contains("a8bfb961-c123-4aac-a686-48a33de8cb11"));

		Credentials userCredentials = new UsernamePasswordCredentials(
				"monitor", "monitor");
		result = makeDeleteRequest(
				"http://localhost:8080/measurements/a8bfb961-c123-4aac-a686-48a33de8cb11",
				userCredentials);
		Assert.assertEquals(HttpStatus.SC_OK, result.statusLine.getStatusCode());

		result = makeGetRequest("http://localhost:8080/measurements", null);
		Assume.assumeTrue(result.statusLine.getStatusCode() == HttpStatus.SC_OK);
		Assert.assertFalse(result.resultContent
				.contains("a8bfb961-c123-4aac-a686-48a33de8cb11"));
	}

	@Test
	public void testDeleteNotAuthenticated() throws IOException {
		checkMockServer();

		String data = "{}";
		HttpEntity dataEntity = new StringEntity(data,
				ContentType.APPLICATION_JSON);
		Credentials userCredentials = new UsernamePasswordCredentials(
				"badUser", "badPassword");
		HttpResponseResult result = makePostRequest(
				"http://localhost:8080/measurements", dataEntity,
				userCredentials);
		Assert.assertEquals(HttpStatus.SC_UNAUTHORIZED,
				result.statusLine.getStatusCode());
	}

	@Test
	public void testDeleteNotAuthenticated2() throws IOException {
		checkMockServer();

		HttpResponseResult result = makeDeleteRequest(
				"http://localhost:8080/measurements/a8bfb961-c123-4aac-a686-48a33de8cb11",
				null);
		Assert.assertEquals(HttpStatus.SC_UNAUTHORIZED,
				result.statusLine.getStatusCode());
	}

	@Test
	public void testDeleteNotAuthorized() throws IOException {
		checkMockServer();

		Credentials userCredentials = new UsernamePasswordCredentials("user",
				"user");
		HttpResponseResult result = makeDeleteRequest(
				"http://localhost:8080/measurements/a8bfb961-c123-4aac-a686-48a33de8cb11",
				userCredentials);
		Assert.assertEquals(HttpStatus.SC_FORBIDDEN,
				result.statusLine.getStatusCode());
	}

	@Test
	public void testAddNotAuthenticated() throws IOException {
		checkMockServer();

		Credentials userCredentials = new UsernamePasswordCredentials(
				"badUser", "badPassword");
		HttpResponseResult result = makeDeleteRequest(
				"http://localhost:8080/measurements/a8bfb961-c123-4aac-a686-48a33de8cb11",
				userCredentials);
		Assert.assertEquals(HttpStatus.SC_UNAUTHORIZED,
				result.statusLine.getStatusCode());
	}

	@Test
	public void testAddNotAuthenticated2() throws IOException {
		checkMockServer();

		String data = "{}";
		HttpEntity dataEntity = new StringEntity(data,
				ContentType.APPLICATION_JSON);
		HttpResponseResult result = makePostRequest(
				"http://localhost:8080/measurements", dataEntity, null);
		Assert.assertEquals(HttpStatus.SC_UNAUTHORIZED,
				result.statusLine.getStatusCode());
	}

	@Test
	public void testAddNotAuthorized() throws IOException {
		checkMockServer();

		String data = "{}";
		HttpEntity dataEntity = new StringEntity(data,
				ContentType.APPLICATION_JSON);
		Credentials userCredentials = new UsernamePasswordCredentials("user",
				"user");
		HttpResponseResult result = makePostRequest(
				"http://localhost:8080/measurements", dataEntity,
				userCredentials);
		Assert.assertEquals(HttpStatus.SC_FORBIDDEN,
				result.statusLine.getStatusCode());
	}

	public void checkMockServer() throws IOException {
		try {
			new URL("http://localhost:8080/measurements").openConnection()
					.connect();
		} catch (IOException e) {
			Assume.assumeNoException("Server is not up", e);
		}
	}
}
