/**
 * Created on 24.09.13.
 * Copyright (C) 2013 Petrozavodsk State University.
 *
 * Thil file is part of WebD.
 */  

package ru.petrsu.imo;

import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.io.File;

/**
 * Contains common code.
 */ 
public class Utils {
    /**
     * Returns list with years which may contains index.xml.
     */ 
    public static List<String> getYears() {
	List<String> years = new ArrayList<String>();

	Calendar now = Calendar.getInstance();
	int currentYear = now.get(Calendar.YEAR);

	for (int year = Config.FIRST_YEAR_OF_NEW_STRUCTURE; year <= currentYear; year++) {
	    String y = (new Integer(year)).toString();
	    // Check whether directory with this year is exists.
	    File file = new File(Config.PATH_TO_PROJECTS + "/" + y);
	    if (file.exists()) {
		years.add(y);
	    }
	}

	return years;
    }


    /**
     * Returns current year.
     */ 
    public static String getCurrentYear() {
	Calendar now = Calendar.getInstance();
	int currentYear = now.get(Calendar.YEAR);

	return (new Integer(currentYear)).toString();
    }
    
    
    /**
     * Returns groups due to the specified year.
     *
     * @param year year for which groups are searched. 
     * If year is "all" then all groups is returned.
     * @param isCourse defines whether groups like 223* are
     * included to list.
     * @return list of groups. In case year is all,
     * then group is "year/course/group", otherwise
     * group is "group".
     */ 
    public static List<String> getGroups(String year, String course) {
	List<String> groups = new ArrayList<String>();

	// Defines list of years.
	if (year == null) {
	    return groups;
	}
	List<String> years = null;
	if (year.equals(Config.ALL_YEARS)) {
	    years = Utils.getYears();
	} else {
	    years = new ArrayList<String>();
	    years.add(year);
	}

	// Defines list of groups.
	for (String y : years) {
	    String [] courses = null;
	    if (!course.isEmpty() && !course.equals("*")) {
		courses = new String[]{course};
	    } else {
		courses = Config.COURSES;
	    }
	    for (String c : courses) {
		if (course.equals("*")) {
		    if (year.equals(Config.ALL_YEARS)) {
			groups.add(y + "/" + c + "/" + "22" + c + "*");
		    } else {
			groups.add("22" + c + "*");
		    }

		}
		File folder = new File(Config.PATH_TO_PROJECTS + "/" + y + "/" + c);
		if (folder.exists()) {
                
		    File[] listOfFiles = folder.listFiles();

		    for (int i = 0; i < listOfFiles.length; i++) {
			if (year.equals(Config.ALL_YEARS)) {
			    groups.add(listOfFiles[i].getPath().replace(Config.PATH_TO_PROJECTS + "/", ""));
			} else {
			    groups.add(listOfFiles[i].getName());
			}
		    }
		}
	    }
	}

	Collections.sort(groups);
	return groups;
    }


    /**
     * Returns true in case student is satisfied parameters of search.
     *
     * @param searchSN student name.
     * @param searchKP key prase.
     * @param searchT teacher name.
     */ 
    public static boolean isStudentForSearch(Student student, String searchSN, String searchKP, String searchT) {
	return (student.getStudentName().toLowerCase().contains(searchSN.toLowerCase())
		&& student.getWorkTitle().toLowerCase().contains(searchKP.toLowerCase())
		&& student.getAdviserName().toLowerCase().contains(searchT.toLowerCase()));
    }
}

