package nz.denys.reactjs.spring;

import com.vaadin.spring.annotation.EnableVaadin;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.ex.ConfigurationRuntimeException;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.AsyncHttpClientConfig;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.context.annotation.Bean;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManagerFactory;
import java.io.*;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

@EnableVaadin
@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public AsyncHttpClient client() {
        Security.addProvider(new BouncyCastleProvider());

        // get cert
        X509Certificate rootCert =
                null;
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File certFile = new File(classLoader.getResource("vpc-07248a63airnzconz.crt").getFile());
            InputStream stream = new FileInputStream(certFile);
            rootCert = (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(
                    new BufferedInputStream(stream)
            );
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // add cert to keystore
        KeyStore keystore = null;
        try {
            keystore = KeyStore.getInstance("PKCS12", BouncyCastleProvider.PROVIDER_NAME);
            keystore.load(null, "".toCharArray());
            keystore.setCertificateEntry("vpc-ca", rootCert);
            KeyManagerFactory keyManagerFactory =
                    KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(keystore, "".toCharArray());

            TrustManagerFactory trustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keystore);
            SslContext sslContext = SslContextBuilder.forClient()
                    .keyManager(keyManagerFactory)
                    .trustManager(trustManagerFactory)
                    .build();
            AsyncHttpClientConfig config = new DefaultAsyncHttpClientConfig.Builder().setSslContext(sslContext).build();
            return new DefaultAsyncHttpClient(config);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Bean
    public org.apache.commons.configuration2.Configuration propertiesConfig() {
        FileBasedConfigurationBuilder<PropertiesConfiguration> builder =
                new FileBasedConfigurationBuilder<>(PropertiesConfiguration.class)
                        .configure(new Parameters().properties()
                                .setFileName("svof.properties")
                                .setThrowExceptionOnMissing(true)
                                .setListDelimiterHandler(new DefaultListDelimiterHandler(';'))
                                .setIncludesAllowed(false));
        PropertiesConfiguration config;
        try {
            config = builder.getConfiguration();
        } catch (ConfigurationException e) {
            throw new ConfigurationRuntimeException("Unable to find properties file");
        }
        return config;
    }
}
