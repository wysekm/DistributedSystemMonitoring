package pl.edu.agh.dsm.front.web.Infrastructure;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Tom on 2014-05-31.
 */
public class UriHelper {

	public static String extractHostAddress(String uri) throws MalformedURLException {
		URL url = new URL(uri);
		return url.getProtocol() + "://" + url.getHost() + ":" + url.getPort();
	}
}
