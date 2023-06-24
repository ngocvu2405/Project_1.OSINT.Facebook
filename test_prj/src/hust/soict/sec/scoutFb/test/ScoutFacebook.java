package hust.soict.sec.scoutFb.test;
import com.restfb.*;
import com.restfb.types.User;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ScoutFacebook {
	ObjectMapper mapper = new ObjectMapper();
	String accessToken = "EAAKOcuxF84gBAEi6oTThSLFZAn1JTljOZBSCvnRoBHvMMCxv82ZBPvUnvfZBNYpRxljQ4Pq2MBvc2jFwYLzKOsXJi55BoshVw22xdneeSyCuEbixJGLEiL9Tc98ZA3UiZBe5c5pvMiUQ3QaAaZCyd53yGfUdaZArdFGuqW7uSBya6GZCTmgsSOEmHc4kmDoTDLWboXojD6tle5ZCHBVBrQNY1yR59qKJ6mR1IZCiJjL0T5KZBFx1ZApXYAsjOhl4ckNDNqnwZD";
	FacebookClient fbClient = new DefaultFacebookClient(accessToken, Version.VERSION_12_0);
	User me = fbClient.fetchObject("me", User.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ObjectMapper mapper = new ObjectMapper();
		
	}

}
