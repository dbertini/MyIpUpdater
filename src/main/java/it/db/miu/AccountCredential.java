package it.db.miu;

import java.io.Serializable;

/**
 * Variabili di accesso al servizio no-ip
 * @author D.Bertini
 *
 */
public class AccountCredential implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -3427900463956452752L;
    
    private String username;
    private String password;
    private String host;
    
    public AccountCredential() {
        //Aggiungere le corrette credenziali di accesso!
        this.username = "";
        this.password = "";
        this.host = "";
    }
    
    public String getUserName() {
        return this.username;
    }
    public String getPassword() {
        return this.password;
    }
    public String getHost() {
        return this.host;
    }

}
