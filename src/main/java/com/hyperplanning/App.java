package com.hyperplanning;

import com.hyperplanning.daos.UtilisateurDao;
import com.hyperplanning.exceptions.DataAccessException;
import com.hyperplanning.exceptions.NotFoundException;
import lombok.extern.java.Log;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Log
public class App {
    protected static final Properties properties = new Properties();

    static void loadProperties(String propFileName) throws IOException {
        InputStream inputstream = App.class.getClassLoader().getResourceAsStream(propFileName);
        if (inputstream == null) throw new FileNotFoundException();
        properties.load(inputstream);
    }


    public static void main(String[] args) throws IOException, NotFoundException, DataAccessException {
        loadProperties("app.properties");
        UtilisateurDao utilisateurDao = new UtilisateurDao();
        utilisateurDao.persist("DJESSOU","MAHUGNON","regisdjessou2@gmail.com","abcd");
        log.info(" utilisateurDao persisted " + utilisateurDao);

    }

    public static String getProperty(String s) {
        return properties.getProperty(s);
    }
}