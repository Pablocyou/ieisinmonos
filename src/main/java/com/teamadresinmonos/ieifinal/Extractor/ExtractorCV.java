package com.teamadresinmonos.ieifinal.Extractor;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.teamadresinmonos.ieifinal.Util.mariadbConnect;
import com.teamadresinmonos.ieifinal.Wrapper.WrapperCV;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.Duration;
import java.util.List;

public class ExtractorCV {
    static Double longitud=0.0;
    static Double latitud=0.0;
    static WebDriver driver;
    public static int dunk(String filename) throws InterruptedException {
        System.out.println("Empezando dunk de CV");
        Connection connection = mariadbConnect.mdbconn();
        List<String> lista = WrapperCV.kebab(filename);
        int insertados = 0;

        boolean online = false;
        try{
        if(isSiteUp(new URL("https://www.coordenadas-gps.com/"))) online = true;}catch(Exception ignored){}

        if(online) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
            driver.get("https://www.coordenadas-gps.com/");
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
            Thread.sleep(2000);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("javascript:window.scrollBy(750,950)");
        }

        lista.remove(0);
        while(lista.size()>0) {
            try {
                String current = lista.remove(0);
                JsonObject jsonObject = JsonParser.parseString(current).getAsJsonObject();

                String nombre = jsonObject.get("denominacionEspecifica").getAsString();
                String naturalesa = jsonObject.get("regimen").getAsString();
                    if(naturalesa.contains("PÚB.")) naturalesa = "PUBLICO";
                    else
                    if(naturalesa.contains("PRIV.")) naturalesa = "PRIVADO";
                    else
                    if(naturalesa.contains("CONC")) naturalesa = "CONCERTADO";
                    else{naturalesa = "OTROS";}


                String codiPostal = jsonObject.get("codigoPostal").getAsString();
                //region selenium
                String direccion = jsonObject.get("tipoVia").getAsString() + " " +
                        jsonObject.get("direccion").getAsString() + " " +
                        jsonObject.get("numero").getAsString();

                latitud=39.46975;
                longitud=-0.37739;

                if(online) {
                    try {
                        seleniumCore(driver, direccion);
                    } catch (Exception e) {
                        driver = resetBrowser();
                        seleniumCore(driver, direccion);
                    }
                }

                //endregion
                String telefono = jsonObject.get("telefono").getAsString();
                String descripcion = "Fax y cif: " + jsonObject.get("fax").getAsString() + ", " + jsonObject.get("cif").getAsString();
                String nombrelocalidad = jsonObject.get("localidad").getAsString();
                String codigolocalidad;
                if(jsonObject.get("codigoPostal").getAsString().length()==5)
                    codigolocalidad = jsonObject.get("codigoPostal").getAsString().substring(2,5);
                else
                    codigolocalidad = ("0" + jsonObject.get("codigoPostal").getAsString()).substring(2,5);

                String nombreprovincia = jsonObject.get("provincia").getAsString();

                String codigoprovincia;

                if(jsonObject.get("codigoPostal").getAsString().length()==5)
                    codigoprovincia = jsonObject.get("codigoPostal").getAsString().substring(0,2);
                else
                    codigoprovincia = ("0" + jsonObject.get("codigoPostal").getAsString()).substring(0,2);


                //Insertamos la provincia solo si no esta, si ya esta pasamos
                PreparedStatement statement = connection.prepareStatement("""
                    INSERT IGNORE INTO provincia SET codigo = ?, nombre = ?
""");
                statement.setString(1,codigoprovincia);
                statement.setString(2,nombreprovincia);
                statement.executeUpdate();

                //Insertamos la localidad solo si no esta, si ya esta pasamos
                PreparedStatement statement2 = connection.prepareStatement("""
                    INSERT IGNORE INTO localidad SET codigo = ?, nombre = ?, provincia = ? 
""");
                statement2.setString(1,codigolocalidad);
                statement2.setString(2,nombrelocalidad);
                statement2.setString(3,codigoprovincia);
                statement2.executeUpdate();

                //Insertamos el centro
                PreparedStatement statement3 = connection.prepareStatement("""
                    INSERT IGNORE INTO centro SET nombre = ?,
                     tipo = ?, direccion = ?, codigo_postal = ?, longitud = ?, latitud = ?,
                     telefono = ?, descripcion = ?, localidad = ?, comunidad = ? 
""");
                statement3.setString(1,nombre);
                statement3.setString(2,naturalesa);
                statement3.setString(3,direccion);
                statement3.setString(4,codiPostal);
                statement3.setDouble(5,longitud);
                statement3.setDouble(6,latitud);
                statement3.setString(7,telefono);
                statement3.setString(8,descripcion);
                statement3.setString(9,codigolocalidad);
                statement3.setString(10, "CV");
                int res = statement3.executeUpdate();
                if(res == 1)
                    insertados++;
            }
            catch(Exception e){System.out.println("EXCEPTION EXTRACTOR CV: " + e);}
        }
        if(!online){return -1*insertados;}
        driver.quit();
        return insertados;
    }

    private static WebDriver resetBrowser() throws InterruptedException {
        System.out.println("El navegador se ha cerrado asi que lo reiniciamos!");
        WebDriver driver;
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.get("https://www.coordenadas-gps.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(750,950)");
        return driver;
    }

    private static void seleniumCore(WebDriver driver, String direccion) throws InterruptedException {
        WebElement textBox = driver.findElement(By.xpath("//*[@id=\"address\"]"));
        WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"wrap\"]/div[2]/div[3]/div[1]/form[1]/div[2]/div/button"));
        WebElement textBoxlat = driver.findElement(By.xpath("//*[@id=\"latitude\"]"));
        WebElement textBoxlong = driver.findElement(By.xpath("//*[@id=\"longitude\"]"));
        Thread.sleep(100);
        Thread.sleep(100);
        textBox.clear();
        textBox.sendKeys(direccion);
        Thread.sleep(100);
        submitButton.click();
        Thread.sleep(100);
        latitud = Double.valueOf(textBoxlat.getAttribute("value"));
        longitud = Double.valueOf(textBoxlong.getAttribute("value"));
    }

    public static boolean isSiteUp(URL site) {
        try {
            HttpURLConnection conn = (HttpURLConnection) site.openConnection();
            conn.getContent();
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return true;
            }
            return false;
        } catch (SocketTimeoutException tout) {
            return false;
        } catch (IOException ioex) {
            // You may decide on more specific behaviour...
            return false;
        }
    }

}
