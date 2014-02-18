/**
 * Copyright (C) 2013 Petrozavodsk State University.
 *
 * Thil file is part of WebD.
 */  

package ru.petrsu.imo;

/**
 * Contains common data.
 */ 
public class Config {
    /**
     * Directory on server which contains projects of students.
     */ 
    public static String PATH_TO_PROJECTS = "/srv/www/projects";

    
    /**
     * Directory which contains final report.
     */ 
    public static String DIRECTORY_FINAL_REPORT = "final/report";

    
    /**
     * File with final report.
     */ 
    public static String FILE_FINAL_REPORT = "report.pdf";

    
    /**
     * Directory which contains interim report.
     */ 
    public static String DIRECTORY_INTERIM_REPORT = "interim/report";

    /**
     * File with interim report.
     */ 
    public static String FILE_INTERIM_REPORT = "report.pdf";

    /**
     * Directory which contains final presentation.
     */ 
    public static String DIRECTORY_FINAL_PRES = "final/presentation";


    /**
     * File with final presentation.
     */ 
    public static String FILE_FINAL_PRES = "slides.pdf";

    
    /**
     * Directory which contains interim presentation.
     */ 
    public static String DIRECTORY_INTERIM_PRES = "interim/presentation";

    
    /**
     * File with interim presentation.
     */ 
    public static String FILE_INTERIM_PRES = "slides.pdf";

    
    /**
     * Defines first year for which directory contains index.xml.
     */ 
    public static int FIRST_YEAR_OF_NEW_STRUCTURE = 2012;
    
    
    /**
     * Modificator which defines all years.
     */ 
    public static String ALL_YEARS = "all";

    
    /**
     * List of courses for which directories are created.
     */
   public static String[] COURSES = {"2", "3", "4", "5", "6"};
}
