package it.db.miu;

/**
 * 
 * @author D.Bertini
 *
 */
public enum HostIp {
    WHATISMYIPADDRESS("http://ipv4bot.whatismyipaddress.com/"), IPIFY("https://api.ipify.org"), ICANHAZIP(
            "http://icanhazip.com"), WTFISMYIP("http://wtfismyip.com/text");

    private String host;

    HostIp(String host) {
        this.host = host;
    }

    public String getHost() {
        return this.host;
    }
}
