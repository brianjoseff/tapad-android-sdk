package com.tapad.tracking;

import com.tapad.util.HttpClientUtil;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the resource interface to the event registration service.
 * Does the actual registration work using a HTTP Client.
 * <p/>
 * Single-threaded access only.
 */
class EventResource {
    private static final String RESOURCE_URL = "https://analytics.tapad.com/app/event";
    private static final String PARAM_APP_ID = "app_id";
    private static final String PARAM_DEVICE_ID = "device_id";
    private static final String PARAM_EVENT_ID = "action_id";

    private DefaultHttpClient client;

    private String appId;
    private DeviceIdentifier deviceId;

    /**
     * Constructs a new EventResource instance.
     *
     * @param appId    the application id to use
     * @param deviceId the device id to use
     */
    EventResource(String appId, DeviceIdentifier deviceId) {
        this.appId = appId;
        this.deviceId = deviceId;
        this.client = HttpClientUtil.createClient("Android/TapadEventAPI/1.0");
    }

    protected void post(Event e) throws IOException {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(PARAM_APP_ID, appId));
        params.add(new BasicNameValuePair(PARAM_DEVICE_ID, deviceId.get()));
        params.add(new BasicNameValuePair(PARAM_EVENT_ID, e.getId()));
        String query = URLEncodedUtils.format(params, "UTF-8");

        String uri = RESOURCE_URL + "?" + query;
        HttpResponse response = client.execute(new HttpPost(uri));
        response.getEntity().consumeContent();
    }
}
