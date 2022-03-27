package com.zoomtest.zoom.initsdk;

public interface AuthConstants {

	// TODO Change it to your web domain
	String WEB_DOMAIN = "zoom.us";

	/**
	 * We recommend that, you can generate jwttoken on your own server instead of hardcore in the code.
	 * We hardcore it here, just to run the demo.
	 *
	 * You can generate a jwttoken on the https://jwt.io/
	 * with this payload:
	 * {
	 *
	 * "appKey": "string", // app key
	 * "iat": long, // access token issue timestamp
	 * "exp": long, // access token expire time
	 * "tokenExp": long // token expire time
	 * }
	 */
	String SDK_JWTTOKEN =
			"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhcHBLZXkiOiJGOG9uYnloNnlBU1BYNGRUdGhZVG5UT3hzWUJORkRXSXBORkEiLCJpYXQiOjE2MjkwMzA5MzksImV4cCI6MTYyOTExNzMzOSwidG9rZW5FeHAiOjE2MjkxMTczMzl9.a8lb4G8UjsHB5VoG3TY8Fc9rKjycaqi0QXWsH5RMYgQ";
}
