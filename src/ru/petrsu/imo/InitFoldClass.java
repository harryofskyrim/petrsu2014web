package ru.petrsu.imo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class InitFoldClass {
    private String year = "";
    private String group = "";
    private String course = "";
    private List<File> files = new ArrayList<File>();

    public List<File> getFiles() {
	// List with paths.
	List<String> paths = new ArrayList<String>();
        
	// Group and year are not defined.
        if (year.equals(Config.ALL_YEARS) && group.equals(Config.ALL_YEARS)) {
	    for (String g : Utils.getGroups(Config.ALL_YEARS, "")) {
		paths.add(Config.PATH_TO_PROJECTS + "/" + g);
	    }
	
	// Year is defined but group are not defined.
	} else if (!year.equals(Config.ALL_YEARS) && group.equals(Config.ALL_YEARS)) {
	    for (String g : Utils.getGroups(year, "")) {
		String c = g.substring(2, 3);
		paths.add(Config.PATH_TO_PROJECTS + "/" + year + "/" + c + "/" + g);
	    }
	// Group is defined, but yaer is not.
	} else if (year.equals(Config.ALL_YEARS) && !group.equals(Config.ALL_YEARS)) {
	    if (group.endsWith("*")) {
		course = group.substring(9, 10);
		String y = group.substring(0, 4);
		for (String g : Utils.getGroups(y, course)) {
		    paths.add(Config.PATH_TO_PROJECTS + "/" + y + "/" + course + "/" + g);
		}
	    } else {
		paths.add(Config.PATH_TO_PROJECTS + "/" + group);
	    }
        
	// Year and group are defined.
	} else {
            course = group.substring(2, 3);
	    if (group.endsWith("*")) {
		for (String g : Utils.getGroups(year, course)) {
		    paths.add(Config.PATH_TO_PROJECTS + "/" + year + "/" + course + "/" + g);
		}
	    } else {
		paths.add(Config.PATH_TO_PROJECTS + "/" + year + "/" + course + "/" + group);
	    }
        }
        
	// Gets files (file is student's directory) from the list with paths.
	if (!paths.isEmpty()) {
	    for (String path : paths) {
		File folder = new File(path);
		if (folder.exists()) {
                
		    File[] listOfFiles = folder.listFiles();

		    for (int i = 0; i < listOfFiles.length; i++) {
			files.add(listOfFiles[i]);
		    }
		}
	    }
	}

        return files;
    }    
    

    // Getters and setters.
    public void setFiles(List<File> files) {
        this.files = files;
    }
    
    public String getYear() {
        return this.year;
    }
    public void setYear(String year) {
        this.year = year;
    }
 
    public String getGroup() {
        return this.group;
    }
    public void setGroup(String group) {
        this.group = group;
    }
 
    public String getCourse() {
        return this.course;
    }
    public void setCourse(String course) {
        this.course = course;
    }
}
