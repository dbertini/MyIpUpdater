package it.db.miu;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 
 * @author D.Bertini
 *
 */
public class MyIpUpdater {

    public static void main(String[] args) {
        
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");

        String lsMyNewIp = "";
        boolean found = false;

        try {
            URL url = new URL(HostIp.WHATISMYIPADDRESS.getHost());
            lsMyNewIp = IOUtils.toString(new InputStreamReader(url.openStream()));
            found = true;
        } catch (Exception e) {
            e.printStackTrace();
            found = false;
        }

        if (!found) {
            try {
                URL url = new URL(HostIp.IPIFY.getHost());
                lsMyNewIp = IOUtils.toString(new InputStreamReader(url.openStream()));
                found = true;
            } catch (Exception e) {
                e.printStackTrace();
                found = false;
            }
        }

        if (!found) {
            try {
                URL url = new URL(HostIp.ICANHAZIP.getHost());
                lsMyNewIp = IOUtils.toString(new InputStreamReader(url.openStream()));
                found = true;
            } catch (Exception e) {
                e.printStackTrace();
                found = false;
            }
        }

        if (!found) {
            try {
                URL url = new URL(HostIp.WTFISMYIP.getHost());
                lsMyNewIp = IOUtils.toString(new InputStreamReader(url.openStream()));
                found = true;
            } catch (Exception e) {
                e.printStackTrace();
                found = false;
            }
        }

        if (found) {
            System.out.println(fmt.format(new Date()) + " - IP trovato: " + lsMyNewIp);

            CloseableHttpClient httpclient = HttpClients.createDefault();
            try {

                AccountCredential cred = new AccountCredential();
                URI uri = new URIBuilder().setScheme("https")
                        .setHost(cred.getUserName() + ":" + cred.getPassword() + "@dynupdate.no-ip.com")
                        .setPath("/nic/update")
                        .setParameter("hostname", cred.getHost())
                        .setParameter("myip", lsMyNewIp)
                        .build();
                HttpGet httpget = new HttpGet(uri);
                System.out.println(fmt.format(new Date()) + " - Executing request " + httpget.getRequestLine());
                // Create a custom response handler
                ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                    public String handleResponse(final HttpResponse response)
                            throws ClientProtocolException, IOException {
                        int status = response.getStatusLine().getStatusCode();
                        if (status >= 200 && status < 300) {
                            HttpEntity entity = response.getEntity();
                            return entity != null ? EntityUtils.toString(entity) : null;
                        } else {
                            throw new ClientProtocolException("Unexpected response status: " + status);
                        }
                    }

                };
                String responseBody = httpclient.execute(httpget, responseHandler);
                System.out.println(fmt.format(new Date()) + " - ----------------------------------------");
                System.out.println(fmt.format(new Date()) + " - Response from service: [" + responseBody + "]");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    httpclient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } else {
            System.err.println("Attenzione nessun sito ha risposto con il corretto IP!");
        }

    }

}
